package mc.carlton.freerpg.combatEvents;

import mc.carlton.freerpg.FreeRPG;
import mc.carlton.freerpg.gameTools.FireworkShotByPlayerTracker;
import mc.carlton.freerpg.globalVariables.ItemGroups;
import mc.carlton.freerpg.perksAndAbilities.*;
import mc.carlton.freerpg.playerAndServerInfo.AbilityTracker;
import mc.carlton.freerpg.playerAndServerInfo.ChangeStats;
import mc.carlton.freerpg.playerAndServerInfo.ConfigLoad;
import mc.carlton.freerpg.playerAndServerInfo.PlayerStats;
import org.bukkit.Material;
import org.bukkit.attribute.Attributable;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class EntityHitEntity implements Listener {
    @EventHandler(priority = EventPriority.HIGH)

    void onEntityHit(EntityDamageByEntityEvent e) {
        Random rand = new Random();
        Plugin plugin = FreeRPG.getPlugin(FreeRPG.class);

        if (e.isCancelled()) {
            return;
        }

        //removes PvP effects if PvP is disabled
        if (e.getEntity() instanceof Player) {
            ConfigLoad loadConfig = new ConfigLoad();
            if (e.getDamager() instanceof Player) {
                if (!loadConfig.isAllowPvP()) {
                    return;
                }
            }
            else if (e.getDamager() instanceof Projectile) {
                if (((Projectile) e.getDamager()).getShooter() instanceof Player) {
                    if (!loadConfig.isAllowPvP()) {
                        return;
                    }
                }
            }
        }
        //removes hurting animals effects if hurting animals is disabled
        else if (e.getEntity() instanceof Animals) {
            ConfigLoad loadConfig = new ConfigLoad();
            if (e.getDamager() instanceof Player) {
                if (!loadConfig.isAllowHurtAnimals()) {
                    return;
                }
            }
            else if (e.getDamager() instanceof Projectile) {
                if (((Projectile) e.getDamager()).getShooter() instanceof Player) {
                    if (!loadConfig.isAllowHurtAnimals()) {
                        return;
                    }
                }
            }
        }

        if (e.getDamager() instanceof Player) {
            ItemGroups itemGroups = new ItemGroups();
            List<Material> shovels = itemGroups.getShovels();
            List<Material> swords = itemGroups.getSwords();
            List<Material> axes = itemGroups.getAxes();


            Player p = (Player) e.getDamager();
            PlayerStats pStatClass = new PlayerStats(p);
            Map<String, ArrayList<Number>> pStat = pStatClass.getPlayerData();
            AbilityTracker abilities = new AbilityTracker(p);
            Integer[] pAbilities = abilities.getPlayerAbilities();



            //Digging
            if (shovels.contains(p.getInventory().getItemInMainHand().getType())) {
                ConfigLoad configLoad = new ConfigLoad();
                if (!configLoad.getAllowedSkillsMap().get("digging")) {
                    return;
                }
                int shovelKnightLevel = (int) pStat.get("digging").get(12);
                double multiplier = Math.min(2.0,1.0+shovelKnightLevel);
                e.setDamage(e.getDamage() * multiplier);
            }

            //swordsmanship
            else if (swords.contains(p.getInventory().getItemInMainHand().getType())) {
                ConfigLoad configLoad = new ConfigLoad();
                if (!configLoad.getAllowedSkillsMap().get("swordsmanship")) {
                    return;
                }
                Swordsmanship swordsmanshipClass = new Swordsmanship(p);
                if (pAbilities[7] > -1) {
                    swordsmanshipClass.enableAbility();
                }
                double damage = e.getDamage();
                if ((int)pStat.get("swordsmanship").get(13) > 0) {
                    damage += 2;
                    e.setDamage(damage);
                }
                Entity damagedEntity = e.getEntity();
                swordsmanshipClass.doubleHit(damagedEntity,damage,p);
                swordsmanshipClass.giveHitEXP(e.getFinalDamage());
            }

            //Defense
            else if (p.getInventory().getItemInMainHand().getType() == Material.AIR) {
                Defense defenseClass = new Defense(p);
                if (pAbilities[8] > -1) {
                    defenseClass.enableAbility();
                }
            }

            else if (axes.contains(p.getInventory().getItemInMainHand().getType())) {
                ConfigLoad configLoad = new ConfigLoad();
                if (!configLoad.getAllowedSkillsMap().get("axeMastery")) {
                    return;
                }
                AxeMastery axeMasteryClass = new AxeMastery(p);
                double damage = e.getDamage();
                if ((int)pStat.get("axeMastery").get(13) > 0) {
                    damage += 2;
                    e.setDamage(damage);
                }
                if (pAbilities[9] > -1) {
                    axeMasteryClass.enableAbility();
                    axeMasteryClass.greaterAxe(e.getEntity(),p.getWorld(),e.getFinalDamage());
                }
                else if (pAbilities[9] == -2) {
                    axeMasteryClass.greaterAxe(e.getEntity(),p.getWorld(),e.getFinalDamage());
                }
                double multiplier = axeMasteryClass.divineCritical();
                e.setDamage(e.getDamage()*multiplier);
                axeMasteryClass.holyAxe(e.getEntity(),p.getWorld(),e.getFinalDamage());
                axeMasteryClass.giveHitEXP(e.getFinalDamage());

            }

        }
        //Arrow of Light
        else if (e.getDamager() instanceof Arrow) {
            if (((Arrow) e.getDamager()).getShooter() instanceof Player) {
                ConfigLoad configLoad = new ConfigLoad();
                if (!configLoad.getAllowedSkillsMap().get("archery")) {
                    return;
                }
                Player p = (Player) ((Arrow) e.getDamager()).getShooter();
                PlayerStats pStatClass = new PlayerStats(p);
                Map<String, ArrayList<Number>> pStat = pStatClass.getPlayerData();
                Archery archeryClass = new Archery(p);
                archeryClass.explosiveArrows(e.getDamager(),e.getEntity().getLocation());
                Material arrowType = archeryClass.getArrowType();
                if (arrowType == Material.SPECTRAL_ARROW) {
                    int arrowOfLightLevel = (int) pStat.get("archery").get(10);
                    double multiplier = Math.min(arrowOfLightLevel * 0.05 + 1.0, 2.0);
                    e.setDamage(Math.min(e.getDamage() * multiplier, 32));
                }
                archeryClass.giveHitEXP(e.getFinalDamage());
                if (e.getEntity() instanceof LivingEntity) {
                    if (e.getFinalDamage() > ((LivingEntity) e.getEntity()).getHealth()) {
                        archeryClass.giveKillEXP(e.getEntity());
                    }
                }
            }
        }
        //Crossbow Strike
        else if (e.getDamager() instanceof Firework) {
            ConfigLoad configLoad = new ConfigLoad();
            if (!configLoad.getAllowedSkillsMap().get("archery")) {
                return;
            }
            Entity firework = e.getDamager();
            FireworkShotByPlayerTracker fireworkTracker = new FireworkShotByPlayerTracker();
            Player p = fireworkTracker.getPlayer(firework);
            fireworkTracker.removeFireWork(firework);
            if (p != null) {
                Archery archeryClass = new Archery(p);
                PlayerStats pStatClass = new PlayerStats(p);
                Map<String, ArrayList<Number>> pStat = pStatClass.getPlayerData();
                if ((int) pStat.get("archery").get(13) > 0) {
                    e.setDamage(Math.min(e.getDamage() * 2, 32));
                    archeryClass.giveHitEXP(e.getFinalDamage());
                }
                if (e.getEntity() instanceof LivingEntity) {
                    if (e.getFinalDamage() > ((LivingEntity) e.getEntity()).getHealth()) {
                        archeryClass.giveKillEXP(e.getEntity());
                    }
                }
            }
        }
        //Sharp Teeth and Keep Away
        else if (e.getDamager() instanceof Entity) {
            ConfigLoad configLoad = new ConfigLoad();
            if (!configLoad.getAllowedSkillsMap().get("beastMastery")) {
                return;
            }
            Entity wolf = e.getDamager();
            if (wolf.getType() == EntityType.WOLF) {
                Tameable dog = (Tameable) wolf;
                if (dog.isTamed()) {
                    Player p = (Player) dog.getOwner();
                    ChangeStats increaseStats = new ChangeStats(p);
                    PlayerStats pStatClass = new PlayerStats(p);
                    Map<String, ArrayList<Number>> pStat = pStatClass.getPlayerData();

                    double crit = 1.0;
                    int critLevel = (int) pStat.get("beastMastery").get(5);
                    double critChance = critLevel * 0.00025;
                    if (critChance > rand.nextDouble()) {
                        crit = 1.5;
                    }

                    int sharpTeethLevel = (int) pStat.get("beastMastery").get(8);
                    double damageMultiplier = sharpTeethLevel * 0.1 + 1;
                    e.setDamage(e.getDamage() * crit * damageMultiplier);

                    Entity enemy = e.getEntity();
                    int keepAwayLevel = (int) pStat.get("beastMastery").get(10);
                    double knockBackChance = keepAwayLevel * 0.05;
                    if (knockBackChance > rand.nextDouble()) {
                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                enemy.setVelocity(enemy.getVelocity().multiply(5).setY(0.4));
                            }
                        }.runTaskLater(plugin, 1);
                    }
                    Map<String, Integer> expMap = configLoad.getExpMapForSkill("beastMastery");
                    LivingEntity livingEnemy = (LivingEntity) enemy;
                    if (e.getFinalDamage() > livingEnemy.getHealth()) {
                        double heartsHealed = (int) pStat.get("beastMastery").get(9);
                        LivingEntity livingDog = (LivingEntity) dog;
                        double maxHealth = ((Attributable) dog).getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue();
                        livingDog.setHealth(Math.min(livingDog.getHealth() + heartsHealed, maxHealth));
                        BeastMastery beastMastery = new BeastMastery(p);
                        beastMastery.dogKillEntity(enemy);
                    }
                    increaseStats.changeEXP("beastMastery", (int) Math.round(e.getFinalDamage() * expMap.get("dogDamage_EXPperDamagePointDone")));

                }
            }
        }
        //Wolf take damage EXP
        else if (e.getEntity() instanceof Entity) {
            ConfigLoad configLoad = new ConfigLoad();
            if (!configLoad.getAllowedSkillsMap().get("beastMastery")) {
                return;
            }
            if (!(e.getDamager() instanceof Player)) {
                Entity wolf = e.getEntity();
                if (wolf.getType() == EntityType.WOLF) {
                    Tameable dog = (Tameable) wolf;
                    if (dog.isTamed()) {
                        Player p = (Player) dog.getOwner();
                        ChangeStats increaseStats = new ChangeStats(p);
                        increaseStats.changeEXP("beastMastery", (int) Math.round(e.getFinalDamage() * 3) * 10);
                    }
                }
            }
        }

        //Getting hit (defense)
        if (e.getEntity() instanceof Player) {
            Player p = (Player) e.getEntity();
            Agility agilityClass = new Agility(p);
            boolean dodge = agilityClass.dodge(e.getFinalDamage());
            if (dodge) {
                e.setCancelled(true);
            }
            Defense defenseClass = new Defense(p);
            double multiplier = defenseClass.hardBody();
            e.setDamage(e.getDamage()*multiplier);
            defenseClass.reactions(e.getFinalDamage());
            defenseClass.giveHitEXP(e.getDamage());

        }

    }
}

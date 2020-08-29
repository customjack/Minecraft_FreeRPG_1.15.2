package mc.carlton.freerpg.perksAndAbilities;

import mc.carlton.freerpg.FreeRPG;
import mc.carlton.freerpg.globalVariables.ItemGroups;
import mc.carlton.freerpg.playerAndServerInfo.ChangeStats;
import mc.carlton.freerpg.playerAndServerInfo.ConfigLoad;
import mc.carlton.freerpg.playerAndServerInfo.PlayerStats;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.*;

public class Global {
    Plugin plugin = FreeRPG.getPlugin(FreeRPG.class);
    private Player p;
    private String pName;
    private ItemStack itemInHand;
    private static Map<Player, ArrayList<ItemStack>> playerSavedDrops = new HashMap<>();

    ChangeStats increaseStats; //Changing Stats

    PlayerStats pStatClass;
    //GET PLAYER STATS LIKE THIS:        Map<String, ArrayList<Number>> pStat = pStatClass.getPlayerData(p);

    Random rand = new Random(); //Random class Import

    private boolean runMethods;



    public Global(Player p) {
        this.p = p;
        this.pName = p.getDisplayName();
        this.itemInHand = p.getInventory().getItemInMainHand();
        this.increaseStats = new ChangeStats(p);
        this.pStatClass = new PlayerStats(p);
        ConfigLoad configLoad = new ConfigLoad();
        this.runMethods = configLoad.getAllowedSkillsMap().get("global");
    }

    public double expBoost(String skillName) {
        if (!runMethods) {
            return 1.0;
        }
        double boost = 1.0;
        Map<String, ArrayList<Number>> pStat = pStatClass.getPlayerData();
        String[] gatherer0 = {"digging","woodcutting","mining","farming","fishing"};
        String[] scholar0 =  {"repair","agility","alchemy","smelting","enchanting"};
        String[] fighter0 = {"archery","beastMastery","swordsmanship","defense","axeMastery"};
        List<String> gatherer = Arrays.asList(gatherer0);
        List<String> scholar = Arrays.asList(scholar0);
        List<String> fighter = Arrays.asList(fighter0);
        if (gatherer.contains(skillName) && (int) pStat.get("global").get(2) > 0 ) {
            boost = 1.2;
        }
        else if (scholar.contains(skillName) && (int) pStat.get("global").get(3) > 0 ) {
            boost = 1.2;
        }
        else if (fighter.contains(skillName) && (int) pStat.get("global").get(4) > 0 ) {
            boost = 1.2;
        }

        return boost;
    }

    public void skillTokenBoost(int type) {
        if (!runMethods) {
            return;
        }
        String[] gatherer0 = {"digging","woodcutting","mining","farming","fishing"};
        String[] scholar0 =  {"repair","agility","alchemy","smelting","enchanting"};
        String[] fighter0 = {"archery","beastMastery","swordsmanship","defense","axeMastery"};
        List<String> gatherer = Arrays.asList(gatherer0);
        List<String> scholar = Arrays.asList(scholar0);
        List<String> fighter = Arrays.asList(fighter0);
        switch (type) {
            case 5:
                for (String skillName : gatherer) {
                    increaseStats.increaseTokens(skillName,"skill",1);
                }
                break;
            case 6:
                for (String skillName : scholar) {
                    increaseStats.increaseTokens(skillName,"skill",1);
                }
                break;
            case 7:
                for (String skillName : fighter) {
                    increaseStats.increaseTokens(skillName,"skill",1);
                }
                break;
            default:
                break;
        }
    }

    public void passiveTokenBoost() { //Old skill_3b perk
        String[] abilities0 = {"digging", "woodcutting", "mining", "farming", "fishing", "archery", "beastMastery", "swordsmanship", "defense", "axeMastery"};
        String[] nonAbilities0 = {"repair", "agility", "alchemy", "smelting", "enchanting"};
        List<String> abilities = Arrays.asList(abilities0);
        List<String> nonAbilities = Arrays.asList(nonAbilities0);
        for (String skillName : abilities) {
            increaseStats.increaseTokens(skillName, "passive", 50);
        }
        for (String skillName : nonAbilities) {
            increaseStats.changeStat(skillName, 4, 50);
        }
    }

    public void gainSoul(Entity entity) {
        if (!runMethods) {
            return;
        }
        if (!p.hasPermission("freeRPG.getSouls")) {
            return;
        }
        EntityType[] hostileMobs0 = {EntityType.SPIDER, EntityType.CAVE_SPIDER, EntityType.ENDERMAN, EntityType.PIG_ZOMBIE,
                EntityType.BLAZE, EntityType.CREEPER, EntityType.DROWNED, EntityType.ELDER_GUARDIAN,
                EntityType.ENDERMITE, EntityType.EVOKER, EntityType.GHAST, EntityType.GUARDIAN,
                EntityType.HUSK, EntityType.MAGMA_CUBE, EntityType.PHANTOM, EntityType.PILLAGER,
                EntityType.RAVAGER, EntityType.SHULKER, EntityType.SKELETON, EntityType.SLIME,
                EntityType.STRAY, EntityType.VEX, EntityType.VINDICATOR, EntityType.WITCH,
                EntityType.WITHER_SKELETON, EntityType.ZOMBIE, EntityType.ZOMBIE_VILLAGER, EntityType.WITHER, EntityType.ENDER_DRAGON};
        List<EntityType> hostileMobs = Arrays.asList(hostileMobs0);
        EntityType entityType = entity.getType();
        if (hostileMobs.contains(entityType)) {
            int amountGained = 1;
            switch (entityType) {
                case WITHER:
                    amountGained = 100;
                    break;
                case ENDER_DRAGON:
                    amountGained = 200;
                    break;
                default:
                    break;
            }
            UUID uuid = p.getUniqueId();
            PlayerStats pStatClass = new PlayerStats(p);
            Map<UUID, Map<String, ArrayList<Number>>> statAll = pStatClass.getData();
            Map<String, ArrayList<Number>> pStatAll = statAll.get(uuid);
            int soulHarvestLevel = (int) pStatAll.get("global").get(9);
            if (soulHarvestLevel > 0) {
                int souls = (int) pStatAll.get("global").get(20);
                pStatAll.get("global").set(20, souls + amountGained);
                statAll.put(uuid, pStatAll);
                pStatClass.setData(statAll);
            }
        }
    }

    public void loseSouls(int amountLost) {
        if (!runMethods) {
            return;
        }
        UUID uuid = p.getUniqueId();
        PlayerStats pStatClass = new PlayerStats(p);
        Map<UUID, Map<String, ArrayList<Number>>> statAll = pStatClass.getData();
        Map<String, ArrayList<Number>> pStatAll = statAll.get(uuid);
        int souls =  (int) pStatAll.get("global").get(20);
        pStatAll.get("global").set(20,souls-amountLost);
        statAll.put(uuid, pStatAll);
        pStatClass.setData(statAll);
    }

    public void betterResurrectionDeath(List<ItemStack> drops) {
        if (!runMethods) {
            return;
        }
        Map<String, ArrayList<Number>> pStat = pStatClass.getPlayerData();
        if ((int) pStat.get("global").get(8) > 0) {
            ArrayList<ItemStack> savedDrops = new ArrayList<>();
            ItemGroups itemGroups = new ItemGroups();
            List<Material> valuableItems =  itemGroups.getValuableItems();
            for (ItemStack drop : drops) {
                if (drop.getEnchantments().size() != 0 || valuableItems.contains(drop.getType())) {
                    double randomNum = rand.nextDouble();
                    int initialAmount = drop.getAmount();
                    if (0.5 < randomNum) {
                        if (initialAmount == 1) {
                            ItemStack dropCopy = drop.clone();
                            savedDrops.add(dropCopy);
                            drop.setAmount(0);
                        }
                        else if (initialAmount > 1) {
                            int savedAmount = (int) Math.round(initialAmount*randomNum);
                            ItemStack dropCopy = drop.clone();
                            drop.setAmount(initialAmount-savedAmount);
                            dropCopy.setAmount(savedAmount);
                            savedDrops.add(dropCopy);
                        }
                    }
                }
            }
            playerSavedDrops.put(p,savedDrops);
        }
    }

    public void betterResurrectionRespawn() {
        if (!runMethods) {
            return;
        }
        Map<String, ArrayList<Number>> pStat = pStatClass.getPlayerData();
        if ((int) pStat.get("global").get(8) > 0 && playerSavedDrops.containsKey(p)) {
            ArrayList<ItemStack> savedDrops = playerSavedDrops.get(p);
            for (ItemStack savedDrop : savedDrops) {
                p.getInventory().addItem(savedDrop);
            }
            playerSavedDrops.remove(p);
        }
    }

    public void avatar(){
        if (!runMethods) {
            return;
        }
        PotionEffectType[] positiveEffects0  = {PotionEffectType.DOLPHINS_GRACE, PotionEffectType.LUCK, PotionEffectType.INVISIBILITY, PotionEffectType.NIGHT_VISION,
                                               PotionEffectType.FIRE_RESISTANCE, PotionEffectType.WATER_BREATHING, PotionEffectType.SPEED, PotionEffectType.JUMP,
                                               PotionEffectType.ABSORPTION, PotionEffectType.CONDUIT_POWER, PotionEffectType.DAMAGE_RESISTANCE, PotionEffectType.FAST_DIGGING,
                                               PotionEffectType.HEAL, PotionEffectType.HEALTH_BOOST, PotionEffectType.INCREASE_DAMAGE, PotionEffectType.REGENERATION,
                                               PotionEffectType.SATURATION};
        List<PotionEffectType> positiveEffects = Arrays.asList(positiveEffects0);
        for (PotionEffectType effect : positiveEffects) {
            p.addPotionEffect(new PotionEffect(effect,10*20,0));
        }
    }




}

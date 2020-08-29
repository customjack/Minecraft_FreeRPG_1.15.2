package mc.carlton.freerpg.combatEvents;

import mc.carlton.freerpg.perksAndAbilities.Digging;
import mc.carlton.freerpg.perksAndAbilities.Global;
import mc.carlton.freerpg.perksAndAbilities.Mining;
import mc.carlton.freerpg.perksAndAbilities.Swordsmanship;
import mc.carlton.freerpg.playerAndServerInfo.AbilityLogoutTracker;
import mc.carlton.freerpg.playerAndServerInfo.AbilityTracker;
import mc.carlton.freerpg.playerAndServerInfo.ConfigLoad;
import mc.carlton.freerpg.playerAndServerInfo.PlayerStats;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PlayerDeath implements Listener {

    @EventHandler
    void onPlayerDie(PlayerDeathEvent e){
        Player p = e.getEntity();
        PlayerStats pStatClass = new PlayerStats(p);
        Map<String, ArrayList<Number>> pStat = pStatClass.getPlayerData();
        ConfigLoad configLoad = new ConfigLoad();
        if (configLoad.getAllowedSkillsMap().get("alchemy")) {
            int immortalExperienceLevel = (int) pStat.get("enchanting").get(13);
            int expBuffLevel = (int) pStat.get("enchanting").get(4);
            double multiplier = 1 + expBuffLevel*0.002;
            e.setDroppedExp( (int) Math.round(e.getDroppedExp()/multiplier) );
            if (immortalExperienceLevel > 0) {
                e.setKeepLevel(true);
                e.setDroppedExp(0);
            }
        }
        List<ItemStack> drops = e.getDrops();
        AbilityTracker abilities = new AbilityTracker(p);
        Integer[] pAbilities = abilities.getPlayerAbilities();
        if (pAbilities[0] != -1) {
            AbilityLogoutTracker logoutTracker = new AbilityLogoutTracker(p);
            NamespacedKey key = logoutTracker.getPlayerItems(p)[0];
            int taskID_digging = logoutTracker.getPlayerTasks(p)[0];
            for (ItemStack drop : drops) {
                if (drop.getItemMeta().getPersistentDataContainer().has(key, PersistentDataType.STRING)) {
                    ItemStack abilityItem = drop;
                    Digging diggingClass = new Digging(p);
                    diggingClass.preventLogoutTheft(taskID_digging, abilityItem);
                    break;
                }
            }
        }
        if (pAbilities[2] != -1) {
            AbilityLogoutTracker logoutTracker = new AbilityLogoutTracker(p);
            NamespacedKey key = logoutTracker.getPlayerItems(p)[2];
            int taskID_mining = logoutTracker.getPlayerTasks(p)[2];
            for (ItemStack drop: drops) {
                if (drop.getItemMeta().getPersistentDataContainer().has(key, PersistentDataType.STRING)) {
                    ItemStack abilityItem = drop;
                    Mining miningClass = new Mining(p);
                    miningClass.preventLogoutTheft(taskID_mining, abilityItem);
                    break;
                }
            }
        }
        if (pAbilities[7] != -1) {
            AbilityLogoutTracker logoutTracker = new AbilityLogoutTracker(p);
            NamespacedKey key = logoutTracker.getPlayerItems(p)[7];
            int taskID_swordsmanship = logoutTracker.getPlayerTasks(p)[7];
            for (ItemStack drop: drops) {
                if (drop.getItemMeta().getPersistentDataContainer().has(key, PersistentDataType.STRING)) {
                    ItemStack abilityItem = drop;
                    Swordsmanship swordsmanshipClass = new Swordsmanship(p);
                    swordsmanshipClass.preventLogoutTheft(taskID_swordsmanship, abilityItem);
                    break;
                }
            }
        }


        Global globalClass = new Global(p);
        globalClass.betterResurrectionDeath(drops);

    }
}

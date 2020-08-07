package mc.carlton.freerpg.combatEvents;

import mc.carlton.freerpg.playerAndServerInfo.PlayerStats;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Tameable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import java.util.ArrayList;
import java.util.Map;

public class EntityGetDamaged implements Listener {
    @EventHandler(priority = EventPriority.HIGH)

    void onEntityDamaged(EntityDamageEvent e) {
        if (e.isCancelled()) {
            return;
        }
        if (e.getEntity() instanceof Entity) {
            Entity wolf = e.getEntity();
            if (wolf.getType() == EntityType.WOLF) {
                Tameable dog = (Tameable) wolf;
                if (dog.isTamed()){
                    Player p = (Player) dog.getOwner();
                    PlayerStats pStatClass = new PlayerStats(p);
                    Map<String, ArrayList<Number>> pStat = pStatClass.getPlayerData();
                    if (e.getCause() == EntityDamageEvent.DamageCause.FALL) {
                        if ((int)pStat.get("beastMastery").get(11) > 0) {
                            e.setDamage(0);
                        }
                    }
                    else {
                        int thickFurLevel = (int)pStat.get("beastMastery").get(7);
                        double thickFurMultiplier = 1 - thickFurLevel*0.1;
                        e.setDamage(e.getDamage()*thickFurMultiplier);
                    }
                }
            }
        }
    }
}

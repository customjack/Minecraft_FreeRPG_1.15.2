package mc.carlton.freerpg.miscEvents;

import mc.carlton.freerpg.FreeRPG;
import mc.carlton.freerpg.perksAndAbilities.Agility;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSprintEvent;
import org.bukkit.plugin.Plugin;

public class PlayerToggleSprint implements Listener {
    Plugin plugin = FreeRPG.getPlugin(FreeRPG.class);
    @EventHandler(priority = EventPriority.HIGH)
    void onSprintToggle(PlayerToggleSprintEvent e){
        if (e.isCancelled()) {
            return;
        }
        Player p = e.getPlayer();
        Agility agilityClass = new Agility(p);
        agilityClass.sprintingEXP(e.isSprinting());

    }
}

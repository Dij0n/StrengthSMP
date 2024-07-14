package dijon.strengthsmp.event.handlers;

import dijon.strengthsmp.StrengthSMP;
import dijon.strengthsmp.event.BatteryItem;
import dijon.strengthsmp.event.DEventManager;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DKillHandler implements Listener {

    StrengthSMP plugin;

    public DKillHandler(StrengthSMP plugin) {
        this.plugin = plugin;
        Bukkit.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onEventKill(PlayerDeathEvent e){
        if(!DEventManager.eventActive) return;
        //ADD WORLD CHECKER
        //ADD WORLD CHECKER
        //ADD WORLD CHECKER
        //ADD WORLD CHECKER

        if(e.getPlayer().getKiller() != null){

            if(!DEventManager.onSameTeam(e.getPlayer(), e.getPlayer().getKiller())){
                e.getPlayer().getWorld().dropItemNaturally(e.getPlayer().getLocation(), BatteryItem.battery);
            }

            int teamIndex = DEventManager.getTeamIndexByPlayer(e.getPlayer());
            StationHandler.chargeBatteryByIndex(teamIndex, -2);

        }
    }

}

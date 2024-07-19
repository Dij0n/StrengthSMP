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
        if(!e.getPlayer().getWorld().equals(StationHandler.strengthWorld)) return;

        if(e.getPlayer().getKiller() != null){

            if(!DEventManager.onSameTeam(e.getPlayer(), e.getPlayer().getKiller())){
                e.getPlayer().getWorld().dropItemNaturally(e.getPlayer().getLocation(), BatteryItem.battery);
            }
        }
        int teamIndex = DEventManager.getTeamIndexByPlayer(e.getPlayer());
        StationHandler.chargeBatteryByIndex(teamIndex, -2);
        DEventManager.teamMessage(DEventManager.teams.get(teamIndex),
                "§6Battery Discharged! "
                        + "§e☣ "
                        + "§c§o§l"
                        + (int) StationHandler.stations.get(teamIndex).getChargeValue()
                        + "%");
        DEventManager.bossBars.get(teamIndex).setProgress(StationHandler.stations.get(teamIndex).getChargeValue() / 100);
    }

}

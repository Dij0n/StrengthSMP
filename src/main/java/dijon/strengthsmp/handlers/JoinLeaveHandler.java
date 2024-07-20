package dijon.strengthsmp.handlers;

import dijon.strengthsmp.StrengthSMP;
import dijon.strengthsmp.data.PlayerData;
import dijon.strengthsmp.data.PlayerDataManager;
import dijon.strengthsmp.event.handlers.StationHandler;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class JoinLeaveHandler implements Listener {

    StrengthSMP plugin;

    public JoinLeaveHandler(StrengthSMP plugin) {
        this.plugin = plugin;
        Bukkit.getServer().getPluginManager().registerEvents(this, plugin);
    }


    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Bukkit.getLogger().info("Adding player");

        if(!PlayerDataManager.masterPlayerData.containsKey(e.getPlayer().getUniqueId())){
            Bukkit.getLogger().info("Adding new player " + e.getPlayer().getName());
            PlayerDataManager.addPlayer(e.getPlayer());
            PlayerDataManager.setPlayer(e.getPlayer());
            Bukkit.getLogger().info("With strength " + PlayerDataManager.getStrength(e.getPlayer()));
            e.getPlayer().teleport(new Location(StationHandler.strengthWorld, -157, 110, 123));
            e.getPlayer().setBedSpawnLocation(new Location(StationHandler.strengthWorld, -157, 110, 123), true);
        }else{
            PlayerDataManager.setPlayer(e.getPlayer());
        }

        PlayerDataManager.savePlayers();
        e.getPlayer().getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(1 + PlayerDataManager.getStrength(e.getPlayer()));
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent e){
        PlayerDataManager.savePlayers();
    }
}

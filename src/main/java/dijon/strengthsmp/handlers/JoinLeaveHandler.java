package dijon.strengthsmp.handlers;

import dijon.strengthsmp.StrengthSMP;
import dijon.strengthsmp.data.PlayerData;
import dijon.strengthsmp.data.PlayerDataManager;
import org.bukkit.Bukkit;
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

    public static HashMap<UUID, PlayerData> toAdd = new HashMap<>();

    public JoinLeaveHandler(StrengthSMP plugin) {
        this.plugin = plugin;
        Bukkit.getServer().getPluginManager().registerEvents(this, plugin);
    }


    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        PlayerDataManager.addPlayer(e.getPlayer());

        UUID uuid = e.getPlayer().getUniqueId();

        if(toAdd.containsKey(uuid)){
            toAdd.get(uuid).player = e.getPlayer();
            PlayerDataManager.addPlayer(toAdd.get(uuid));
            toAdd.remove(uuid);
        }

        PlayerDataManager.savePlayers();
        e.getPlayer().getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(1 + PlayerDataManager.getStrength(e.getPlayer()));
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent e){
        PlayerDataManager.savePlayers();
    }
}

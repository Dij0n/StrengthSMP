package dijon.strengthsmp.event.handlers;


import dijon.strengthsmp.StrengthSMP;
import dijon.strengthsmp.event.DEventManager;
import dijon.strengthsmp.event.Station;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.ArrayList;

public class StationHandler implements Listener {

    StrengthSMP plugin;

    public static ArrayList<Station> stations = new ArrayList<>();

    public StationHandler(StrengthSMP plugin) {
        this.plugin = plugin;
        Bukkit.getServer().getPluginManager().registerEvents(this, plugin);
        //STATION INITIALIZER HERE
        //STATION INITIALIZER HERE
        //STATION INITIALIZER HERE
        //STATION INITIALIZER HERE
        //STATION INITIALIZER HERE
        //STATION INITIALIZER HERE
    }

    @EventHandler
    public void onStationClick(PlayerInteractEvent e){
        //ADD WORLD CHECKER
        //ADD WORLD CHECKER
        //ADD WORLD CHECKER
        //ADD WORLD CHECKER


        if(!DEventManager.eventActive) return;
        if(!e.getAction().isRightClick()) return;
        if(e.getClickedBlock() == null) return;
        if(e.getItem() == null) return;
        if(!isBlockAStation(e.getClickedBlock())) return;
        if(!e.getItem().getType().equals(Material.BLACK_CANDLE)) return;
        if(e.getItem().getItemMeta().getCustomModelData() != 9) return;

        Station s = getStationByBlock(e.getClickedBlock()); //Redundant but good for safety
        int stationIndex = stations.indexOf(s);
        stations.get(stationIndex).charge(2);

    }

    public static void chargeBatteryByIndex(int index, double chargeVal){
        stations.get(index).charge(chargeVal);
    }

    private boolean isBlockAStation(Block b){
        for(Station s : stations){
            if(s.getBlock().equals(b)){
                return true;
            }
        }
        return false;
    }

    private Station getStationByBlock (Block b){
        for(Station s : stations){
            if(s.getBlock().equals(b)){
                return s;
            }
        }
        return null;
    }



}

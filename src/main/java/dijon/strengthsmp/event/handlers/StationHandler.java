package dijon.strengthsmp.event.handlers;


import dijon.strengthsmp.StrengthSMP;
import dijon.strengthsmp.event.DEventManager;
import dijon.strengthsmp.event.Station;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.ArrayList;

public class StationHandler implements Listener {

    StrengthSMP plugin;

    public static ArrayList<Station> stations = new ArrayList<>();

    public static World strengthWorld;

    public StationHandler(StrengthSMP plugin) {
        this.plugin = plugin;
        strengthWorld = Bukkit.getWorld("world");
        Bukkit.getServer().getPluginManager().registerEvents(this, plugin);
        initializeStations();
    }

    @EventHandler
    public void onStationClick(PlayerInteractEvent e){
        if(!e.getPlayer().getWorld().equals(strengthWorld)) return;

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

        e.getItem().setAmount(e.getItem().getAmount() - 1);
        e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.BLOCK_AMETHYST_BLOCK_RESONATE, 10.0F, 1.3F);



        DEventManager.teamMessage(DEventManager.teams.get(stationIndex),
                "§6Battery Charged! "
                        + "§e\uD83D\uDDF2 " //Lightning emojis
                        + "§c§o§l"
                        + (int) stations.get(stationIndex).getChargeValue()
                        + "%");
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

    private static void initializeStations(){
        Station station1 = new Station(strengthWorld.getBlockAt(802, 69, -2658), 0);
        Station station2 = new Station(strengthWorld.getBlockAt(800, 69, -2658), 0);
        Station station3 = new Station(strengthWorld.getBlockAt(798, 69, -2658), 0);
        Station station4 = new Station(strengthWorld.getBlockAt(0, 0, 0), 0);
        Station station5 = new Station(strengthWorld.getBlockAt(0, 0, 0), 0);
        Station station6 = new Station(strengthWorld.getBlockAt(0, 0, 0), 0);
        stations.add(station1);
        stations.add(station2);
        stations.add(station3);
        stations.add(station4);
        stations.add(station5);
        stations.add(station6);
    }



}

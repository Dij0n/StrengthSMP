package dijon.strengthsmp.event.handlers;


import dijon.strengthsmp.StrengthSMP;
import dijon.strengthsmp.event.DEventManager;
import dijon.strengthsmp.event.Station;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.ArrayList;

public class StationHandler implements Listener {

    StrengthSMP plugin;

    public static ArrayList<Station> stations = new ArrayList<>();

    public static World strengthWorld;

    public StationHandler(StrengthSMP plugin) {
        this.plugin = plugin;
        Bukkit.getServer().getPluginManager().registerEvents(this, plugin);
        strengthWorld = Bukkit.getWorld("world_strength_strength_dimension");
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
        e.getPlayer().getWorld().spawnParticle(Particle.SONIC_BOOM, stations.get(stationIndex).getBlock().getLocation(), 5);

        DEventManager.bossBars.get(stationIndex).setProgress(stations.get(stationIndex).getChargeValue() / 100);
        DEventManager.teamMessage(DEventManager.teams.get(stationIndex),
                "§6Battery Charged! "
                        + "§e\uD83D\uDDF2 " //Lightning emojis
                        + "§c§o§l"
                        + (int) stations.get(stationIndex).getChargeValue()
                        + "%");

        DEventManager.otherTeamMessage(DEventManager.teams.get(stationIndex),
                "§6"
                        + stations.get(stationIndex).getTeamname()
                        + "'s Battery has been Charged! "
                        + "§e\uD83D\uDDF2 " //Lightning emojis
                        + "§c§o§l"
                        + (int) stations.get(stationIndex).getChargeValue()
                        + "%");

        if(stations.get(stationIndex).getChargeValue() >= 100){
            DEventManager.batteryCharged(stationIndex);
        }
    }

    @EventHandler
    public void onJoinBossBar(PlayerJoinEvent e){
        if(DEventManager.eventActive) DEventManager.reloadBossBars();
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
        Station station1 = new Station(strengthWorld.getBlockAt(-273, 120, 294), 0, "The Tridents");
        Station station2 = new Station(strengthWorld.getBlockAt(-273, 120, 297), 0, "The Monarchs");
        Station station3 = new Station(strengthWorld.getBlockAt(-300, 120, 323), 0, "The Insurrection");
        Station station4 = new Station(strengthWorld.getBlockAt(-302, 120, 323), 0, "Super Duper Epic");
        Station station5 = new Station(strengthWorld.getBlockAt(-328, 120, 296), 0, "The Order");
        Station station6 = new Station(strengthWorld.getBlockAt(-328, 120, 294), 0, "B.O.T.S");
        stations.add(station1);
        stations.add(station2);
        stations.add(station3);
        stations.add(station4);
        stations.add(station5);
        stations.add(station6);
    }



}

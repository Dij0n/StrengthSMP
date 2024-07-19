package dijon.strengthsmp.event;

import dijon.strengthsmp.StrengthSMP;
import dijon.strengthsmp.event.handlers.StationHandler;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class BatteryFileManager {

    public static File batteries;

    public BatteryFileManager(StrengthSMP plugin){
        batteries = new File(plugin.getDataFolder(), "batteries.yml");

        if (!batteries.exists()) {
            try {
                batteries.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            saveBatteries();
        }
        loadBatteries();
    }

    public static void saveBatteries() {
        YamlConfiguration config = new YamlConfiguration();
        for(Station station : StationHandler.stations){
            Bukkit.getLogger().info("Saving battery " + station.getTeamname());
            config.set(station.getTeamname(), station.getChargeValue());
        }

        config.set("Enabled", DEventManager.eventActive);

        try {
            config.save(batteries);
            Bukkit.getLogger().info("Saved");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadBatteries(){
        YamlConfiguration config = new YamlConfiguration();

        try {
            config.load(batteries);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }

        for (Station station : StationHandler.stations) {
            Bukkit.getLogger().info("Loading battery " + station.getTeamname());

            double charge = (double) config.get(station.getTeamname());

            int index = StationHandler.stations.indexOf(station); //Redundant but safe
            StationHandler.stations.get(index).setChargeValue(charge);
        }

        DEventManager.eventActive = (boolean) config.get("Enabled");

    }

}

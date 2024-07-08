package dijon.strengthsmp.data;

import dijon.strengthsmp.StrengthSMP;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class PlayerDataManager {

    public static final String[] BASIC_ATTACKS = new String[] { "Swift Strikes", "Slashes", "Double Strikes", "Uppercuts" };

    public static final String[] ULTIMATE_ATTACKS = new String[] { "Dragon Fists", "Reaping Claw", "Multi Strike", "Whirlwinds" };

    public static HashMap<Player, PlayerData> masterPlayerData = new HashMap<>();

    public static File playerDataFile;

    public PlayerDataManager(StrengthSMP plugin){
        playerDataFile = new File(plugin.getDataFolder(), "playerData.yml");
        if (!playerDataFile.exists()){
            try {
                playerDataFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void setStrength(Player p, int strength){
        masterPlayerData.get(p).setStrength(strength);
    }

    public static void incStrength(Player p){
        masterPlayerData.get(p).incStrength();
    }

    public static int getStrength(Player p){
        return masterPlayerData.get(p).getStrength();
    }

    public static int getHits(Player p){
        return masterPlayerData.get(p).getPlayerHits();
    }

    public static void incHits(Player p){
        masterPlayerData.get(p).incPlayerHits();
    }

    public static void resetUltTime(Player p){
        masterPlayerData.get(p).lastUltActivation = System.currentTimeMillis();
    }




    public static PlayerData getPlayerData(Player p){
        return masterPlayerData.get(p);
    }

    public static void addPlayer(Player p){
        if(!masterPlayerData.containsKey(p)){
            PlayerData save = new PlayerData(p);
            masterPlayerData.put(p, save);
        }
    }

    public static void savePlayers(){
        YamlConfiguration config = YamlConfiguration.loadConfiguration(playerDataFile);

        for(PlayerData save : masterPlayerData.values()){
            config.set(save.player.getUniqueId() + ".strength", save.strength);
            config.set(save.player.getUniqueId() + ".basicAttack", save.basicAttack);
            config.set(save.player.getUniqueId() + ".ultimateAttack", save.ultimateAttack);
        }

        try {
            config.save(playerDataFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadPlayers(){
        YamlConfiguration config = YamlConfiguration.loadConfiguration(playerDataFile);

        try {
            config.load(playerDataFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }

        masterPlayerData = new HashMap<>();
        for(String uuid : config.getKeys(false)){
            PlayerData playerData = new PlayerData(Bukkit.getPlayer(uuid));
            playerData.strength = config.getInt(uuid + ".strength", 2);
            playerData.basicAttack = config.getString(uuid + ".basicAttack", playerData.getRandomBasicAttack());
            playerData.ultimateAttack = config.getString(uuid + ".ultimateAttack", null);
            masterPlayerData.put(playerData.player, playerData);
        }

    }

}

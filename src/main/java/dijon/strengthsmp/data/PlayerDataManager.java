package dijon.strengthsmp.data;

import dijon.strengthsmp.StrengthSMP;
import dijon.strengthsmp.handlers.JoinLeaveHandler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class PlayerDataManager {

    public static final String[] BASIC_ATTACKS = new String[] { "Swift_Strikes", "Slashes", "Double_Strike", "Uppercut", "Zen_Throw" };

    public static final String[] ULTIMATE_ATTACKS = new String[] { "Dragon_Fists", "Reaping_Claw", "Multi_Strike", "Whirlwind", "Deadly_Squash" };

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

    public static boolean getEgg(Player p){
        if(masterPlayerData.get(p) == null){
            return false;
        }
        return masterPlayerData.get(p).isDragonEgg();
    }

    public static void setEgg(Player p, boolean egg){
        masterPlayerData.get(p).setDragonEgg(egg);
    }

    public static void dragonActivation(Player p){
        masterPlayerData.get(p).setStrength(4);
        masterPlayerData.get(p).setUltimateAttack("Dragonborn");
        masterPlayerData.get(p).setDragonEgg(true);
        p.sendMessage(ChatColor.GREEN + "You found the Dragon Egg!");
        p.sendMessage(ChatColor.GREEN + "Your new strength is " + ChatColor.DARK_GREEN + "+4");
        p.sendMessage(ChatColor.GREEN + "Your new ultimate attack is " + ChatColor.DARK_GREEN + "Dragonborn");
    }

    public static void dragonLoss(Player p){
        masterPlayerData.get(p).setStrength(3);
        masterPlayerData.get(p).setUltimateAttack(masterPlayerData.get(p).getRandomUltimateAttack());
        masterPlayerData.get(p).setDragonEgg(false);
        p.sendMessage(ChatColor.RED + "You lost the Dragon Egg!");
        p.sendMessage(ChatColor.RED + "Your new strength is " + ChatColor.DARK_RED + "+3");
        p.sendMessage(ChatColor.RED + "Your new ultimate attack is " + ChatColor.DARK_GREEN + masterPlayerData.get(p).getUltimateAttack());
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

    public static void addPlayer(PlayerData save){
        if(!masterPlayerData.containsValue(save)){
            masterPlayerData.put(save.player, save);
        }
    }

    public static void savePlayers(){
        YamlConfiguration config = YamlConfiguration.loadConfiguration(playerDataFile);

        for(PlayerData save : masterPlayerData.values()){

            config.set(save.player.getUniqueId() + ".strength", save.strength);
            config.set(save.player.getUniqueId() + ".basicAttack", save.basicAttack);
            config.set(save.player.getUniqueId() + ".ultimateAttack", save.ultimateAttack);
            config.set(save.player.getUniqueId() + ".dragon", save.dragonEgg);
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
            PlayerData playerData = new PlayerData();

            playerData.uuid = UUID.fromString(uuid);
            playerData.strength = config.getInt(uuid + ".strength", 2);
            playerData.basicAttack = config.getString(uuid + ".basicAttack", playerData.getRandomBasicAttack());
            playerData.ultimateAttack = config.getString(uuid + ".ultimateAttack", null);
            playerData.dragonEgg = Boolean.parseBoolean(config.getString(uuid + ".dragon", null));
            JoinLeaveHandler.toAdd.put(UUID.fromString(uuid), playerData);
        }

    }

}

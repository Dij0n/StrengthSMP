package dijon.strengthsmp.commands;

import dijon.strengthsmp.data.PlayerData;
import dijon.strengthsmp.data.PlayerDataManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class strength implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            if (sender instanceof Player) {
                Player player = (Player)sender;
                PlayerData playerData = PlayerDataManager.getPlayerData(player);
                player.sendMessage("Your current strength is: " + playerData.getStrength());
                player.sendMessage("Your current basic attack is: " + playerData.getBasicAttack());
                player.sendMessage("Your current ultimate attack is: " + playerData.getUltimateAttack());
            } else {
                sender.sendMessage("Only players can use this command without arguments.");
            }
            return true;
        }

        return false;
    }

}

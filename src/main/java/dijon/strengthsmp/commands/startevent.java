package dijon.strengthsmp.commands;

import dijon.strengthsmp.data.PlayerDataManager;
import dijon.strengthsmp.event.DEventManager;
import dijon.strengthsmp.handlers.MythicHandler;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class startevent implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player)sender;
            if(!player.isOp()) return true;
            DEventManager.start();
        }

        return true;
    }

}

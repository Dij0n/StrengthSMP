package dijon.strengthsmp.commands;

import dijon.strengthsmp.event.handlers.StationHandler;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class tpstrength implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        Player player = (Player) commandSender;

        Location loc = new Location(StationHandler.strengthWorld, player.getX(), player.getY(), player.getZ());
        player.teleport(loc);
        return false;
    }
}

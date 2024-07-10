package dijon.strengthsmp;

import dijon.strengthsmp.commands.attack;
import dijon.strengthsmp.commands.strength;
import dijon.strengthsmp.crafting.RandomBook;
import dijon.strengthsmp.crafting.StrengthItem;
import dijon.strengthsmp.data.PlayerDataManager;
import dijon.strengthsmp.handlers.AttackHandler;
import dijon.strengthsmp.handlers.JoinLeaveHandler;
import dijon.strengthsmp.handlers.RandomHandler;
import dijon.strengthsmp.handlers.item.ItemHandler;
import org.bukkit.plugin.java.JavaPlugin;

public final class StrengthSMP extends JavaPlugin {

    @Override
    public void onEnable() {

        new PlayerDataManager(this);

        PlayerDataManager.loadPlayers();

        new RandomHandler(this);
        new JoinLeaveHandler(this);
        new AttackHandler(this);
        new ItemHandler(this);

        new TestRunnable().runTaskTimer(this, 1, 10);

        new RandomBook(this);
        new StrengthItem(this);

        this.getCommand("strength").setExecutor(new strength());
        this.getCommand("attack").setExecutor(new attack());

    }

    @Override
    public void onDisable() {
        PlayerDataManager.savePlayers();
        // Plugin shutdown logic
    }
}

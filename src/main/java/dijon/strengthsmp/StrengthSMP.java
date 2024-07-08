package dijon.strengthsmp;

import dijon.strengthsmp.commands.strength;
import dijon.strengthsmp.crafting.RandomBook;
import dijon.strengthsmp.crafting.StrengthItem;
import dijon.strengthsmp.data.PlayerDataManager;
import dijon.strengthsmp.handlers.AttackHandler;
import dijon.strengthsmp.handlers.JoinLeaveHandler;
import dijon.strengthsmp.handlers.RandomHandler;
import org.bukkit.plugin.java.JavaPlugin;

public final class StrengthSMP extends JavaPlugin {

    @Override
    public void onEnable() {
        new PlayerDataManager(this);
        new RandomHandler(this);
        new JoinLeaveHandler(this);
        new AttackHandler(this);

        new TestRunnable().runTaskTimer(this, 1, 10);

        new RandomBook(this);
        new StrengthItem(this);

        this.getCommand("strength").setExecutor(new strength());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}

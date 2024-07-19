package dijon.strengthsmp;

import dijon.strengthsmp.commands.*;
import dijon.strengthsmp.crafting.RandomBook;
import dijon.strengthsmp.crafting.StrengthItem;
import dijon.strengthsmp.data.PlayerDataManager;
import dijon.strengthsmp.event.BatteryFileManager;
import dijon.strengthsmp.event.BatteryItem;
import dijon.strengthsmp.event.DEventManager;
import dijon.strengthsmp.event.handlers.DKillHandler;
import dijon.strengthsmp.event.handlers.StationHandler;
import dijon.strengthsmp.handlers.AttackHandler;
import dijon.strengthsmp.handlers.DragonHandler;
import dijon.strengthsmp.handlers.JoinLeaveHandler;
import dijon.strengthsmp.handlers.item.CraftingHandler;
import dijon.strengthsmp.handlers.item.RandomHandler;
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
        new DragonHandler(this);
        new CraftingHandler(this);

        new TestRunnable().runTaskTimer(this, 1, 10);

        new RandomBook(this);
        new StrengthItem(this);
        new BatteryItem();

        //DIMENSION EVENT
        new DKillHandler(this);
        new BatteryItem();
        new StationHandler(this);
        new DEventManager();
        new BatteryFileManager(this);

        //COMMANDS
        this.getCommand("strength").setExecutor(new strength());
        this.getCommand("attack").setExecutor(new attack());
        this.getCommand("basic").setExecutor(new basic());
        this.getCommand("startevent").setExecutor(new startevent());
        this.getCommand("tpstrength").setExecutor(new tpstrength());

    }

    @Override
    public void onDisable() {
        PlayerDataManager.savePlayers();
        BatteryFileManager.saveBatteries();
    }
}

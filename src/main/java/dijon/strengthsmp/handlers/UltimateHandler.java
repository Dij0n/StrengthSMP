package dijon.strengthsmp.handlers;

import dijon.strengthsmp.data.PlayerData;
import dijon.strengthsmp.data.PlayerDataManager;
import org.bukkit.entity.Player;

public class UltimateHandler {

    public static void ultimateAttack(Player p){
        PlayerData save = PlayerDataManager.getPlayerData(p);
        if(System.currentTimeMillis() - save.lastUltActivation < 10000){
            p.sendMessage("On cooldown!!!");
            return;
        }

        PlayerDataManager.resetUltTime(p);
        p.sendMessage("Ult!");

    }


}

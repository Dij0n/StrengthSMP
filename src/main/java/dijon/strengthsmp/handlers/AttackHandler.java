package dijon.strengthsmp.handlers;

import dijon.strengthsmp.StrengthSMP;
import dijon.strengthsmp.data.PlayerData;
import dijon.strengthsmp.data.PlayerDataManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

public class AttackHandler implements Listener {

    StrengthSMP plugin;

    public AttackHandler(StrengthSMP plugin) {
        this.plugin = plugin;
        Bukkit.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onAttack(EntityDamageByEntityEvent e){
        if(e.getDamager() instanceof Player p){
            PlayerDataManager.incHits(p);
            p.sendMessage(String.valueOf(PlayerDataManager.getHits(p)));
        }
    }

    @EventHandler
    public void onKill(PlayerDeathEvent e) {

        Player victim = e.getEntity();
        PlayerData victimData = PlayerDataManager.getPlayerData(victim);

        if (e.getEntity().getKiller() != null) {
            Player killer = e.getEntity().getKiller();
            PlayerData killerData = PlayerDataManager.getPlayerData(killer);

            if(killerData.getStrength() <= victimData.getStrength() && victimData.getStrength() != 1){
                killerData.incStrength();
            }
        }

        victimData.decStrength();

    }

    private boolean isSword(Material material) {
        return (material == Material.WOODEN_SWORD || material == Material.STONE_SWORD || material == Material.IRON_SWORD || material == Material.GOLDEN_SWORD || material == Material.DIAMOND_SWORD || material == Material.NETHERITE_SWORD);
    }

}

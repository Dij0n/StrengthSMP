package dijon.strengthsmp.handlers;

import dijon.strengthsmp.StrengthSMP;
import dijon.strengthsmp.data.PlayerData;
import dijon.strengthsmp.data.PlayerDataManager;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class RandomHandler implements Listener {

    StrengthSMP plugin;

    public RandomHandler(StrengthSMP plugin) {
        this.plugin = plugin;
        Bukkit.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerUse(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();

        if (item != null && item.getType() == Material.BOOK && item.hasItemMeta()) {
            ItemMeta meta = item.getItemMeta();
            if(meta == null) return;
            if(meta.getCustomModelData() == 7) {
                PlayerData playerData = PlayerDataManager.getPlayerData(player);
                if (playerData.getBasicAttack() != null) {
                    String newBasicAttack = playerData.getDifferentBasicAttack();
                    playerData.setBasicAttack(newBasicAttack);
                    player.sendMessage("Your basic attack has been rerolled to: " + newBasicAttack);
                }
                if (playerData.getUltimateAttack() != null) {
                    String newUltimateAttack = playerData.getDifferentUltimateAttack();
                    playerData.setUltimateAttack(newUltimateAttack);
                    player.sendMessage("Your ultimate attack has been rerolled to: " + newUltimateAttack);
                }

                item.setAmount(item.getAmount() - 1);
                player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.0F);
                player.getWorld().spawnParticle(Particle.DUST, player.getLocation(), 100, 0.5D, 0.5D, 0.5D, new Particle.DustOptions(Color.RED, 1.0F));
            }
        }
    }

}

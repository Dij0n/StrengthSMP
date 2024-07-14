package dijon.strengthsmp.event;
import dijon.strengthsmp.StrengthSMP;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class BatteryItem implements Listener {

    public static ItemStack battery;

    public BatteryItem() {
        initailizeItem();
    }


    public static void initailizeItem(){
        battery = new ItemStack(Material.BLACK_CANDLE);
        ItemMeta meta = battery.getItemMeta();
        meta.setDisplayName(ChatColor.YELLOW + "Battery");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.BLUE + "Deposit this at spawn to charge your team's battery!");
        meta.setLore(lore);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.addEnchant(Enchantment.BINDING_CURSE,1 , true);
        meta.setCustomModelData(9);
        battery.setItemMeta(meta);
    }

}

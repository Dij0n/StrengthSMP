package dijon.strengthsmp.crafting;

import dijon.strengthsmp.StrengthSMP;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

import static org.bukkit.Bukkit.getServer;

public class StrengthItem {

    public static ShapedRecipe recipe;
    public static ItemStack strengthItem;

    public StrengthItem(StrengthSMP plugin){
        strengthItem = new ItemStack(Material.GHAST_TEAR);
        ItemMeta meta = strengthItem.getItemMeta();
        if (meta != null) {
            meta.setDisplayName("Strength");
            meta.setCustomModelData(5);
            strengthItem.setItemMeta(meta);
        }
        NamespacedKey key = new NamespacedKey(plugin, "strengthitem");
        recipe = new ShapedRecipe(key, strengthItem);

        recipe.shape("DDD", "DDD", "DDD");
        recipe.setIngredient('D', Material.DIAMOND_BLOCK);

        getServer().addRecipe(recipe);
    }

}

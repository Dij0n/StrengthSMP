package dijon.strengthsmp.crafting;

import dijon.strengthsmp.StrengthSMP;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

import static org.bukkit.Bukkit.getServer;

public class RandomBook {

    public RandomBook(StrengthSMP plugin){
        ItemStack randomizer = new ItemStack(Material.BOOK);
        ItemMeta meta = randomizer.getItemMeta();
        if (meta != null) {
            meta.setDisplayName("Randomizer");
            List<String> lore = new ArrayList<>();
            lore.add("this book to reroll your attacks!");
            meta.setLore(lore);
            meta.setCustomModelData(7);
            randomizer.setItemMeta(meta);
        }
        NamespacedKey key = new NamespacedKey(plugin, "randomizer");
        ShapedRecipe recipe = new ShapedRecipe(key, randomizer);
        recipe.shape(new String[] { "PEP", "EME", "PEP" });
        recipe.setIngredient('E', Material.ECHO_SHARD);
        recipe.setIngredient('P', Material.PHANTOM_MEMBRANE);
        recipe.setIngredient('M', Material.ENCHANTING_TABLE);

        getServer().addRecipe(recipe);
    }

}

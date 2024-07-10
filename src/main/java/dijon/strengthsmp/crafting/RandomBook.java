package dijon.strengthsmp.crafting;

import dijon.strengthsmp.StrengthSMP;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
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
            meta.setDisplayName(ChatColor.AQUA + "Randomizer");
            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.BLUE + "Use this book to reroll your attacks!");
            meta.setLore(lore);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            meta.addEnchant(Enchantment.BINDING_CURSE,1 , true);
            meta.setCustomModelData(7);
            randomizer.setItemMeta(meta);
        }
        NamespacedKey key = new NamespacedKey(plugin, "randomizer");
        ShapedRecipe recipe = new ShapedRecipe(key, randomizer);
        recipe.shape(new String[] { "PEP", "EME", "PEP" });
        recipe.setIngredient('E', Material.ECHO_SHARD);
        recipe.setIngredient('P', Material.PHANTOM_MEMBRANE);
        recipe.setIngredient('M', Material.MUSIC_DISC_5);

        getServer().addRecipe(recipe);
    }

}

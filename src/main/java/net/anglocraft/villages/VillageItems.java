package net.anglocraft.villages;

import net.anglocraft.Main;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class VillageItems {

    private final Main main = Main.getPlugin(Main.class);
    public ItemStack saxonHouse(int tier) {
        ItemStack item = new ItemStack(Material.valueOf(main.config().getString("Settings.Village.Item").toUpperCase()));
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&a&lSaxon House Tier " + tier));
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.YELLOW + "Right Click to place.");
        meta.setLore(lore);
        meta.hasEnchants();
        item.setItemMeta(meta);
        return item;
    }
}

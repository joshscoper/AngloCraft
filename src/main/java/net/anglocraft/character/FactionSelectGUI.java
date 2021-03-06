package net.anglocraft.character;

import net.anglocraft.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class FactionSelectGUI {
   private Main main = (Main)Main.getPlugin(Main.class);

   public FactionSelectGUI() {
   }

   Inventory factionSelect() {
      Inventory inv = Bukkit.createInventory((InventoryHolder)null, InventoryType.HOPPER, ChatColor.translateAlternateColorCodes('&', main.config().getString("Settings.GUI_Titles.Faction_Select")));
      inv.setItem(1, this.saxon());
      inv.setItem(3, this.dane());
      return inv;
   }

   ItemStack saxon() {
      String type = this.main.getConfig().getString("Settings.Character_Creation.Saxon_Item").toUpperCase();
      type = type.replaceAll(" ", "_");
      Material material = Material.getMaterial(type);
      ItemStack item = new ItemStack(material);
      ItemMeta meta = item.getItemMeta();
      String name = ChatColor.translateAlternateColorCodes('&', this.main.getConfig().getString("Settings.Character_Creation.Saxon_Item_Name"));
      meta.setDisplayName(name);
      meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
      item.setItemMeta(meta);
      return item;
   }

   ItemStack dane() {
      String type = this.main.getConfig().getString("Settings.Character_Creation.Dane_Item").toUpperCase();
      type = type.replaceAll(" ", "_");
      Material material = Material.getMaterial(type);
      ItemStack item = new ItemStack(material);
      ItemMeta meta = item.getItemMeta();
      String name = ChatColor.translateAlternateColorCodes('&', this.main.getConfig().getString("Settings.Character_Creation.Dane_Item_Name"));
      meta.setDisplayName(name);
      meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ATTRIBUTES});
      item.setItemMeta(meta);
      return item;
   }
}

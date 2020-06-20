package net.anglocraft.character;

import net.Indyuce.mmocore.api.player.PlayerData;
import net.anglocraft.Lang;
import net.anglocraft.Main;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class InventoryInteraction implements Listener {

    private Main main = Main.getPlugin(Main.class);

    @EventHandler
    public void charCreate(InventoryClickEvent event) {
        Inventory inv = event.getInventory();
        Player player = (Player) event.getWhoClicked();
        FactionSelectGUI gui = new FactionSelectGUI(player);
        Inventory guiInv = gui.factionSelect();
        PlayerData data = PlayerData.get(player.getUniqueId());
        if (inv.equals(guiInv)){
            if (!player.hasPermission("ac.created") || player.isOp()) {
                event.setCancelled(true);
                event.setCursor(null);
                int slot = event.getSlot();
                switch (slot) {
                    case 1:
                        if (inv.getItem(slot) == gui.saxon()) {
                            String msg = Lang.PREFIX + Lang.SAXON_JOIN.toString();
                            player.sendMessage(msg);
                            player.hasPermission("ac.created");
                            player.hasPermission("ac.saxons");
                            player.closeInventory();
                            player.setLevel(0);
                            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1,1);
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "mmocore admin class " + player.getName() + " SAXON");
                        }
                        break;
                    case 3:
                        if (inv.getItem(slot) == gui.dane()) {
                            String msg = Lang.PREFIX + Lang.DANE_JOIN.toString();
                            player.sendMessage(msg);
                            player.hasPermission("ac.created");
                            player.hasPermission("ac.danes");
                            player.closeInventory();
                            player.setLevel(0);
                            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1,1);
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "mmocore admin class " + player.getName() + " DANE");
                        }
                        break;
                }
            }
        }
    }

}

package net.anglocraft.character;

import net.Indyuce.mmocore.api.player.PlayerData;
import net.anglocraft.Lang;
import net.anglocraft.Main;
import net.anglocraft.player.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class InventoryInteraction implements Listener {

    private final Main main = Main.getPlugin(Main.class);

    @EventHandler
    public void charCreate(InventoryClickEvent event) {
        Inventory inv = event.getInventory();
        Player player = (Player) event.getWhoClicked();
        FactionSelectGUI gui = new FactionSelectGUI();
        Inventory guiInv = gui.factionSelect();
        PlayerData data = PlayerData.get(player.getUniqueId());
        String contmsg = Lang.SKIN_SELECT.toString();
        PlayerManager manager = new PlayerManager(player);
        if (inv.contains(gui.dane())){
            if (!player.hasPermission("ac.created") || player.isOp()) {
                event.setCancelled(true);
                event.setCursor(null);
                int slot = event.getSlot();
                switch (slot) {
                    case 1:
                        String saxonmsg = Lang.PREFIX + Lang.SAXON_JOIN.toString();
                        player.sendMessage(saxonmsg);
                        player.hasPermission("ac.created");
                        player.hasPermission("ac.saxons");
                        manager.setFaction("Saxons");
                        player.closeInventory();
                        data.setLevel(1);
                        data.setExperience(0);
                        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1,1);
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "mmocore admin class " + player.getName() + " SAXON");
                        contmsg = ChatColor.translateAlternateColorCodes('&',contmsg.replaceAll("%guide%", main.config().getString("Settings.Character_Creation.Saxon_NPC")));
                        player.sendMessage(contmsg);
                        break;
                    case 3:
                        String danemsg = Lang.PREFIX + Lang.DANE_JOIN.toString();
                        player.sendMessage(danemsg);
                        player.hasPermission("ac.created");
                        player.hasPermission("ac.danes");
                        manager.setFaction("Danes");
                        player.closeInventory();
                        data.setLevel(1);
                        data.setExperience(0);
                        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1,1);
                        contmsg = ChatColor.translateAlternateColorCodes('&',contmsg.replaceAll("%guide%", main.config().getString("Settings.Character_Creation.Dane_NPC")));
                        player.sendMessage(contmsg);
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "mmocore admin class " + player.getName() + " DANE");

                        break;
                }
            } else {
                player.closeInventory();
            }
        }
    }

}

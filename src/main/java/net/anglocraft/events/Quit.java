package net.anglocraft.events;

import net.anglocraft.Lang;
import net.anglocraft.Main;
import net.anglocraft.player.PlayerManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class Quit implements Listener {
   private final Main main = (Main)Main.getPlugin(Main.class);

   @EventHandler
   public void onQuit(PlayerQuitEvent event) {
      Player player = event.getPlayer();
      PlayerManager manager = new PlayerManager(player);
      manager.savePlayerData();
      String quit = ChatColor.translateAlternateColorCodes('&', Lang.QUIT.toString());
      quit = quit.replaceAll("%player%", player.getName());
      event.setQuitMessage(quit);
   }
}

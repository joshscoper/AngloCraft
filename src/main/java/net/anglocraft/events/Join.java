package net.anglocraft.events;

import net.anglocraft.Lang;
import net.anglocraft.Main;
import net.anglocraft.player.PlayerManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Join implements Listener {
   private final Main main = (Main)Main.getPlugin(Main.class);

   @EventHandler
   public void onJoin(PlayerJoinEvent event) {
      Player player = event.getPlayer();
      String join = ChatColor.translateAlternateColorCodes('&', Lang.JOIN.toString());
      join = join.replaceAll("%player%", player.getName());
      event.setJoinMessage(join);
      PlayerManager manager = new PlayerManager(player);
      if (!player.hasPlayedBefore() || !manager.hasFile()) {
         this.firstJoin(player);
      }

   }

   public void firstJoin(Player player) {
      String welcome = ChatColor.translateAlternateColorCodes('&', Lang.WELCOME_MESSAGE.toString());
      welcome = welcome.replaceAll("%player%", player.getName());
      player.sendMessage(welcome);
      PlayerManager manager = new PlayerManager(player);
      manager.registerPlayer();
   }
}

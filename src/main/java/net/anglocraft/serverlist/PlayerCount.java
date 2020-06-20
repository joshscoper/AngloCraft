package net.anglocraft.serverlist;

import net.anglocraft.Main;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

public class PlayerCount implements Listener {
   Main main = (Main)Main.getPlugin(Main.class);

   @EventHandler
   public void setPlayerCount(ServerListPingEvent event) {
      if (Bukkit.getOnlinePlayers().size() >= 1) {
         int count = Bukkit.getOnlinePlayers().size() + 1;
         event.setMaxPlayers(count);
      } else {
         event.setMaxPlayers(this.main.config().getInt("Settings.MaxPlayers"));
      }

   }
}

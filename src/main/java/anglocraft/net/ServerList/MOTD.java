package anglocraft.net.ServerList;

import anglocraft.net.Main;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

public class MOTD implements Listener {
   Main main = (Main)Main.getPlugin(Main.class);

   @EventHandler
   public void setMOTD(ServerListPingEvent event) {
      String motd = ChatColor.translateAlternateColorCodes('&', this.main.config().getString("Settings.MOTD"));
      event.setMotd(motd);
   }
}

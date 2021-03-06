package net.anglocraft.commands;

import net.anglocraft.Lang;
import net.anglocraft.Main;
import net.anglocraft.player.PlayerManager;
import java.util.logging.Logger;

import net.wesjd.anvilgui.AnvilGUI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Name implements CommandExecutor {
   private final Main main = (Main)Main.getPlugin(Main.class);
   String admin_prefix;

   public Name() {
      this.admin_prefix = ChatColor.translateAlternateColorCodes('&', Lang.PREFIX.toString());
   }

   public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
      if (cmd.getName().equalsIgnoreCase("name")) {
         if (sender instanceof Player && args.length == 0) {
            Player player = (Player)sender;
            (new AnvilGUI.Builder()).onClose((p) -> {
            }).onComplete((p, text) -> {
               player.setDisplayName(text);
               player.setPlayerListName(text);
               player.setCustomName(text);
               //PlayerManager manager = new PlayerManager(p);
               //manager.registerPlayer();
               player.setCustomNameVisible(true);
               player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1.0F, 1.0F);
               player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&lPriest &f-> &aWell nice to meet you &6&n" + text + "&e!"));
               return AnvilGUI.Response.close();
            }).preventClose().text("Select your name.").item(new ItemStack(Material.NAME_TAG)).title("What's your name?").plugin(Main.getPlugin(Main.class)).open(player);
            return true;
         }
         // Admin Change
         if (sender.hasPermission("ac.admin")) {
            Logger logger;
            if (args.length > 0) {
               if (args[0] != null) {
                  String target = args[0];
                  Player player = (Player)Bukkit.getOfflinePlayer(target);
                  if (Bukkit.getOnlinePlayers().contains(player)) {
                     Player t = player;
                     (new AnvilGUI.Builder()).onClose((p) -> {
                     }).onComplete((p, text) -> {
                        sender.sendMessage(Lang.PREFIX.toString() + ChatColor.RED + t.getName() + " has been changed to " + ChatColor.WHITE + text);
                        t.setDisplayName(text);
                       /* PlayerManager manager = new PlayerManager(p);
                        if (manager.getPlayerData().getName().equals(p.getUniqueId().toString()) && manager.getPlayerData().getName() != null) {
                           manager.setRPName(text);
                           sender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.admin_prefix + "&7" + player.getDisplayName() + "'s name has been changed to &8&l" + text));
                        } else {
                           manager.registerPlayer();
                           sender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.admin_prefix + "&7" + player.getDisplayName() + "'s name has been changed to &8&l" + text));
                        }*/

                        t.setPlayerListName(text);
                        t.setCustomName(text);
                        t.setCustomNameVisible(true);
                        t.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1.0F, 1.0F);
                        t.sendMessage(ChatColor.translateAlternateColorCodes('&', this.admin_prefix + "&cYour name has been changed to " + text));
                        return AnvilGUI.Response.close();
                     }).preventClose().text("Select target's name").item(new ItemStack(Material.NAME_TAG)).title(Lang.PREFIX.toString() + " Name Change").plugin(Main.getPlugin(Main.class)).open((Player) sender);
                     return true;
                  }

                  return true;
               }
               return true;
            }
            return true;
         }
      }

      return false;
   }
}

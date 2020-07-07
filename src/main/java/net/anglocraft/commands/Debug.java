package net.anglocraft.commands;

import net.anglocraft.Lang;
import net.anglocraft.villages.VillageItems;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Debug implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            Player player = (Player) sender;
            if (player.isOp()) {
                if (command.getName().equalsIgnoreCase("villageitem")) {
                    VillageItems vitem = new VillageItems();
                    if (args.length > 0) {
                        if (isInt(args[0])) {
                            int tier = Integer.parseInt(args[0]);
                            if (tier > 0 && tier <= 10){
                                player.getInventory().addItem(vitem.saxonHouse(tier));
                                player.sendMessage(Lang.PREFIX + "" + ChatColor.GREEN + "You received " + ChatColor.YELLOW + "1x Saxon House Tier " + tier + ChatColor.GREEN + ".");
                                return true;
                            } else {
                                player.sendMessage(Lang.PREFIX + "" + ChatColor.RED + "You must use a number between 1 and 10");
                                return true;
                            }
                        }
                        player.sendMessage(Lang.PREFIX + "" + ChatColor.RED + "You must use a number between 1 and 10");
                        return true;
                    }
                    player.getInventory().addItem(vitem.saxonHouse(1));
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isInt(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}

package net.anglocraft.events;

import net.anglocraft.BlockLocate;
import net.anglocraft.Lang;
import net.anglocraft.Main;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.Arrays;
import java.util.HashSet;

public class Movement implements Listener {

    Main main = Main.getPlugin(Main.class);

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        Location from = event.getFrom();
        Location to = event.getTo();
        Player player = event.getPlayer();
        //Anti Swim
        if(player.isSwimming() && main.config().get("Settings.Swim.Anti_Swim") == "true" ) {
            Location location = from;
            BlockLocate locate = new BlockLocate();
            Block closest = locate.closestBlock(location, new HashSet<Integer>(Arrays.asList(Material.GRASS_BLOCK.getId())));
            location = closest.getLocation();
            player.teleport(location);
            player.sendMessage(Lang.PREFIX + "" + ChatColor.RED + "You can't swim!");
            return;
        }
    }
}

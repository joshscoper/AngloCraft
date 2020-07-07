package net.anglocraft.events;

import net.anglocraft.Lang;
import net.anglocraft.Main;
import net.anglocraft.cooldown.JumpCooldownManager;
import net.anglocraft.customevents.JumpEvent;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class Jump implements Listener {

    Main main = Main.getPlugin(Main.class);

    @EventHandler
    public void onJump(JumpEvent event) {
        Player player = event.getPlayer();
        if (main.config().get("Settings.Jump.Anti_Bhop") == "true") {
            Long cd = main.config().getLong("Settings.Jump.Cooldown");
            JumpCooldownManager cooldown = new JumpCooldownManager(player, cd);
            if (!cooldown.isOnCooldown()) {
                cooldown.addCooldown();
            }  else {
                player.sendMessage(Lang.PREFIX + "" + ChatColor.RED + "You can't jump yet.");
                if (!player.isOnGround()) {
                    Location back = player.getLocation();
                    double y = back.getY() - 1;
                    back.setY(y);
                    player.teleport(back);
                }
            }
        }
    }

}

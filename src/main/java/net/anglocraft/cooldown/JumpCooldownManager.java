package net.anglocraft.cooldown;

import net.anglocraft.Lang;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import javax.swing.*;
import java.util.HashMap;

public class JumpCooldownManager {

    private HashMap<Player, Long> cooldown;
    private Player player;
    private Long time;


    public JumpCooldownManager(Player player, Long time) {
        this.player = player;
        this.time = time;
    }

    public boolean isOnCooldown(){
        if (cooldown.containsKey(player)) {
            return true;
        } else {
            return false;
        }
    }

    public int cooldownTime() {
        return cooldown.get(player).intValue();
    }

    public void addCooldown() {
        cooldown.put(player, time);
        for (int i = Math.toIntExact(time); i > -1; i--) {
            if (i == 0) {
                cooldown.remove(player);
                return;
            }
        }
    }

}

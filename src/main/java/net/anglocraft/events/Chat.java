package net.anglocraft.events;

import net.Indyuce.mmocore.api.player.PlayerData;
import net.anglocraft.Main;
import net.anglocraft.chat.ChatScrambler;
import net.anglocraft.chat.JsonManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerChatEvent;

import java.util.ArrayList;
import java.util.List;


public class Chat implements Listener {
//TODO Proximity Chat (Configurable distances)

    @EventHandler
    public void onChat(PlayerChatEvent event) {
        event.setCancelled(true);
        List<Player> recipients = new ArrayList<>();
        recipients.addAll(event.getRecipients());
        ChatScrambler chat = new ChatScrambler(event.getPlayer(), recipients);
        PlayerData data = PlayerData.get(event.getPlayer().getUniqueId());
        if (data.getProfess().getName().equals(ChatColor.translateAlternateColorCodes('&',"&e&lSaxons"))){
            event.setFormat(ChatColor.translateAlternateColorCodes('&', "&b&l" + event.getPlayer().getCustomName() + "&7: &a"));
            chat.sendMessage(event.getMessage());
        } else {
            event.setFormat(ChatColor.translateAlternateColorCodes('&', "&c&l" + event.getPlayer().getCustomName() + "&7: &a"));
            chat.sendMessage(event.getMessage());
        }
    }
}

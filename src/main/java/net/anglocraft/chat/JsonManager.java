package net.anglocraft.chat;

import net.anglocraft.player.PlayerManager;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class JsonManager {

    Player player;

    public JsonManager(Player player) {
        this.player = player;
    }

    public BaseComponent friendlyJsonName(){
        PlayerManager manager = new PlayerManager(player);
        String rpname = ChatColor.translateAlternateColorCodes('&',"&b&l" + manager.getRPName());
        TextComponent name = new TextComponent(rpname);
        name.setBold(true);
        name.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,new ComponentBuilder(ChatColor.GRAY + "Name: ")
                .append(ChatColor.WHITE + player.getName() + "\n")
                .append(ChatColor.GRAY + "Faction: ")
                .append(ChatColor.WHITE + manager.getFaction() + "\n")
                .append(ChatColor.GRAY + "Village: ")
                .append(ChatColor.WHITE + manager.getVillage() + "\n").create()));
        return name;
    }

    public BaseComponent hostileJsonName(){
        PlayerManager manager = new PlayerManager(player);
        String rpname = ChatColor.translateAlternateColorCodes('&',"&c&l" + manager.getRPName());
        TextComponent name = new TextComponent(rpname);
        name.setBold(true);
        name.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,new ComponentBuilder(ChatColor.GRAY + "Name: ")
                .append(ChatColor.WHITE + player.getName() + "\n")
                .append(ChatColor.GRAY + "Faction: ")
                .append(ChatColor.WHITE + manager.getFaction() + "\n")
                .append(ChatColor.GRAY + "Village: ")
                .append(ChatColor.WHITE + manager.getVillage() + "\n").create()));
        return name;
    }

    public BaseComponent neutralJsonName(){
        PlayerManager manager = new PlayerManager(player);
        String rpname = ChatColor.translateAlternateColorCodes('&',"&e&l" + manager.getRPName());
        TextComponent name = new TextComponent(rpname);
        name.setBold(true);
        name.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,new ComponentBuilder(ChatColor.GRAY + "Name: ")
                .append(ChatColor.WHITE + player.getName() + "\n")
                .append(ChatColor.GRAY + "Faction: ")
                .append(ChatColor.WHITE + manager.getFaction() + "\n")
                .append(ChatColor.GRAY + "Village: ")
                .append(ChatColor.WHITE + manager.getVillage() + "\n").create()));
        return name;
    }


}

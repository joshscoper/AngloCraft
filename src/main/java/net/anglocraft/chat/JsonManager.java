package net.anglocraft.chat;

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

    public TextComponent jsonName(){
        String rpname = ChatColor.translateAlternateColorCodes('&',player.getCustomName());
        TextComponent name = new TextComponent(rpname);
        name.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,new ComponentBuilder("Will be RP Info").create()));
        return name;
    }



}

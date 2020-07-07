package net.anglocraft.chat;

import com.google.common.escape.CharEscaperBuilder;
import net.Indyuce.mmocore.api.player.PlayerData;
import net.anglocraft.Lang;
import net.anglocraft.Main;
import net.anglocraft.player.PlayerManager;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ChatScrambler {

    Player player;
    List<Player> recipients;
    private Main main = Main.getPlugin(Main.class);
    private List<Player> foreign = new ArrayList<Player>();
    private List<Player> same = new ArrayList<Player>();

    public ChatScrambler(Player player, List<Player> recipients){
        this.player = player;
        this.recipients = recipients;
    }

    public void checkDistance(){
        recipients.clear();
        recipients.add(player);
        int radius = main.config().getInt("Settings.Chat_Distance");
        for (Entity entity : player.getNearbyEntities(radius,radius,radius)) {
            if (entity instanceof Player){
                Player r = (Player) entity;
                recipients.add(r);
            }
        }
    }

    public void checkFaction(){
        for (Player r : recipients){
            PlayerData senderData = PlayerData.get(player.getUniqueId());
            PlayerData recipientData = PlayerData.get(r.getUniqueId());
            if (!senderData.getProfess().getName().equals(recipientData.getProfess().getName())){
              foreign.add(r);
            } else {
                same.add(r);
            }
        }
    }


    public void sendMessage(String message){
        JsonManager json = new JsonManager(player);
        PlayerManager manager = new PlayerManager(player);
        char prefix = message.charAt(0);
        if (prefix == '!'){
            //OOC
            for (Player r : recipients) {
                message = message.replace(prefix, ' ');
                TextComponent msg = new TextComponent(ChatColor.translateAlternateColorCodes('&', " &7:&e" + message));
                r.spigot().sendMessage(json.ooc(), json.neutralJsonName(), msg );
            }
            return;
        }
        if (prefix == '$' && player.hasPermission("ac.staff")){
            //Staff
            for (Player r : recipients){
                if (r.hasPermission("ac.staff")){
                    message = message.replace(prefix, ' ');
                    TextComponent msg = new TextComponent(ChatColor.translateAlternateColorCodes('&', " &7:&c" + message));
                    r.spigot().sendMessage(json.staff(), json.neutralJsonName(), msg);
                }
            }
            return;
        }

        //Proximity Chat
        if (manager.hasRPName()) {
            if (main.config().get("Settings.Proximity_Chat").equals("true")) {
                checkDistance();
                if (main.config().get("Settings.Scramble_Chat").equals("true")) {
                    checkFaction();
                    for (Player r : same) {
                        TextComponent msg = new TextComponent(ChatColor.translateAlternateColorCodes('&', " &7: &f" + message));
                        r.spigot().sendMessage(json.friendlyJsonName(), msg);
                        break;
                    }
                    for (Player r: foreign) {
                        message = ChatColor.translateAlternateColorCodes('&', "&f&k" + message);
                        TextComponent msg = new TextComponent(ChatColor.translateAlternateColorCodes('&', " &7: &f" + message));
                        r.spigot().sendMessage(json.hostileJsonName(), msg);
                        break;
                    }
                } else {
                    for (Player r: recipients){
                        TextComponent msg = new TextComponent(ChatColor.translateAlternateColorCodes('&', " &7: &f" + message));
                        r.spigot().sendMessage(json.neutralJsonName(), msg);
                    }
                }
            } else {
                for (Player r: recipients){
                    TextComponent msg = new TextComponent(ChatColor.translateAlternateColorCodes('&', " &7: &f" + message));
                    r.spigot().sendMessage(json.neutralJsonName(), msg);
                }
            }
        } else {
            TextComponent msg = new TextComponent(ChatColor.translateAlternateColorCodes('&', " &7: &f" + message));
            for (Player r : recipients) {
                r.spigot().sendMessage(json.name(), msg);
                break;
            }
        }

    }

}

package net.anglocraft.chat;

import com.google.common.escape.CharEscaperBuilder;
import net.Indyuce.mmocore.api.player.PlayerData;
import net.anglocraft.Main;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import javax.xml.soap.Text;
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
        checkFaction();
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
        if (main.config().getBoolean("Settings.Scramble_Chat")){
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
    }

}

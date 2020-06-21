package net.anglocraft.chat;

import net.Indyuce.mmocore.api.player.PlayerData;
import net.anglocraft.Main;
import org.bukkit.ChatColor;
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
        if (main.config().getBoolean("Settings.Scramble_Chat")){
            for (Player r : same) {
                r.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&l" + player.getCustomName() + "&7: &f" + message));
                break;
            }
            for (Player r: foreign) {
                r.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&l" + player.getCustomName() + "&7: &f&k" + message));
                break;
            }
        }
    }

}

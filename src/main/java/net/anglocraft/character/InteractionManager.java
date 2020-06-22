package net.anglocraft.character;

import net.Indyuce.mmocore.api.player.PlayerData;
import net.anglocraft.Lang;
import net.anglocraft.Main;
import net.wesjd.anvilgui.AnvilGUI;
import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class InteractionManager {

    private final Main main = Main.getPlugin(Main.class);
    private final String charcreate = ChatColor.translateAlternateColorCodes('&', this.main.getConfig().getString("Settings.Character_Creation.NPC_Name"));
    private final String dane = ChatColor.translateAlternateColorCodes('&', this.main.getConfig().getString("Settings.Character_Creation.Dane_NPC"));
    private final String saxon = ChatColor.translateAlternateColorCodes('&', this.main.getConfig().getString("Settings.Character_Creation.Saxon_NPC"));
    private Player player;
    private Entity entity;
    private String name;


    public InteractionManager(Player player, Entity entity, String name) {
        this.player = player;
        this.entity = entity;
        this.name = name;
    }


    public void interactNPC(){
        PlayerData data = PlayerData.get(player.getUniqueId());
        String faction = data.getProfess().getName();
        faction = ChatColor.stripColor(faction);
        if (entity.getName().equals(charcreate)) {
            if (player.hasPermission("ac.created") || player.isOp()) {
                if (name.equals(charcreate)) {
                    FactionSelectGUI gui = new FactionSelectGUI();
                    player.openInventory(gui.factionSelect());
                }
            }
        }

        if (name.equals(dane)) {
            if (faction.equalsIgnoreCase("dane")){
                nameSelect();
            } else {
                player.sendMessage(Lang.PREFIX.toString() + ChatColor.RED + "Talk to the " + ChatColor.GOLD + ChatColor.BOLD +  "Priest" + ChatColor.RED + ".");
                }
            }

        if (name.equals(saxon)) {
            if (faction.equalsIgnoreCase("Saxon")){
                nameSelect();
            } else {
                player.sendMessage(Lang.PREFIX.toString() + ChatColor.RED + "Talk to the " + ChatColor.GOLD + ChatColor.BOLD +  "Seer" + ChatColor.RED + ".");
                }
            }
        }

    public void nameSelect(){
        (new AnvilGUI.Builder()).onClose((p) -> {
        }).onComplete((p, text) -> {
            player.setDisplayName(text);
            player.setPlayerListName(text);
            player.setCustomName(text);
            player.setCustomNameVisible(true);
            PlayerData playerData;
            playerData = PlayerData.get(p.getUniqueId());
            String faction = playerData.getProfess().getName().replaceAll("&e&l" , "");
            Bukkit.getScheduler().scheduleSyncDelayedTask(main, new Runnable() {
                @Override
                public void run() {
                    skinSelect(faction);
                }
            }, 20);
            player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1.0F, 1.0F);
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&lPriest &f-> &aWell nice to meet you &6&n" + text + "&e!"));
            return AnvilGUI.Response.close();
        }).preventClose().text("Select your name.").item(new ItemStack(Material.NAME_TAG)).title("What's your name?").plugin(Main.getPlugin(Main.class)).open(player);
    }


    public void skinSelect(String faction){
        if (!player.hasPermission("ac.created") || player.isOp()) {
            Location charCreate = player.getLocation();
            player.sendMessage(Lang.SKIN_SELECT_LOADING.toString());
            String factions= ChatColor.stripColor(faction);
            player.hasPermission("ac.created");
            charCreate.setPitch(0.3F);
            charCreate.setYaw(35.3F);
            player.teleport(charCreate);
            Bukkit.getScheduler().scheduleSyncDelayedTask(this.main, new Runnable() {
                public void run() {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&lPriest &f-> &aMy eyes aren't what they used to be. What do you look like?"));
                    player.performCommand("wardrobe open " + factions);
                    player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1.0F, 1.0F);
                    Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "speed walking 0 " + player.getName());
                    player.hasPermission("ac.created");
                }
            }, 20*2);
        }
    }
}

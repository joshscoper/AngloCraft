package net.anglocraft.character;

import net.anglocraft.Main;
import java.util.Iterator;
import net.Indyuce.mmocore.api.player.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.EquipmentSlot;

public class Interaction implements Listener {
   Main main = (Main)Main.getPlugin(Main.class);

   @EventHandler
   public void characterCreate(PlayerInteractAtEntityEvent event) {
      final Player player = event.getPlayer();
      Entity ent = event.getRightClicked();
      String saxon = ChatColor.translateAlternateColorCodes('&', this.main.getConfig().getString("Settings.Character_Creation.Saxon_NPC"));
      String dane = ChatColor.translateAlternateColorCodes('&', this.main.getConfig().getString("Settings.Character_Creation.Dane_NPC"));
      String daneMSG = ChatColor.translateAlternateColorCodes('&', "&6&l" + dane + " &e>> &aHey you come here!");
      String priestMSG = ChatColor.translateAlternateColorCodes('&', "&6&l" + saxon + " &e>> &aHello, may I have a word with you?");
      if (event.getHand().equals(EquipmentSlot.HAND)) {
         String name = ChatColor.translateAlternateColorCodes('&', this.main.getConfig().getString("Settings.Character_Creation.NPC_Name"));
         if (ent.getName().equals(name) && ent instanceof Villager) {
            new FactionSelectGUI(player);
            event.setCancelled(true);
         }

         if (ent.getName().equals(dane) && PlayerData.get(player).getProfess().getName().equals(ChatColor.translateAlternateColorCodes('&', "&e&lDane"))) {
            player.sendMessage("Dane Skin Selection");
            event.setCancelled(true);
         } else if (ent.getName().equals(dane) && !PlayerData.get(player).getProfess().getName().equals(ChatColor.translateAlternateColorCodes('&', "&e&lDane"))) {
            player.sendMessage(ChatColor.RED + "You aren't in that faction talk to the Saxon Priest");
            event.setCancelled(true);
         }

         if (ent.getName().equals(saxon) && PlayerData.get(player).getProfess().getName().equals(ChatColor.translateAlternateColorCodes('&', "&e&lSaxon"))) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&lPriest &f-> &aHeavenly Father bless... well, who are you?"));
            player.hasPermission("ac.created");
            Iterator var9 = Bukkit.getOnlinePlayers().iterator();

            while(var9.hasNext()) {
               Player x = (Player)var9.next();
               player.hidePlayer(x);
            }

            Location charCreate = player.getLocation();
            charCreate.setPitch(0.3F);
            charCreate.setYaw(35.3F);
            player.teleport(charCreate);
            Bukkit.getScheduler().scheduleSyncDelayedTask(this.main, new Runnable() {
               public void run() {
                  player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&lPriest &f-> &aMy eyes aren't what they used to be. What do you look like?"));
                  player.performCommand("wardrobe open Saxons");
                  player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1.0F, 1.0F);
                  Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "speed walking 0 " + player.getName());
               }
            }, 40L);
            event.setCancelled(true);
         } else if (ent.getName().equals(saxon) && !PlayerData.get(player).getProfess().getName().equals(ChatColor.translateAlternateColorCodes('&', "&e&lSaxon"))) {
            player.sendMessage(ChatColor.RED + "You aren't in that faction talk to the Dane Seer");
            event.setCancelled(true);
         }
      }

   }
}

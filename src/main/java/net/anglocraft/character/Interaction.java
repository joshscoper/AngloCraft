package net.anglocraft.character;

import net.anglocraft.Lang;
import net.anglocraft.Main;
import java.util.Iterator;
import net.Indyuce.mmocore.api.player.PlayerData;
import net.wesjd.anvilgui.AnvilGUI;
import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;


public class Interaction implements Listener {
   Main main = (Main)Main.getPlugin(Main.class);


   @EventHandler
   public void characterCreate(PlayerInteractAtEntityEvent event) {
      final Player player = event.getPlayer();
      Entity ent = event.getRightClicked();
      if (event.getHand().equals(EquipmentSlot.HAND)) {
         event.setCancelled(true);
         InteractionManager interaction = new InteractionManager(player, ent, ent.getName());
         interaction.interactNPC();
      }

   }


}

package net.anglocraft.events;

import net.anglocraft.Lang;
import net.anglocraft.villages.VillageItems;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class ItemInteraction implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        VillageItems item = new VillageItems();
        int[] tier = {1,2,3,4,5,6,7,8,9,10};
        for (int x : tier){
            if (player.getInventory().getItemInMainHand().equals(item.saxonHouse(x))){
                player.sendMessage(Lang.PREFIX + "" + ChatColor.GREEN + "You placed a " + player.getInventory().getItemInMainHand().getItemMeta().getDisplayName());
                player.getInventory().setItemInMainHand(new ItemStack(Material.AIR));
                event.setCancelled(true);
                return;
            }
            if (player.getInventory().getItemInOffHand().equals(item.saxonHouse(x))) {
                event.setCancelled(true);
                return;
            }
        }

    }

}

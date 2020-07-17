package net.anglocraft.events;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.WorldEditException;
import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormat;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormats;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardReader;
import com.sk89q.worldedit.extent.clipboard.io.MCEditSchematicReader;
import com.sk89q.worldedit.function.operation.Operation;
import com.sk89q.worldedit.function.operation.Operations;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.session.ClipboardHolder;
import com.sk89q.worldedit.world.World;
import net.anglocraft.Lang;
import net.anglocraft.villages.VillageItems;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class VillageItemEvents implements Listener {

    Clipboard clipboard;

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

    public void loadSchematic(File file){
        ClipboardFormat format = ClipboardFormats.findByFile(file);
        try (ClipboardReader reader = format.getReader(new FileInputStream(file))) {
            clipboard = reader.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void pasteSchematic(Location location) {
        double x = location.getX();
        double y = location.getY();
        double z = location.getZ();
        World world = (World) location.getWorld();
        try (EditSession editSession = WorldEdit.getInstance().getEditSessionFactory().getEditSession(world, -1)) {
            Operation operation = new ClipboardHolder(clipboard)
                    .createPaste(editSession)
                    .to(BlockVector3.at(x, y, z))
                    // configure here
                    .build();
            Operations.complete(operation);
        } catch (WorldEditException e) {
            e.printStackTrace();
        }
    }


}

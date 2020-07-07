package net.anglocraft;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;

import java.util.Set;

public class BlockLocate {

    /**
     * Finds the closest block in a vertical column.
     * @param origin The location around which to search.
     *               This location will NOT be included in the search, but all other locations in the column will.
     * @param types  A Set (preferably a HashSet) that contains the type IDs of blocks to search for
     * @return The closest block, or null if one was not found in the column.
     *         In the case of a tie, the higher block wins.
     */
    public Block closestBlock(Location origin, Set<Integer> types)
    {
        int x = origin.getBlockX();
        int y = origin.getBlockY();
        int z = origin.getBlockZ();
        World world = origin.getWorld();

        for(int cy = 2; cy < 512; cy++)
        {
            int testx;
            if((cy & 1) == 0) //if even
            {
                testx = x + cy / 2;
                if(testx > 255)
                    continue;
            }
            else
            {
                testx = x - cy / 2;
                if(testx < 0)
                    continue;
            }

            if(types.contains(world.getBlockAt(testx, y, z)))
                return world.getBlockAt(testx, y, z);
        }

        return null;
    }
}

package net.anglocraft.claim;

import org.apache.commons.lang.Validate;
import org.bukkit.Chunk;
import org.bukkit.World;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Ben Kinney
 * @since 2020/06/20
 */
// TODO 2020/06/20: Load/save claims
public final class WorldClaimManager {

    private final World                 world;
    private final Map<Claim.Key, Claim> claimMap = new HashMap<>();

    public WorldClaimManager(World world) {
        this.world = world;
    }

    public World getWorld() {
        return world;
    }

    public Claim getClaim(int x, int z) {
        return claimMap.computeIfAbsent(new Claim.Key(x, z), Claim::new);
    }

    public Claim getClaim(Chunk chunk) {
        Validate.isTrue(chunk.getWorld() == world, "chunk's world is not the same as claim manager's world");
        return getClaim(chunk.getX(), chunk.getZ());
    }

    public void removeClaim(int x, int z) {
        claimMap.remove(new Claim.Key(x, z));
    }

    public void removeClaim(Chunk chunk) {
        removeClaim(chunk.getX(), chunk.getZ());
    }

    public boolean isClaimed(int x, int z) {
        return claimMap.containsKey(new Claim.Key(x, z));
    }

    public boolean isClaimed(Chunk chunk) {
        return isClaimed(chunk.getX(), chunk.getZ());
    }
}

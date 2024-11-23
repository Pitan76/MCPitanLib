package net.pitan76.mcpitanlib.midohra.world.chunk;

import net.pitan76.mcpitanlib.midohra.world.BlockView;
import net.pitan76.mcpitanlib.midohra.world.IWorldView;
import net.pitan76.mcpitanlib.midohra.world.World;

public class ChunkManager {
    private final net.minecraft.world.chunk.ChunkManager chunkManager;

    protected ChunkManager(net.minecraft.world.chunk.ChunkManager chunkManager) {
        this.chunkManager = chunkManager;
    }

    public static ChunkManager of(net.minecraft.world.chunk.ChunkManager chunkManager) {
        return new ChunkManager(chunkManager);
    }

    public static ChunkManager of(net.minecraft.world.World world) {
        return of(world.getChunkManager());
    }

    public static ChunkManager of(World world) {
        return of(world.getRaw());
    }

    public net.minecraft.world.chunk.ChunkManager getRaw() {
        return chunkManager;
    }

    public net.minecraft.world.chunk.ChunkManager toMinecraft() {
        return getRaw();
    }

    public IWorldView getWorld() {
        return BlockView.of(getRaw().getWorld());
    }

    public boolean isChunkLoaded(int x, int z) {
        return getRaw().isChunkLoaded(x, z);
    }
}

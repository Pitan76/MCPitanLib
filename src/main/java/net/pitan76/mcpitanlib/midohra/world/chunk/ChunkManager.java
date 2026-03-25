package net.pitan76.mcpitanlib.midohra.world.chunk;

import net.pitan76.mcpitanlib.midohra.world.BlockView;
import net.pitan76.mcpitanlib.midohra.world.IWorldView;
import net.pitan76.mcpitanlib.midohra.world.World;

public class ChunkManager {
    private final net.minecraft.world.level.chunk.ChunkSource chunkManager;

    protected ChunkManager(net.minecraft.world.level.chunk.ChunkSource chunkManager) {
        this.chunkManager = chunkManager;
    }

    public static ChunkManager of(net.minecraft.world.level.chunk.ChunkSource chunkManager) {
        return new ChunkManager(chunkManager);
    }

    public static ChunkManager of(net.minecraft.world.level.Level world) {
        return of(world.getChunkSource());
    }

    public static ChunkManager of(World world) {
        return of(world.getRaw());
    }

    public net.minecraft.world.level.chunk.ChunkSource getRaw() {
        return chunkManager;
    }

    public net.minecraft.world.level.chunk.ChunkSource toMinecraft() {
        return getRaw();
    }

    public IWorldView getWorld() {
        return BlockView.of(getRaw().getLevel());
    }

    public boolean isChunkLoaded(int x, int z) {
        return getRaw().hasChunk(x, z);
    }
}

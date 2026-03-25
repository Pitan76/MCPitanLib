package net.pitan76.mcpitanlib.midohra.world.chunk;

import net.pitan76.mcpitanlib.api.util.world.ChunkManagerUtil;
import net.pitan76.mcpitanlib.midohra.util.math.BlockPos;
import net.pitan76.mcpitanlib.midohra.util.math.ChunkPos;
import net.pitan76.mcpitanlib.midohra.world.ServerWorld;
import net.pitan76.mcpitanlib.midohra.world.World;

public class ServerChunkManager extends ChunkManager {
    private final net.minecraft.server.level.ServerChunkCache chunkManager;

    protected ServerChunkManager(net.minecraft.server.level.ServerChunkCache chunkManager) {
        super(null);
        this.chunkManager = chunkManager;
    }

    public static ServerChunkManager of(net.minecraft.server.level.ServerChunkCache chunkManager) {
        return new ServerChunkManager(chunkManager);
    }

    public static ServerChunkManager of(ServerWorld world) {
        return of(world.getRaw().getChunkSource());
    }

    @Override
    public net.minecraft.server.level.ServerChunkCache getRaw() {
        return chunkManager;
    }

    @Override
    public net.minecraft.server.level.ServerChunkCache toMinecraft() {
        return getRaw();
    }

    @Override
    public World getWorld() {
        return World.of(getRaw().getLevel());
    }

    public <T> void addTicket(ChunkTicketType<T> ticketType, ChunkPos pos, int radius, T argument) {
        getRaw().addTicketWithRadius(ticketType.getRaw(), pos.getRaw(), radius);
    }

    public <T> void removeTicket(ChunkTicketType<T> ticketType, ChunkPos pos, int radius, T argument) {
        getRaw().removeTicketWithRadius(ticketType.getRaw(), pos.getRaw(), radius);
    }

    public void markForUpdate(BlockPos pos) {
        ChunkManagerUtil.markForUpdate(getRaw(), pos);
    }
}

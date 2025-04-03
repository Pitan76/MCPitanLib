package net.pitan76.mcpitanlib.midohra.world.chunk;

import net.pitan76.mcpitanlib.midohra.util.math.ChunkPos;
import net.pitan76.mcpitanlib.midohra.world.ServerWorld;
import net.pitan76.mcpitanlib.midohra.world.World;

public class ServerChunkManager extends ChunkManager {
    private final net.minecraft.server.world.ServerChunkManager chunkManager;

    protected ServerChunkManager(net.minecraft.server.world.ServerChunkManager chunkManager) {
        super(null);
        this.chunkManager = chunkManager;
    }

    public static ServerChunkManager of(net.minecraft.server.world.ServerChunkManager chunkManager) {
        return new ServerChunkManager(chunkManager);
    }

    public static ServerChunkManager of(ServerWorld world) {
        return of(world.getRaw().getChunkManager());
    }

    @Override
    public net.minecraft.server.world.ServerChunkManager getRaw() {
        return chunkManager;
    }

    @Override
    public net.minecraft.server.world.ServerChunkManager toMinecraft() {
        return getRaw();
    }

    @Override
    public World getWorld() {
        return World.of(getRaw().getWorld());
    }

    public <T> void addTicket(ChunkTicketType<T> ticketType, ChunkPos pos, int radius, T argument) {
        getRaw().addTicket(ticketType.getRaw(), pos.getRaw(), radius);
    }

    public <T> void removeTicket(ChunkTicketType<T> ticketType, ChunkPos pos, int radius, T argument) {
        getRaw().removeTicket(ticketType.getRaw(), pos.getRaw(), radius);
    }
}

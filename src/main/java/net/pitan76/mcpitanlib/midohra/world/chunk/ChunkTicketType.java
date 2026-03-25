package net.pitan76.mcpitanlib.midohra.world.chunk;

import net.minecraft.util.Unit;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.ChunkPos;

import java.util.Comparator;

public class ChunkTicketType<T> {

    public static final ChunkTicketType<Unit> START = of(net.minecraft.server.level.TicketType.PLAYER_SPAWN);
    public static final ChunkTicketType<Unit> DRAGON = of(net.minecraft.server.level.TicketType.DRAGON);
    public static final ChunkTicketType<ChunkPos> PLAYER = of(net.minecraft.server.level.TicketType.PLAYER_SIMULATION);
    public static final ChunkTicketType<ChunkPos> FORCED = of(net.minecraft.server.level.TicketType.FORCED);
    public static final ChunkTicketType<BlockPos> PORTAL = of(net.minecraft.server.level.TicketType.PORTAL);
    public static final ChunkTicketType<ChunkPos> ENDER_PEARL = of(net.minecraft.server.level.TicketType.ENDER_PEARL);
    public static final ChunkTicketType<Integer> POST_TELEPORT = of(net.minecraft.server.level.TicketType.PORTAL);
    public static final ChunkTicketType<ChunkPos> UNKNOWN = of(net.minecraft.server.level.TicketType.UNKNOWN);

    private final net.minecraft.server.level.TicketType ticketType;

    protected ChunkTicketType(net.minecraft.server.level.TicketType ticketType) {
        this.ticketType = ticketType;
    }

    public static <T> ChunkTicketType<T> of(net.minecraft.server.level.TicketType ticketType) {
        return new ChunkTicketType<>(ticketType);
    }

    public net.minecraft.server.level.TicketType getRaw() {
        return ticketType;
    }

    public net.minecraft.server.level.TicketType toMinecraft() {
        return getRaw();
    }

    public static <T> ChunkTicketType<T> create(String name, Comparator<T> argumentComparator) {
        return (ChunkTicketType<T>) create(name);
    }

    public static <T> ChunkTicketType<T> create(String name, Comparator<T> argumentComparator, int expiryTicks) {
        return (ChunkTicketType<T>) create(name, expiryTicks);
    }

    public Comparator<T> getArgumentComparator() {
        return null;
    }

    public long getExpiryTicks() {
        return getRaw().timeout();
    }

    @Override
    public String toString() {
        return getRaw().toString();
    }

    public ChunkTicketType(String name, long expiryTicks) {
        this(new net.minecraft.server.level.TicketType(expiryTicks, 6));
    }

    public ChunkTicketType(String name) {
        this(name, 0);
    }

    public static ChunkTicketType<ChunkPos> create(String name, long expiryTicks) {
        return new ChunkTicketType<>(name, expiryTicks);
    }

    public static ChunkTicketType<ChunkPos> create(String name) {
        return new ChunkTicketType<>(name);
    }

    @Override
    public int hashCode() {
        return ticketType.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof ChunkTicketType)) return false;
        ChunkTicketType<?> that = (ChunkTicketType<?>) obj;
        return ticketType.equals(that.ticketType);
    }
}

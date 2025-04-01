package net.pitan76.mcpitanlib.midohra.world.chunk;

import net.minecraft.util.Unit;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;

import java.util.Comparator;

public class ChunkTicketType<T> {

    public static final ChunkTicketType<Unit> START = of(net.minecraft.server.world.ChunkTicketType.START);
    public static final ChunkTicketType<Unit> DRAGON = of(net.minecraft.server.world.ChunkTicketType.DRAGON);
    public static final ChunkTicketType<ChunkPos> PLAYER = of(net.minecraft.server.world.ChunkTicketType.PLAYER);
    public static final ChunkTicketType<ChunkPos> FORCED = of(net.minecraft.server.world.ChunkTicketType.FORCED);
    public static final ChunkTicketType<BlockPos> PORTAL = of(net.minecraft.server.world.ChunkTicketType.PORTAL);
    public static final ChunkTicketType<ChunkPos> ENDER_PEARL = of(net.minecraft.server.world.ChunkTicketType.ENDER_PEARL);
    public static final ChunkTicketType<Integer> POST_TELEPORT = of(net.minecraft.server.world.ChunkTicketType.POST_TELEPORT);
    public static final ChunkTicketType<ChunkPos> UNKNOWN = of(net.minecraft.server.world.ChunkTicketType.UNKNOWN);

    private final net.minecraft.server.world.ChunkTicketType<T> ticketType;

    protected ChunkTicketType(net.minecraft.server.world.ChunkTicketType<T> ticketType) {
        this.ticketType = ticketType;
    }

    public static <T> ChunkTicketType<T> of(net.minecraft.server.world.ChunkTicketType<T> ticketType) {
        return new ChunkTicketType<>(ticketType);
    }

    public net.minecraft.server.world.ChunkTicketType<T> getRaw() {
        return ticketType;
    }

    public net.minecraft.server.world.ChunkTicketType<T> toMinecraft() {
        return getRaw();
    }

    public static <T> ChunkTicketType<T> create(String name, Comparator<T> argumentComparator) {
        return of(net.minecraft.server.world.ChunkTicketType.create(name, argumentComparator));
    }

    public static <T> ChunkTicketType<T> create(String name, Comparator<T> argumentComparator, int expiryTicks) {
        return of(net.minecraft.server.world.ChunkTicketType.create(name, argumentComparator, expiryTicks));
    }

    public Comparator<T> getArgumentComparator() {
        return getRaw().getArgumentComparator();
    }

    public long getExpiryTicks() {
        return getRaw().getExpiryTicks();
    }

    @Override
    public String toString() {
        return getRaw().toString();
    }
}

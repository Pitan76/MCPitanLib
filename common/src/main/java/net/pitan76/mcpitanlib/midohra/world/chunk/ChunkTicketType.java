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
    public static final ChunkTicketType<Integer> POST_TELEPORT = of(net.minecraft.server.world.ChunkTicketType.create("mpl_post_teleport", Comparator.comparingInt(Integer::intValue)));
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

    public ChunkTicketType(String name, long expiryTicks) {
        this(_of(name, expiryTicks));
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

    private static <T> net.minecraft.server.world.ChunkTicketType<T> _of(String name, long expiryTicks) {
        net.minecraft.server.world.ChunkTicketType<ChunkPos> type = net.minecraft.server.world.ChunkTicketType.create(name, Comparator.comparingLong(ChunkPos::toLong), (int) expiryTicks);
        return (net.minecraft.server.world.ChunkTicketType<T>) type;
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

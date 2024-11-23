package net.pitan76.mcpitanlib.midohra.util.math;

import java.util.stream.Stream;

public class ChunkPos {
    private final net.minecraft.util.math.ChunkPos pos;

    protected ChunkPos(net.minecraft.util.math.ChunkPos pos) {
        this.pos = pos;
    }

    public static ChunkPos of(net.minecraft.util.math.ChunkPos pos) {
        return new ChunkPos(pos);
    }

    public static ChunkPos of(int x, int z) {
        return of(new net.minecraft.util.math.ChunkPos(x, z));
    }

    public static ChunkPos of(BlockPos pos) {
        return of(new net.minecraft.util.math.ChunkPos(pos.toMinecraft()));
    }

    public static ChunkPos of(long pos) {
        return of(new net.minecraft.util.math.ChunkPos(pos));
    }

    public static ChunkPos fromRegion(int x, int z) {
        return of(x << 5, z << 5);
    }

    public static ChunkPos fromRegionCenter(int x, int z) {
        return of(x - 16, z - 16);
    }

    public net.minecraft.util.math.ChunkPos getRaw() {
        return pos;
    }

    public net.minecraft.util.math.ChunkPos toMinecraft() {
        return getRaw();
    }

    public int getX() {
        return getRaw().x;
    }

    public int getZ() {
        return getRaw().z;
    }

    public int getOffsetX(int offsetX) {
        return getStartX() + offsetX;
    }

    public int getOffsetZ(int offsetZ) {
        return getStartX() + offsetZ;
    }

    public int getStartX() {
        return getRaw().getStartX();
    }

    public int getStartZ() {
        return getRaw().getStartZ();
    }

    public int getEndX() {
        return getRaw().getEndX();
    }

    public int getEndZ() {
        return getRaw().getEndZ();
    }

    public int getCenterX() {
        return getStartX() + 8;
    }

    public int getCenterZ() {
        return getStartZ() + 8;
    }

    public int getRegionX() {
        return getRaw().getRegionX();
    }

    public int getRegionZ() {
        return getRaw().getRegionZ();
    }

    public int getRegionRelativeX() {
        return getRaw().getRegionRelativeX();
    }

    public int getRegionRelativeZ() {
        return getRaw().getRegionRelativeZ();
    }

    public BlockPos getStartPos() {
        return BlockPos.of(getRaw().getStartPos());
    }

    public BlockPos getCenterAtY(int y) {
        return BlockPos.of(getCenterX(), y, getCenterZ());
    }

    public BlockPos getBlockPos(int offsetX, int y, int offsetZ) {
        return BlockPos.of(getOffsetX(offsetX), y, getOffsetZ(offsetZ));
    }

    public long toLong() {
        return getRaw().toLong();
    }

    @Override
    public String toString() {
        return getRaw().toString();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof ChunkPos && getRaw().equals(((ChunkPos) obj).getRaw());
    }

    @Override
    public int hashCode() {
        return getRaw().hashCode();
    }

    public int getChebyshevDistance(ChunkPos pos) {
        return Math.max(Math.abs(getX() - pos.getX()), Math.abs(getZ() - pos.getZ()));
    }

    public int getChebyshevDistance(int x, int z) {
        return getChebyshevDistance(of(x, z));
    }

    public int getSquaredDistance(ChunkPos pos) {
        int dx = getX() - pos.getX();
        int dz = getZ() - pos.getZ();
        return dx * dx + dz * dz;
    }

    public int getSquaredDistance(long pos) {
        return getSquaredDistance(of(pos));
    }

    public static Stream<ChunkPos> stream(ChunkPos center, int radius) {
        return net.minecraft.util.math.ChunkPos.stream(center.getRaw(), radius).map(ChunkPos::of);
    }

    public static Stream<ChunkPos> stream(final ChunkPos pos1, final ChunkPos pos2) {
        return net.minecraft.util.math.ChunkPos.stream(pos1.getRaw(), pos2.getRaw()).map(ChunkPos::of);
    }
}

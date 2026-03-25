package net.pitan76.mcpitanlib.midohra.util.math;

import java.util.stream.Stream;

public class ChunkPos {
    private final net.minecraft.world.level.ChunkPos pos;

    protected ChunkPos(net.minecraft.world.level.ChunkPos pos) {
        this.pos = pos;
    }

    public static ChunkPos of(net.minecraft.world.level.ChunkPos pos) {
        return new ChunkPos(pos);
    }

    public static ChunkPos of(int x, int z) {
        return of(new net.minecraft.world.level.ChunkPos(x, z));
    }

    public static ChunkPos of(BlockPos pos) {
        return of(new net.minecraft.world.level.ChunkPos(pos.toMinecraft()));
    }

    public static ChunkPos of(long pos) {
        return of(new net.minecraft.world.level.ChunkPos(pos));
    }

    public static ChunkPos fromRegion(int x, int z) {
        return of(net.minecraft.world.level.ChunkPos.minFromRegion(x, z));
    }

    public static ChunkPos fromRegionCenter(int x, int z) {
        return of(net.minecraft.world.level.ChunkPos.maxFromRegion(x, z));
    }

    public net.minecraft.world.level.ChunkPos getRaw() {
        return pos;
    }

    public net.minecraft.world.level.ChunkPos toMinecraft() {
        return getRaw();
    }

    public int getX() {
        return getRaw().x;
    }

    public int getZ() {
        return getRaw().z;
    }

    public int getOffsetX(int offsetX) {
        return getRaw().getBlockX(offsetX);
    }

    public int getOffsetZ(int offsetZ) {
        return getRaw().getBlockZ(offsetZ);
    }

    public int getStartX() {
        return getRaw().getMinBlockX();
    }

    public int getStartZ() {
        return getRaw().getMinBlockZ();
    }

    public int getEndX() {
        return getRaw().getMaxBlockX();
    }

    public int getEndZ() {
        return getRaw().getMaxBlockZ();
    }

    public int getCenterX() {
        return getRaw().getMiddleBlockX();
    }

    public int getCenterZ() {
        return getRaw().getMiddleBlockZ();
    }

    public int getRegionX() {
        return getRaw().getRegionX();
    }

    public int getRegionZ() {
        return getRaw().getRegionZ();
    }

    public int getRegionRelativeX() {
        return getRaw().getRegionLocalX();
    }

    public int getRegionRelativeZ() {
        return getRaw().getRegionLocalZ();
    }

    public BlockPos getStartPos() {
        return BlockPos.of(getRaw().getWorldPosition());
    }

    public BlockPos getCenterAtY(int y) {
        return BlockPos.of(getRaw().getMiddleBlockPosition(y));
    }

    public BlockPos getBlockPos(int offsetX, int y, int offsetZ) {
        return BlockPos.of(getRaw().getBlockAt(offsetX, y, offsetZ));
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
        return getRaw().getChessboardDistance(pos.getRaw());
    }

    public int getChebyshevDistance(int x, int z) {
        return getRaw().getChessboardDistance(x, z);
    }

    public int getSquaredDistance(ChunkPos pos) {
        return getRaw().distanceSquared(pos.getRaw());
    }

    public int getSquaredDistance(long pos) {
        return getRaw().distanceSquared(pos);
    }

    public static Stream<ChunkPos> stream(ChunkPos center, int radius) {
        return net.minecraft.world.level.ChunkPos.rangeClosed(center.getRaw(), radius).map(ChunkPos::of);
    }

    public static Stream<ChunkPos> stream(final ChunkPos pos1, final ChunkPos pos2) {
        return net.minecraft.world.level.ChunkPos.rangeClosed(pos1.getRaw(), pos2.getRaw()).map(ChunkPos::of);
    }
}

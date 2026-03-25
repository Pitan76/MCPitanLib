package net.pitan76.mcpitanlib.midohra.util.math;

import net.minecraft.core.Vec3i;

import java.util.Iterator;

public class BlockPos {
    private final net.minecraft.core.BlockPos blockPos;

    protected BlockPos(net.minecraft.core.BlockPos blockPos) {
        this.blockPos = blockPos;
    }

    public static BlockPos of(net.minecraft.core.BlockPos blockPos) {
        return new BlockPos(blockPos);
    }

    public static BlockPos of(int x, int y, int z) {
        return new BlockPos(new net.minecraft.core.BlockPos(x, y, z));
    }

    public int getX() {
        return blockPos.getX();
    }

    public int getY() {
        return blockPos.getY();
    }

    public int getZ() {
        return blockPos.getZ();
    }

    public net.minecraft.core.BlockPos toMinecraft() {
        return blockPos;
    }

    public net.minecraft.core.BlockPos toRaw() {
        return toMinecraft();
    }

    public BlockPos add(int x, int y, int z) {
        return new BlockPos(blockPos.offset(x, y, z));
    }

    public BlockPos add(BlockPos pos) {
        return new BlockPos(blockPos.offset(pos.blockPos));
    }

    public BlockPos subtract(int x, int y, int z) {
        return new BlockPos(blockPos.subtract(new Vec3i(x, y, z)));
    }

    public BlockPos subtract(BlockPos pos) {
        return new BlockPos(blockPos.subtract(pos.blockPos));
    }

    public BlockPos up() {
        return new BlockPos(blockPos.above());
    }

    public BlockPos down() {
        return new BlockPos(blockPos.below());
    }

    public BlockPos north() {
        return new BlockPos(blockPos.north());
    }

    public BlockPos south() {
        return new BlockPos(blockPos.south());
    }

    public BlockPos east() {
        return new BlockPos(blockPos.east());
    }

    public BlockPos west() {
        return new BlockPos(blockPos.west());
    }

    public BlockPos offset(Direction direction, int amount) {
        return new BlockPos(blockPos.relative(direction.toMinecraft(), amount));
    }

    public BlockPos offset(Direction direction) {
        return new BlockPos(blockPos.relative(direction.toMinecraft()));
    }

    public ChunkPos toChunkPos() {
        return ChunkPos.of(this);
    }

    @Override
    public int hashCode() {
        return blockPos.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        BlockPos pos = (BlockPos) obj;
        return blockPos.equals(pos.blockPos);
    }

    public static Iterable<BlockPos> iterate(BlockPos start, BlockPos end) {
        return () -> new Iterator<>() {
            private final Iterator<net.minecraft.core.BlockPos> rawIterator = net.minecraft.core.BlockPos.betweenClosed(start.toRaw(), end.toRaw()).iterator();

            @Override
            public boolean hasNext() {
                return rawIterator.hasNext();
            }

            @Override
            public BlockPos next() {
                return new BlockPos(rawIterator.next());
            }
        };
    }

    public long asLong() {
        return blockPos.asLong();
    }

    public Vector3i toVector3i() {
        return new Vector3i(getX(), getY(), getZ());
    }

    public Vector3d toVector3d() {
        return new Vector3d(getX(), getY(), getZ());
    }

    public Vector3d toCenterVector3d() {
        return new Vector3d(getX() + 0.5, getY() + 0.5, getZ() + 0.5);
    }
}
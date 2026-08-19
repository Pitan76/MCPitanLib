package net.pitan76.mcpitanlib.midohra.util.math;

import net.minecraft.util.math.Vec3i;

import java.util.Iterator;

public class BlockPos {
    private final net.minecraft.util.math.BlockPos blockPos;

    protected BlockPos(net.minecraft.util.math.BlockPos blockPos) {
        this.blockPos = blockPos;
    }

    public static BlockPos of(net.minecraft.util.math.BlockPos blockPos) {
        return new BlockPos(blockPos);
    }

    public static BlockPos of(int x, int y, int z) {
        return new BlockPos(new net.minecraft.util.math.BlockPos(x, y, z));
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

    public net.minecraft.util.math.BlockPos toMinecraft() {
        return blockPos;
    }

    public net.minecraft.util.math.BlockPos toRaw() {
        return toMinecraft();
    }

    public BlockPos add(int x, int y, int z) {
        return new BlockPos(blockPos.add(x, y, z));
    }

    public BlockPos add(BlockPos pos) {
        return new BlockPos(blockPos.add(pos.blockPos));
    }

    public BlockPos subtract(int x, int y, int z) {
        return new BlockPos(blockPos.subtract(new Vec3i(x, y, z)));
    }

    public BlockPos subtract(BlockPos pos) {
        return new BlockPos(blockPos.subtract(pos.blockPos));
    }

    public BlockPos up() {
        return new BlockPos(blockPos.up());
    }

    public BlockPos down() {
        return new BlockPos(blockPos.down());
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
        return new BlockPos(blockPos.offset(direction.toMinecraft(), amount));
    }

    public BlockPos offset(Direction direction) {
        return new BlockPos(blockPos.offset(direction.toMinecraft()));
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
            private final Iterator<net.minecraft.util.math.BlockPos> rawIterator = net.minecraft.util.math.BlockPos.iterate(start.toRaw(), end.toRaw()).iterator();

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

    public int getManhattanDistance(Vector3i other) {
        return blockPos.getManhattanDistance(other.toMinecraft());
    }

    public int getManhattanDistance(BlockPos other) {
        return blockPos.getManhattanDistance(other.toMinecraft());
    }

    public BlockPos up(int n) {
        return new BlockPos(blockPos.up(n));
    }

    public BlockPos down(int n) {
        return new BlockPos(blockPos.down(n));
    }

    public BlockPos north(int n) {
        return new BlockPos(blockPos.north(n));
    }

    public BlockPos south(int n) {
        return new BlockPos(blockPos.south(n));
    }

    public BlockPos east(int n) {
        return new BlockPos(blockPos.east(n));
    }

    public BlockPos west(int n) {
        return new BlockPos(blockPos.west(n));
    }

    public BlockPos[] getAllFaces() {
        return new BlockPos[] {
                up(), down(), north(), south(), east(), west()
        };
    }

    public BlockPos[] getAllFaces(int n) {
        return new BlockPos[] {
                up(n), down(n), north(n), south(n), east(n), west(n)
        };
    }

    public BlockPos[] getVerticalFaces() {
        return new BlockPos[] {
                up(), down()
        };
    }

    public BlockPos[] getHorizontalFaces() {
        return new BlockPos[] {
                north(), south(), east(), west()
        };
    }
}

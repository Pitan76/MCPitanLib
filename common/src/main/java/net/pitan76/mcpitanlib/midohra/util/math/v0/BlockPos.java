package net.pitan76.mcpitanlib.midohra.util.math.v0;

import net.minecraft.util.math.Vec3i;
import net.pitan76.mcpitanlib.midohra.util.math.Direction;

public class BlockPos extends net.minecraft.util.math.BlockPos {
    private final net.minecraft.util.math.BlockPos blockPos;

    protected BlockPos(net.minecraft.util.math.BlockPos blockPos) {
        super(blockPos.getX(), blockPos.getY(), blockPos.getZ());
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

    @Override
    public BlockPos add(Vec3i vec3i) {
        return new BlockPos(blockPos.add(vec3i));
    }
}

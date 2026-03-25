package net.pitan76.mcpitanlib.midohra.util.math.v0;

import net.minecraft.core.Vec3i;
import net.pitan76.mcpitanlib.midohra.util.math.Direction;

public class BlockPos extends net.minecraft.core.BlockPos {
    private final net.minecraft.core.BlockPos blockPos;

    protected BlockPos(net.minecraft.core.BlockPos blockPos) {
        super(blockPos.getX(), blockPos.getY(), blockPos.getZ());
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

    public BlockPos offset(int x, int y, int z) {
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

    public BlockPos above() {
        return new BlockPos(blockPos.above());
    }

    public BlockPos below() {
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

    @Override
    public BlockPos offset(Vec3i vec3i) {
        return new BlockPos(blockPos.offset(vec3i));
    }
}

package net.pitan76.mcpitanlib.api.util;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class BlockEntityUtil {

    public static BlockEntity getBlockEntity(World world, BlockPos pos) {
        return WorldUtil.getBlockEntity(world, pos);
    }

    public static NbtCompound getBlockEntityNbt(@Nullable World world, BlockEntity blockEntity) {
        if (world == null)
            world = getWorld(blockEntity);
        if (world == null)
            return NbtUtil.create();

        return blockEntity.createNbt();
    }

    public static NbtCompound getBlockEntityNbt(BlockEntity blockEntity) {
        return getBlockEntityNbt(null, blockEntity);
    }

    public static World getWorld(BlockEntity blockEntity) {
        return blockEntity.getWorld();
    }

    public static boolean hasWorld(BlockEntity blockEntity) {
        return blockEntity.hasWorld();
    }

    public static BlockPos getPos(BlockEntity blockEntity) {
        return blockEntity.getPos();
    }

    public static BlockState getCachedState(BlockEntity blockEntity) {
        return blockEntity.getCachedState();
    }

    public static BlockState getBlockState(BlockEntity blockEntity) {
        return getWorld(blockEntity).getBlockState(getPos(blockEntity));
    }

    public static Block getBlock(BlockEntity blockEntity) {
        return getBlockState(blockEntity).getBlock();
    }

    public static void markRemoved(BlockEntity blockEntity) {
        blockEntity.markRemoved();
    }

    public static void markDirty(BlockEntity blockEntity) {
        blockEntity.markDirty();
    }
}

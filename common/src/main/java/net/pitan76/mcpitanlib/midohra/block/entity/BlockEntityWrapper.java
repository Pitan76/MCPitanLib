package net.pitan76.mcpitanlib.midohra.block.entity;

import net.pitan76.mcpitanlib.api.registry.CompatRegistryLookup;
import net.pitan76.mcpitanlib.api.util.BlockEntityUtil;
import net.pitan76.mcpitanlib.api.util.RegistryLookupUtil;
import net.pitan76.mcpitanlib.midohra.block.BlockState;
import net.pitan76.mcpitanlib.midohra.block.BlockWrapper;
import net.pitan76.mcpitanlib.midohra.nbt.NbtCompound;
import net.pitan76.mcpitanlib.midohra.util.math.BlockPos;
import net.pitan76.mcpitanlib.midohra.world.World;

public class BlockEntityWrapper {
    private final net.minecraft.block.entity.BlockEntity blockEntity;

    public static final BlockEntityWrapper EMPTY = new BlockEntityWrapper(null);

    protected BlockEntityWrapper(net.minecraft.block.entity.BlockEntity blockEntity) {
        this.blockEntity = blockEntity;
    }

    public static BlockEntityWrapper of(net.minecraft.block.entity.BlockEntity blockEntity) {
        return new BlockEntityWrapper(blockEntity);
    }

    public static BlockEntityWrapper of() {
        return EMPTY;
    }

    public net.minecraft.block.entity.BlockEntity get() {
        return blockEntity;
    }

    public BlockPos getPos() {
        return BlockPos.of(get().getPos());
    }

    public boolean isExist() {
        return !isEmpty();
    }

    public boolean isEmpty() {
        return this == EMPTY || get() == null;
    }

    public boolean isRemoved() {
        return get().isRemoved();
    }

    public void markDirty() {
        BlockEntityUtil.markDirty(get());
    }

    public World getWorld() {
        return World.of(BlockEntityUtil.getWorld(get()));
    }

    public BlockWrapper getBlock() {
        return BlockWrapper.of(BlockEntityUtil.getBlock(get()));
    }


    public BlockState getBlockState() {
        return BlockState.of(BlockEntityUtil.getBlockState(get()));
    }

    public BlockState getCachedState() {
        return BlockState.of(BlockEntityUtil.getCachedState(get()));
    }

    public NbtCompound createNbt() {
        return NbtCompound.of(BlockEntityUtil.getBlockEntityNbt(get()));
    }

    public void markRemoved() {
        BlockEntityUtil.markRemoved(get());
    }

    public void writeNbt(NbtCompound nbt, CompatRegistryLookup registryLookup) {
        BlockEntityUtil.writeNbt(get(), nbt.toMinecraft(), registryLookup);
    }

    public void readNbt(NbtCompound nbt, CompatRegistryLookup registryLookup) {
        BlockEntityUtil.readNbt(get(), nbt.toMinecraft(), registryLookup);
    }

    public void writeNbt(NbtCompound nbt) {
        writeNbt(nbt, RegistryLookupUtil.getRegistryLookup(get()));
    }

    public void readNbt(NbtCompound nbt) {
        readNbt(nbt, RegistryLookupUtil.getRegistryLookup(get()));
    }
}

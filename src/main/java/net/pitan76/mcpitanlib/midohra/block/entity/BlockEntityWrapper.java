package net.pitan76.mcpitanlib.midohra.block.entity;

import net.pitan76.mcpitanlib.api.registry.CompatRegistryLookup;
import net.pitan76.mcpitanlib.api.tile.CompatBlockEntity;
import net.pitan76.mcpitanlib.api.util.BlockEntityUtil;
import net.pitan76.mcpitanlib.api.util.RegistryLookupUtil;
import net.pitan76.mcpitanlib.midohra.block.BlockState;
import net.pitan76.mcpitanlib.midohra.block.BlockWrapper;
import net.pitan76.mcpitanlib.midohra.easybuilder.built.BuiltBlockEntity;
import net.pitan76.mcpitanlib.midohra.nbt.NbtCompound;
import net.pitan76.mcpitanlib.midohra.util.math.BlockPos;
import net.pitan76.mcpitanlib.midohra.world.World;

import java.util.Optional;

public class BlockEntityWrapper {
    private final net.minecraft.world.level.block.entity.BlockEntity blockEntity;

    public static final BlockEntityWrapper EMPTY = new BlockEntityWrapper(null);

    protected BlockEntityWrapper() {
        this.blockEntity = null;
    }

    protected BlockEntityWrapper(net.minecraft.world.level.block.entity.BlockEntity blockEntity) {
        this.blockEntity = blockEntity;
    }

    public static BlockEntityWrapper of(net.minecraft.world.level.block.entity.BlockEntity blockEntity) {
        return new BlockEntityWrapper(blockEntity);
    }

    public static BlockEntityWrapper of() {
        return EMPTY;
    }

    public net.minecraft.world.level.block.entity.BlockEntity get() {
        return blockEntity;
    }

    public BlockPos getPos() {
        return BlockPos.of(get().getBlockPos());
    }

    public boolean isPresent() {
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

    @Override
    public int hashCode() {
        return get() != null ? get().hashCode() : 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        BlockEntityWrapper blockEntity = (BlockEntityWrapper) obj;
        return get() == blockEntity.get();
    }

    public static BlockEntityWrapper of(BlockPos pos, World world) {
        net.minecraft.world.level.block.entity.BlockEntity blockEntity = BlockEntityUtil.getBlockEntity(world.toMinecraft(), pos.toMinecraft());
        return of(blockEntity);
    }

    public Optional<CompatBlockEntity> toCompatBlockEntity() {
        if (get() instanceof CompatBlockEntity) {
            return Optional.of((CompatBlockEntity) get());
        }
        return Optional.empty();
    }

    public Optional<BuiltBlockEntity> toBuiltBlockEntity() {
        if (get() instanceof BuiltBlockEntity) {
            return Optional.of((BuiltBlockEntity) get());
        }
        return Optional.empty();
    }
}

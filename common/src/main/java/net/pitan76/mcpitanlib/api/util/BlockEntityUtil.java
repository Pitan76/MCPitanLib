package net.pitan76.mcpitanlib.api.util;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.storage.TagValueOutput;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.util.ProblemReporter;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.pitan76.mcpitanlib.api.registry.CompatRegistryLookup;
import net.pitan76.mcpitanlib.core.mc1216.NbtDataConverter;
import org.jetbrains.annotations.Nullable;

public class BlockEntityUtil {

    public static BlockEntity getBlockEntity(Level world, BlockPos pos) {
        return WorldUtil.getBlockEntity(world, pos);
    }

    public static CompoundTag getBlockEntityNbt(@Nullable Level world, BlockEntity blockEntity) {
        if (world == null)
            world = getWorld(blockEntity);
        if (world == null)
            return NbtUtil.create();

        return blockEntity.saveWithoutMetadata(world.registryAccess());
    }

    public static CompoundTag getBlockEntityNbt(BlockEntity blockEntity) {
        return getBlockEntityNbt(null, blockEntity);
    }

    public static Level getWorld(BlockEntity blockEntity) {
        return blockEntity.getLevel();
    }

    public static boolean hasWorld(BlockEntity blockEntity) {
        return blockEntity.hasLevel();
    }

    public static BlockPos getPos(BlockEntity blockEntity) {
        return blockEntity.getBlockPos();
    }

    public static BlockState getCachedState(BlockEntity blockEntity) {
        return blockEntity.getBlockState();
    }

    public static BlockState getBlockState(BlockEntity blockEntity) {
        return getWorld(blockEntity).getBlockState(getPos(blockEntity));
    }

    public static Block getBlock(BlockEntity blockEntity) {
        return getBlockState(blockEntity).getBlock();
    }

    public static void markRemoved(BlockEntity blockEntity) {
        blockEntity.setRemoved();
    }

    public static void markDirty(BlockEntity blockEntity) {
        blockEntity.setChanged();
    }

    public static BlockEntityType<?> getType(BlockEntity blockEntity) {
        return blockEntity.getType();
    }

    public static void readNbt(BlockEntity blockEntity, CompoundTag nbt, CompatRegistryLookup registryLookup) {
        ValueInput view = NbtDataConverter.nbt2readData(nbt, registryLookup);
        blockEntity.loadCustomOnly(view);
    }

    public static void writeNbt(BlockEntity blockEntity, CompoundTag nbt, CompatRegistryLookup registryLookup) {
        TagValueOutput view = TagValueOutput.createWithoutContext(ProblemReporter.DISCARDING);
        blockEntity.saveCustomOnly(view);
        NbtDataConverter.data2nbt(view, nbt);
    }

    public static void read(BlockEntity blockEntity, CompoundTag nbt, CompatRegistryLookup registryLookup) {
        ValueInput view = NbtDataConverter.nbt2readData(nbt, registryLookup);
        blockEntity.loadWithComponents(view);
    }

    public static CompoundTag createNbt(BlockEntity blockEntity, CompatRegistryLookup registryLookup) {
        return blockEntity.saveWithoutMetadata(registryLookup.getRegistryLookup());
    }

    public static void setStackNbt(BlockEntity blockEntity, ItemStack stack, CompatRegistryLookup registryLookup) {
        blockEntity.applyComponentsFromItemStack(stack);
    }

    public static ServerLevel getServerWorld(BlockEntity blockEntity) {
        return (ServerLevel) getWorld(blockEntity);
    }

    public static void writeToStack(ItemStack stack, BlockEntity blockEntity, CompatRegistryLookup registryLookup) {
        CompoundTag nbt = blockEntity.saveCustomOnly(registryLookup.getRegistryLookup());
        if (!NbtUtil.has(nbt, "id"))
            NbtUtil.putString(nbt, "id", BlockEntityTypeUtil.toID(BlockEntityUtil.getType(blockEntity)).toString());

        BlockEntityDataUtil.setBlockEntityNbt(stack, nbt);

        blockEntity.collectComponents().forEach(stack::set);
    }

    public static boolean isRemoved(BlockEntity blockEntity) {
        return blockEntity.isRemoved();
    }
}

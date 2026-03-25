package net.pitan76.mcpitanlib.api.util;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.component.TypedEntityData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.pitan76.mcpitanlib.api.event.nbt.ReadNbtArgs;
import net.pitan76.mcpitanlib.api.event.nbt.WriteNbtArgs;
import net.pitan76.mcpitanlib.api.tile.CompatBlockEntity;

public class BlockEntityDataUtil {
    public static CompoundTag getBlockEntityNbt(ItemStack stack) {
        if (!hasBlockEntityNbt(stack)) return NbtUtil.create();
        TypedEntityData<BlockEntityType<?>> data = stack.get(DataComponents.BLOCK_ENTITY_DATA);
        CompoundTag nbt = data.copyTagWithoutId();

        String id = BlockEntityTypeUtil.toCompatID(data.type()).toString();
        NbtUtil.putString(nbt, "id", id);
        return nbt;
    }

    public static void setBlockEntityNbt(ItemStack stack, CompoundTag nbt) {
        if (!nbt.contains("id")) return;

        BlockEntityType<?> type = BlockEntityTypeUtil.fromId(CompatIdentifier.of(NbtUtil.getString(nbt, "id")));
        stack.set(DataComponents.BLOCK_ENTITY_DATA, TypedEntityData.of(type, nbt));
    }

    public static boolean hasBlockEntityNbt(ItemStack stack) {
        return stack.get(DataComponents.BLOCK_ENTITY_DATA) != null;
    }

    public static void readCompatBlockEntityNbtFromStack(ItemStack stack, CompatBlockEntity blockEntity) {
        CompoundTag nbt = getBlockEntityNbt(stack);
        blockEntity.readNbt(new ReadNbtArgs(nbt, RegistryLookupUtil.getRegistryLookup(blockEntity)));
    }

    public static void writeCompatBlockEntityNbtToStack(ItemStack stack, CompatBlockEntity blockEntity) {
        CompoundTag nbt = getBlockEntityNbt(stack);
        blockEntity.writeNbt(new WriteNbtArgs(nbt, RegistryLookupUtil.getRegistryLookup(blockEntity)));
        NbtUtil.putString(nbt, "id", BlockEntityTypeUtil.toCompatID(blockEntity.getType()).toString());
        setBlockEntityNbt(stack, nbt);
    }

    public static void removeBlockEntityNbt(ItemStack stack) {
        stack.remove(DataComponents.BLOCK_ENTITY_DATA);
    }
}

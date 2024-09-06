package net.pitan76.mcpitanlib.api.util;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.pitan76.mcpitanlib.api.event.nbt.ReadNbtArgs;
import net.pitan76.mcpitanlib.api.event.nbt.WriteNbtArgs;
import net.pitan76.mcpitanlib.api.tile.CompatBlockEntity;

public class BlockEntityDataUtil {
    public static NbtCompound getBlockEntityNbt(ItemStack stack) {
        if (!stack.contains(DataComponentTypes.BLOCK_ENTITY_DATA)) return NbtUtil.create();
        NbtComponent component = stack.get(DataComponentTypes.BLOCK_ENTITY_DATA);
        if (component == null) return NbtUtil.create();

        NbtCompound nbt = component.copyNbt();
        if (nbt == null) return NbtUtil.create();
        return nbt;
    }

    public static void setBlockEntityNbt(ItemStack stack, NbtCompound nbt) {
        stack.set(DataComponentTypes.BLOCK_ENTITY_DATA, NbtComponent.of(nbt));
    }

    public static boolean hasBlockEntityNbt(ItemStack stack) {
        return stack.contains(DataComponentTypes.BLOCK_ENTITY_DATA);
    }

    public static void readCompatBlockEntityNbtFromStack(ItemStack stack, CompatBlockEntity blockEntity) {
        NbtCompound nbt = getBlockEntityNbt(stack);
        blockEntity.readNbt(new ReadNbtArgs(nbt));
    }

    public static void writeCompatBlockEntityNbtToStack(ItemStack stack, CompatBlockEntity blockEntity) {
        NbtCompound nbt = getBlockEntityNbt(stack);
        blockEntity.writeNbt(new WriteNbtArgs(nbt));
        setBlockEntityNbt(stack, nbt);
    }

    public static void removeBlockEntityNbt(ItemStack stack) {
        stack.remove(DataComponentTypes.BLOCK_ENTITY_DATA);
    }
}

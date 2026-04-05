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
        return stack.get(DataComponentTypes.BLOCK_ENTITY_DATA) != null;
    }

    public static void readCompatBlockEntityNbtFromStack(ItemStack stack, CompatBlockEntity blockEntity) {
        NbtCompound nbt = getBlockEntityNbt(stack);
        blockEntity.readNbt(new ReadNbtArgs(nbt, RegistryLookupUtil.getRegistryLookup(blockEntity)));
    }

    public static void writeCompatBlockEntityNbtToStack(ItemStack stack, CompatBlockEntity blockEntity) {
        NbtCompound nbt = getBlockEntityNbt(stack);
        blockEntity.writeNbt(new WriteNbtArgs(nbt, RegistryLookupUtil.getRegistryLookup(blockEntity)));
        NbtUtil.set(nbt, "id", BlockEntityTypeUtil.toID(blockEntity.getType()).toString());
        setBlockEntityNbt(stack, nbt);
    }

    public static void removeBlockEntityNbt(ItemStack stack) {
        stack.remove(DataComponentTypes.BLOCK_ENTITY_DATA);
    }

    public static boolean hasBlockEntityNbt(net.pitan76.mcpitanlib.midohra.item.ItemStack stack) {
        return hasBlockEntityNbt(stack.toMinecraft());
    }

    public static net.pitan76.mcpitanlib.midohra.nbt.NbtCompound getBlockEntityNbt(net.pitan76.mcpitanlib.midohra.item.ItemStack stack) {
        return net.pitan76.mcpitanlib.midohra.nbt.NbtCompound.of(getBlockEntityNbt(stack.toMinecraft()));
    }

    public static void setBlockEntityNbt(net.pitan76.mcpitanlib.midohra.item.ItemStack stack, net.pitan76.mcpitanlib.midohra.nbt.NbtCompound nbt) {
        setBlockEntityNbt(stack.toMinecraft(), nbt.toMinecraft());
    }

    public static void removeBlockEntityNbt(net.pitan76.mcpitanlib.midohra.item.ItemStack stack) {
        removeBlockEntityNbt(stack.toMinecraft());
    }

    public static void readCompatBlockEntityNbtFromStack(net.pitan76.mcpitanlib.midohra.item.ItemStack stack, CompatBlockEntity blockEntity) {
        readCompatBlockEntityNbtFromStack(stack.toMinecraft(), blockEntity);
    }

    public static void writeCompatBlockEntityNbtToStack(net.pitan76.mcpitanlib.midohra.item.ItemStack stack, CompatBlockEntity blockEntity) {
        writeCompatBlockEntityNbtToStack(stack.toMinecraft(), blockEntity);
    }
}

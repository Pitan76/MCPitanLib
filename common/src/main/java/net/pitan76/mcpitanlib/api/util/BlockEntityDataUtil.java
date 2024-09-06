package net.pitan76.mcpitanlib.api.util;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.pitan76.mcpitanlib.api.event.nbt.ReadNbtArgs;
import net.pitan76.mcpitanlib.api.event.nbt.WriteNbtArgs;
import net.pitan76.mcpitanlib.api.tile.CompatBlockEntity;

public class BlockEntityDataUtil {
    public static NbtCompound getBlockEntityNbt(ItemStack stack) {
        if (!hasBlockEntityNbt(stack)) return NbtUtil.create();

        return stack.getSubNbt("BlockEntityTag");
    }

    public static void setBlockEntityNbt(ItemStack stack, NbtCompound nbt) {
        stack.setSubNbt("BlockEntityTag", nbt);
    }

    public static boolean hasBlockEntityNbt(ItemStack stack) {
        if (!stack.hasNbt()) return false;
        if (!NbtUtil.has(stack.getNbt(), "BlockEntityTag")) return false;

        return true;
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
        stack.removeSubNbt("BlockEntityTag");
    }
}

package net.pitan76.mcpitanlib.api.util;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.pitan76.mcpitanlib.api.event.nbt.ReadNbtArgs;
import net.pitan76.mcpitanlib.api.event.nbt.WriteNbtArgs;
import net.pitan76.mcpitanlib.api.tile.CompatBlockEntity;

public class BlockEntityDataUtil {
    public static NbtCompound getBlockEntityNbt(ItemStack stack) {
        if (!hasBlockEntityNbt(stack)) return NbtUtil.create();

        return stack.getSubTag("BlockEntityTag");
    }

    public static void setBlockEntityNbt(ItemStack stack, NbtCompound nbt) {
        stack.putSubTag("BlockEntityTag", nbt);
    }

    public static boolean hasBlockEntityNbt(ItemStack stack) {
        if (!stack.hasTag()) return false;
        if (!NbtUtil.has(stack.getTag(), "BlockEntityTag")) return false;

        return true;
    }

    public static void readCompatBlockEntityNbtFromStack(ItemStack stack, CompatBlockEntity blockEntity) {
        NbtCompound nbt = getBlockEntityNbt(stack);
        blockEntity.readNbt(new ReadNbtArgs(nbt));
    }

    public static void writeCompatBlockEntityNbtToStack(ItemStack stack, CompatBlockEntity blockEntity) {
        NbtCompound nbt = getBlockEntityNbt(stack);
        blockEntity.writeNbt(new WriteNbtArgs(nbt));
        NbtUtil.set(nbt, "id", BlockEntityTypeUtil.toID(blockEntity.getType()).toString());
        setBlockEntityNbt(stack, nbt);
    }

    public static void removeBlockEntityNbt(ItemStack stack) {
        stack.removeSubTag("BlockEntityTag");
    }
}

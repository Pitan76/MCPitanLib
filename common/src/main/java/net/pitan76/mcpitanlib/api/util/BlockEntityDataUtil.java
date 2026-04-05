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

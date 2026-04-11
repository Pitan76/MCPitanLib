package net.pitan76.mcpitanlib.api.util.nbt;

import net.minecraft.world.ContainerHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.NonNullList;
import net.pitan76.mcpitanlib.api.event.nbt.ReadNbtArgs;
import net.pitan76.mcpitanlib.api.event.nbt.WriteNbtArgs;
import net.pitan76.mcpitanlib.api.util.collection.ItemStackList;

public class InvRWUtil {
    public static void putInv(WriteNbtArgs args, NonNullList<ItemStack> stacks) {
        ContainerHelper.saveAllItems(args.view, stacks);
    }

    public static void getInv(ReadNbtArgs args, NonNullList<ItemStack> stacks) {
        ContainerHelper.loadAllItems(args.view, stacks);
    }

    public static void putInv(WriteNbtArgs args, ItemStackList stacks) {
        putInv(args, (NonNullList<ItemStack>) stacks);
    }

    public static void getInv(ReadNbtArgs args, ItemStackList stacks) {
        getInv(args, (NonNullList<ItemStack>) stacks);
    }
}

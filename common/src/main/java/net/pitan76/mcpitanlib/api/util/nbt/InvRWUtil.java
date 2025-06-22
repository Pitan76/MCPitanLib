package net.pitan76.mcpitanlib.api.util.nbt;

import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;
import net.pitan76.mcpitanlib.api.event.nbt.ReadNbtArgs;
import net.pitan76.mcpitanlib.api.event.nbt.WriteNbtArgs;
import net.pitan76.mcpitanlib.api.util.collection.ItemStackList;

public class InvRWUtil {
    public static void putInv(WriteNbtArgs args, DefaultedList<ItemStack> stacks) {
        Inventories.writeData(args.view, stacks);
    }

    public static void getInv(ReadNbtArgs args, DefaultedList<ItemStack> stacks) {
        Inventories.readData(args.view, stacks);
    }

    public static void putInv(WriteNbtArgs args, ItemStackList stacks) {
        putInv(args, (DefaultedList<ItemStack>) stacks);
    }

    public static void getInv(ReadNbtArgs args, ItemStackList stacks) {
        getInv(args, (DefaultedList<ItemStack>) stacks);
    }
}

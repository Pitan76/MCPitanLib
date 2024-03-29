package net.pitan76.mcpitanlib.api.util;

import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;

public class InventoryUtil {
    public static boolean insertItem(ItemStack insertStack, DefaultedList<ItemStack> inventory) {
        return insertItem(insertStack, inventory, false);
    }

    public static boolean insertItem(ItemStack insertStack, DefaultedList<ItemStack> inventory, boolean test) {
        boolean isInserted = false;
        for (int i = 0; i < inventory.size(); i++) {
            ItemStack stack = inventory.get(i);
            if (stack.isEmpty()) {
                if (!test) inventory.set(i, insertStack);
                isInserted = true;
                break;
            } else if (canMergeItems(stack, insertStack)) {
                int j = insertStack.getCount();
                if (!test) stack.increment(j);
                isInserted = j > 0;
                break;
            }
        }
        return isInserted;

    }

    public static boolean canMergeItems(ItemStack first, ItemStack second) {
        if (!first.isOf(second.getItem())) {
            return false;
        }
        if (first.getDamage() != second.getDamage()) {
            return false;
        }
        if (first.getCount() + second.getCount() > first.getMaxCount()) {
            return false;
        }
        return ItemStackUtil.areNbtOrComponentEqual(first, second);
    }

    public static NbtCompound writeNbt(World world, NbtCompound nbt, DefaultedList<ItemStack> stacks) {
        return writeNbt(world, nbt, true, stacks);
    }

    public static NbtCompound writeNbt(World world, NbtCompound nbt, boolean setIfEmpty, DefaultedList<ItemStack> stacks) {
        return Inventories.writeNbt(nbt, stacks, setIfEmpty);
    }

    public static void readNbt(World world, NbtCompound nbt, DefaultedList<ItemStack> stacks) {
        Inventories.readNbt(nbt, stacks);
    }
}

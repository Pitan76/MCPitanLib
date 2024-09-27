package net.pitan76.mcpitanlib.api.util;

import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;
import net.pitan76.mcpitanlib.api.event.nbt.NbtRWArgs;
import net.pitan76.mcpitanlib.api.registry.CompatRegistryLookup;

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

    public static NbtCompound writeNbt(NbtRWArgs args, NbtCompound nbt, DefaultedList<ItemStack> stacks, boolean setIfEmpty) {
        return Inventories.writeNbt(nbt, stacks, setIfEmpty);
    }

    public static void readNbt(NbtRWArgs args, NbtCompound nbt, DefaultedList<ItemStack> stacks) {
        Inventories.readNbt(nbt, stacks);
    }

    public static NbtCompound writeNbt(NbtRWArgs args, DefaultedList<ItemStack> stacks, boolean setIfEmpty) {
        return Inventories.writeNbt(args.getNbt(), stacks, setIfEmpty);
    }

    public static NbtCompound writeNbt(NbtRWArgs args, DefaultedList<ItemStack> stacks) {
        return writeNbt(args, stacks, true);
    }

    public static void readNbt(NbtRWArgs args, DefaultedList<ItemStack> stacks) {
        Inventories.readNbt(args.getNbt(), stacks);
    }

    public static void readNbt(CompatRegistryLookup registryLookup, NbtCompound nbt, DefaultedList<ItemStack> stacks) {
        Inventories.readNbt(nbt, stacks);
    }

    public static NbtCompound writeNbt(CompatRegistryLookup registryLookup, NbtCompound nbt, DefaultedList<ItemStack> stacks, boolean setIfEmpty) {
        return Inventories.writeNbt(nbt, stacks, setIfEmpty);
    }

    public static NbtCompound writeNbt(CompatRegistryLookup registryLookup, NbtCompound nbt, DefaultedList<ItemStack> stacks) {
        return writeNbt(registryLookup, nbt, stacks, true);
    }

    // deprecated
    /**
     * @deprecated Use {@link #writeNbt(NbtRWArgs, DefaultedList)} instead
     */
    @Deprecated
    public static NbtCompound writeNbt(World world, NbtCompound nbt, DefaultedList<ItemStack> stacks) {
        return writeNbt(world, nbt, true, stacks);
    }

    /**
     * @deprecated Use {@link #writeNbt(NbtRWArgs, DefaultedList, boolean)} instead
     */
    @Deprecated
    public static NbtCompound writeNbt(World world, NbtCompound nbt, boolean setIfEmpty, DefaultedList<ItemStack> stacks) {
        return Inventories.writeNbt(nbt, stacks, setIfEmpty);
    }

    /**
     * @deprecated Use {@link #readNbt(NbtRWArgs, DefaultedList)} instead
     */
    @Deprecated
    public static void readNbt(World world, NbtCompound nbt, DefaultedList<ItemStack> stacks) {
        Inventories.readNbt(nbt, stacks);
    }
    // ----

    public static SimpleInventory createSimpleInventory(int size) {
        return new SimpleInventory(size);
    }

    public static void copyToInv(DefaultedList<ItemStack> from, Inventory to) {
        for (int i = 0; i < from.size(); i++) {
            to.setStack(i, from.get(i));
        }
    }

    public static void copyToList(Inventory from, DefaultedList<ItemStack> to) {
        for (int i = 0; i < from.size(); i++) {
            to.set(i, from.getStack(i));
        }
    }
}

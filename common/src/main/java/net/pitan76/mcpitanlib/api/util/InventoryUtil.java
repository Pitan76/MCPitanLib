package net.pitan76.mcpitanlib.api.util;

import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.storage.NbtReadView;
import net.minecraft.storage.NbtWriteView;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.util.ErrorReporter;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;
import net.pitan76.mcpitanlib.api.event.nbt.NbtRWArgs;
import net.pitan76.mcpitanlib.api.event.nbt.ReadNbtArgs;
import net.pitan76.mcpitanlib.api.event.nbt.WriteNbtArgs;
import net.pitan76.mcpitanlib.api.registry.CompatRegistryLookup;
import net.pitan76.mcpitanlib.api.util.nbt.NbtListUtil;
import net.pitan76.mcpitanlib.core.mc1216.NbtDataConverter;

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
        boolean nbtNull = nbt == null;

        if (args instanceof WriteNbtArgs) {
            WriteNbtArgs writeNbtArgs = (WriteNbtArgs) args;
            if (writeNbtArgs.view == null) writeNbtArgs.view = NbtWriteView.create(ErrorReporter.EMPTY);

            Inventories.writeData(writeNbtArgs.view, stacks, setIfEmpty);
            if (!nbtNull)
                NbtUtil.put(nbt, "Items", NbtListUtil.create()); // dummy list to compat with old mod

            NbtDataConverter.data2nbt(writeNbtArgs.view, nbt);
            return nbt;
        }

        WriteView view = NbtDataConverter.nbt2writeData(nbt, args.registryLookup);
        Inventories.writeData(view, stacks, setIfEmpty);
        if (!nbtNull)
            NbtUtil.put(nbt, "Items", NbtListUtil.create()); // dummy list to compat with old mod

        NbtDataConverter.data2nbt(view, nbt);
        return nbt;
    }

    public static void readNbt(NbtRWArgs args, NbtCompound nbt, DefaultedList<ItemStack> stacks) {
        if (args instanceof ReadNbtArgs) {
            ReadNbtArgs readNbtArgs = (ReadNbtArgs) args;
            if (readNbtArgs.view == null) return;

            Inventories.readData(readNbtArgs.view, stacks);
            return;
        }

        ReadView view = NbtDataConverter.nbt2readData(nbt, args.registryLookup);
        Inventories.readData(view, stacks);
    }

    public static NbtCompound writeNbt(NbtRWArgs args, DefaultedList<ItemStack> stacks, boolean setIfEmpty) {
        return writeNbt(args, args.getNbt(), stacks, setIfEmpty);
    }

    public static NbtCompound writeNbt(NbtRWArgs args, DefaultedList<ItemStack> stacks) {
        return writeNbt(args, stacks, true);
    }

    public static void readNbt(NbtRWArgs args, DefaultedList<ItemStack> stacks) {
        readNbt(args, args.getNbt(), stacks);
    }

    public static void readNbt(CompatRegistryLookup registryLookup, NbtCompound nbt, DefaultedList<ItemStack> stacks) {
        ReadView view = NbtDataConverter.nbt2readData(nbt, registryLookup);
        Inventories.readData(view, stacks);
    }

    public static NbtCompound writeNbt(CompatRegistryLookup registryLookup, NbtCompound nbt, DefaultedList<ItemStack> stacks, boolean setIfEmpty) {
        NbtUtil.put(nbt, "Items", NbtListUtil.create());
        WriteView view = NbtDataConverter.nbt2writeData(nbt, registryLookup);
        Inventories.writeData(view, stacks, setIfEmpty);

        NbtDataConverter.data2nbt(view, nbt);

        System.out.println("writeNbt(): " + nbt);

        return nbt;
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
        return writeNbt(new NbtRWArgs(nbt), stacks, setIfEmpty);
    }

    /**
     * @deprecated Use {@link #readNbt(NbtRWArgs, DefaultedList)} instead
     */
    @Deprecated
    public static void readNbt(World world, NbtCompound nbt, DefaultedList<ItemStack> stacks) {
        readNbt(new ReadNbtArgs(nbt), stacks);
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

    public static int getSize(Inventory inventory) {
        return inventory.size();
    }

    public static ItemStack getStack(Inventory inventory, int slot) {
        return inventory.getStack(slot);
    }

    public static void setStack(Inventory inventory, int slot, ItemStack stack) {
        inventory.setStack(slot, stack);
    }

    public static boolean isEmpty(Inventory inventory) {
        return inventory.isEmpty();
    }

    public static ItemStack removeStack(Inventory inventory, int slot) {
        return inventory.removeStack(slot);
    }

    public static ItemStack removeStack(Inventory inventory, int slot, int amount) {
        return inventory.removeStack(slot, amount);
    }

    public static void clear(Inventory inventory) {
        inventory.clear();
    }

    public static void markDirty(Inventory inventory) {
        inventory.markDirty();
    }
}

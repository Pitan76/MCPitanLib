package net.pitan76.mcpitanlib.api.util;

import net.minecraft.world.ContainerHelper;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.storage.TagValueInput;
import net.minecraft.world.level.storage.TagValueOutput;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.util.ProblemReporter;
import net.minecraft.core.NonNullList;
import net.minecraft.world.level.Level;
import net.pitan76.mcpitanlib.api.event.nbt.NbtRWArgs;
import net.pitan76.mcpitanlib.api.event.nbt.ReadNbtArgs;
import net.pitan76.mcpitanlib.api.event.nbt.WriteNbtArgs;
import net.pitan76.mcpitanlib.api.registry.CompatRegistryLookup;
import net.pitan76.mcpitanlib.api.util.nbt.NbtListUtil;
import net.pitan76.mcpitanlib.core.mc1216.NbtDataConverter;

public class InventoryUtil {
    public static boolean insertItem(ItemStack insertStack, NonNullList<ItemStack> inventory) {
        return insertItem(insertStack, inventory, false);
    }

    public static boolean insertItem(ItemStack insertStack, NonNullList<ItemStack> inventory, boolean test) {
        boolean isInserted = false;
        for (int i = 0; i < inventory.size(); i++) {
            ItemStack stack = inventory.get(i);
            if (stack.isEmpty()) {
                if (!test) inventory.set(i, insertStack);
                isInserted = true;
                break;
            } else if (canMergeItems(stack, insertStack)) {
                int j = insertStack.getCount();
                if (!test) stack.grow(j);
                isInserted = j > 0;
                break;
            }
        }
        return isInserted;

    }

    public static boolean canMergeItems(ItemStack first, ItemStack second) {
        if (!first.is(second.getItem())) {
            return false;
        }
        if (first.getDamageValue() != second.getDamageValue()) {
            return false;
        }
        if (first.getCount() + second.getCount() > first.getMaxStackSize()) {
            return false;
        }
        return ItemStackUtil.areNbtOrComponentEqual(first, second);
    }

    public static CompoundTag writeNbt(NbtRWArgs args, CompoundTag nbt, NonNullList<ItemStack> stacks, boolean setIfEmpty) {
        boolean nbtNull = nbt == null;

        if (args instanceof WriteNbtArgs) {
            WriteNbtArgs writeNbtArgs = (WriteNbtArgs) args;
            if (writeNbtArgs.view == null) writeNbtArgs.view = TagValueOutput.createWithoutContext(ProblemReporter.DISCARDING);

            ContainerHelper.saveAllItems(writeNbtArgs.view, stacks, setIfEmpty);
            if (!nbtNull)
                NbtUtil.put(nbt, "Items", NbtListUtil.create()); // dummy list to compat with old mod

            NbtDataConverter.data2nbt(writeNbtArgs.view, nbt);
            return nbt;
        }

        ValueOutput view = NbtDataConverter.nbt2writeData(nbt, args.registryLookup);
        ContainerHelper.saveAllItems(view, stacks, setIfEmpty);
        if (!nbtNull)
            NbtUtil.put(nbt, "Items", NbtListUtil.create()); // dummy list to compat with old mod

        NbtDataConverter.data2nbt(view, nbt);
        return nbt;
    }

    public static void readNbt(NbtRWArgs args, CompoundTag nbt, NonNullList<ItemStack> stacks) {
        if (args instanceof ReadNbtArgs) {
            ReadNbtArgs readNbtArgs = (ReadNbtArgs) args;
            if (readNbtArgs.view == null) return;

            ContainerHelper.loadAllItems(readNbtArgs.view, stacks);
            return;
        }

        ValueInput view = NbtDataConverter.nbt2readData(nbt, args.registryLookup);
        ContainerHelper.loadAllItems(view, stacks);
    }

    public static CompoundTag writeNbt(NbtRWArgs args, NonNullList<ItemStack> stacks, boolean setIfEmpty) {
        return writeNbt(args, args.getNbt(), stacks, setIfEmpty);
    }

    public static CompoundTag writeNbt(NbtRWArgs args, NonNullList<ItemStack> stacks) {
        return writeNbt(args, stacks, true);
    }

    public static void readNbt(NbtRWArgs args, NonNullList<ItemStack> stacks) {
        readNbt(args, args.getNbt(), stacks);
    }

    public static void readNbt(CompatRegistryLookup registryLookup, CompoundTag nbt, NonNullList<ItemStack> stacks) {
        ValueInput view = NbtDataConverter.nbt2readData(nbt, registryLookup);
        ContainerHelper.loadAllItems(view, stacks);
    }

    public static CompoundTag writeNbt(CompatRegistryLookup registryLookup, CompoundTag nbt, NonNullList<ItemStack> stacks, boolean setIfEmpty) {
        NbtUtil.put(nbt, "Items", NbtListUtil.create());
        ValueOutput view = NbtDataConverter.nbt2writeData(nbt, registryLookup);
        ContainerHelper.saveAllItems(view, stacks, setIfEmpty);

        NbtDataConverter.data2nbt(view, nbt);

        System.out.println("writeNbt(): " + nbt);

        return nbt;
    }

    public static CompoundTag writeNbt(CompatRegistryLookup registryLookup, CompoundTag nbt, NonNullList<ItemStack> stacks) {
        return writeNbt(registryLookup, nbt, stacks, true);
    }

    // deprecated
    /**
     * @deprecated Use {@link #writeNbt(NbtRWArgs, NonNullList)} instead
     */
    @Deprecated
    public static CompoundTag writeNbt(Level world, CompoundTag nbt, NonNullList<ItemStack> stacks) {
        return writeNbt(world, nbt, true, stacks);
    }

    /**
     * @deprecated Use {@link #writeNbt(NbtRWArgs, NonNullList, boolean)} instead
     */
    @Deprecated
    public static CompoundTag writeNbt(Level world, CompoundTag nbt, boolean setIfEmpty, NonNullList<ItemStack> stacks) {
        return writeNbt(new NbtRWArgs(nbt), stacks, setIfEmpty);
    }

    /**
     * @deprecated Use {@link #readNbt(NbtRWArgs, NonNullList)} instead
     */
    @Deprecated
    public static void readNbt(Level world, CompoundTag nbt, NonNullList<ItemStack> stacks) {
        readNbt(new ReadNbtArgs(nbt), stacks);
    }
    // ----

    public static SimpleContainer createSimpleInventory(int size) {
        return new SimpleContainer(size);
    }

    public static void copyToInv(NonNullList<ItemStack> from, Container to) {
        for (int i = 0; i < from.size(); i++) {
            to.setItem(i, from.get(i));
        }
    }

    public static void copyToList(Container from, NonNullList<ItemStack> to) {
        for (int i = 0; i < from.getContainerSize(); i++) {
            to.set(i, from.getItem(i));
        }
    }

    public static int getSize(Container inventory) {
        return inventory.getContainerSize();
    }

    public static ItemStack getStack(Container inventory, int slot) {
        return inventory.getItem(slot);
    }

    public static void setStack(Container inventory, int slot, ItemStack stack) {
        inventory.setItem(slot, stack);
    }

    public static boolean isEmpty(Container inventory) {
        return inventory.isEmpty();
    }

    public static ItemStack removeStack(Container inventory, int slot) {
        return inventory.removeItemNoUpdate(slot);
    }

    public static ItemStack removeStack(Container inventory, int slot, int amount) {
        return inventory.removeItem(slot, amount);
    }

    public static void clear(Container inventory) {
        inventory.clearContent();
    }

    public static void markDirty(Container inventory) {
        inventory.setChanged();
    }

    public static net.pitan76.mcpitanlib.midohra.nbt.NbtCompound writeNbt(CompatRegistryLookup registryLookup, net.pitan76.mcpitanlib.midohra.nbt.NbtCompound nbt, NonNullList<ItemStack> stacks, boolean setIfEmpty) {
        return net.pitan76.mcpitanlib.midohra.nbt.NbtCompound.of(writeNbt(registryLookup, nbt.toMinecraft(), stacks, setIfEmpty));
    }

     public static net.pitan76.mcpitanlib.midohra.nbt.NbtCompound writeNbt(CompatRegistryLookup registryLookup, net.pitan76.mcpitanlib.midohra.nbt.NbtCompound nbt, NonNullList<ItemStack> stacks) {
        return writeNbt(registryLookup, nbt, stacks, true);
    }

     public static void readNbt(CompatRegistryLookup registryLookup, net.pitan76.mcpitanlib.midohra.nbt.NbtCompound nbt, NonNullList<ItemStack> stacks) {
        readNbt(registryLookup, nbt.toMinecraft(), stacks);
    }

    public static net.pitan76.mcpitanlib.midohra.nbt.NbtCompound writeNbt(net.pitan76.mcpitanlib.api.event.nbt.NbtRWArgs args, net.pitan76.mcpitanlib.midohra.nbt.NbtCompound nbt, NonNullList<ItemStack> stacks, boolean setIfEmpty) {
        return net.pitan76.mcpitanlib.midohra.nbt.NbtCompound.of(writeNbt(args, nbt.toMinecraft(), stacks, setIfEmpty));
    }

     public static net.pitan76.mcpitanlib.midohra.nbt.NbtCompound writeNbt(net.pitan76.mcpitanlib.api.event.nbt.NbtRWArgs args, net.pitan76.mcpitanlib.midohra.nbt.NbtCompound nbt, NonNullList<ItemStack> stacks) {
        return writeNbt(args, nbt, stacks, true);
    }

     public static void readNbt(net.pitan76.mcpitanlib.api.event.nbt.NbtRWArgs args, net.pitan76.mcpitanlib.midohra.nbt.NbtCompound nbt, NonNullList<ItemStack> stacks) {
        readNbt(args, nbt.toMinecraft(), stacks);
    }

    public static boolean canMergeItems(net.pitan76.mcpitanlib.midohra.item.ItemStack first, net.pitan76.mcpitanlib.midohra.item.ItemStack second) {
        return canMergeItems(first.toMinecraft(), second.toMinecraft());
    }
}

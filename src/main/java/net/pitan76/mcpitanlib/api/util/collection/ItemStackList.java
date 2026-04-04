package net.pitan76.mcpitanlib.api.util.collection;

import com.google.common.collect.Lists;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.NonNullList;
import net.pitan76.mcpitanlib.api.gui.inventory.IInventory;
import net.pitan76.mcpitanlib.api.util.ItemStackUtil;
import org.apache.commons.lang3.Validate;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ItemStackList extends NonNullList<ItemStack> {

    public ItemStackList(List<ItemStack> delegate, @Nullable ItemStack initialElement) {
        super(delegate, initialElement);
    }

    public static ItemStackList of() {
        return new ItemStackList(Lists.newArrayList(), ItemStackUtil.empty());
    }

    public static ItemStackList ofSize(int size) {
        return ofSize(size, ItemStackUtil.empty());
    }

    public static ItemStackList ofSize(int size, ItemStack defaultStack) {
        Validate.notNull(defaultStack);
        ItemStack[] objects = new ItemStack[size];
        Arrays.fill(objects, defaultStack);
        return new ItemStackList(Arrays.asList(objects), defaultStack);
    }

    public static ItemStackList copyOf(ItemStack defaultStack, ItemStack... stacks) {
        return new ItemStackList(Arrays.asList(stacks), defaultStack);
    }

    public static Container toInventory(NonNullList<ItemStack> list) {
        return IInventory.of(list);
    }

    public static NonNullList<ItemStack> toDefaultedList(Container inventory) {
        NonNullList<ItemStack> list = NonNullList.withSize(inventory.getContainerSize(), ItemStackUtil.empty());
        for (int i = 0; i < inventory.getContainerSize(); i++) {
            list.set(i, inventory.getItem(i));
        }

        return list;
    }

    public static ItemStackList fromInventory(Container inventory) {
        return new ItemStackList(toDefaultedList(inventory), ItemStackUtil.empty());
    }

    public Container toInventory() {
        return toInventory(this);
    }

    public NonNullList<ItemStack> defaultedList() {
        return this;
    }

    public static ItemStackList of(NonNullList<ItemStack> defaultedList) {
        ItemStackList stacks = ItemStackList.ofSize(defaultedList.size());
        for (int i = 0; i < defaultedList.size(); i++) {
            stacks.set(i , defaultedList.get(i));
        }

        return stacks;
    }

    public static ItemStackList of(ItemStack stack) {
        return ItemStackList.ofSize(1, stack);
    }

    // Midohra
    public List<net.pitan76.mcpitanlib.midohra.item.ItemStack> toMidohra() {
        List<net.pitan76.mcpitanlib.midohra.item.ItemStack> stacks = new ArrayList<>();
        for (ItemStack stack : this) {
            stacks.add(net.pitan76.mcpitanlib.midohra.item.ItemStack.of(stack));
        }

        return stacks;
    }

    public @NotNull net.pitan76.mcpitanlib.midohra.item.ItemStack getAsMidohra(int index) {
        return net.pitan76.mcpitanlib.midohra.item.ItemStack.of(get(index));
    }

    public net.pitan76.mcpitanlib.midohra.item.ItemStack getFirstAsMidohra() {
        return net.pitan76.mcpitanlib.midohra.item.ItemStack.of(getFirst());
    }

    public net.pitan76.mcpitanlib.midohra.item.ItemStack getLastAsMidohra() {
        return net.pitan76.mcpitanlib.midohra.item.ItemStack.of(getLast());
    }

    public boolean add(net.pitan76.mcpitanlib.midohra.item.ItemStack stack) {
        return add(stack.toMinecraft());
    }

    public boolean addAll(List<net.pitan76.mcpitanlib.midohra.item.ItemStack> stacks) {
        boolean changed = false;
        for (net.pitan76.mcpitanlib.midohra.item.ItemStack stack : stacks) {
            changed |= add(stack);
        }

        return changed;
    }

    public boolean addAll(net.pitan76.mcpitanlib.midohra.item.ItemStack... stacks) {
        return addAll(Arrays.asList(stacks));
    }

    public boolean remove(net.pitan76.mcpitanlib.midohra.item.ItemStack stack) {
        return remove(stack.toMinecraft());
    }

    public boolean removeAll(List<net.pitan76.mcpitanlib.midohra.item.ItemStack> stacks) {
        boolean changed = false;
        for (net.pitan76.mcpitanlib.midohra.item.ItemStack stack : stacks) {
            changed |= remove(stack);
        }

        return changed;
    }

    public boolean removeAll(net.pitan76.mcpitanlib.midohra.item.ItemStack... stacks) {
        return removeAll(Arrays.asList(stacks));
    }

    public boolean contains(net.pitan76.mcpitanlib.midohra.item.ItemStack stack) {
        return contains(stack.toMinecraft());
    }

    public boolean containsAll(List<net.pitan76.mcpitanlib.midohra.item.ItemStack> stacks) {
        for (net.pitan76.mcpitanlib.midohra.item.ItemStack stack : stacks) {
            if (!contains(stack)) {
                return false;
            }
        }

        return true;
    }

    public boolean containsAll(net.pitan76.mcpitanlib.midohra.item.ItemStack... stacks) {
        return containsAll(Arrays.asList(stacks));
    }

    public boolean containsAny(List<net.pitan76.mcpitanlib.midohra.item.ItemStack> stacks) {
        for (net.pitan76.mcpitanlib.midohra.item.ItemStack stack : stacks) {
            if (contains(stack)) {
                return true;
            }
        }

        return false;
    }

    public boolean containsAny(net.pitan76.mcpitanlib.midohra.item.ItemStack... stacks) {
        return containsAny(Arrays.asList(stacks));
    }

    public boolean equals(List<net.pitan76.mcpitanlib.midohra.item.ItemStack> stacks) {
        if (size() != stacks.size()) {
            return false;
        }

        for (int i = 0; i < size(); i++) {
            if (!get(i).equals(stacks.get(i).toMinecraft())) {
                return false;
            }
        }

        return true;
    }

    public boolean equals(net.pitan76.mcpitanlib.midohra.item.ItemStack... stacks) {
        return equals(Arrays.asList(stacks));
    }

    public static ItemStackList of(List<net.pitan76.mcpitanlib.midohra.item.ItemStack> stacks) {
        ItemStackList list = ItemStackList.ofSize(stacks.size());
        for (int i = 0; i < stacks.size(); i++) {
            list.set(i, stacks.get(i).toMinecraft());
        }

        return list;
    }

    public static ItemStackList of(net.pitan76.mcpitanlib.midohra.item.ItemStack... stacks) {
        return ItemStackList.of(Arrays.asList(stacks));
    }

    public static ItemStackList of(net.pitan76.mcpitanlib.midohra.item.ItemStack stack) {
        return of(stack, 1);
    }

    public static ItemStackList of(net.pitan76.mcpitanlib.midohra.item.ItemStack stack, int size) {
        return ItemStackList.ofSize(size, stack.toMinecraft());
    }

    public static ItemStackList of2(List<ItemStack> stacks) {
        ItemStackList list = ItemStackList.ofSize(stacks.size());
        for (int i = 0; i < stacks.size(); i++)
            list.set(i, stacks.get(i));

        return list;
    }

    public void set(int index, net.pitan76.mcpitanlib.midohra.item.ItemStack stack) {
        set(index, stack.toMinecraft());
    }
}

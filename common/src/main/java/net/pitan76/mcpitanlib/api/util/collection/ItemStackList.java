package net.pitan76.mcpitanlib.api.util.collection;

import com.google.common.collect.Lists;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;
import net.pitan76.mcpitanlib.api.gui.inventory.IInventory;
import net.pitan76.mcpitanlib.api.util.ItemStackUtil;
import org.apache.commons.lang3.Validate;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;

public class ItemStackList extends DefaultedList<ItemStack> {

    public ItemStackList(List<ItemStack> delegate, @Nullable ItemStack initialElement) {
        super(delegate, initialElement);
    }

    public static ItemStackList of() {
        return new ItemStackList(Lists.newArrayList(), ItemStackUtil.empty());
    }

    public static ItemStackList ofSize(int size) {
        return new ItemStackList(Lists.newArrayListWithCapacity(size), ItemStackUtil.empty());
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

    public static Inventory toInventory(DefaultedList<ItemStack> list) {
        return IInventory.of(list);
    }

    public static DefaultedList<ItemStack> toDefaultedList(Inventory inventory) {
        DefaultedList<ItemStack> list = DefaultedList.ofSize(inventory.size(), ItemStackUtil.empty());
        for (int i = 0; i < inventory.size(); i++) {
            list.set(i, inventory.getStack(i));
        }

        return list;
    }

    public static ItemStackList fromInventory(Inventory inventory) {
        return new ItemStackList(toDefaultedList(inventory), ItemStackUtil.empty());
    }

    public Inventory toInventory() {
        return toInventory(this);
    }

    public DefaultedList<ItemStack> defaultedList() {
        return this;
    }
}

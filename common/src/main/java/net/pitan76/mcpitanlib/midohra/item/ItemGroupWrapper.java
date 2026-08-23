package net.pitan76.mcpitanlib.midohra.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.pitan76.mcpitanlib.api.text.TextComponent;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;
import net.pitan76.mcpitanlib.api.util.item.ItemGroupUtil;
import net.pitan76.mcpitanlib.api.util.item.ItemUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ItemGroupWrapper {
    private final net.minecraft.item.ItemGroup itemGroup;
    private final java.util.function.Supplier<net.minecraft.item.ItemGroup> itemGroupSupplier;

    protected ItemGroupWrapper() {
        this.itemGroup = null;
        this.itemGroupSupplier = null;
    }

    protected ItemGroupWrapper(net.minecraft.item.ItemGroup itemGroup) {
        this.itemGroup = itemGroup;
        this.itemGroupSupplier = null;
    }

    protected ItemGroupWrapper(java.util.function.Supplier<net.minecraft.item.ItemGroup> itemGroupSupplier) {
        this.itemGroup = null;
        this.itemGroupSupplier = itemGroupSupplier;
    }

    public static ItemGroupWrapper of(net.minecraft.item.ItemGroup itemGroup) {
        return new ItemGroupWrapper(itemGroup);
    }

    public static ItemGroupWrapper of(java.util.function.Supplier<net.minecraft.item.ItemGroup> itemGroupSupplier) {
        return new ItemGroupWrapper(itemGroupSupplier);
    }

    public static ItemGroupWrapper of(CompatIdentifier id) {
        if (ItemGroupUtil.isExist(id))
            return of(ItemGroupUtil.fromId(id));

        return of();
    }

    public static ItemGroupWrapper of() {
        return new ItemGroupWrapper();
    }

    public boolean isPresent() {
        return !isEmpty();
    }

    public boolean isEmpty() {
        return itemGroup == null && itemGroupSupplier == null;
    }

    public net.minecraft.item.ItemGroup get() {
        if (itemGroup != null) return itemGroup;
        if (itemGroupSupplier != null) return itemGroupSupplier.get();
        return null;
    }

    public java.util.function.Supplier<net.minecraft.item.ItemGroup> getSupplier() {
        if (itemGroupSupplier != null) return itemGroupSupplier;
        return () -> itemGroup;
    }

    public CompatIdentifier getId() {
        if (isEmpty()) return CompatIdentifier.empty();
        return ItemGroupUtil.toCompatID(get());
    }

    public ItemStack getIcon() {
        if (isEmpty()) return ItemStack.EMPTY;
        return ItemStack.of(get().getIcon());
    }

    public boolean rawEquals(ItemGroupWrapper other) {
        return get() == other.get();
    }

    @Override
    public int hashCode() {
        return get() != null ? get().hashCode() : 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof ItemGroupWrapper)) return false;

        ItemGroupWrapper other = (ItemGroupWrapper) obj;
        return rawEquals(other);
    }

    public ItemWrapper getIconItem() {
        return getIcon().getItem();
    }

    public Type getType() {
        if (get() == ItemGroup.INVENTORY) {
            return Type.INVENTORY;
        } else if (get() == ItemGroup.HOTBAR) {
            return Type.HOTBAR;
        } else if (get() == ItemGroup.SEARCH) {
            return Type.SEARCH;
        } else if (get() != null) {
            return Type.CATEGORY;
        }

        return Type.EMPTY;
    }

    public Collection<ItemStack> getDisplayItems() {
        if (isEmpty()) return Collections.emptyList();
        List<ItemStack> displayStacks = new ArrayList<>();

        for (Item item : ItemUtil.getItems()) {
            if (item.getGroup() == get()) {
                displayStacks.add(ItemStack.of(item.getDefaultStack()));
            }
        }

        return displayStacks;
    }

    public TextComponent getDisplayName() {
        if (isEmpty()) return new TextComponent("");
        return new TextComponent(get().getDisplayName());
    }

    public static enum Type {
        EMPTY,
        CATEGORY,
        INVENTORY,
        HOTBAR,
        SEARCH,
    }
}

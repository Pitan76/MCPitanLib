package net.pitan76.mcpitanlib.midohra.item;

import net.minecraft.item.ItemGroup;
import net.pitan76.mcpitanlib.api.text.TextComponent;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;
import net.pitan76.mcpitanlib.api.util.item.ItemGroupUtil;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

public class ItemGroupWrapper {
    private final net.minecraft.item.ItemGroup itemGroup;
    private final java.util.function.Supplier<net.minecraft.item.ItemGroup> itemGroupSupplier;
    private final net.minecraft.registry.RegistryKey<net.minecraft.item.ItemGroup> itemGroupKey;

    protected ItemGroupWrapper() {
        this.itemGroup = null;
        this.itemGroupSupplier = null;
        this.itemGroupKey = null;
    }

    protected ItemGroupWrapper(net.minecraft.item.ItemGroup itemGroup) {
        this.itemGroup = itemGroup;
        this.itemGroupSupplier = null;
        this.itemGroupKey = null;
    }

    protected ItemGroupWrapper(java.util.function.Supplier<net.minecraft.item.ItemGroup> itemGroupSupplier) {
        this.itemGroup = null;
        this.itemGroupSupplier = itemGroupSupplier;
        this.itemGroupKey = null;
    }

    protected ItemGroupWrapper(net.minecraft.registry.RegistryKey<net.minecraft.item.ItemGroup> itemGroupKey) {
        this.itemGroup = null;
        this.itemGroupSupplier = null;
        this.itemGroupKey = itemGroupKey;
    }

    public static ItemGroupWrapper of(net.minecraft.item.ItemGroup itemGroup) {
        return new ItemGroupWrapper(itemGroup);
    }

    public static ItemGroupWrapper of(java.util.function.Supplier<net.minecraft.item.ItemGroup> itemGroupSupplier) {
        return new ItemGroupWrapper(itemGroupSupplier);
    }

    public static ItemGroupWrapper of(net.minecraft.registry.RegistryKey<net.minecraft.item.ItemGroup> itemGroupKey) {
        return new ItemGroupWrapper(itemGroupKey);
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
        return itemGroup == null && itemGroupSupplier == null && itemGroupKey == null;
    }

    public net.minecraft.item.ItemGroup get() {
        if (itemGroup != null) return itemGroup;
        if (itemGroupSupplier != null) return itemGroupSupplier.get();
        if (itemGroupKey != null) return net.minecraft.registry.Registries.ITEM_GROUP.get(itemGroupKey);
        return null;
    }

    public java.util.function.Supplier<net.minecraft.item.ItemGroup> getSupplier() {
        if (itemGroupSupplier != null) return itemGroupSupplier;
        if (itemGroupKey != null) return () -> net.minecraft.registry.Registries.ITEM_GROUP.get(itemGroupKey);
        return () -> itemGroup;
    }

    public net.minecraft.registry.RegistryKey<net.minecraft.item.ItemGroup> getKey() {
        if (itemGroupKey != null) return itemGroupKey;
        if (itemGroup != null) return net.minecraft.registry.Registries.ITEM_GROUP.getKey(itemGroup).orElse(null);
        if (itemGroupSupplier != null) {
            try {
                net.minecraft.item.ItemGroup group = itemGroupSupplier.get();
                if (group != null) return net.minecraft.registry.Registries.ITEM_GROUP.getKey(group).orElse(null);
            } catch (Exception ignored) {}
        }
        return null;
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
        if (obj == null || getClass() != obj.getClass()) return false;

        ItemGroupWrapper other = (ItemGroupWrapper) obj;
        return rawEquals(other);
    }

    public ItemWrapper getIconItem() {
        return getIcon().getItem();
    }

    public Type getType() {
        if (get().getType().equals(ItemGroup.Type.CATEGORY)) {
            return Type.CATEGORY;
        } else if (get().getType().equals(ItemGroup.Type.INVENTORY)) {
            return Type.INVENTORY;
        } else if (get().getType().equals(ItemGroup.Type.HOTBAR)) {
            return Type.HOTBAR;
        } else if (get().getType().equals(ItemGroup.Type.SEARCH)) {
            return Type.SEARCH;
        }

        return Type.EMPTY;
    }

    public Collection<ItemStack> getDisplayItems() {
        if (isEmpty()) return Collections.emptyList();
        return get().getDisplayStacks().stream().map(ItemStack::of).collect(Collectors.toList());
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

package net.pitan76.mcpitanlib.midohra.item;

import net.pitan76.mcpitanlib.api.text.TextComponent;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;
import net.pitan76.mcpitanlib.api.util.item.ItemGroupUtil;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

public class ItemGroupWrapper {
    private final net.minecraft.world.item.CreativeModeTab itemGroup;

    protected ItemGroupWrapper() {
        this.itemGroup = null;
    }

    protected ItemGroupWrapper(net.minecraft.world.item.CreativeModeTab itemGroup) {
        this.itemGroup = itemGroup;
    }

    public static ItemGroupWrapper of(net.minecraft.world.item.CreativeModeTab itemGroup) {
        return new ItemGroupWrapper(itemGroup);
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
        return itemGroup == null;
    }

    public net.minecraft.world.item.CreativeModeTab get() {
        return itemGroup;
    }

    public CompatIdentifier getId() {
        if (isEmpty()) return CompatIdentifier.empty();
        return ItemGroupUtil.toCompatID(get());
    }

    public ItemStack getIcon() {
        if (isEmpty()) return ItemStack.EMPTY;
        return ItemStack.of(get().getIconItem());
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
        if (get().getType().equals(net.minecraft.world.item.CreativeModeTab.Type.CATEGORY)) {
            return Type.CATEGORY;
        } else if (get().getType().equals(net.minecraft.world.item.CreativeModeTab.Type.INVENTORY)) {
            return Type.INVENTORY;
        } else if (get().getType().equals(net.minecraft.world.item.CreativeModeTab.Type.HOTBAR)) {
            return Type.HOTBAR;
        } else if (get().getType().equals(net.minecraft.world.item.CreativeModeTab.Type.SEARCH)) {
            return Type.SEARCH;
        }

        return Type.EMPTY;
    }

    public Collection<ItemStack> getDisplayItems() {
        if (isEmpty()) return Collections.emptyList();
        return get().getDisplayItems().stream().map(ItemStack::of).collect(Collectors.toList());
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

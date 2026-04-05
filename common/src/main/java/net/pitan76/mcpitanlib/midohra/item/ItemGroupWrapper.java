package net.pitan76.mcpitanlib.midohra.item;

import net.pitan76.mcpitanlib.api.util.CompatIdentifier;
import net.pitan76.mcpitanlib.api.util.item.ItemGroupUtil;

public class ItemGroupWrapper {
    private final net.minecraft.item.ItemGroup itemGroup;

    protected ItemGroupWrapper() {
        this.itemGroup = null;
    }

    protected ItemGroupWrapper(net.minecraft.item.ItemGroup itemGroup) {
        this.itemGroup = itemGroup;
    }

    public static ItemGroupWrapper of(net.minecraft.item.ItemGroup itemGroup) {
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

    public net.minecraft.item.ItemGroup get() {
        return itemGroup;
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
}

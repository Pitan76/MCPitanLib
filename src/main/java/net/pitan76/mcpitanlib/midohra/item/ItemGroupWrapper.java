package net.pitan76.mcpitanlib.midohra.item;

import net.pitan76.mcpitanlib.api.util.CompatIdentifier;
import net.pitan76.mcpitanlib.api.util.item.ItemGroupUtil;

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
        return ItemGroupUtil.toCompatID(itemGroup);
    }

    public ItemStack getIcon() {
        if (isEmpty()) return ItemStack.EMPTY;
        return ItemStack.of(itemGroup.getIconItem());
    }

    public boolean rawEquals(ItemGroupWrapper other) {
        return itemGroup == other.itemGroup;
    }
}

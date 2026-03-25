package net.pitan76.mcpitanlib.midohra.item;

import net.minecraft.world.item.Item;

import java.util.function.Supplier;

public class SupplierItemWrapper extends ItemWrapper {
    private final Supplier<Item> supplier;

    protected SupplierItemWrapper(Supplier<Item> supplier) {
        this.supplier = supplier;
    }

    public static SupplierItemWrapper of(Supplier<Item> supplier) {
        return new SupplierItemWrapper(supplier);
    }

    @Override
    public Item get() {
        return supplier.get();
    }
}

package net.pitan76.mcpitanlib.midohra.item;

import me.shedaniel.architectury.registry.RegistrySupplier;
import net.minecraft.item.Item;
import net.pitan76.mcpitanlib.api.registry.result.RegistryResult;

import java.util.function.Supplier;

public class SupplierItemWrapper extends ItemWrapper {
    private final Supplier<Item> supplier;

    protected SupplierItemWrapper(Supplier<Item> supplier) {
        this.supplier = supplier;
    }

    public static SupplierItemWrapper of(Supplier<Item> supplier) {
        return new SupplierItemWrapper(supplier);
    }

    public static SupplierItemWrapper of(RegistryResult<Item> result) {
        return new SupplierItemWrapper(result::get);
    }

    public static SupplierItemWrapper of(RegistrySupplier<Item> result) {
        return new SupplierItemWrapper(result::get);
    }

    @Override
    public Item get() {
        return supplier.get();
    }
}

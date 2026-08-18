package net.pitan76.mcpitanlib.midohra.item;

import net.pitan76.mcpitanlib.api.registry.result.RegistrySupplier;
import net.minecraft.item.ItemGroup;
import net.pitan76.mcpitanlib.api.item.CreativeTabBuilder;
import net.pitan76.mcpitanlib.api.registry.result.RegistryResult;

import java.util.function.Supplier;

public class SupplierItemGroupWrapper extends ItemGroupWrapper {
    private final Supplier<ItemGroup> supplier;

    protected SupplierItemGroupWrapper(Supplier<ItemGroup> supplier) {
        this.supplier = supplier;
    }

    public static SupplierItemGroupWrapper of(Supplier<ItemGroup> supplier) {
        return new SupplierItemGroupWrapper(supplier);
    }

    public static SupplierItemGroupWrapper of(RegistryResult<ItemGroup> result) {
        return new SupplierItemGroupWrapper(result::get);
    }

    public static SupplierItemGroupWrapper of(RegistrySupplier<ItemGroup> result) {
        return new SupplierItemGroupWrapper(result::get);
    }

    public static SupplierItemGroupWrapper of(CreativeTabBuilder builder) {
        return new SupplierItemGroupWrapper(builder::build);
    }

    @Override
    public ItemGroup get() {
        return supplier.get();
    }
}

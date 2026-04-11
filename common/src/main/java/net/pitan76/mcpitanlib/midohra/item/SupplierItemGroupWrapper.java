package net.pitan76.mcpitanlib.midohra.item;

import net.minecraft.world.item.CreativeModeTab;
import net.pitan76.mcpitanlib.api.item.CreativeTabBuilder;
import net.pitan76.mcpitanlib.api.registry.result.RegistryResult;
import net.pitan76.mcpitanlib.api.registry.result.RegistrySupplier;

import java.util.function.Supplier;

public class SupplierItemGroupWrapper extends ItemGroupWrapper {
    private final Supplier<CreativeModeTab> supplier;

    protected SupplierItemGroupWrapper(Supplier<CreativeModeTab> supplier) {
        this.supplier = supplier;
    }

    public static SupplierItemGroupWrapper of(Supplier<CreativeModeTab> supplier) {
        return new SupplierItemGroupWrapper(supplier);
    }

    public static SupplierItemGroupWrapper of(RegistryResult<CreativeModeTab> result) {
        return new SupplierItemGroupWrapper(result::get);
    }

    public static SupplierItemGroupWrapper of(RegistrySupplier<CreativeModeTab> result) {
        return new SupplierItemGroupWrapper(result::get);
    }

    public static SupplierItemGroupWrapper of(CreativeTabBuilder builder) {
        return new SupplierItemGroupWrapper(builder::build);
    }

    @Override
    public CreativeModeTab get() {
        return supplier.get();
    }
}

package net.pitan76.mcpitanlib.midohra.screen;

import net.pitan76.mcpitanlib.api.registry.result.RegistrySupplier;
import net.minecraft.screen.ScreenHandlerType;
import net.pitan76.mcpitanlib.api.registry.result.RegistryResult;
import net.pitan76.mcpitanlib.midohra.core.INonTypedSupplier;

import java.util.function.Supplier;

public class SupplierScreenHandlerTypeWrapper extends ScreenHandlerTypeWrapper implements INonTypedSupplier<SupplierScreenHandlerTypeWrapper> {
    private final Supplier<ScreenHandlerType<?>> supplier;

    protected SupplierScreenHandlerTypeWrapper(Supplier<ScreenHandlerType<?>> supplier) {
        this.supplier = supplier;
    }

    public static SupplierScreenHandlerTypeWrapper of(Supplier<ScreenHandlerType<?>> supplier) {
        return new SupplierScreenHandlerTypeWrapper(supplier);
    }

    public static SupplierScreenHandlerTypeWrapper of(RegistryResult<ScreenHandlerType<?>> result) {
        return new SupplierScreenHandlerTypeWrapper(result::get);
    }

    public static SupplierScreenHandlerTypeWrapper of(RegistrySupplier<ScreenHandlerType<?>> result) {
        return new SupplierScreenHandlerTypeWrapper(result::get);
    }

    @Override
    public SupplierScreenHandlerTypeWrapper asNonTyped() {
        return this;
    }

    @Override
    public ScreenHandlerType<?> get() {
        return supplier.get();
    }
}

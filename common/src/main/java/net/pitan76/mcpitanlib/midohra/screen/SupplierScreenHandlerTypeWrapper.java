package net.pitan76.mcpitanlib.midohra.screen;

import net.minecraft.world.inventory.MenuType;
import net.pitan76.mcpitanlib.api.registry.result.RegistryResult;
import net.pitan76.mcpitanlib.api.registry.result.RegistrySupplier;
import net.pitan76.mcpitanlib.midohra.core.INonTypedSupplier;

import java.util.function.Supplier;

public class SupplierScreenHandlerTypeWrapper extends ScreenHandlerTypeWrapper implements INonTypedSupplier<SupplierScreenHandlerTypeWrapper> {
    private final Supplier<MenuType<?>> supplier;

    protected SupplierScreenHandlerTypeWrapper(Supplier<MenuType<?>> supplier) {
        this.supplier = supplier;
    }

    public static SupplierScreenHandlerTypeWrapper of(Supplier<MenuType<?>> supplier) {
        return new SupplierScreenHandlerTypeWrapper(supplier);
    }

    public static SupplierScreenHandlerTypeWrapper of(RegistryResult<MenuType<?>> result) {
        return new SupplierScreenHandlerTypeWrapper(result::get);
    }

    public static SupplierScreenHandlerTypeWrapper of(RegistrySupplier<MenuType<?>> result) {
        return new SupplierScreenHandlerTypeWrapper(result::get);
    }

    @Override
    public SupplierScreenHandlerTypeWrapper asNonTyped() {
        return this;
    }

    @Override
    public MenuType<?> get() {
        return supplier.get();
    }
}

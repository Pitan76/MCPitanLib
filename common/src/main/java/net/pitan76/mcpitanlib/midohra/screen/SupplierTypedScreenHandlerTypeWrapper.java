package net.pitan76.mcpitanlib.midohra.screen;

import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.pitan76.mcpitanlib.api.registry.result.RegistryResult;
import net.pitan76.mcpitanlib.api.registry.result.RegistrySupplier;
import net.pitan76.mcpitanlib.midohra.core.INonTypedSupplier;

import java.util.function.Supplier;

public class SupplierTypedScreenHandlerTypeWrapper<T extends AbstractContainerMenu> extends TypedScreenHandlerTypeWrapper<T> implements INonTypedSupplier<SupplierScreenHandlerTypeWrapper> {
    private final Supplier<MenuType<T>> supplier;

    protected SupplierTypedScreenHandlerTypeWrapper(Supplier<MenuType<T>> supplier) {
        super(null);
        this.supplier = supplier;
    }

    public static <T extends AbstractContainerMenu> SupplierTypedScreenHandlerTypeWrapper<T> of(Supplier<MenuType<T>> supplier) {
        return new SupplierTypedScreenHandlerTypeWrapper<>(supplier);
    }

    public static <T extends AbstractContainerMenu> SupplierTypedScreenHandlerTypeWrapper<T> of(RegistryResult<MenuType<T>> result) {
        return new SupplierTypedScreenHandlerTypeWrapper<>(result::get);
    }

    public static <T extends AbstractContainerMenu> SupplierTypedScreenHandlerTypeWrapper<T> of(RegistrySupplier<MenuType<T>> result) {
        return new SupplierTypedScreenHandlerTypeWrapper<>(result::get);
    }

    public static <T extends AbstractContainerMenu> SupplierTypedScreenHandlerTypeWrapper<T> of(SupplierScreenHandlerTypeWrapper wrapper) {
        return new SupplierTypedScreenHandlerTypeWrapper<>(() -> (MenuType<T>) wrapper.get());
    }

    @Override
    public SupplierScreenHandlerTypeWrapper asNonTyped() {
        return SupplierScreenHandlerTypeWrapper.of(this::get);
    }

    @Override
    public MenuType<T> get() {
        return supplier.get();
    }
}

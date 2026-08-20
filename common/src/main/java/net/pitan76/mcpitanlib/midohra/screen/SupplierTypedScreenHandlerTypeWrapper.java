package net.pitan76.mcpitanlib.midohra.screen;

import net.pitan76.mcpitanlib.api.registry.result.RegistrySupplier;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.pitan76.mcpitanlib.api.registry.result.RegistryResult;
import net.pitan76.mcpitanlib.midohra.core.INonTypedSupplier;

import java.util.function.Supplier;

public class SupplierTypedScreenHandlerTypeWrapper<T extends ScreenHandler> extends TypedScreenHandlerTypeWrapper<T> implements INonTypedSupplier<SupplierScreenHandlerTypeWrapper> {
    private final Supplier<ScreenHandlerType<T>> supplier;

    protected SupplierTypedScreenHandlerTypeWrapper(Supplier<ScreenHandlerType<T>> supplier) {
        super(null);
        this.supplier = supplier;
    }

    public static <T extends ScreenHandler> SupplierTypedScreenHandlerTypeWrapper<T> of(Supplier<ScreenHandlerType<T>> supplier) {
        return new SupplierTypedScreenHandlerTypeWrapper<>(supplier);
    }

    public static <T extends ScreenHandler> SupplierTypedScreenHandlerTypeWrapper<T> of(RegistryResult<ScreenHandlerType<T>> result) {
        return new SupplierTypedScreenHandlerTypeWrapper<>(result::get);
    }

    public static <T extends ScreenHandler> SupplierTypedScreenHandlerTypeWrapper<T> of(RegistrySupplier<ScreenHandlerType<T>> result) {
        return new SupplierTypedScreenHandlerTypeWrapper<>(result::get);
    }

    public static <T extends ScreenHandler> SupplierTypedScreenHandlerTypeWrapper<T> of(SupplierScreenHandlerTypeWrapper wrapper) {
        return new SupplierTypedScreenHandlerTypeWrapper<>(() -> (ScreenHandlerType<T>) wrapper.get());
    }

    @Override
    public SupplierScreenHandlerTypeWrapper asNonTyped() {
        return SupplierScreenHandlerTypeWrapper.of(this::get);
    }

    @Override
    public ScreenHandlerType<T> get() {
        return supplier.get();
    }
}

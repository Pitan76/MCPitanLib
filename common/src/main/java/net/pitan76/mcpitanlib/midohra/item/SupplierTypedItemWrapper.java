package net.pitan76.mcpitanlib.midohra.item;

import net.pitan76.mcpitanlib.api.registry.result.RegistrySupplier;
import net.minecraft.item.Item;
import net.pitan76.mcpitanlib.api.registry.result.RegistryResult;
import net.pitan76.mcpitanlib.midohra.core.INonTypedSupplier;

import java.util.function.Supplier;

public class SupplierTypedItemWrapper<T extends Item> extends TypedItemWrapper<T> implements INonTypedSupplier<SupplierItemWrapper> {
    private final Supplier<T> supplier;

    protected SupplierTypedItemWrapper(Supplier<T> supplier) {
        super(null);
        this.supplier = supplier;
    }

    public static <T extends Item> SupplierTypedItemWrapper<T> of(Supplier<T> supplier) {
        return new SupplierTypedItemWrapper<>(supplier);
    }

    public static <T extends Item> SupplierTypedItemWrapper<T> of(RegistryResult<T> result) {
        return new SupplierTypedItemWrapper<>(result::get);
    }

    public static <T extends Item> SupplierTypedItemWrapper<T> of(RegistrySupplier<T> result) {
        return new SupplierTypedItemWrapper<>(result::get);
    }

    public static <T extends Item> SupplierTypedItemWrapper<T> of(SupplierItemWrapper result) {
        return new SupplierTypedItemWrapper<>(() -> (T) result.get());
    }

    @Override
    public SupplierItemWrapper asNonTyped() {
        return SupplierItemWrapper.of(this::get);
    }

    @Override
    public T get() {
        return supplier.get();
    }
}

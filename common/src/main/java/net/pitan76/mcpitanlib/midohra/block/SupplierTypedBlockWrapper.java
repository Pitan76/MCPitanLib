package net.pitan76.mcpitanlib.midohra.block;

import net.pitan76.mcpitanlib.api.registry.result.RegistrySupplier;
import net.minecraft.block.Block;
import net.pitan76.mcpitanlib.api.registry.result.RegistryResult;
import net.pitan76.mcpitanlib.midohra.core.INonTypedSupplier;

import java.util.function.Supplier;

public class SupplierTypedBlockWrapper<T extends Block> extends TypedBlockWrapper<T> implements INonTypedSupplier<SupplierBlockWrapper> {
    private final Supplier<T> supplier;

    protected SupplierTypedBlockWrapper(Supplier<T> supplier) {
        super(null);
        this.supplier = supplier;
    }

    public static <T extends Block> SupplierTypedBlockWrapper<T> of(Supplier<T> supplier) {
        return new SupplierTypedBlockWrapper<>(supplier);
    }

    public static <T extends Block> SupplierTypedBlockWrapper<T> of(RegistryResult<T> result) {
        return new SupplierTypedBlockWrapper<>(result::get);
    }

    public static <T extends Block> SupplierTypedBlockWrapper<T> of(RegistrySupplier<T> result) {
        return new SupplierTypedBlockWrapper<>(result::get);
    }

    public static <T extends Block> SupplierTypedBlockWrapper<T> of(SupplierBlockWrapper result) {
        return new SupplierTypedBlockWrapper<>(() -> (T) result.get());
    }

    @Override
    public SupplierBlockWrapper asNonTyped() {
        return SupplierBlockWrapper.of(this::get);
    }

    @Override
    public T get() {
        return supplier.get();
    }
}

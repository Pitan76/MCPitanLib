package net.pitan76.mcpitanlib.midohra.block.entity;

import net.pitan76.mcpitanlib.api.registry.result.RegistrySupplier;
import net.minecraft.block.entity.BlockEntity;
import net.pitan76.mcpitanlib.api.registry.result.RegistryResult;
import net.pitan76.mcpitanlib.midohra.core.INonTypedSupplier;

import java.util.function.Supplier;

public class SupplierTypedBlockEntityWrapper<T extends BlockEntity> extends TypedBlockEntityWrapper<T> implements INonTypedSupplier<SupplierBlockEntityWrapper> {
    private final Supplier<T> supplier;

    protected SupplierTypedBlockEntityWrapper(Supplier<T> supplier) {
        super(null);
        this.supplier = supplier;
    }

    public static <T extends BlockEntity> SupplierTypedBlockEntityWrapper<T> of(Supplier<T> supplier) {
        return new SupplierTypedBlockEntityWrapper<>(supplier);
    }

    public static <T extends BlockEntity> SupplierTypedBlockEntityWrapper<T> of(RegistryResult<T> result) {
        return new SupplierTypedBlockEntityWrapper<>(result::get);
    }

    public static <T extends BlockEntity> SupplierTypedBlockEntityWrapper<T> of(RegistrySupplier<T> result) {
        return new SupplierTypedBlockEntityWrapper<>(result::get);
    }

    public static <T extends BlockEntity> SupplierTypedBlockEntityWrapper<T> of(SupplierBlockEntityWrapper result) {
        return new SupplierTypedBlockEntityWrapper<>(() -> (T) result.get());
    }

    @Override
    public SupplierBlockEntityWrapper asNonTyped() {
        return SupplierBlockEntityWrapper.of(this::get);
    }

    @Override
    public T get() {
        return supplier.get();
    }
}

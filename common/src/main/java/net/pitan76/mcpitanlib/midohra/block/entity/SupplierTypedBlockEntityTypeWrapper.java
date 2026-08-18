package net.pitan76.mcpitanlib.midohra.block.entity;

import net.pitan76.mcpitanlib.api.registry.result.RegistrySupplier;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.pitan76.mcpitanlib.api.registry.result.RegistryResult;
import net.pitan76.mcpitanlib.midohra.core.INonTypedSupplier;

import java.util.function.Supplier;

public class SupplierTypedBlockEntityTypeWrapper<T extends BlockEntity> extends TypedBlockEntityTypeWrapper<T> implements INonTypedSupplier<SupplierBlockEntityTypeWrapper> {
    private final Supplier<BlockEntityType<T>> supplier;

    protected SupplierTypedBlockEntityTypeWrapper(Supplier<BlockEntityType<T>> supplier) {
        super(null);
        this.supplier = supplier;
    }

    public static <T extends BlockEntity> SupplierTypedBlockEntityTypeWrapper<T> of(Supplier<BlockEntityType<T>> supplier) {
        return new SupplierTypedBlockEntityTypeWrapper<>(supplier);
    }

    public static <T extends BlockEntity> SupplierTypedBlockEntityTypeWrapper<T> of(RegistryResult<BlockEntityType<T>> result) {
        return new SupplierTypedBlockEntityTypeWrapper<>(result::get);
    }

    public static <T extends BlockEntity> SupplierTypedBlockEntityTypeWrapper<T> of(RegistrySupplier<BlockEntityType<T>> result) {
        return new SupplierTypedBlockEntityTypeWrapper<>(result::get);
    }

    public static <T extends BlockEntity> SupplierTypedBlockEntityTypeWrapper<T> of(SupplierBlockEntityTypeWrapper result) {
        return new SupplierTypedBlockEntityTypeWrapper<>(() -> (BlockEntityType<T>) result.get());
    }

    @Override
    public SupplierBlockEntityTypeWrapper asNonTyped() {
        return SupplierBlockEntityTypeWrapper.of(this::get);
    }

    @Override
    public BlockEntityType<T> get() {
        return supplier.get();
    }
}

package net.pitan76.mcpitanlib.midohra.block.entity;

import net.pitan76.mcpitanlib.api.registry.result.RegistrySupplier;
import net.minecraft.block.entity.BlockEntity;
import net.pitan76.mcpitanlib.api.registry.result.RegistryResult;
import net.pitan76.mcpitanlib.midohra.core.INonTypedSupplier;

import java.util.function.Supplier;

public class SupplierBlockEntityWrapper extends BlockEntityWrapper implements INonTypedSupplier<SupplierBlockEntityWrapper> {
    private final Supplier<BlockEntity> supplier;

    protected SupplierBlockEntityWrapper(Supplier<BlockEntity> supplier) {
        this.supplier = supplier;
    }

    public static SupplierBlockEntityWrapper of(Supplier<BlockEntity> supplier) {
        return new SupplierBlockEntityWrapper(supplier);
    }

    public static SupplierBlockEntityWrapper of(RegistryResult<BlockEntity> result) {
        return new SupplierBlockEntityWrapper(result::get);
    }

    public static SupplierBlockEntityWrapper of(RegistrySupplier<BlockEntity> result) {
        return new SupplierBlockEntityWrapper(result::get);
    }

    @Override
    public SupplierBlockEntityWrapper asNonTyped() {
        return this;
    }

    @Override
    public BlockEntity get() {
        return supplier.get();
    }
}

package net.pitan76.mcpitanlib.midohra.block.entity;

import net.minecraft.block.entity.BlockEntityType;
import net.pitan76.mcpitanlib.api.registry.result.RegistryResult;

import java.util.function.Supplier;

public class SupplierBlockEntityTypeWrapper extends BlockEntityTypeWrapper {
    private final Supplier<BlockEntityType<?>> supplier;

    protected SupplierBlockEntityTypeWrapper(Supplier<BlockEntityType<?>> supplier) {
        this.supplier = supplier;
    }

    public static SupplierBlockEntityTypeWrapper of(Supplier<BlockEntityType<?>> supplier) {
        return new SupplierBlockEntityTypeWrapper(supplier);
    }

    public static SupplierBlockEntityTypeWrapper of(RegistryResult<BlockEntityType<?>> result) {
        return new SupplierBlockEntityTypeWrapper(result::get);
    }

    @Override
    public BlockEntityType<?> get() {
        return supplier.get();
    }
}

package net.pitan76.mcpitanlib.midohra.block.entity;

import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.block.entity.BlockEntity;
import net.pitan76.mcpitanlib.api.registry.result.RegistryResult;

import java.util.function.Supplier;

public class SupplierBlockEntityWrapper extends BlockEntityWrapper {
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
    public BlockEntity get() {
        return supplier.get();
    }
}

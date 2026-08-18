package net.pitan76.mcpitanlib.midohra.block;

import net.pitan76.mcpitanlib.api.registry.result.RegistrySupplier;
import net.minecraft.block.Block;
import net.pitan76.mcpitanlib.api.registry.result.RegistryResult;
import net.pitan76.mcpitanlib.midohra.core.INonTypedSupplier;

import java.util.function.Supplier;

public class SupplierBlockWrapper extends BlockWrapper implements INonTypedSupplier<SupplierBlockWrapper> {
    private final Supplier<Block> supplier;

    protected SupplierBlockWrapper(Supplier<Block> supplier) {
        this.supplier = supplier;
    }

    public static SupplierBlockWrapper of(Supplier<Block> supplier) {
        return new SupplierBlockWrapper(supplier);
    }

    public static SupplierBlockWrapper of(RegistryResult<Block> result) {
        return new SupplierBlockWrapper(result::get);
    }

    public static SupplierBlockWrapper of(RegistrySupplier<Block> result) {
        return new SupplierBlockWrapper(result::get);
    }

    @Override
    public SupplierBlockWrapper asNonTyped() {
        return this;
    }

    @Override
    public Block get() {
        return supplier.get();
    }
}

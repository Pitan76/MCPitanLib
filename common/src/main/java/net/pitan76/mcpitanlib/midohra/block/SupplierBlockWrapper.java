package net.pitan76.mcpitanlib.midohra.block;

import net.minecraft.block.Block;

import java.util.function.Supplier;

public class SupplierBlockWrapper extends BlockWrapper {
    private final Supplier<Block> supplier;

    protected SupplierBlockWrapper(Supplier<Block> supplier) {
        this.supplier = supplier;
    }

    public static SupplierBlockWrapper of(Supplier<Block> supplier) {
        return new SupplierBlockWrapper(supplier);
    }

    @Override
    public Block get() {
        return supplier.get();
    }
}

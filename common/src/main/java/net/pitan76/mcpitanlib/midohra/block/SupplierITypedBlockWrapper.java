package net.pitan76.mcpitanlib.midohra.block;

import net.minecraft.block.Block;
import net.pitan76.mcpitanlib.api.block.ICompatBlock;
import net.pitan76.mcpitanlib.api.registry.result.RegistryResult;
import net.pitan76.mcpitanlib.midohra.core.INonTypedSupplier;
import net.pitan76.mcpitanlib.api.registry.result.RegistrySupplier;

import java.util.function.Supplier;

public class SupplierITypedBlockWrapper<T extends ICompatBlock> extends ITypedBlockWrapper<T> implements INonTypedSupplier<SupplierBlockWrapper> {
    private final Supplier<T> supplier;

    protected SupplierITypedBlockWrapper(Supplier<T> supplier) {
        super(null);
        this.supplier = supplier;
    }

    public static <T extends ICompatBlock> SupplierITypedBlockWrapper<T> of(BlockWrapper wrapper) {
        if (wrapper instanceof SupplierBlockWrapper) {
            SupplierBlockWrapper supplierWrapper = (SupplierBlockWrapper) wrapper;
            return SupplierITypedBlockWrapper.of(supplierWrapper);
        }

        return new SupplierITypedBlockWrapper<>(() -> (T) wrapper.get());
    }

    public static <T extends ICompatBlock> SupplierITypedBlockWrapper<T> of(Supplier<T> supplier) {
        return new SupplierITypedBlockWrapper<>(supplier);
    }

    public static <T extends ICompatBlock> SupplierITypedBlockWrapper<T> of(RegistryResult<T> result) {
        return new SupplierITypedBlockWrapper<>(result::get);
    }

    public static <T extends ICompatBlock> SupplierITypedBlockWrapper<T> of(RegistrySupplier<T> result) {
        return new SupplierITypedBlockWrapper<>(result::get);
    }

    public static <T extends ICompatBlock> SupplierITypedBlockWrapper<T> of(SupplierBlockWrapper result) {
        return new SupplierITypedBlockWrapper<>(() -> (T) result.get());
    }

    @Override
    public SupplierBlockWrapper asNonTyped() {
        return SupplierBlockWrapper.of(this::get);
    }

    @Override
    public Block get() {
        return (Block) supplier.get();
    }

    public T getICompat() {
        return supplier.get();
    }
}

package net.pitan76.mcpitanlib.midohra.block;

import net.minecraft.block.Block;
import net.pitan76.mcpitanlib.api.block.ICompatBlock;

public class ITypedBlockWrapper<T extends ICompatBlock> extends BlockWrapper {
    protected ITypedBlockWrapper(T block) {
        super((Block) block);
    }

    public static <T extends ICompatBlock> ITypedBlockWrapper<T> ofRaw(T item) {
        return new ITypedBlockWrapper<>(item);
    }

    public static <T extends ICompatBlock> ITypedBlockWrapper<T> of(BlockWrapper wrapper) {
        if (wrapper instanceof SupplierBlockWrapper) {
            SupplierBlockWrapper supplierWrapper = (SupplierBlockWrapper) wrapper;
            return SupplierITypedBlockWrapper.of(supplierWrapper);
        }

        return new ITypedBlockWrapper<>((T) wrapper.get());
    }

    @Override
    public Block get() {
        return super.get();
    }

    public T getICompat() {
        return (T) super.get();
    }
}

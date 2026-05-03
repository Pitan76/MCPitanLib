package net.pitan76.mcpitanlib.midohra.item;

import net.minecraft.item.Item;
import net.pitan76.mcpitanlib.api.item.ICompatItem;

public class ITypedItemWrapper<T extends ICompatItem> extends ItemWrapper {
    protected ITypedItemWrapper(T item) {
        super((Item) item);
    }

    public static <T extends ICompatItem> ITypedItemWrapper<T> ofRaw(T item) {
        return new ITypedItemWrapper<>(item);
    }

    public static <T extends ICompatItem> ITypedItemWrapper<T> of(ItemWrapper wrapper) {
        if (wrapper instanceof SupplierItemWrapper) {
            SupplierItemWrapper supplierWrapper = (SupplierItemWrapper) wrapper;
            return SupplierITypedItemWrapper.of(supplierWrapper);
        }

        return new ITypedItemWrapper<>((T) wrapper.get());
    }

    @Override
    public Item get() {
        return super.get();
    }

    public T getICompat() {
        return (T) super.get();
    }
}

package net.pitan76.mcpitanlib.midohra.item;

import net.minecraft.item.Item;
import net.pitan76.mcpitanlib.api.item.ICompatItem;
import net.pitan76.mcpitanlib.api.registry.result.RegistryResult;
import net.pitan76.mcpitanlib.midohra.core.INonTypedSupplier;
import net.pitan76.mcpitanlib.api.registry.result.RegistrySupplier;

import java.util.function.Supplier;

public class SupplierITypedItemWrapper<T extends ICompatItem> extends ITypedItemWrapper<T> implements INonTypedSupplier<SupplierItemWrapper> {
    private final Supplier<T> supplier;

    protected SupplierITypedItemWrapper(Supplier<T> supplier) {
        super(null);
        this.supplier = supplier;
    }

    public static <T extends ICompatItem> SupplierITypedItemWrapper<T> of(ItemWrapper wrapper) {
        if (wrapper instanceof SupplierItemWrapper) {
            SupplierItemWrapper supplierWrapper = (SupplierItemWrapper) wrapper;
            return SupplierITypedItemWrapper.of(supplierWrapper);
        }

        return new SupplierITypedItemWrapper<>(() -> (T) wrapper.get());
    }

    public static <T extends ICompatItem> SupplierITypedItemWrapper<T> of(Supplier<T> supplier) {
        return new SupplierITypedItemWrapper<>(supplier);
    }

    public static <T extends ICompatItem> SupplierITypedItemWrapper<T> of(RegistryResult<T> result) {
        return new SupplierITypedItemWrapper<>(result::get);
    }

    public static <T extends ICompatItem> SupplierITypedItemWrapper<T> of(RegistrySupplier<T> result) {
        return new SupplierITypedItemWrapper<>(result::get);
    }

    public static <T extends ICompatItem> SupplierITypedItemWrapper<T> of(SupplierItemWrapper result) {
        return new SupplierITypedItemWrapper<>(() -> (T) result.get());
    }

    @Override
    public SupplierItemWrapper asNonTyped() {
        return SupplierItemWrapper.of(this::get);
    }

    @Override
    public Item get() {
        return (Item) supplier.get();
    }

    public T getICompat() {
        return supplier.get();
    }
}

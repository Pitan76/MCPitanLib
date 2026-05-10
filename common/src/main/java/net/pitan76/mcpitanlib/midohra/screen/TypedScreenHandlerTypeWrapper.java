package net.pitan76.mcpitanlib.midohra.screen;

import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.pitan76.mcpitanlib.api.util.inventory.CompatPlayerInventory;

public class TypedScreenHandlerTypeWrapper<T extends ScreenHandler> extends ScreenHandlerTypeWrapper {
    protected TypedScreenHandlerTypeWrapper(ScreenHandlerType<T> type) {
        super(type);
    }

    public static <T extends ScreenHandler> TypedScreenHandlerTypeWrapper<T> ofRaw(ScreenHandlerType<T> type) {
        return new TypedScreenHandlerTypeWrapper<>(type);
    }

    public static <T extends ScreenHandler> TypedScreenHandlerTypeWrapper<T> of(ScreenHandlerTypeWrapper wrapper) {
        if (wrapper instanceof SupplierScreenHandlerTypeWrapper) {
            SupplierScreenHandlerTypeWrapper supplierWrapper = (SupplierScreenHandlerTypeWrapper) wrapper;
            return SupplierTypedScreenHandlerTypeWrapper.of(supplierWrapper);
        }

        return new TypedScreenHandlerTypeWrapper<>((ScreenHandlerType<T>) wrapper.get());
    }

    @Override
    public ScreenHandlerType<T> get() {
        return (ScreenHandlerType<T>) super.get();
    }

    public T create(int syncId, CompatPlayerInventory playerInventory) {
        return get().create(syncId, playerInventory.getRaw());
    }
}

package net.pitan76.mcpitanlib.midohra.screen;

import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.pitan76.mcpitanlib.api.util.inventory.CompatPlayerInventory;

public class TypedScreenHandlerTypeWrapper<T extends AbstractContainerMenu> extends ScreenHandlerTypeWrapper {
    protected TypedScreenHandlerTypeWrapper(MenuType<T> type) {
        super(type);
    }

    public static <T extends AbstractContainerMenu> TypedScreenHandlerTypeWrapper<T> ofRaw(MenuType<T> type) {
        return new TypedScreenHandlerTypeWrapper<>(type);
    }

    public static <T extends AbstractContainerMenu> TypedScreenHandlerTypeWrapper<T> of(ScreenHandlerTypeWrapper wrapper) {
        if (wrapper instanceof SupplierScreenHandlerTypeWrapper) {
            SupplierScreenHandlerTypeWrapper supplierWrapper = (SupplierScreenHandlerTypeWrapper) wrapper;
            return SupplierTypedScreenHandlerTypeWrapper.of(supplierWrapper);
        }

        return new TypedScreenHandlerTypeWrapper<>((MenuType<T>) wrapper.get());
    }

    @Override
    public MenuType<T> get() {
        return (MenuType<T>) super.get();
    }

    public T create(int syncId, CompatPlayerInventory playerInventory) {
        return get().create(syncId, playerInventory.getRaw());
    }
}

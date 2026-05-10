package net.pitan76.mcpitanlib.midohra.screen;

import net.minecraft.world.inventory.AbstractContainerMenu;

public class TypedScreenHandlerWrapper<T extends AbstractContainerMenu> extends ScreenHandlerWrapper {
    protected TypedScreenHandlerWrapper(T screenHandler) {
        super(screenHandler);
    }

    public static <T extends AbstractContainerMenu> TypedScreenHandlerWrapper<T> ofRaw(T screenHandler) {
        return new TypedScreenHandlerWrapper<>(screenHandler);
    }

    public static <T extends AbstractContainerMenu> TypedScreenHandlerWrapper<T> of(ScreenHandlerWrapper wrapper) {
        return new TypedScreenHandlerWrapper<>((T) wrapper.get());
    }
}

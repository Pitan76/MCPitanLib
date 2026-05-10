package net.pitan76.mcpitanlib.midohra.screen;

import net.minecraft.screen.ScreenHandler;

public class TypedScreenHandlerWrapper<T extends ScreenHandler> extends ScreenHandlerWrapper {
    protected TypedScreenHandlerWrapper(T screenHandler) {
        super(screenHandler);
    }

    public static <T extends ScreenHandler> TypedScreenHandlerWrapper<T> ofRaw(T screenHandler) {
        return new TypedScreenHandlerWrapper<>(screenHandler);
    }

    public static <T extends ScreenHandler> TypedScreenHandlerWrapper<T> of(ScreenHandlerWrapper wrapper) {
        return new TypedScreenHandlerWrapper<>((T) wrapper.get());
    }
}

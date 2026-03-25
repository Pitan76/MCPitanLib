package net.pitan76.mcpitanlib.api.util.inventory;

import net.minecraft.screen.ScreenHandler;

public class ContainerInventory<T extends ScreenHandler> extends CompatInventory {

    public final T screenHandler;

    public ContainerInventory(T screenHandler, int size) {
        super(size);
        this.screenHandler = screenHandler;
    }

    public T getScreenHandler() {
        return screenHandler;
    }
}

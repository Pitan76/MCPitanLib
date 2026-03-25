package net.pitan76.mcpitanlib.api.util.inventory;

import net.minecraft.world.inventory.AbstractContainerMenu;

public class ContainerInventory<T extends AbstractContainerMenu> extends CompatInventory {

    public final T screenHandler;

    public ContainerInventory(T screenHandler, int size) {
        super(size);
        this.screenHandler = screenHandler;
    }

    public T getScreenHandler() {
        return screenHandler;
    }
}

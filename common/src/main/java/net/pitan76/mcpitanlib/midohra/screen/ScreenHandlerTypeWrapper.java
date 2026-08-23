package net.pitan76.mcpitanlib.midohra.screen;

import net.pitan76.mcpitanlib.api.util.inventory.CompatPlayerInventory;

public class ScreenHandlerTypeWrapper {
    private final net.minecraft.world.inventory.MenuType<?> type;

    public static final ScreenHandlerTypeWrapper EMPTY = new ScreenHandlerTypeWrapper();

    protected ScreenHandlerTypeWrapper() {
        this.type = null;
    }

    protected ScreenHandlerTypeWrapper(net.minecraft.world.inventory.MenuType<?> screenHandlerType) {
        this.type = screenHandlerType;
    }

    public static ScreenHandlerTypeWrapper of(net.minecraft.world.inventory.MenuType<?> screenHandlerType) {
        return new ScreenHandlerTypeWrapper(screenHandlerType);
    }

    public static ScreenHandlerTypeWrapper of() {
        return EMPTY;
    }

    public net.minecraft.world.inventory.MenuType<?> get() {
        return type;
    }

    public boolean isEmpty() {
        return get() == null;
    }

    public boolean isPresent() {
        return get() != null;
    }

    public ScreenHandlerWrapper createScreenHandler(int syncId, net.minecraft.world.entity.player.Inventory playerInventory) {
        if (isEmpty()) return ScreenHandlerWrapper.EMPTY;
        return ScreenHandlerWrapper.of(get().create(syncId, playerInventory));
    }

    public ScreenHandlerWrapper createScreenHandler(int syncId, CompatPlayerInventory playerInventory) {
        return createScreenHandler(syncId, playerInventory.getRaw());
    }

    @Override
    public int hashCode() {
        return isEmpty() ? 0 : get().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof ScreenHandlerTypeWrapper)) return false;

        ScreenHandlerTypeWrapper other = (ScreenHandlerTypeWrapper) obj;
        if (isEmpty() && other.isEmpty()) return true;
        if (isEmpty() || other.isEmpty()) return false;

        return get().equals(other.get());
    }
}

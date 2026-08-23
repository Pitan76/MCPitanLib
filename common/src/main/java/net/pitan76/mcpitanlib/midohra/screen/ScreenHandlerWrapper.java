package net.pitan76.mcpitanlib.midohra.screen;

import net.pitan76.mcpitanlib.api.gui.SimpleScreenHandler;
import net.pitan76.mcpitanlib.api.util.ScreenHandlerUtil;
import net.pitan76.mcpitanlib.api.util.collection.ItemStackList;
import net.pitan76.mcpitanlib.midohra.item.ItemStack;

import java.util.Optional;

public class ScreenHandlerWrapper {
    private final net.minecraft.world.inventory.AbstractContainerMenu screenHandler;

    public static final ScreenHandlerWrapper EMPTY = new ScreenHandlerWrapper();

    protected ScreenHandlerWrapper() {
        this.screenHandler = null;
    }

    protected ScreenHandlerWrapper(net.minecraft.world.inventory.AbstractContainerMenu screenHandler) {
        this.screenHandler = screenHandler;
    }

    public static ScreenHandlerWrapper of(net.minecraft.world.inventory.AbstractContainerMenu screenHandler) {
        return new ScreenHandlerWrapper(screenHandler);
    }

    public static ScreenHandlerWrapper of() {
        return EMPTY;
    }

    public net.minecraft.world.inventory.AbstractContainerMenu get() {
        return screenHandler;
    }

    public ItemStackList getStacks() {
        return ItemStackList.of(get().getItems());
    }

    public ItemStack getCursorStack() {
        return ScreenHandlerUtil.getCursorStackM(get());
    }

    public void setCursorStack(ItemStack stack) {
        ScreenHandlerUtil.setCursorStackM(get(), stack);
    }

    public boolean isPresent() {
        return get() != null;
    }

    public boolean isEmpty() {
        return get() == null;
    }

    public int getSyncId() {
        if (isEmpty()) return -1;
        return get().containerId;
    }

    @Override
    public int hashCode() {
        return isEmpty() ? 0 : get().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof ScreenHandlerWrapper)) return false;

        ScreenHandlerWrapper other = (ScreenHandlerWrapper) obj;
        if (isEmpty() && other.isEmpty()) return true;
        if (isEmpty() || other.isEmpty()) return false;

        return get().equals(other.get());
    }

    public ScreenHandlerTypeWrapper getType() {
        if (isEmpty()) return ScreenHandlerTypeWrapper.EMPTY;
        return ScreenHandlerTypeWrapper.of(get().getType());
    }

    /**
     * instanceof check for the screen handler of this wrapper.
     * @param clazz the class of the screen handler to check
     * @return true if the screen handler of this wrapper is an instance of the given class, false otherwise
     */
    public boolean instanceOf(Class<?> clazz) {
        if (isEmpty()) return false;

        return clazz.isInstance(get());
    }

    /**
    * instanceof check for the screen handler of this wrapper.
    * @param wrapper the screen handler to check
    * @return true if the screen handler of this wrapper is an instance of the given screen handler, false otherwise
    */
    public boolean instanceOf(ScreenHandlerWrapper wrapper) {
        if (isEmpty()) return false;

        ScreenHandlerWrapper other = wrapper;
        if (other.isEmpty()) return false;

        Class<?> clazz = other.get().getClass();
        return clazz.isInstance(get());
    }

    public <T extends SimpleScreenHandler> T getSimpleScreenHandler(Class<T> clazz) {
        if (isEmpty()) return null;
        if (get() instanceof SimpleScreenHandler) {
            SimpleScreenHandler screenHandler = (SimpleScreenHandler) get();
            if (clazz.isInstance(screenHandler))
                return clazz.cast(screenHandler);
        }
        return null;
    }

    public <T extends SimpleScreenHandler> Optional<T> toSimpleScreenHandler(Class<T> clazz) {
        return Optional.ofNullable(getSimpleScreenHandler(clazz));
    }
}

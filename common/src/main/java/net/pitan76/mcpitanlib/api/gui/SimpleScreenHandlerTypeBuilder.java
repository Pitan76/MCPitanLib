package net.pitan76.mcpitanlib.api.gui;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.pitan76.mcpitanlib.api.gui.args.CreateMenuEvent;
import net.pitan76.mcpitanlib.midohra.screen.TypedScreenHandlerTypeWrapper;

public class SimpleScreenHandlerTypeBuilder<T extends AbstractContainerMenu> {

    private final Factory<T> factory;

    public SimpleScreenHandlerTypeBuilder(Factory<T> factory) {
        this.factory = factory;
    }

    public SimpleScreenHandlerTypeBuilder(Factory2<T> factory) {
        this.factory = factory;
    }

    public MenuType<T> build() {
        return build(factory);
    }

    public TypedScreenHandlerTypeWrapper<T> buildWrapper() {
        return TypedScreenHandlerTypeWrapper.ofRaw(build(factory));
    }

    @ExpectPlatform
    public static <T extends AbstractContainerMenu> MenuType<T> build(Factory<T> factory) {
        throw new AssertionError();
    }

    @FunctionalInterface
    public interface Factory<T extends AbstractContainerMenu> {
        T create(int syncId, Inventory inventory);
    }

    @FunctionalInterface
    public interface Factory2<T extends AbstractContainerMenu> extends Factory<T> {
        T create(CreateMenuEvent e);

        @Override
        default T create(int syncId, Inventory inventory) {
            return create(new CreateMenuEvent(syncId, inventory));
        }
    }
}

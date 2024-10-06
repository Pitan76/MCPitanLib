package net.pitan76.mcpitanlib.api.gui;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.pitan76.mcpitanlib.api.gui.args.CreateMenuEvent;

public class SimpleScreenHandlerTypeBuilder<T extends ScreenHandler> {

    private final Factory<T> factory;

    public SimpleScreenHandlerTypeBuilder(Factory<T> factory) {
        this.factory = factory;
    }

    public SimpleScreenHandlerTypeBuilder(Factory2<T> factory) {
        this.factory = factory;
    }

    public ScreenHandlerType<T> build() {
        return new ScreenHandlerType<>(factory::create);
    }

    @FunctionalInterface
    public interface Factory<T extends ScreenHandler> {
        T create(int syncId, PlayerInventory inventory);
    }

    @FunctionalInterface
    public interface Factory2<T extends ScreenHandler> extends Factory<T> {
        T create(CreateMenuEvent e);

        @Override
        default T create(int syncId, PlayerInventory inventory) {
            return create(new CreateMenuEvent(syncId, inventory));
        }
    }
}

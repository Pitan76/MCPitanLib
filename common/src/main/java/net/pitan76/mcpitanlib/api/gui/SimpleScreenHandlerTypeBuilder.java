package net.pitan76.mcpitanlib.api.gui;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.pitan76.mcpitanlib.api.gui.args.CreateMenuEvent;

public class SimpleScreenHandlerTypeBuilder<T extends AbstractContainerMenu> {

    private final Factory<T> factory;

    public SimpleScreenHandlerTypeBuilder(Factory<T> factory) {
        this.factory = factory;
    }

    public SimpleScreenHandlerTypeBuilder(Factory2<T> factory) {
        this.factory = factory;
    }

    public MenuType<T> build() {
        return new MenuType<>(factory::create, FeatureFlags.VANILLA_SET);
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

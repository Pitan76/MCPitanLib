package net.pitan76.mcpitanlib.api.gui;

import dev.architectury.registry.menu.MenuRegistry;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.pitan76.mcpitanlib.api.gui.args.CreateMenuEvent;

public class ExtendedScreenHandlerTypeBuilder<T extends ScreenHandler> {

    private final Factory<T> factory;

    public ExtendedScreenHandlerTypeBuilder(Factory<T> factory) {
        this.factory = factory;
    }

    public ExtendedScreenHandlerTypeBuilder(Factory2<T> factory) {
        this.factory = factory;
    }

    public ScreenHandlerType<T> build() {
        return MenuRegistry.ofExtended(factory::create);
    }

    @FunctionalInterface
    public interface Factory<T extends ScreenHandler> {
        T create(int syncId, PlayerInventory inventory, PacketByteBuf buf);
    }

    @FunctionalInterface
    public interface Factory2<T extends ScreenHandler> extends Factory<T> {
        T create(CreateMenuEvent e, PacketByteBuf buf);

        @Override
        default T create(int syncId, PlayerInventory inventory, PacketByteBuf buf) {
            return create(new CreateMenuEvent(syncId, inventory), buf);
        }
    }
}

package net.pitan76.mcpitanlib.api.gui;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.pitan76.mcpitanlib.api.gui.args.CreateMenuEvent;
import net.pitan76.mcpitanlib.midohra.screen.TypedScreenHandlerTypeWrapper;

public class ExtendedScreenHandlerTypeBuilder<T extends ScreenHandler> {

    public final Factory<T> factory;

    public ExtendedScreenHandlerTypeBuilder(Factory<T> factory) {
        this.factory = factory;
    }

    public ExtendedScreenHandlerTypeBuilder(Factory2<T> factory) {
        this.factory = factory;
    }

    public ExtendedScreenHandlerTypeBuilder(Factory3<T> factory) {
        this.factory = factory;
    }

    public ScreenHandlerType<T> build() {
        return build(this);
    }

    @ExpectPlatform
    public static <T extends ScreenHandler> ScreenHandlerType<T> build(ExtendedScreenHandlerTypeBuilder<T> builder) {
        throw new AssertionError();
    }

    public TypedScreenHandlerTypeWrapper<T> buildWrapper() {
        return TypedScreenHandlerTypeWrapper.ofRaw(build());
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

    @FunctionalInterface
    public interface Factory3<T extends ScreenHandler> extends Factory2<T> {
        T create(CreateMenuEvent e, net.pitan76.mcpitanlib.midohra.network.PacketByteBuf buf);

        @Override
        default T create(CreateMenuEvent e, PacketByteBuf buf) {
            if (buf == null) return create(e, (net.pitan76.mcpitanlib.midohra.network.PacketByteBuf) null);

            return create(e, net.pitan76.mcpitanlib.midohra.network.PacketByteBuf.of(buf));
        }
    }
}

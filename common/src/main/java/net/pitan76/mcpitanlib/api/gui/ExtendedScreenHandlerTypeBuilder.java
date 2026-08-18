package net.pitan76.mcpitanlib.api.gui;

import dev.architectury.injectables.annotations.ExpectPlatform;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.pitan76.mcpitanlib.api.gui.args.CreateMenuEvent;
import net.pitan76.mcpitanlib.midohra.screen.TypedScreenHandlerTypeWrapper;

public class ExtendedScreenHandlerTypeBuilder<T extends ScreenHandler> {

    private final Factory<T> factory;

    public ExtendedScreenHandlerTypeBuilder(Factory<T> factory) {
        this.factory = factory;
    }

    public ExtendedScreenHandlerTypeBuilder(Factory2<T> factory) {
        this.factory = factory;
    }

    public ExtendedScreenHandlerTypeBuilder(Factory3<T> factory) {
        this.factory = factory;
    }

    public static PacketCodec<ByteBuf, PacketByteBuf> CODEC = PacketCodecs.BYTE_ARRAY.xmap(
            (data) -> new PacketByteBuf(Unpooled.wrappedBuffer(data)),
            (buf) -> ByteBufUtil.getBytes(buf.unwrap()));

    public ScreenHandlerType<T> build() {
        return build(factory);
    }

    @ExpectPlatform
    public static <T extends ScreenHandler> ScreenHandlerType<T> build(Factory<T> factory) {
        throw new AssertionError();
    }

    public TypedScreenHandlerTypeWrapper<T> buildWrapper() {
        return TypedScreenHandlerTypeWrapper.ofRaw(build(factory));
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
            return create(e, net.pitan76.mcpitanlib.midohra.network.PacketByteBuf.of(buf));
        }
    }
}

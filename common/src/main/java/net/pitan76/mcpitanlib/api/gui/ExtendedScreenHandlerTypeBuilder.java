package net.pitan76.mcpitanlib.api.gui;

import dev.architectury.injectables.annotations.ExpectPlatform;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.pitan76.mcpitanlib.api.gui.args.CreateMenuEvent;
import net.pitan76.mcpitanlib.midohra.screen.TypedScreenHandlerTypeWrapper;

public class ExtendedScreenHandlerTypeBuilder<T extends AbstractContainerMenu> {

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

    public static StreamCodec<ByteBuf, FriendlyByteBuf> CODEC = ByteBufCodecs.BYTE_ARRAY.map(
            (data) -> new FriendlyByteBuf(Unpooled.wrappedBuffer(data))
            , (buf) -> ByteBufUtil.getBytes(buf.unwrap()));

    public MenuType<T> build() {
        return build(factory);
    }

    @ExpectPlatform
    public static <T extends AbstractContainerMenu> MenuType<T> build(Factory<T> factory) {
        throw new AssertionError();
    }

    public TypedScreenHandlerTypeWrapper<T> buildWrapper() {
        return TypedScreenHandlerTypeWrapper.ofRaw(build(factory));
    }

    @FunctionalInterface
    public interface Factory<T extends AbstractContainerMenu> {
        T create(int syncId, Inventory inventory, FriendlyByteBuf buf);
    }

    @FunctionalInterface
    public interface Factory2<T extends AbstractContainerMenu> extends Factory<T> {
        T create(CreateMenuEvent e, FriendlyByteBuf buf);

        @Override
        default T create(int syncId, Inventory inventory, FriendlyByteBuf buf) {
            return create(new CreateMenuEvent(syncId, inventory), buf);
        }
    }

    @FunctionalInterface
    public interface Factory3<T extends AbstractContainerMenu> extends Factory2<T> {
        T create(CreateMenuEvent e, net.pitan76.mcpitanlib.midohra.network.PacketByteBuf buf);

        @Override
        default T create(CreateMenuEvent e, FriendlyByteBuf buf) {
            if (buf == null) return create(e, (net.pitan76.mcpitanlib.midohra.network.PacketByteBuf) null);

            return create(e, net.pitan76.mcpitanlib.midohra.network.PacketByteBuf.of(buf));
        }
    }
}

package net.pitan76.mcpitanlib.api.gui.fabric;

import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.pitan76.mcpitanlib.api.gui.ExtendedScreenHandlerTypeBuilder;

public class ExtendedScreenHandlerTypeBuilderImpl {
    // Fabric APIの1.16.5版は公開のExtendedScreenHandlerTypeを持たず、
    // 登録と生成が一体のScreenHandlerRegistry.registerExtendedしか無いため、
    // 登録と分離できる内部実装のほうを使う
    public static <T extends ScreenHandler> ScreenHandlerType<T> build(final ExtendedScreenHandlerTypeBuilder<T> builder) {
        return new net.fabricmc.fabric.impl.screenhandler.ExtendedScreenHandlerType<T>(new ScreenHandlerRegistry.ExtendedClientHandlerFactory<T>() {
            @Override
            public T create(int syncId, PlayerInventory inventory, PacketByteBuf buf) {
                return builder.factory.create(syncId, inventory, buf);
            }
        });
    }
}

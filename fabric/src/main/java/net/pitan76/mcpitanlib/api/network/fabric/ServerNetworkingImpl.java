package net.pitan76.mcpitanlib.api.network.fabric;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.pitan76.mcpitanlib.api.network.ServerNetworking;

public class ServerNetworkingImpl {
    public static void send(ServerPlayerEntity player, Identifier identifier, PacketByteBuf buf) {
        ServerPlayNetworking.send(player, identifier, buf);
    }

    public static void registerReceiver(Identifier identifier, final ServerNetworking.ServerNetworkHandler handler) {
        ServerPlayNetworking.registerGlobalReceiver(identifier, new ServerPlayNetworking.PlayChannelHandler() {
            @Override
            public void receive(final MinecraftServer server, final ServerPlayerEntity player, ServerPlayNetworkHandler networkHandler, PacketByteBuf buf, PacketSender responseSender) {
                // ここはnettyのIOスレッドで呼ばれる。
                // bufもこのメソッドを抜けると無効になるのでコピーしてからサーバースレッドへ渡す。
                final PacketByteBuf copied = new PacketByteBuf(buf.copy());

                server.execute(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            handler.receive(server, player, copied);
                        } finally {
                            copied.release();
                        }
                    }
                });
            }
        });
    }
}

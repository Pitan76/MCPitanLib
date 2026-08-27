package net.pitan76.mcpitanlib.core.mc261.fabric;

import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerPlayer;
import net.pitan76.mcpitanlib.api.network.ServerNetworking;
import net.pitan76.mcpitanlib.core.network.BufPayload;

public class ServerPlayNetworkingImpl {

    public static void send(ServerPlayer player, CustomPacketPayload payload) {
        ServerPlayNetworking.send(player, payload);
    }

    public static void registerGlobalReceiver(CustomPacketPayload.Type<BufPayload> id, ServerNetworking.ServerNetworkHandler handler) {
        ServerPlayNetworking.registerGlobalReceiver(id, (payload, context) -> {
            FriendlyByteBuf buf = new FriendlyByteBuf(Unpooled.wrappedBuffer(payload.getData()));
            handler.receive(context.server(), context.player(), buf);
            buf.release();
        });
    }

    public static void registerS2CPayloadType(Identifier identifier) {
        PayloadTypeRegistryUtil.registerBoth(identifier);
    }
}

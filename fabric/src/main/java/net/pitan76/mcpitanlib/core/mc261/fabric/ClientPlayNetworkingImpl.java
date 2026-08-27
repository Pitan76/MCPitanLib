package net.pitan76.mcpitanlib.core.mc261.fabric;

import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import net.pitan76.mcpitanlib.api.network.ClientNetworking;
import net.pitan76.mcpitanlib.core.network.BufPayload;

public class ClientPlayNetworkingImpl {
    public static void send(CustomPacketPayload payload) {
        ClientPlayNetworking.send(payload);
    }

    public static void registerGlobalReceiver(BufPayload.Type<BufPayload> type, ClientNetworking.ClientNetworkHandler handler) {
        ClientPlayNetworking.registerGlobalReceiver(type, (payload, context) -> {
            FriendlyByteBuf buf = new FriendlyByteBuf(Unpooled.wrappedBuffer(payload.getData()));

            LocalPlayer player = null;
            if (context.player() instanceof LocalPlayer)
                player = context.player();

            handler.receive(Minecraft.getInstance(), player, buf);
            buf.release();
        });
    }

    public static void registerC2SPayloadType(Identifier identifier) {
        PayloadTypeRegistryUtil.registerBoth(identifier);
    }
}

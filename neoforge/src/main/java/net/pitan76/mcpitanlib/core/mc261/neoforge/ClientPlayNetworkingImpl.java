package net.pitan76.mcpitanlib.core.mc261.neoforge;

import io.netty.buffer.Unpooled;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import net.neoforged.neoforge.client.network.ClientPacketDistributor;
import net.pitan76.mcpitanlib.api.network.ClientNetworking;
import net.pitan76.mcpitanlib.core.network.BufPayload;

public class ClientPlayNetworkingImpl {
    public static void send(CustomPacketPayload payload) {
        ClientPacketDistributor.sendToServer(payload);
    }

    public static void registerGlobalReceiver(BufPayload.Type<BufPayload> type, ClientNetworking.ClientNetworkHandler handler) {
        NetworkPayloadRegistry.registerType(type.id());
        NetworkPayloadRegistry.clientHandlerMap.put(type.id(), (payload, context) -> {
            FriendlyByteBuf buf = new FriendlyByteBuf(Unpooled.wrappedBuffer(payload.getData()));

            LocalPlayer player = null;
            if (context.player() instanceof LocalPlayer)
                player = (LocalPlayer) context.player();

            handler.receive(Minecraft.getInstance(), player, buf);
            buf.release();
        });
    }

    public static void registerC2SPayloadType(Identifier identifier) {
        NetworkPayloadRegistry.registerType(identifier);
    }
}

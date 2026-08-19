package net.pitan76.mcpitanlib.core.network.fabric;

import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.MinecraftClient;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;
import net.pitan76.mcpitanlib.api.network.ClientNetworking;
import net.pitan76.mcpitanlib.core.network.BufPayload;


public class ClientPlayNetworkingImpl {

    public static void send(CustomPayload payload) {
        ClientPlayNetworking.send(payload);
    }

    public static void registerGlobalReceiver(CustomPayload.Id<BufPayload> id, ClientNetworking.ClientNetworkHandler handler) {
        FabricPayloadTypes.registerS2C(id.id());

        ClientPlayNetworking.registerGlobalReceiver(id, (payload, context) -> {
            PacketByteBuf buf = new PacketByteBuf(Unpooled.wrappedBuffer(payload.getData()));
            handler.receive(MinecraftClient.getInstance(), context.player(), buf);
            buf.release();
        });
    }

    public static void registerC2SPayloadType(Identifier identifier) {
        FabricPayloadTypes.registerC2S(identifier);
    }
}

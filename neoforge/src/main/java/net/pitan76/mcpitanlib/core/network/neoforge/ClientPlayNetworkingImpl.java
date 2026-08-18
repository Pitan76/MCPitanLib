package net.pitan76.mcpitanlib.core.network.neoforge;

import io.netty.buffer.Unpooled;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;
import net.neoforged.neoforge.client.network.ClientPacketDistributor;
import net.pitan76.mcpitanlib.api.network.ClientNetworking;
import net.pitan76.mcpitanlib.core.network.BufPayload;

public class ClientPlayNetworkingImpl {

    public static void send(CustomPayload payload) {
        ClientPacketDistributor.sendToServer(payload);
    }

    public static void registerGlobalReceiver(CustomPayload.Id<BufPayload> id, ClientNetworking.ClientNetworkHandler handler) {
        PayloadRegistry.registerClientHandler(id.id(), (payload, context) -> {
            PacketByteBuf buf = new PacketByteBuf(Unpooled.wrappedBuffer(payload.getData()));

            ClientPlayerEntity player = null;
            if (context.player() instanceof ClientPlayerEntity)
                player = (ClientPlayerEntity) context.player();

            handler.receive(MinecraftClient.getInstance(), player, buf);
            buf.release();
        });
    }

    public static void registerC2SPayloadType(Identifier identifier) {
        PayloadRegistry.registerType(identifier);
    }
}

package net.pitan76.mcpitanlib.core.network.fabric;

import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.pitan76.mcpitanlib.api.network.ServerNetworking;
import net.pitan76.mcpitanlib.core.network.BufPayload;


public class ServerPlayNetworkingImpl {

    public static void send(ServerPlayerEntity player, CustomPayload payload) {
        ServerPlayNetworking.send(player, payload);
    }

    public static void registerGlobalReceiver(CustomPayload.Id<BufPayload> id, ServerNetworking.ServerNetworkHandler handler) {
        FabricPayloadTypes.registerC2S(id.id());

        ServerPlayNetworking.registerGlobalReceiver(id, (payload, context) -> {
            PacketByteBuf buf = new PacketByteBuf(Unpooled.wrappedBuffer(payload.getData()));
            handler.receive(context.server(), context.player(), buf);
            buf.release();
        });
    }

    public static void registerS2CPayloadType(Identifier identifier) {
        FabricPayloadTypes.registerS2C(identifier);
    }
}

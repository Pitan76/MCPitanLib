package net.pitan76.mcpitanlib.core.network.neoforge;

import io.netty.buffer.Unpooled;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.neoforged.neoforge.network.PacketDistributor;
import net.pitan76.mcpitanlib.api.network.ServerNetworking;
import net.pitan76.mcpitanlib.core.network.BufPayload;

public class ServerPlayNetworkingImpl {

    public static void send(ServerPlayerEntity player, CustomPayload payload) {
        PacketDistributor.sendToPlayer(player, payload);
    }

    public static void registerGlobalReceiver(CustomPayload.Id<BufPayload> id, ServerNetworking.ServerNetworkHandler handler) {
        PayloadRegistry.registerServerHandler(id.id(), (payload, context) -> {
            PacketByteBuf buf = new PacketByteBuf(Unpooled.wrappedBuffer(payload.getData()));

            ServerPlayerEntity player = null;
            MinecraftServer server = null;
            if (context.player() instanceof ServerPlayerEntity) {
                player = (ServerPlayerEntity) context.player();
                server = player.getServer();
            }

            handler.receive(server, player, buf);
            buf.release();
        });
    }

    public static void registerS2CPayloadType(Identifier identifier) {
        PayloadRegistry.registerType(identifier);
    }
}

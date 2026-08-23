package net.pitan76.mcpitanlib.core.mc261.neoforge;

import io.netty.buffer.Unpooled;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.PacketDistributor;
import net.pitan76.mcpitanlib.api.network.ServerNetworking;
import net.pitan76.mcpitanlib.core.network.BufPayload;

public class ServerPlayNetworkingImpl {

    public static void send(ServerPlayer player, CustomPacketPayload payload) {
        PacketDistributor.sendToPlayer(player, payload);
    }

    public static void registerGlobalReceiver(CustomPacketPayload.Type<BufPayload> id, ServerNetworking.ServerNetworkHandler handler) {
        NetworkPayloadRegistry.registerType(id.id());
        NetworkPayloadRegistry.serverHandlerMap.put(id.id(), (payload, context) -> {
            FriendlyByteBuf buf = new FriendlyByteBuf(Unpooled.wrappedBuffer(payload.getData()));

            ServerPlayer player = null;
            MinecraftServer server = null;
            if (context.player() instanceof ServerPlayer serverPlayer) {
                player = serverPlayer;
                server = serverPlayer.level().getServer();
            }

            handler.receive(server, player, buf);

            buf.release();
        });
    }

    public static void registerS2CPayloadType(Identifier identifier) {
        NetworkPayloadRegistry.registerType(identifier);
    }
}

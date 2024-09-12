package net.pitan76.mcpitanlib.api.network;

import dev.architectury.impl.NetworkAggregator;
import dev.architectury.networking.NetworkManager;
import io.netty.buffer.Unpooled;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.pitan76.mcpitanlib.core.network.BufPayload;

import java.util.ArrayList;
import java.util.List;

public class ServerNetworking {
    public static void send(ServerPlayerEntity player, Identifier identifier, PacketByteBuf buf) {
        registerS2CPayloadType(identifier);

        BufPayload payload = new BufPayload(buf, identifier);
        NetworkManager.sendToPlayer(player, payload);
    }

    public static void send(Iterable<ServerPlayerEntity> players, Identifier identifier, PacketByteBuf buf) {
        registerS2CPayloadType(identifier);

        BufPayload payload = new BufPayload(buf, identifier);
        NetworkManager.sendToPlayers(players, payload);
    }

    public static void sendAll(MinecraftServer server, Identifier identifier, PacketByteBuf buf) {
        send(server.getPlayerManager().getPlayerList(), identifier, buf);
    }

    public static void registerReceiver(Identifier identifier, ServerNetworkHandler handler) {
        BufPayload.Id<BufPayload> id = BufPayload.id(identifier);
        NetworkManager.registerReceiver(NetworkManager.Side.C2S, id, BufPayload.getCodec(id), List.of(),
                (payload, context) -> {
                    PacketByteBuf buf = new PacketByteBuf(Unpooled.wrappedBuffer(payload.getData()));

                    ServerPlayerEntity player = null;
                    if (context.getPlayer() instanceof ServerPlayerEntity)
                        player = (ServerPlayerEntity) context.getPlayer();

                    handler.receive(context.getPlayer().getServer(), player, buf);
                    buf.release();
                });
    }

    private static final List<Identifier> registeredList = new ArrayList<>();

    public static void registerS2CPayloadType(Identifier identifier) {
        if (registeredList.contains(identifier)) return;
        registeredList.add(identifier);

        if (NetworkAggregator.S2C_CODECS.containsKey(identifier)) return;

        BufPayload.Id<BufPayload> id = BufPayload.id(identifier);
        NetworkManager.registerS2CPayloadType(id, BufPayload.getCodec(id));
    }

    @FunctionalInterface
    public interface ServerNetworkHandler {
        void receive(MinecraftServer server, ServerPlayerEntity player, PacketByteBuf buf);
    }
}

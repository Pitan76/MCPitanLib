package net.pitan76.mcpitanlib.api.network;

import dev.architectury.impl.NetworkAggregator;
import dev.architectury.networking.NetworkManager;
import io.netty.buffer.Unpooled;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.resources.Identifier;
import net.pitan76.mcpitanlib.core.network.BufPayload;

import java.util.ArrayList;
import java.util.List;

public class ServerNetworking {
    public static void send(ServerPlayer player, Identifier identifier, FriendlyByteBuf buf) {
        registerS2CPayloadType(identifier);

        BufPayload payload = new BufPayload(buf, identifier);
        NetworkManager.sendToPlayer(player, payload);
    }

    public static void send(Iterable<ServerPlayer> players, Identifier identifier, FriendlyByteBuf buf) {
        registerS2CPayloadType(identifier);

        BufPayload payload = new BufPayload(buf, identifier);
        NetworkManager.sendToPlayers(players, payload);
    }

    public static void sendAll(MinecraftServer server, Identifier identifier, FriendlyByteBuf buf) {
        send(server.getPlayerList().getPlayers(), identifier, buf);
    }

    public static void registerReceiver(Identifier identifier, ServerNetworkHandler handler) {
        BufPayload.Type<BufPayload> id = BufPayload.id(identifier);
        NetworkManager.registerReceiver(NetworkManager.Side.C2S, id, BufPayload.getCodec(id),
                (payload, context) -> {
                    FriendlyByteBuf buf = new FriendlyByteBuf(Unpooled.wrappedBuffer(payload.getData()));

                    ServerPlayer player = null;
                    if (context.getPlayer() instanceof ServerPlayer)
                        player = (ServerPlayer) context.getPlayer();

                    handler.receive(context.getPlayer().level().getServer(), player, buf);
                    buf.release();
                });
    }

    private static final List<Identifier> registeredList = new ArrayList<>();

    public static void registerS2CPayloadType(Identifier identifier) {
        if (registeredList.contains(identifier)) return;
        registeredList.add(identifier);

        if (NetworkAggregator.S2C_CODECS.containsKey(identifier)) return;

        BufPayload.Type<BufPayload> id = BufPayload.id(identifier);
        NetworkManager.registerS2CPayloadType(id, BufPayload.getCodec(id));
    }

    @FunctionalInterface
    public interface ServerNetworkHandler {
        void receive(MinecraftServer server, ServerPlayer player, FriendlyByteBuf buf);
    }
}

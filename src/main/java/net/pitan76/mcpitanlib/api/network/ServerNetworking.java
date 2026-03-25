package net.pitan76.mcpitanlib.api.network;

import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
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
        ServerPlayNetworking.send(player, payload);
    }

    public static void send(Iterable<ServerPlayer> players, Identifier identifier, FriendlyByteBuf buf) {
        registerS2CPayloadType(identifier);

        BufPayload payload = new BufPayload(buf, identifier);
        for (ServerPlayer player : players) {
            ServerPlayNetworking.send(player, payload);
        }
    }

    public static void sendAll(MinecraftServer server, Identifier identifier, FriendlyByteBuf buf) {
        send(server.getPlayerList().getPlayers(), identifier, buf);
    }

    public static void registerReceiver(Identifier identifier, ServerNetworkHandler handler) {
        BufPayload.Type<BufPayload> id = BufPayload.id(identifier);

        ServerPlayNetworking.registerGlobalReceiver(id, (payload, context) -> {
            FriendlyByteBuf buf = new FriendlyByteBuf(Unpooled.wrappedBuffer(payload.getData()));

            ServerPlayer player = null;
            if (context.player() instanceof ServerPlayer)
                player = context.player();

            handler.receive(context.player().level().getServer(), player, buf);
            buf.release();
        });
    }

    private static final List<Identifier> registeredList = new ArrayList<>();

    public static void registerS2CPayloadType(Identifier identifier) {
        if (registeredList.contains(identifier)) return;
        registeredList.add(identifier);

        BufPayload.Type<BufPayload> id = BufPayload.id(identifier);
        PayloadTypeRegistry.serverboundPlay().register(id, BufPayload.getCodec(id));
    }

    @FunctionalInterface
    public interface ServerNetworkHandler {
        void receive(MinecraftServer server, ServerPlayer player, FriendlyByteBuf buf);
    }
}

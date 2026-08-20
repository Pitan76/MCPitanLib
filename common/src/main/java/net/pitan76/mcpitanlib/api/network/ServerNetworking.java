package net.pitan76.mcpitanlib.api.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.resources.Identifier;
import net.pitan76.mcpitanlib.core.mc261.ServerPlayNetworking;
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
        registerS2CPayloadType(identifier);
        BufPayload.Type<BufPayload> id = BufPayload.id(identifier);

        ServerPlayNetworking.registerGlobalReceiver(id, (server, player, buf) -> {
            handler.receive(server, player, buf);
            buf.release();
        });
    }

    private static final List<Identifier> registeredList = new ArrayList<>();

    /**
     * S2Cのペイロードタイプを登録する。
     * <p>
     * <b>MODの初期化時(共通初期化)に呼ぶこと。</b> 送信時に初めて登録しても間に合わず、
     * 専用サーバーでBufPayloadがDiscardedPayloadへキャストできずクラッシュする。
     */
    public static void registerS2CPayloadType(Identifier identifier) {
        ServerPlayNetworking.registerS2CPayloadType(identifier);
    }

    @FunctionalInterface
    public interface ServerNetworkHandler {
        void receive(MinecraftServer server, ServerPlayer player, FriendlyByteBuf buf);
    }
}

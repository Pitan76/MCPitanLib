package net.pitan76.mcpitanlib.api.network.v2;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.pitan76.mcpitanlib.api.entity.Player;
import net.pitan76.mcpitanlib.api.network.PacketByteUtil;
import net.pitan76.mcpitanlib.api.network.v2.args.ServerReceiveEvent;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;
import net.pitan76.mcpitanlib.midohra.network.CompatPacketByteBuf;
import net.pitan76.mcpitanlib.midohra.server.MCServer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class ServerNetworking {
    public static void send(ServerPlayer serverPlayerEntity, CompatIdentifier id, FriendlyByteBuf buf) {
        net.pitan76.mcpitanlib.api.network.ServerNetworking.send(serverPlayerEntity, id.toMinecraft(), buf);
    }

    public static void sendByServerPlayerEntity(Iterable<ServerPlayer> players, CompatIdentifier id, FriendlyByteBuf buf) {
        net.pitan76.mcpitanlib.api.network.ServerNetworking.send(players, id.toMinecraft(), buf);
    }

    public static void send(Player player, CompatIdentifier id, FriendlyByteBuf buf) {
        Optional<ServerPlayer> optional = player.getServerPlayer();
        if (optional.isEmpty()) return;

        send(optional.get(), id, buf);
    }

    public static void send(Iterable<Player> players, CompatIdentifier id, FriendlyByteBuf buf) {
        List<ServerPlayer> list = new ArrayList<>();
        for (Player player : players) {
            Optional<ServerPlayer> optional = player.getServerPlayer();
            optional.ifPresent(list::add);
        }

        sendByServerPlayerEntity(list, id, buf);
    }

    public static void sendAll(MinecraftServer server, CompatIdentifier id, FriendlyByteBuf buf) {
        net.pitan76.mcpitanlib.api.network.ServerNetworking.sendAll(server, id.toMinecraft(), buf);
    }

    public static void sendAll(Level world, CompatIdentifier id, FriendlyByteBuf buf) {
        sendAll(world.getServer(), id, buf);
    }

    /**
     * S2Cのペイロードタイプを登録する。
     * <p>
     * <b>MODの初期化時(共通初期化)に呼ぶこと。</b> 送信時に初めて登録しても、
     * その時点ではプロトコルのコーデックが構築済みのため間に合わず、
     * 専用サーバーで送信時にBufPayloadがDiscardedPayloadへキャストできずクラッシュする。
     * (クライアント側はレシーバー登録時に登録されるため、シングルプレイでは表面化しない)
     *
     * @param id パケットのID
     */
    public static void registerS2CPayloadType(CompatIdentifier id) {
        net.pitan76.mcpitanlib.api.network.ServerNetworking.registerS2CPayloadType(id.toMinecraft());
    }

    public static void registerReceiver(CompatIdentifier id, Consumer<ServerReceiveEvent> consumer) {
        net.pitan76.mcpitanlib.api.network.ServerNetworking.registerReceiver(id.toMinecraft(), (server, player, buf) -> {
            consumer.accept(new ServerReceiveEvent(server, player, buf));
        });
    }

    public static void send(ServerPlayer serverPlayerEntity, CompatIdentifier id, CompatPacketByteBuf buf) {
        send(serverPlayerEntity, id, buf.getRaw());
    }

    public static void send(Player player, CompatIdentifier id, CompatPacketByteBuf buf) {
        send(player, id, buf.getRaw());
    }

    public static void send(Iterable<Player> players, CompatIdentifier id, CompatPacketByteBuf buf) {
        send(players, id, buf.getRaw());
    }

    public static void sendAll(MinecraftServer server, CompatIdentifier id, CompatPacketByteBuf buf) {
        sendAll(server, id, buf.getRaw());
    }

    public static void sendAll(Level world, CompatIdentifier id, CompatPacketByteBuf buf) {
        sendAll(world.getServer(), id, buf.getRaw());
    }

    public static void send(ServerPlayer serverPlayerEntity, CompatIdentifier id) {
        send(serverPlayerEntity, id, PacketByteUtil.create());
    }

    public static void send(Player player, CompatIdentifier id) {
        send(player, id, PacketByteUtil.create());
    }

    public static void send(Iterable<Player> players, CompatIdentifier id) {
        send(players, id, PacketByteUtil.create());
    }

    public static void sendAll(MinecraftServer server, CompatIdentifier id) {
        sendAll(server, id, PacketByteUtil.create());
    }

    public static void sendAll(Level world, CompatIdentifier id) {
        sendAll(world.getServer(), id, PacketByteUtil.create());
    }

    public static void sendAll(MCServer server, CompatIdentifier id, CompatPacketByteBuf buf) {
        sendAll(server.getRaw(), id, buf);
    }

    public static void sendAll(MCServer server, CompatIdentifier id) {
        sendAll(server.getRaw(), id);
    }

    public static void sendAll(net.pitan76.mcpitanlib.midohra.world.World world, CompatIdentifier id, CompatPacketByteBuf buf) {
        sendAll(world.getRaw(), id, buf);
    }

    public static void sendAll(net.pitan76.mcpitanlib.midohra.world.World world, CompatIdentifier id) {
        sendAll(world.getRaw(), id);
    }
}

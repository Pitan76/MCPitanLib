package net.pitan76.mcpitanlib.api.network.v2;

import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.World;
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
    public static void send(ServerPlayerEntity serverPlayerEntity, CompatIdentifier id, PacketByteBuf buf) {
        net.pitan76.mcpitanlib.api.network.ServerNetworking.send(serverPlayerEntity, id.toMinecraft(), buf);
    }

    public static void sendByServerPlayerEntity(Iterable<ServerPlayerEntity> players, CompatIdentifier id, PacketByteBuf buf) {
        net.pitan76.mcpitanlib.api.network.ServerNetworking.send(players, id.toMinecraft(), buf);
    }

    public static void send(Player player, CompatIdentifier id, PacketByteBuf buf) {
        Optional<ServerPlayerEntity> optional = player.getServerPlayer();
        if (!optional.isPresent()) return;

        send(optional.get(), id, buf);
    }

    public static void send(Iterable<Player> players, CompatIdentifier id, PacketByteBuf buf) {
        List<ServerPlayerEntity> list = new ArrayList<>();
        for (Player player : players) {
            Optional<ServerPlayerEntity> optional = player.getServerPlayer();
            optional.ifPresent(list::add);
        }

        sendByServerPlayerEntity(list, id, buf);
    }

    public static void sendAll(MinecraftServer server, CompatIdentifier id, PacketByteBuf buf) {
        net.pitan76.mcpitanlib.api.network.ServerNetworking.sendAll(server, id.toMinecraft(), buf);
    }

    public static void sendAll(World world, CompatIdentifier id, PacketByteBuf buf) {
        sendAll(world.getServer(), id, buf);
    }

    /**
     * S2Cのペイロードタイプを登録する。
     * <p>
     * このバージョンでは何もしない (1.20.5以降との互換のために存在する)。
     * 呼ぶ場合はMODの初期化時(共通初期化)に呼ぶこと。
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

    public static void send(ServerPlayerEntity serverPlayerEntity, CompatIdentifier id, CompatPacketByteBuf buf) {
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

    public static void sendAll(World world, CompatIdentifier id, CompatPacketByteBuf buf) {
        sendAll(world.getServer(), id, buf.getRaw());
    }

    public static void send(ServerPlayerEntity serverPlayerEntity, CompatIdentifier id) {
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

    public static void sendAll(World world, CompatIdentifier id) {
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

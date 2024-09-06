package net.pitan76.mcpitanlib.api.network.v2;

import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.World;
import net.pitan76.mcpitanlib.api.entity.Player;
import net.pitan76.mcpitanlib.api.network.v2.args.ServerReceiveEvent;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;

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
        if (optional.isEmpty()) return;

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

    public static void registerReceiver(CompatIdentifier id, Consumer<ServerReceiveEvent> consumer) {
        net.pitan76.mcpitanlib.api.network.ServerNetworking.registerReceiver(id.toMinecraft(), (server, player, buf) -> {
            consumer.accept(new ServerReceiveEvent(server, player, buf));
        });
    }
}

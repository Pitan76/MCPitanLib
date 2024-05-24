package net.pitan76.mcpitanlib.api.network;

import dev.architectury.impl.NetworkAggregator;
import dev.architectury.networking.NetworkManager;
import io.netty.buffer.ByteBufUtil;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

import static dev.architectury.impl.NetworkAggregator.C2S_TYPE;
import static dev.architectury.impl.NetworkAggregator.S2C_TYPE;
import static dev.architectury.networking.NetworkManager.Side.C2S;
import static dev.architectury.networking.NetworkManager.Side.S2C;

public class ServerNetworking {
    public static void send(ServerPlayerEntity player, Identifier identifier, PacketByteBuf buf) {
        CustomPayload payload = new NetworkAggregator.BufCustomPacketPayload(S2C_TYPE.get(identifier), ByteBufUtil.getBytes(buf));
        NetworkManager.sendToPlayer(player, payload);
    }

    public static void send(Iterable<ServerPlayerEntity> players, Identifier identifier, PacketByteBuf buf) {
        CustomPayload payload = new NetworkAggregator.BufCustomPacketPayload(S2C_TYPE.get(identifier), ByteBufUtil.getBytes(buf));
        NetworkManager.sendToPlayers(players, payload);
    }


    public static void sendAll(MinecraftServer server, Identifier identifier, PacketByteBuf buf) {
        send(server.getPlayerManager().getPlayerList(), identifier, buf);
    }

    public static void registerReceiver(Identifier identifier, ServerNetworkHandler handler) {
        CustomPayload.Id<NetworkAggregator.BufCustomPacketPayload> type = CustomPayload.id(identifier.toString());
        // Todo: ごみ
        NetworkManager.registerReceiver(S2C, type, NetworkAggregator.BufCustomPacketPayload.streamCodec(type), ((buf, context) -> handler.receive(context.getPlayer().getServer(), (ServerPlayerEntity) context.getPlayer(), PacketByteUtil.create())));
    }

    @FunctionalInterface
    public interface ServerNetworkHandler {
        void receive(MinecraftServer server, ServerPlayerEntity player, PacketByteBuf buf);
    }
}

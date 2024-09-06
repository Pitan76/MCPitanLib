package net.pitan76.mcpitanlib.api.network;

import dev.architectury.impl.NetworkAggregator;
import dev.architectury.networking.NetworkManager;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

import java.util.List;

import static dev.architectury.impl.NetworkAggregator.S2C_TYPE;

import static dev.architectury.impl.NetworkAggregator.BufCustomPacketPayload;

public class ServerNetworking {
    public static void send(ServerPlayerEntity player, Identifier identifier, PacketByteBuf buf) {
        if (!S2C_TYPE.containsKey(identifier)) {
            CustomPayload.Id type = new CustomPayload.Id<>(identifier);
            S2C_TYPE.put(identifier, type);

            //if (!NetworkAggregator.S2C_CODECS.containsKey(type))
            //    NetworkAggregator.registerS2CType(type, BufCustomPacketPayload.streamCodec(type), List.of());
        }

        CustomPayload payload = new BufCustomPacketPayload(S2C_TYPE.get(identifier), ByteBufUtil.getBytes(buf));
        NetworkManager.sendToPlayer(player, payload);
    }

    public static void send(Iterable<ServerPlayerEntity> players, Identifier identifier, PacketByteBuf buf) {
        if (!S2C_TYPE.containsKey(identifier)) {
            CustomPayload.Id<BufCustomPacketPayload> type = new CustomPayload.Id<>(identifier);
            S2C_TYPE.put(identifier, type);

            //if (!NetworkAggregator.S2C_CODECS.containsKey(type))
            //    NetworkAggregator.registerS2CType(type, BufCustomPacketPayload.streamCodec(type), List.of());
        }
        CustomPayload payload = new BufCustomPacketPayload(S2C_TYPE.get(identifier), ByteBufUtil.getBytes(buf));
        NetworkManager.sendToPlayers(players, payload);
    }

    public static void sendAll(MinecraftServer server, Identifier identifier, PacketByteBuf buf) {
        send(server.getPlayerManager().getPlayerList(), identifier, buf);
    }

    public static void registerReceiver(Identifier identifier, ServerNetworkHandler handler) {
        CustomPayload.Id<NetworkAggregator.BufCustomPacketPayload> type = new CustomPayload.Id<>(identifier);
        NetworkManager.registerReceiver(NetworkManager.Side.C2S, type, BufCustomPacketPayload.streamCodec(type), List.of(),
                (value, context) -> {
                    RegistryByteBuf buf = new RegistryByteBuf(Unpooled.wrappedBuffer(value.payload()), context.registryAccess());
                    ServerPlayerEntity player = null;
                    if (context.getPlayer() instanceof ServerPlayerEntity)
                        player = (ServerPlayerEntity) context.getPlayer();

                    handler.receive(context.getPlayer().getServer(), player, buf);
                    buf.release();
        });
    }

    @FunctionalInterface
    public interface ServerNetworkHandler {
        void receive(MinecraftServer server, ServerPlayerEntity player, PacketByteBuf buf);
    }
}

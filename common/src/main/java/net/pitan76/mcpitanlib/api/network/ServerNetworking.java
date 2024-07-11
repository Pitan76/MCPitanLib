package net.pitan76.mcpitanlib.api.network;

import dev.architectury.impl.NetworkAggregator;
import dev.architectury.networking.NetworkManager;
import dev.architectury.networking.simple.BaseC2SMessage;
import dev.architectury.networking.simple.MessageDecoder;
import dev.architectury.networking.simple.SimpleNetworkManager;
import io.netty.buffer.ByteBufUtil;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

import static dev.architectury.impl.NetworkAggregator.S2C_TYPE;

public class ServerNetworking {
    public static void send(ServerPlayerEntity player, Identifier identifier, PacketByteBuf buf) {
        if (!S2C_TYPE.containsKey(identifier))
            S2C_TYPE.put(identifier, new CustomPayload.Id<>(identifier));
        CustomPayload payload = new NetworkAggregator.BufCustomPacketPayload(S2C_TYPE.get(identifier), ByteBufUtil.getBytes(buf));
        NetworkManager.sendToPlayer(player, payload);
    }

    public static void send(Iterable<ServerPlayerEntity> players, Identifier identifier, PacketByteBuf buf) {
        if (!S2C_TYPE.containsKey(identifier))
            S2C_TYPE.put(identifier, new CustomPayload.Id<>(identifier));
        CustomPayload payload = new NetworkAggregator.BufCustomPacketPayload(S2C_TYPE.get(identifier), ByteBufUtil.getBytes(buf));
        NetworkManager.sendToPlayers(players, payload);
    }

    public static void sendAll(MinecraftServer server, Identifier identifier, PacketByteBuf buf) {
        send(server.getPlayerManager().getPlayerList(), identifier, buf);
    }

    public static void registerReceiver(Identifier identifier, ServerNetworkHandler handler) {
        SimpleNetworkManager.create(identifier.getNamespace()).registerC2S(identifier.getPath(), new MessageDecoder<>() {
            @Override
            public BaseC2SMessage decode(RegistryByteBuf buf) {
                return null;
            }

            @Override
            public NetworkManager.NetworkReceiver<RegistryByteBuf> createReceiver() {
                return ((buf, context) -> handler.receive(context.getPlayer().getServer(), (ServerPlayerEntity) context.getPlayer(), buf));
            }
        });
    }

    @FunctionalInterface
    public interface ServerNetworkHandler {
        void receive(MinecraftServer server, ServerPlayerEntity player, PacketByteBuf buf);
    }
}

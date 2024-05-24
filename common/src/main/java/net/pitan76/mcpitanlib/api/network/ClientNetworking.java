package net.pitan76.mcpitanlib.api.network;

import dev.architectury.impl.NetworkAggregator;
import dev.architectury.networking.NetworkManager;
import io.netty.buffer.ByteBufUtil;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

import static dev.architectury.impl.NetworkAggregator.C2S_TYPE;
import static dev.architectury.networking.NetworkManager.Side.S2C;

public class ClientNetworking {
    public static void send(Identifier identifier, PacketByteBuf buf) {
        CustomPayload payload = new NetworkAggregator.BufCustomPacketPayload(C2S_TYPE.get(identifier), ByteBufUtil.getBytes(buf));
        NetworkManager.sendToServer(payload);
    }

    public static void registerReceiver(Identifier identifier, ClientNetworkHandler handler) {
        CustomPayload.Id<NetworkAggregator.BufCustomPacketPayload> type = CustomPayload.id(identifier.toString());
        // Todo: ごみ
        NetworkManager.registerReceiver(S2C, type, NetworkAggregator.BufCustomPacketPayload.streamCodec(type), ((buf, context) -> handler.receive(MinecraftClient.getInstance(), MinecraftClient.getInstance().player, PacketByteUtil.create())));
    }

    @FunctionalInterface
    public interface ClientNetworkHandler {
        void receive(MinecraftClient client, ClientPlayerEntity player, PacketByteBuf buf);
    }
}

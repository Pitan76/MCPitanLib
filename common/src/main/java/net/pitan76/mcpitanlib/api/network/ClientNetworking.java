package net.pitan76.mcpitanlib.api.network;

import dev.architectury.impl.NetworkAggregator;
import dev.architectury.networking.NetworkManager;
import dev.architectury.networking.simple.BaseS2CMessage;
import dev.architectury.networking.simple.MessageDecoder;
import dev.architectury.networking.simple.SimpleNetworkManager;
import io.netty.buffer.ByteBufUtil;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

import static dev.architectury.impl.NetworkAggregator.C2S_TYPE;

public class ClientNetworking {
    public static void send(Identifier identifier, PacketByteBuf buf) {
        if (!C2S_TYPE.containsKey(identifier))
            C2S_TYPE.put(identifier, new CustomPayload.Id<>(identifier));
        CustomPayload payload = new NetworkAggregator.BufCustomPacketPayload(C2S_TYPE.get(identifier), ByteBufUtil.getBytes(buf));
        NetworkManager.sendToServer(payload);
    }

    public static void registerReceiver(Identifier identifier, ClientNetworkHandler handler) {
        SimpleNetworkManager.create(identifier.getNamespace()).registerS2C(identifier.getPath(), new MessageDecoder<>() {
            @Override
            public BaseS2CMessage decode(RegistryByteBuf buf) {
                return null;
            }

            @Override
            public NetworkManager.NetworkReceiver<RegistryByteBuf> createReceiver() {
                return ((buf, context) -> handler.receive(MinecraftClient.getInstance(), MinecraftClient.getInstance().player, buf));
            }
        });
    }

    @FunctionalInterface
    public interface ClientNetworkHandler {
        void receive(MinecraftClient client, ClientPlayerEntity player, PacketByteBuf buf);
    }
}

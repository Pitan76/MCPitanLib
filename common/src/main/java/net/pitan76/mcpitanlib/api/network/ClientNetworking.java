package net.pitan76.mcpitanlib.api.network;

import dev.architectury.impl.NetworkAggregator;
import dev.architectury.networking.NetworkManager;
import dev.architectury.networking.transformers.PacketTransformer;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.Objects;

import static dev.architectury.impl.NetworkAggregator.*;

public class ClientNetworking {
    public static void send(Identifier identifier, PacketByteBuf buf) {
        if (!C2S_TYPE.containsKey(identifier)) {
            CustomPayload.Id type = new CustomPayload.Id<>(identifier);
            C2S_TYPE.put(identifier, type);
            //registerC2SType(type, NetworkAggregator.BufCustomPacketPayload.streamCodec(type), List.of());
        }
        CustomPayload payload = new NetworkAggregator.BufCustomPacketPayload(C2S_TYPE.get(identifier), ByteBufUtil.getBytes(buf));
        NetworkManager.sendToServer(payload);
    }

    public static void registerReceiver(Identifier identifier, ClientNetworkHandler handler) {
        CustomPayload.Id<NetworkAggregator.BufCustomPacketPayload> type = new CustomPayload.Id<>(identifier);
        NetworkManager.registerReceiver(NetworkManager.Side.S2C, type, BufCustomPacketPayload.streamCodec(type), List.of(),
                (value, context) -> {
                    RegistryByteBuf buf = new RegistryByteBuf(Unpooled.wrappedBuffer(value.payload()), context.registryAccess());
                    ClientPlayerEntity player = null;
                    if (context.getPlayer() instanceof ClientPlayerEntity)
                        player = (ClientPlayerEntity) context.getPlayer();

                    handler.receive(MinecraftClient.getInstance(), player, buf);
                    buf.release();
                });
    }

    @FunctionalInterface
    public interface ClientNetworkHandler {
        void receive(MinecraftClient client, ClientPlayerEntity player, PacketByteBuf buf);
    }

    public static void registerC2SType(CustomPayload.Id<BufCustomPacketPayload> type, PacketCodec<? super RegistryByteBuf, BufCustomPacketPayload> codec, List<PacketTransformer> packetTransformers) {
        Objects.requireNonNull(type, "Cannot register a null type!");
        packetTransformers = Objects.requireNonNullElse(packetTransformers, List.of());
        C2S_CODECS.put(type.id(), (PacketCodec<ByteBuf, ?>) codec);
        C2S_TRANSFORMERS.put(type.id(), PacketTransformer.concat(packetTransformers));
    }
}

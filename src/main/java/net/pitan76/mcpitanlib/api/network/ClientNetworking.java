package net.pitan76.mcpitanlib.api.network;

import dev.architectury.networking.NetworkManager;
import dev.architectury.networking.transformers.PacketTransformer;
import io.netty.buffer.Unpooled;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import net.pitan76.mcpitanlib.core.network.BufPayload;

import java.util.List;

import static dev.architectury.impl.NetworkAggregator.*;

public class ClientNetworking {
    public static void send(Identifier identifier, FriendlyByteBuf buf) {
        /*
        if (!C2S_TYPE.containsKey(identifier)) {
            CustomPayload.Id type = new CustomPayload.Id<>(identifier);
            C2S_TYPE.put(identifier, type);
            //registerC2SType(type, NetworkAggregator.BufCustomPacketPayload.streamCodec(type), List.of());
        }
         */

        BufPayload payload = new BufPayload(buf, identifier);
        NetworkManager.sendToServer(payload);
    }

    public static void registerReceiver(Identifier identifier, ClientNetworkHandler handler) {
        BufPayload.Type<BufPayload> id = BufPayload.id(identifier);

        NetworkManager.registerReceiver(NetworkManager.Side.S2C, id, BufPayload.getCodec(id),
                (payload, context) -> {
                    FriendlyByteBuf buf = new FriendlyByteBuf(Unpooled.wrappedBuffer(payload.getData()));

                    LocalPlayer player = null;
                    if (context.getPlayer() instanceof LocalPlayer)
                        player = (LocalPlayer) context.getPlayer();

                    handler.receive(Minecraft.getInstance(), player, buf);
                    buf.release();
                });
    }

    @FunctionalInterface
    public interface ClientNetworkHandler {
        void receive(Minecraft client, LocalPlayer player, FriendlyByteBuf buf);
    }


    public static void registerC2SType(CustomPacketPayload.Type<BufCustomPacketPayload> type, StreamCodec<? super RegistryFriendlyByteBuf, BufCustomPacketPayload> codec, List<PacketTransformer> packetTransformers) {
//        Objects.requireNonNull(type, "Cannot register a null type!");
//        packetTransformers = Objects.requireNonNullElse(packetTransformers, List.of());
//        C2S_CODECS.put(type.id(), (PacketCodec<ByteBuf, ?>) codec);
//        C2S_TRANSFORMERS.put(type.id(), PacketTransformer.concat(packetTransformers));
    }
}

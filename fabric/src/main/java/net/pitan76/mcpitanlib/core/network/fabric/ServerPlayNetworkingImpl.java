package net.pitan76.mcpitanlib.core.network.fabric;

import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.pitan76.mcpitanlib.api.network.ServerNetworking;
import net.pitan76.mcpitanlib.core.network.BufPayload;

import java.util.ArrayList;
import java.util.List;

public class ServerPlayNetworkingImpl {

    public static void send(ServerPlayerEntity player, CustomPayload payload) {
        ServerPlayNetworking.send(player, payload);
    }

    public static void registerGlobalReceiver(CustomPayload.Id<BufPayload> id, ServerNetworking.ServerNetworkHandler handler) {
        registerC2SPayloadType(id.id());

        ServerPlayNetworking.registerGlobalReceiver(id, (payload, context) -> {
            PacketByteBuf buf = new PacketByteBuf(Unpooled.wrappedBuffer(payload.getData()));
            handler.receive(context.server(), context.player(), buf);
            buf.release();
        });
    }

    private static final List<Identifier> s2cRegisteredList = new ArrayList<>();
    private static final List<Identifier> c2sRegisteredList = new ArrayList<>();

    public static void registerS2CPayloadType(Identifier identifier) {
        if (s2cRegisteredList.contains(identifier)) return;
        s2cRegisteredList.add(identifier);

        BufPayload.Id<BufPayload> id = BufPayload.id(identifier);
        PayloadTypeRegistry.playS2C().register(id, BufPayload.getCodec(id));
    }

    private static void registerC2SPayloadType(Identifier identifier) {
        if (c2sRegisteredList.contains(identifier)) return;
        c2sRegisteredList.add(identifier);

        BufPayload.Id<BufPayload> id = BufPayload.id(identifier);
        PayloadTypeRegistry.playC2S().register(id, BufPayload.getCodec(id));
    }
}

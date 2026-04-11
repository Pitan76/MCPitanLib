package net.pitan76.mcpitanlib.api.network;

import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import net.pitan76.mcpitanlib.core.network.BufPayload;

import java.util.ArrayList;
import java.util.List;

public class ClientNetworking {
    public static void send(Identifier identifier, FriendlyByteBuf buf) {
        registerC2SPayloadType(identifier);

        BufPayload payload = new BufPayload(buf, identifier);
        ClientPlayNetworking.send(payload);
    }

    public static void registerReceiver(Identifier identifier, ClientNetworkHandler handler) {
        BufPayload.Type<BufPayload> id = BufPayload.id(identifier);
        registerC2SPayloadType(identifier);

        ClientPlayNetworking.registerGlobalReceiver(id, (payload, context) -> {
            FriendlyByteBuf buf = new FriendlyByteBuf(Unpooled.wrappedBuffer(payload.getData()));

            LocalPlayer player = null;
            if (context.player() instanceof LocalPlayer)
                player = context.player();

            handler.receive(Minecraft.getInstance(), player, buf);
            buf.release();
        });
    }

    @FunctionalInterface
    public interface ClientNetworkHandler {
        void receive(Minecraft client, LocalPlayer player, FriendlyByteBuf buf);
    }

    private static final List<Identifier> registeredList = new ArrayList<>();

    public static void registerC2SPayloadType(Identifier identifier) {
        if (registeredList.contains(identifier)) return;
        registeredList.add(identifier);

        BufPayload.Type<BufPayload> id = BufPayload.id(identifier);
        PayloadTypeRegistry.clientboundPlay().register(id, BufPayload.getCodec(id));
    }
}

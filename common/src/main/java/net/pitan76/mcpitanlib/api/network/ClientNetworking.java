package net.pitan76.mcpitanlib.api.network;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;
import net.pitan76.mcpitanlib.core.network.BufPayload;
import net.pitan76.mcpitanlib.core.network.ClientPlayNetworking;

public class ClientNetworking {
    public static void send(Identifier identifier, PacketByteBuf buf) {
        registerC2SPayloadType(identifier);

        BufPayload payload = new BufPayload(buf, identifier);
        ClientPlayNetworking.send(payload);
    }

    public static void registerReceiver(Identifier identifier, ClientNetworkHandler handler) {
        BufPayload.Id<BufPayload> id = BufPayload.id(identifier);
        ClientPlayNetworking.registerGlobalReceiver(id, handler);
    }

    public static void registerC2SPayloadType(Identifier identifier) {
        ClientPlayNetworking.registerC2SPayloadType(identifier);
    }

    @FunctionalInterface
    public interface ClientNetworkHandler {
        void receive(MinecraftClient client, ClientPlayerEntity player, PacketByteBuf buf);
    }
}

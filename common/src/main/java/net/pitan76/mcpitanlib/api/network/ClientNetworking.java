package net.pitan76.mcpitanlib.api.network;

import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.Identifier;
import net.pitan76.mcpitanlib.core.mc261.ClientPlayNetworking;
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

        ClientPlayNetworking.registerGlobalReceiver(id, handler);
    }

    @FunctionalInterface
    public interface ClientNetworkHandler {
        void receive(Minecraft client, LocalPlayer player, FriendlyByteBuf buf);
    }

    private static final List<Identifier> registeredList = new ArrayList<>();

    public static void registerC2SPayloadType(Identifier identifier) {
        if (registeredList.contains(identifier)) return;
        registeredList.add(identifier);

        ClientPlayNetworking.registerC2SPayloadType(identifier);
    }
}

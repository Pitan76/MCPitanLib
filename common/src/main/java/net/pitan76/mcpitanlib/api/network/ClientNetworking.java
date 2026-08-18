package net.pitan76.mcpitanlib.api.network;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;

public class ClientNetworking {
    @ExpectPlatform
    public static void send(Identifier identifier, PacketByteBuf buf) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static void registerReceiver(Identifier identifier, ClientNetworkHandler handler) {
        throw new AssertionError();
    }

    @FunctionalInterface
    public interface ClientNetworkHandler {
        void receive(MinecraftClient client, ClientPlayerEntity player, PacketByteBuf buf);
    }
}

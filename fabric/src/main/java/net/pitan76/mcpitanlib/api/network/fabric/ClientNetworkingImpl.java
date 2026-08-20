package net.pitan76.mcpitanlib.api.network.fabric;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;
import net.pitan76.mcpitanlib.api.network.ClientNetworking;

@Environment(EnvType.CLIENT)
public class ClientNetworkingImpl {
    public static void send(Identifier identifier, PacketByteBuf buf) {
        ClientPlayNetworking.send(identifier, buf);
    }

    public static void registerReceiver(Identifier identifier, final ClientNetworking.ClientNetworkHandler handler) {
        ClientPlayNetworking.registerGlobalReceiver(identifier, new ClientPlayNetworking.PlayChannelHandler() {
            @Override
            public void receive(MinecraftClient client, ClientPlayNetworkHandler networkHandler, PacketByteBuf buf, PacketSender responseSender) {
                handler.receive(client, client.player, buf);
            }
        });
    }
}

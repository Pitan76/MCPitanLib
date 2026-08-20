package net.pitan76.mcpitanlib.api.network.fabric;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.pitan76.mcpitanlib.api.network.ServerNetworking;

public class ServerNetworkingImpl {
    public static void send(ServerPlayerEntity player, Identifier identifier, PacketByteBuf buf) {
        ServerPlayNetworking.send(player, identifier, buf);
    }

    public static void registerReceiver(Identifier identifier, final ServerNetworking.ServerNetworkHandler handler) {
        ServerPlayNetworking.registerGlobalReceiver(identifier, new ServerPlayNetworking.PlayChannelHandler() {
            @Override
            public void receive(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler networkHandler, PacketByteBuf buf, PacketSender responseSender) {
                handler.receive(server, player, buf);
            }
        });
    }
}

package net.pitan76.mcpitanlib.api.event.v0.event.fabric;

import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.pitan76.mcpitanlib.api.event.v0.EventRegistry;

public class ServerConnectionEventImpl {
    public static void join(final EventRegistry.ServerConnection.PlayerJoin state) {
        ServerPlayConnectionEvents.JOIN.register(new ServerPlayConnectionEvents.Join() {
            @Override
            public void onPlayReady(ServerPlayNetworkHandler handler, PacketSender sender, MinecraftServer server) {
                state.join(handler.player);
            }
        });
    }

    public static void quit(final EventRegistry.ServerConnection.PlayerQuit state) {
        ServerPlayConnectionEvents.DISCONNECT.register(new ServerPlayConnectionEvents.Disconnect() {
            @Override
            public void onPlayDisconnect(ServerPlayNetworkHandler handler, MinecraftServer server) {
                state.quit(handler.player);
            }
        });
    }
}

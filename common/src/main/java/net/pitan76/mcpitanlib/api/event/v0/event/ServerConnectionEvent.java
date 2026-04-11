package net.pitan76.mcpitanlib.api.event.v0.event;

import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.pitan76.mcpitanlib.api.event.v0.EventRegistry;

public class ServerConnectionEvent {
    // Architectury: PlayerEvent
    public static void join(EventRegistry.ServerConnection.PlayerJoin state) {
        ServerPlayConnectionEvents.JOIN.register(((listener, _, _)
                -> state.join(listener.getPlayer())));
    }

    public static void quit(EventRegistry.ServerConnection.PlayerQuit state) {
        ServerPlayConnectionEvents.DISCONNECT.register(((listener, _)
                -> state.quit(listener.getPlayer())));
    }
}

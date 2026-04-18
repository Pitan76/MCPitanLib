package net.pitan76.mcpitanlib.api.event.v0.event;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.pitan76.mcpitanlib.api.event.v0.EventRegistry;

public class ServerConnectionEvent {
    // Architectury: PlayerEvent
    @ExpectPlatform
    public static void join(EventRegistry.ServerConnection.PlayerJoin state) {

    }

    @ExpectPlatform
    public static void quit(EventRegistry.ServerConnection.PlayerQuit state) {

    }
}

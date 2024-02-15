package net.pitan76.mcpitanlib.api.event.v0.event;

import me.shedaniel.architectury.event.events.PlayerEvent;
import net.pitan76.mcpitanlib.api.event.v0.EventRegistry;

public class ServerConnectionEvent {
    // Architectury: PlayerEvent
    public static void join(EventRegistry.ServerConnection.PlayerJoin state) {
        PlayerEvent.PLAYER_JOIN.register(state::join);
    }

    public static void quit(EventRegistry.ServerConnection.PlayerQuit state) {
        PlayerEvent.PLAYER_QUIT.register(state::quit);
    }
}

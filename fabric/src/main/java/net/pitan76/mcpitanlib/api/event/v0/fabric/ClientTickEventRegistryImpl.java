package net.pitan76.mcpitanlib.api.event.v0.fabric;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.pitan76.mcpitanlib.api.event.v0.ClientTickEventRegistry;

public class ClientTickEventRegistryImpl {
    public static void registerPost(ClientTickEventRegistry.Client client) {
        ClientTickEvents.END_CLIENT_TICK.register(client::tick);
    }

    public static void registerPre(ClientTickEventRegistry.Client client) {
        ClientTickEvents.START_CLIENT_TICK.register(client::tick);
    }

    public static void registerLevelPost(ClientTickEventRegistry.ClientLevel world) {
        ClientTickEvents.END_WORLD_TICK.register(world::tick);
    }

    public static void registerLevelPre(ClientTickEventRegistry.ClientLevel world) {
        ClientTickEvents.START_WORLD_TICK.register(world::tick);
    }
}

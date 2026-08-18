package net.pitan76.mcpitanlib.api.event.v0.fabric;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerWorldEvents;
import net.minecraft.server.world.ServerWorld;
import net.pitan76.mcpitanlib.api.event.v0.EventRegistry;

public class EventRegistryServerLifecycleImpl {
    public static void serverStarted(EventRegistry.ServerLifecycle.ServerState state) {
        ServerLifecycleEvents.SERVER_STARTED.register(state::stateChanged);
    }

    public static void serverStarting(EventRegistry.ServerLifecycle.ServerState state) {
        ServerLifecycleEvents.SERVER_STARTING.register(state::stateChanged);
    }

    public static void serverStopped(EventRegistry.ServerLifecycle.ServerState state) {
        ServerLifecycleEvents.SERVER_STOPPED.register(state::stateChanged);
    }

    public static void serverStopping(EventRegistry.ServerLifecycle.ServerState state) {
        ServerLifecycleEvents.SERVER_STOPPING.register(state::stateChanged);
    }

    public static void serverWorldLoad(EventRegistry.ServerLifecycle.ServerWorldState state) {
        ServerWorldEvents.LOAD.register((server, world) -> state.act(world));
    }

    public static void serverWorldSave(EventRegistry.ServerLifecycle.ServerWorldState state) {
        ServerLifecycleEvents.AFTER_SAVE.register((server, flush, force) -> {
            for (ServerWorld world : server.getWorlds()) {
                state.act(world);
            }
        });
    }

    public static void serverWorldUnload(EventRegistry.ServerLifecycle.ServerWorldState state) {
        ServerWorldEvents.UNLOAD.register((server, world) -> state.act(world));
    }
}

package net.pitan76.mcpitanlib.core.event;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.pitan76.mcpitanlib.api.event.v0.EventRegistry;

public class ServerLifecycleHooks {
    @ExpectPlatform
    public static void serverStarted(EventRegistry.ServerLifecycle.ServerState state) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static void serverStarting(EventRegistry.ServerLifecycle.ServerState state) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static void serverStopped(EventRegistry.ServerLifecycle.ServerState state) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static void serverStopping(EventRegistry.ServerLifecycle.ServerState state) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static void serverWorldLoad(EventRegistry.ServerLifecycle.ServerWorldState state) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static void serverWorldSave(EventRegistry.ServerLifecycle.ServerWorldState state) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static void serverWorldUnload(EventRegistry.ServerLifecycle.ServerWorldState state) {
        throw new AssertionError();
    }
}

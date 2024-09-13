package net.pitan76.mcpitanlib.api.client.event;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.pitan76.mcpitanlib.api.client.event.listener.BeforeBlockOutlineListener;
import net.pitan76.mcpitanlib.api.client.event.listener.WorldRenderContextListener;

public class WorldRenderRegistry {
    public WorldRenderRegistry() {

    }

    @ExpectPlatform
    public static void registerWorldRenderBeforeBlockOutline(BeforeBlockOutlineListener listener) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static void registerWorldRenderAfterLevel(WorldRenderContextListener listener) {
        throw new AssertionError();
    }
}

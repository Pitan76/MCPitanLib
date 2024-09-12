package net.pitan76.mcpitanlib.api.client.event;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.pitan76.mcpitanlib.api.client.event.listener.BeforeBlockOutlineListener;

public class WorldRenderRegistry {
    public WorldRenderRegistry() {

    }

    @ExpectPlatform
    public static void registerWorldRenderBeforeBlockOutline(BeforeBlockOutlineListener listener) {
        throw new AssertionError();
    }
}

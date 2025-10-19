package net.pitan76.mcpitanlib.api.client.event;

//import dev.architectury.injectables.annotations.ExpectPlatform;
import net.pitan76.mcpitanlib.api.client.event.listener.BeforeBlockOutlineListener;
import net.pitan76.mcpitanlib.api.client.event.listener.WorldRenderContextListener;

import java.util.ArrayList;
import java.util.List;

public class WorldRenderRegistry {

    public static List<BeforeBlockOutlineListener> beforeBlockOutlineListeners = new ArrayList<>();
    public static List<WorldRenderContextListener> worldRenderAfterLevelListeners = new ArrayList<>();

    public static boolean isEmptyBlockOutlineListeners = true;
    public static boolean isEmptyWorldRenderAfterLevelListeners = true;

    public WorldRenderRegistry() {

    }

    //@ExpectPlatform
    public static void registerWorldRenderBeforeBlockOutline(BeforeBlockOutlineListener listener) {
        beforeBlockOutlineListeners.add(listener);
        isEmptyBlockOutlineListeners = false;
        //throw new AssertionError();
    }

    //@ExpectPlatform
    public static void registerWorldRenderAfterLevel(WorldRenderContextListener listener) {
        worldRenderAfterLevelListeners.add(listener);
        isEmptyWorldRenderAfterLevelListeners = false;
        //throw new AssertionError();
    }
}

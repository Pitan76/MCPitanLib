package net.pitan76.mcpitanlib.api.event.v0.forge;

import net.minecraft.server.world.ServerWorld;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.event.server.ServerStartedEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.event.server.ServerStoppedEvent;
import net.minecraftforge.event.server.ServerStoppingEvent;
import net.pitan76.mcpitanlib.MCPitanLib;
import net.pitan76.mcpitanlib.api.event.v0.EventRegistry;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@EventBusSubscriber(modid = MCPitanLib.MOD_ID)
public class EventRegistryServerLifecycleImpl {

    private static final List<EventRegistry.ServerLifecycle.ServerState> serverStartedListeners = new CopyOnWriteArrayList<>();
    private static final List<EventRegistry.ServerLifecycle.ServerState> serverStartingListeners = new CopyOnWriteArrayList<>();
    private static final List<EventRegistry.ServerLifecycle.ServerState> serverStoppedListeners = new CopyOnWriteArrayList<>();
    private static final List<EventRegistry.ServerLifecycle.ServerState> serverStoppingListeners = new CopyOnWriteArrayList<>();

    private static final List<EventRegistry.ServerLifecycle.ServerWorldState> worldLoadListeners = new CopyOnWriteArrayList<>();
    private static final List<EventRegistry.ServerLifecycle.ServerWorldState> worldSaveListeners = new CopyOnWriteArrayList<>();
    private static final List<EventRegistry.ServerLifecycle.ServerWorldState> worldUnloadListeners = new CopyOnWriteArrayList<>();

    public static void serverStarted(EventRegistry.ServerLifecycle.ServerState state) {
        serverStartedListeners.add(state);
    }

    public static void serverStarting(EventRegistry.ServerLifecycle.ServerState state) {
        serverStartingListeners.add(state);
    }

    public static void serverStopped(EventRegistry.ServerLifecycle.ServerState state) {
        serverStoppedListeners.add(state);
    }

    public static void serverStopping(EventRegistry.ServerLifecycle.ServerState state) {
        serverStoppingListeners.add(state);
    }

    public static void serverWorldLoad(EventRegistry.ServerLifecycle.ServerWorldState state) {
        worldLoadListeners.add(state);
    }

    public static void serverWorldSave(EventRegistry.ServerLifecycle.ServerWorldState state) {
        worldSaveListeners.add(state);
    }

    public static void serverWorldUnload(EventRegistry.ServerLifecycle.ServerWorldState state) {
        worldUnloadListeners.add(state);
    }

    @SubscribeEvent
    public static void onServerStarting(ServerStartingEvent event) {
        for (EventRegistry.ServerLifecycle.ServerState listener : serverStartingListeners) {
            listener.stateChanged(event.getServer());
        }
    }

    @SubscribeEvent
    public static void onServerStarted(ServerStartedEvent event) {
        for (EventRegistry.ServerLifecycle.ServerState listener : serverStartedListeners) {
            listener.stateChanged(event.getServer());
        }
    }

    @SubscribeEvent
    public static void onServerStopping(ServerStoppingEvent event) {
        for (EventRegistry.ServerLifecycle.ServerState listener : serverStoppingListeners) {
            listener.stateChanged(event.getServer());
        }
    }

    @SubscribeEvent
    public static void onServerStopped(ServerStoppedEvent event) {
        for (EventRegistry.ServerLifecycle.ServerState listener : serverStoppedListeners) {
            listener.stateChanged(event.getServer());
        }
    }

    @SubscribeEvent
    public static void onLevelLoad(WorldEvent.Load event) {
        // 繧ｯ繝ｩ繧､繧｢繝ｳ繝亥・縺ｮ繝ｯ繝ｼ繝ｫ繝芽ｪｭ縺ｿ霎ｼ縺ｿ縺ｧ繧ら匱轣ｫ縺吶ｋ縺溘ａ縲ヾerverWorld縺九←縺・°縺ｮ蛻､螳壹′蠢・ｦ・
        if (event.getWorld() instanceof ServerWorld serverWorld) {
            for (EventRegistry.ServerLifecycle.ServerWorldState listener : worldLoadListeners) {
                listener.act(serverWorld);
            }
        }
    }

    @SubscribeEvent
    public static void onLevelSave(WorldEvent.Save event) {
        if (event.getWorld() instanceof ServerWorld serverWorld) {
            for (EventRegistry.ServerLifecycle.ServerWorldState listener : worldSaveListeners) {
                listener.act(serverWorld);
            }
        }
    }

    @SubscribeEvent
    public static void onLevelUnload(WorldEvent.Unload event) {
        if (event.getWorld() instanceof ServerWorld serverWorld) {
            for (EventRegistry.ServerLifecycle.ServerWorldState listener : worldUnloadListeners) {
                listener.act(serverWorld);
            }
        }
    }
}


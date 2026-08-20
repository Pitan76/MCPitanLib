package net.pitan76.mcpitanlib.core.event.forge;

import net.minecraft.server.world.ServerWorld;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.server.FMLServerStartedEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.event.server.FMLServerStoppedEvent;
import net.minecraftforge.fml.event.server.FMLServerStoppingEvent;
import net.pitan76.mcpitanlib.MCPitanLib;
import net.pitan76.mcpitanlib.api.event.v0.EventRegistry;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Mod.EventBusSubscriber(modid = MCPitanLib.MOD_ID)
public class ServerLifecycleHooksImpl {
    private static final List<EventRegistry.ServerLifecycle.ServerState> started = new CopyOnWriteArrayList<EventRegistry.ServerLifecycle.ServerState>();
    private static final List<EventRegistry.ServerLifecycle.ServerState> starting = new CopyOnWriteArrayList<EventRegistry.ServerLifecycle.ServerState>();
    private static final List<EventRegistry.ServerLifecycle.ServerState> stopped = new CopyOnWriteArrayList<EventRegistry.ServerLifecycle.ServerState>();
    private static final List<EventRegistry.ServerLifecycle.ServerState> stopping = new CopyOnWriteArrayList<EventRegistry.ServerLifecycle.ServerState>();
    private static final List<EventRegistry.ServerLifecycle.ServerWorldState> worldLoad = new CopyOnWriteArrayList<EventRegistry.ServerLifecycle.ServerWorldState>();
    private static final List<EventRegistry.ServerLifecycle.ServerWorldState> worldSave = new CopyOnWriteArrayList<EventRegistry.ServerLifecycle.ServerWorldState>();
    private static final List<EventRegistry.ServerLifecycle.ServerWorldState> worldUnload = new CopyOnWriteArrayList<EventRegistry.ServerLifecycle.ServerWorldState>();

    public static void serverStarted(EventRegistry.ServerLifecycle.ServerState state) {
        started.add(state);
    }

    public static void serverStarting(EventRegistry.ServerLifecycle.ServerState state) {
        starting.add(state);
    }

    public static void serverStopped(EventRegistry.ServerLifecycle.ServerState state) {
        stopped.add(state);
    }

    public static void serverStopping(EventRegistry.ServerLifecycle.ServerState state) {
        stopping.add(state);
    }

    public static void serverWorldLoad(EventRegistry.ServerLifecycle.ServerWorldState state) {
        worldLoad.add(state);
    }

    public static void serverWorldSave(EventRegistry.ServerLifecycle.ServerWorldState state) {
        worldSave.add(state);
    }

    public static void serverWorldUnload(EventRegistry.ServerLifecycle.ServerWorldState state) {
        worldUnload.add(state);
    }

    @SubscribeEvent
    public static void onServerStarted(FMLServerStartedEvent event) {
        for (EventRegistry.ServerLifecycle.ServerState state : started) {
            state.stateChanged(event.getServer());
        }
    }

    @SubscribeEvent
    public static void onServerStarting(FMLServerStartingEvent event) {
        for (EventRegistry.ServerLifecycle.ServerState state : starting) {
            state.stateChanged(event.getServer());
        }
    }

    @SubscribeEvent
    public static void onServerStopped(FMLServerStoppedEvent event) {
        for (EventRegistry.ServerLifecycle.ServerState state : stopped) {
            state.stateChanged(event.getServer());
        }
    }

    @SubscribeEvent
    public static void onServerStopping(FMLServerStoppingEvent event) {
        for (EventRegistry.ServerLifecycle.ServerState state : stopping) {
            state.stateChanged(event.getServer());
        }
    }

    @SubscribeEvent
    public static void onWorldLoad(WorldEvent.Load event) {
        if (!(event.getWorld() instanceof ServerWorld)) return;

        for (EventRegistry.ServerLifecycle.ServerWorldState state : worldLoad) {
            state.act((ServerWorld) event.getWorld());
        }
    }

    @SubscribeEvent
    public static void onWorldSave(WorldEvent.Save event) {
        if (!(event.getWorld() instanceof ServerWorld)) return;

        for (EventRegistry.ServerLifecycle.ServerWorldState state : worldSave) {
            state.act((ServerWorld) event.getWorld());
        }
    }

    @SubscribeEvent
    public static void onWorldUnload(WorldEvent.Unload event) {
        if (!(event.getWorld() instanceof ServerWorld)) return;

        for (EventRegistry.ServerLifecycle.ServerWorldState state : worldUnload) {
            state.act((ServerWorld) event.getWorld());
        }
    }
}

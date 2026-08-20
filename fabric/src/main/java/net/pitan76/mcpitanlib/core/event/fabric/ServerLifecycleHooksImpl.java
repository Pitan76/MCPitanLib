package net.pitan76.mcpitanlib.core.event.fabric;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerWorldEvents;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.pitan76.mcpitanlib.api.event.v0.EventRegistry;

public class ServerLifecycleHooksImpl {

    public static void serverStarted(final EventRegistry.ServerLifecycle.ServerState state) {
        ServerLifecycleEvents.SERVER_STARTED.register(new ServerLifecycleEvents.ServerStarted() {
            @Override
            public void onServerStarted(MinecraftServer server) {
                state.stateChanged(server);
            }
        });
    }

    public static void serverStarting(final EventRegistry.ServerLifecycle.ServerState state) {
        ServerLifecycleEvents.SERVER_STARTING.register(new ServerLifecycleEvents.ServerStarting() {
            @Override
            public void onServerStarting(MinecraftServer server) {
                state.stateChanged(server);
            }
        });
    }

    public static void serverStopped(final EventRegistry.ServerLifecycle.ServerState state) {
        ServerLifecycleEvents.SERVER_STOPPED.register(new ServerLifecycleEvents.ServerStopped() {
            @Override
            public void onServerStopped(MinecraftServer server) {
                state.stateChanged(server);
            }
        });
    }

    public static void serverStopping(final EventRegistry.ServerLifecycle.ServerState state) {
        ServerLifecycleEvents.SERVER_STOPPING.register(new ServerLifecycleEvents.ServerStopping() {
            @Override
            public void onServerStopping(MinecraftServer server) {
                state.stateChanged(server);
            }
        });
    }

    public static void serverWorldLoad(final EventRegistry.ServerLifecycle.ServerWorldState state) {
        ServerWorldEvents.LOAD.register(new ServerWorldEvents.Load() {
            @Override
            public void onWorldLoad(MinecraftServer server, ServerWorld world) {
                state.act(world);
            }
        });
    }

    // Fabricにはワールド保存のイベントが無いためアンロード時に代替する
    public static void serverWorldSave(EventRegistry.ServerLifecycle.ServerWorldState state) {
        // TODO: Fabric APIにサーバーワールド保存イベントが無いため未対応
    }

    public static void serverWorldUnload(final EventRegistry.ServerLifecycle.ServerWorldState state) {
        ServerWorldEvents.UNLOAD.register(new ServerWorldEvents.Unload() {
            @Override
            public void onWorldUnload(MinecraftServer server, ServerWorld world) {
                state.act(world);
            }
        });
    }
}

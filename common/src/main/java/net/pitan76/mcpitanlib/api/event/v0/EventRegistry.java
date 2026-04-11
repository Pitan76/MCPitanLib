package net.pitan76.mcpitanlib.api.event.v0;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLevelEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.pitan76.mcpitanlib.api.event.v0.event.ItemStackActionEvent;
import net.pitan76.mcpitanlib.api.event.v0.event.ServerConnectionEvent;

public class EventRegistry {

    public static class ServerConnection {
        // Architectury: PlayerEvent
        public static void join(PlayerJoin state) {
            ServerConnectionEvent.join(state);
        }

        public static void quit(PlayerQuit state) {
            ServerConnectionEvent.quit(state);
        }

        public interface PlayerJoin {
            void join(ServerPlayer player);
        }

        public interface PlayerQuit {
            void quit(ServerPlayer player);
        }
    }

    public static class ItemStackAction {
        public static void damage(ItemStackDamageState state) {
            ItemStackActionEvent.register(state);
        }

        public interface ItemStackDamageState {
            void onDamage(ItemStack stack);
        }
    }

    public static class ServerLifecycle {
        // Architectury: LifecycleEvent
        public static void serverStarted(ServerState state) {
            ServerLifecycleEvents.SERVER_STARTED.register(state::stateChanged);
        }

        public static void serverStarting(ServerState state) {
            ServerLifecycleEvents.SERVER_STARTING.register(state::stateChanged);
        }

        public static void serverStopped(ServerState state) {
            ServerLifecycleEvents.SERVER_STOPPED.register(state::stateChanged);
        }

        public static void serverStopping(ServerState state) {
            ServerLifecycleEvents.SERVER_STOPPING.register(state::stateChanged);
        }

        public static void serverWorldLoad(ServerWorldState state) {
            ServerLevelEvents.LOAD.register((_, level) -> state.act(level));
        }

        public static void serverWorldSave(ServerWorldState state) {
            ServerLifecycleEvents.AFTER_SAVE.register((server, _, _) -> {
                for (ServerLevel level : server.getAllLevels()) {
                    state.act(level);
                }
            });
        }

        public static void serverWorldUnload(ServerWorldState state) {
            ServerLevelEvents.UNLOAD.register((_, level) -> state.act(level));
        }

        public interface ServerState extends InstanceState<MinecraftServer> {
        }

        public interface InstanceState<T> {
            void stateChanged(T instance);
        }

        public interface WorldState<T extends Level> {
            void act(T world);
        }

        public interface ServerWorldState extends WorldState<ServerLevel> {
        }
    }
}

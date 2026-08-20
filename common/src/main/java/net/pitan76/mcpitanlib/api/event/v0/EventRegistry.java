package net.pitan76.mcpitanlib.api.event.v0;

import net.pitan76.mcpitanlib.core.event.ServerLifecycleHooks;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
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
            void join(ServerPlayerEntity player);
        }

        public interface PlayerQuit {
            void quit(ServerPlayerEntity player);
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
            ServerLifecycleHooks.serverStarted(state);
        }

        public static void serverStarting(ServerState state) {
            ServerLifecycleHooks.serverStarting(state);
        }

        public static void serverStopped(ServerState state) {
            ServerLifecycleHooks.serverStopped(state);
        }

        public static void serverStopping(ServerState state) {
            ServerLifecycleHooks.serverStopping(state);
        }

        public static void serverWorldLoad(ServerWorldState state) {
            ServerLifecycleHooks.serverWorldLoad(state);
        }

        public static void serverWorldSave(ServerWorldState state) {
            ServerLifecycleHooks.serverWorldSave(state);
        }

        public static void serverWorldUnload(ServerWorldState state) {
            ServerLifecycleHooks.serverWorldUnload(state);
        }

        public interface ServerState extends InstanceState<MinecraftServer> {
        }

        public interface InstanceState<T> {
            void stateChanged(T instance);
        }

        public interface WorldState<T extends World> {
            void act(T world);
        }

        public interface ServerWorldState extends WorldState<ServerWorld> {
        }
    }
}

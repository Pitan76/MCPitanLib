package net.pitan76.mcpitanlib.api.event.v0;

import dev.architectury.injectables.annotations.ExpectPlatform;
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
        @ExpectPlatform
        public static void serverStarted(ServerState state) {

        }

        @ExpectPlatform
        public static void serverStarting(ServerState state) {

        }

        @ExpectPlatform
        public static void serverStopped(ServerState state) {

        }

        @ExpectPlatform
        public static void serverStopping(ServerState state) {

        }

        @ExpectPlatform
        public static void serverWorldLoad(ServerWorldState state) {

        }

        @ExpectPlatform
        public static void serverWorldSave(ServerWorldState state) {

        }

        @ExpectPlatform
        public static void serverWorldUnload(ServerWorldState state) {

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

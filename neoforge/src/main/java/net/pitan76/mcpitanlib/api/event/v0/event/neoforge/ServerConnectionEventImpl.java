package net.pitan76.mcpitanlib.api.event.v0.event.neoforge;

import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.pitan76.mcpitanlib.api.event.v0.EventRegistry;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@EventBusSubscriber(modid = "mcpitanlib")
public class ServerConnectionEventImpl {

    private static final List<EventRegistry.ServerConnection.PlayerJoin> joinListeners = new CopyOnWriteArrayList<>();
    private static final List<EventRegistry.ServerConnection.PlayerQuit> quitListeners = new CopyOnWriteArrayList<>();

    public static void join(EventRegistry.ServerConnection.PlayerJoin state) {
        joinListeners.add(state);
    }

    public static void quit(EventRegistry.ServerConnection.PlayerQuit state) {
        quitListeners.add(state);
    }

    @SubscribeEvent
    public static void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent event) {
        if (event.getEntity() instanceof ServerPlayer serverPlayer) {
            for (EventRegistry.ServerConnection.PlayerJoin listener : joinListeners) {
                listener.join(serverPlayer);
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerQuit(PlayerEvent.PlayerLoggedOutEvent event) {
        if (event.getEntity() instanceof ServerPlayer serverPlayer) {
            for (EventRegistry.ServerConnection.PlayerQuit listener : quitListeners) {
                listener.quit(serverPlayer);
            }
        }
    }
}
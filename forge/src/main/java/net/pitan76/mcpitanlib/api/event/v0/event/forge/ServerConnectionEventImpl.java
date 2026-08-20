package net.pitan76.mcpitanlib.api.event.v0.event.forge;

import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.pitan76.mcpitanlib.MCPitanLib;
import net.pitan76.mcpitanlib.api.event.v0.EventRegistry;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Mod.EventBusSubscriber(modid = MCPitanLib.MOD_ID)
public class ServerConnectionEventImpl {
    private static final List<EventRegistry.ServerConnection.PlayerJoin> joins = new CopyOnWriteArrayList<EventRegistry.ServerConnection.PlayerJoin>();
    private static final List<EventRegistry.ServerConnection.PlayerQuit> quits = new CopyOnWriteArrayList<EventRegistry.ServerConnection.PlayerQuit>();

    public static void join(EventRegistry.ServerConnection.PlayerJoin state) {
        joins.add(state);
    }

    public static void quit(EventRegistry.ServerConnection.PlayerQuit state) {
        quits.add(state);
    }

    @SubscribeEvent
    public static void onLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        if (!(event.getPlayer() instanceof ServerPlayerEntity)) return;

        for (EventRegistry.ServerConnection.PlayerJoin state : joins) {
            state.join((ServerPlayerEntity) event.getPlayer());
        }
    }

    @SubscribeEvent
    public static void onLoggedOut(PlayerEvent.PlayerLoggedOutEvent event) {
        if (!(event.getPlayer() instanceof ServerPlayerEntity)) return;

        for (EventRegistry.ServerConnection.PlayerQuit state : quits) {
            state.quit((ServerPlayerEntity) event.getPlayer());
        }
    }
}

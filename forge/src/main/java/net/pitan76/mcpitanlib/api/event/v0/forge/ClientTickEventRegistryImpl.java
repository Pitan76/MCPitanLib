package net.pitan76.mcpitanlib.api.event.v0.forge;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.event.TickEvent;
import net.pitan76.mcpitanlib.MCPitanLib;
import net.pitan76.mcpitanlib.api.event.v0.ClientTickEventRegistry;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@EventBusSubscriber(modid = MCPitanLib.MOD_ID, value = Dist.CLIENT)
public class ClientTickEventRegistryImpl {

    private static final List<ClientTickEventRegistry.Client> preClients = new CopyOnWriteArrayList<>();
    private static final List<ClientTickEventRegistry.Client> postClients = new CopyOnWriteArrayList<>();
    private static final List<ClientTickEventRegistry.ClientLevel> preLevels = new CopyOnWriteArrayList<>();
    private static final List<ClientTickEventRegistry.ClientLevel> postLevels = new CopyOnWriteArrayList<>();

    public static void registerPre(ClientTickEventRegistry.Client client) {
        preClients.add(client);
    }

    public static void registerPost(ClientTickEventRegistry.Client client) {
        postClients.add(client);
    }

    public static void registerLevelPre(ClientTickEventRegistry.ClientLevel world) {
        preLevels.add(world);
    }

    public static void registerLevelPost(ClientTickEventRegistry.ClientLevel world) {
        postLevels.add(world);
    }

    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (event.phase == TickEvent.Phase.START) {
            for (ClientTickEventRegistry.Client listener : preClients) {
                listener.tick(mc);
            }
        } else if (event.phase == TickEvent.Phase.END) {
            for (ClientTickEventRegistry.Client listener : postClients) {
                listener.tick(mc);
            }
        }
    }

    @SubscribeEvent
    public static void onLevelTick(TickEvent.LevelTickEvent event) {
        if (event.level instanceof ClientWorld clientWorld) {
            if (event.phase == TickEvent.Phase.START) {
                for (ClientTickEventRegistry.ClientLevel listener : preLevels) {
                    listener.tick(clientWorld);
                }
            } else if (event.phase == TickEvent.Phase.END) {
                for (ClientTickEventRegistry.ClientLevel listener : postLevels) {
                    listener.tick(clientWorld);
                }
            }
        }
    }
}


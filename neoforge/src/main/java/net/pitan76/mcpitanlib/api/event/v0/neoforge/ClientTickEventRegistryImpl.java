package net.pitan76.mcpitanlib.api.event.v0.neoforge;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.event.tick.LevelTickEvent;
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
    public static void onClientTickPre(ClientTickEvent.Pre event) {
        MinecraftClient mc = MinecraftClient.getInstance();
        for (ClientTickEventRegistry.Client listener : preClients) {
            listener.tick(mc);
        }
    }

    @SubscribeEvent
    public static void onClientTickPost(ClientTickEvent.Post event) {
        MinecraftClient mc = MinecraftClient.getInstance();
        for (ClientTickEventRegistry.Client listener : postClients) {
            listener.tick(mc);
        }
    }

    @SubscribeEvent
    public static void onLevelTickPre(LevelTickEvent.Pre event) {
        // LevelTickEventはサーバー側のワールドでも発火するため、クライアントワールドかどうかの判定が必須
        if (event.getLevel() instanceof ClientWorld clientWorld) {
            for (ClientTickEventRegistry.ClientLevel listener : preLevels) {
                listener.tick(clientWorld);
            }
        }
    }

    @SubscribeEvent
    public static void onLevelTickPost(LevelTickEvent.Post event) {
        if (event.getLevel() instanceof ClientWorld clientWorld) {
            for (ClientTickEventRegistry.ClientLevel listener : postLevels) {
                listener.tick(clientWorld);
            }
        }
    }
}

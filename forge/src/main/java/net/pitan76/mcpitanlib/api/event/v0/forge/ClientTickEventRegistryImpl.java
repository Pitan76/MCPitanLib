package net.pitan76.mcpitanlib.api.event.v0.forge;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.pitan76.mcpitanlib.MCPitanLib;
import net.pitan76.mcpitanlib.api.event.v0.ClientTickEventRegistry;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Mod.EventBusSubscriber(modid = MCPitanLib.MOD_ID, value = Dist.CLIENT)
public class ClientTickEventRegistryImpl {
    private static final List<ClientTickEventRegistry.Client> posts = new CopyOnWriteArrayList<ClientTickEventRegistry.Client>();
    private static final List<ClientTickEventRegistry.Client> pres = new CopyOnWriteArrayList<ClientTickEventRegistry.Client>();
    private static final List<ClientTickEventRegistry.ClientLevel> levelPosts = new CopyOnWriteArrayList<ClientTickEventRegistry.ClientLevel>();
    private static final List<ClientTickEventRegistry.ClientLevel> levelPres = new CopyOnWriteArrayList<ClientTickEventRegistry.ClientLevel>();

    public static void registerPost(ClientTickEventRegistry.Client client) {
        posts.add(client);
    }

    public static void registerPre(ClientTickEventRegistry.Client client) {
        pres.add(client);
    }

    public static void registerLevelPost(ClientTickEventRegistry.ClientLevel world) {
        levelPosts.add(world);
    }

    public static void registerLevelPre(ClientTickEventRegistry.ClientLevel world) {
        levelPres.add(world);
    }

    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        MinecraftClient client = MinecraftClient.getInstance();

        if (event.phase == TickEvent.Phase.END) {
            for (ClientTickEventRegistry.Client c : posts) {
                c.tick(client);
            }
        } else {
            for (ClientTickEventRegistry.Client c : pres) {
                c.tick(client);
            }
        }
    }

    @SubscribeEvent
    public static void onWorldTick(TickEvent.WorldTickEvent event) {
        if (!(event.world instanceof ClientWorld)) return;
        ClientWorld world = (ClientWorld) event.world;

        if (event.phase == TickEvent.Phase.END) {
            for (ClientTickEventRegistry.ClientLevel l : levelPosts) {
                l.tick(world);
            }
        } else {
            for (ClientTickEventRegistry.ClientLevel l : levelPres) {
                l.tick(world);
            }
        }
    }
}

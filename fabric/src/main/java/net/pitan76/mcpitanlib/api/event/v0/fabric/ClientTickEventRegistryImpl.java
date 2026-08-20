package net.pitan76.mcpitanlib.api.event.v0.fabric;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;
import net.pitan76.mcpitanlib.api.event.v0.ClientTickEventRegistry;

@Environment(EnvType.CLIENT)
public class ClientTickEventRegistryImpl {
    public static void registerPost(final ClientTickEventRegistry.Client client) {
        ClientTickEvents.END_CLIENT_TICK.register(new ClientTickEvents.EndTick() {
            @Override
            public void onEndTick(MinecraftClient instance) {
                client.tick(instance);
            }
        });
    }

    public static void registerPre(final ClientTickEventRegistry.Client client) {
        ClientTickEvents.START_CLIENT_TICK.register(new ClientTickEvents.StartTick() {
            @Override
            public void onStartTick(MinecraftClient instance) {
                client.tick(instance);
            }
        });
    }

    public static void registerLevelPost(final ClientTickEventRegistry.ClientLevel world) {
        ClientTickEvents.END_WORLD_TICK.register(new ClientTickEvents.EndWorldTick() {
            @Override
            public void onEndTick(ClientWorld instance) {
                world.tick(instance);
            }
        });
    }

    public static void registerLevelPre(final ClientTickEventRegistry.ClientLevel world) {
        ClientTickEvents.START_WORLD_TICK.register(new ClientTickEvents.StartWorldTick() {
            @Override
            public void onStartTick(ClientWorld instance) {
                world.tick(instance);
            }
        });
    }
}

package net.pitan76.mcpitanlib.api.event.v0;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.Minecraft;

public class ClientTickEventRegistry {
    public static void registerPost(Client client) {
        ClientTickEvents.END_CLIENT_TICK.register(client::tick);
    }

    public static void registerPre(Client client) {
        ClientTickEvents.START_CLIENT_TICK.register(client::tick);
    }

    public static void registerLevelPost(ClientLevel world) {
        ClientTickEvents.END_LEVEL_TICK.register(world::tick);
    }

    public static void registerLevelPre(ClientLevel world) {
        ClientTickEvents.START_LEVEL_TICK.register(world::tick);
    }

    @Environment(EnvType.CLIENT)
    public interface Client {
        void tick(Minecraft instance);
    }

    @Environment(EnvType.CLIENT)
    public interface ClientLevel {
        void tick(net.minecraft.client.multiplayer.ClientLevel instance);
    }
}

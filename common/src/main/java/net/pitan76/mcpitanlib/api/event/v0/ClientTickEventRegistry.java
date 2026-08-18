package net.pitan76.mcpitanlib.api.event.v0;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;

public class ClientTickEventRegistry {
    @ExpectPlatform
    public static void registerPost(Client client) {

    }

    @ExpectPlatform
    public static void registerPre(Client client) {

    }

    @ExpectPlatform
    public static void registerLevelPost(ClientLevel world) {

    }

    @ExpectPlatform
    public static void registerLevelPre(ClientLevel world) {

    }

    @Environment(EnvType.CLIENT)
    public interface Client {
        void tick(MinecraftClient instance);
    }

    @Environment(EnvType.CLIENT)
    public interface ClientLevel {
        void tick(ClientWorld instance);
    }
}

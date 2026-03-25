package net.pitan76.mcpitanlib.api.client.registry;

import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.resources.Identifier;
import net.pitan76.mcpitanlib.api.event.v0.ClientTickEventRegistry;
import net.pitan76.mcpitanlib.api.network.ClientNetworking;
import net.pitan76.mcpitanlib.api.network.PacketByteUtil;

public class KeybindingRegistry {
    public static void register(KeyMapping keyBinding) {
        KeyMappingHelper.registerKeyMapping(keyBinding);
    }

    public static void register(KeyMapping keyBinding, ClientTickEventRegistry.Client client) {
        register(keyBinding);
        ClientTickEventRegistry.registerPost(client);
    }

    public static void registerOnLevel(KeyMapping keyBinding, ClientTickEventRegistry.ClientLevel level) {
        register(keyBinding);
        ClientTickEventRegistry.registerLevelPost(level);
    }

    public static void registerWithNetwork(KeyMapping keyBinding, Identifier identifier) {
        register(keyBinding, client -> {
            if (keyBinding.consumeClick())
                ClientNetworking.send(identifier, PacketByteUtil.create());
        });
    }

    public static void registerOnLevelWithNetwork(KeyMapping keyBinding, Identifier identifier) {
        registerOnLevel(keyBinding, client -> {
            if (keyBinding.consumeClick())
                ClientNetworking.send(identifier, PacketByteUtil.create());
        });
    }
}

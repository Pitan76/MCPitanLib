package net.pitan76.mcpitanlib.api.client.registry;

import me.shedaniel.architectury.registry.KeyBindings;
import net.pitan76.mcpitanlib.api.event.v0.ClientTickEventRegistry;
import net.pitan76.mcpitanlib.api.network.ClientNetworking;
import net.pitan76.mcpitanlib.api.network.PacketByteUtil;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.util.Identifier;

public class KeybindingRegistry {
    public static void register(KeyBinding keyBinding) {
        KeyBindings.registerKeyBinding(keyBinding);
    }

    public static void register(KeyBinding keyBinding, ClientTickEventRegistry.Client client) {
        register(keyBinding);
        ClientTickEventRegistry.registerPost(client);
    }

    public static void registerOnLevel(KeyBinding keyBinding, ClientTickEventRegistry.ClientLevel level) {
        register(keyBinding);
        ClientTickEventRegistry.registerLevelPost(level);
    }

    public static void registerWithNetwork(KeyBinding keyBinding, Identifier identifier) {
        register(keyBinding, client -> {
            if (keyBinding.wasPressed())
                ClientNetworking.send(identifier, PacketByteUtil.create());
        });
    }

    public static void registerOnLevelWithNetwork(KeyBinding keyBinding, Identifier identifier) {
        registerOnLevel(keyBinding, client -> {
            if (keyBinding.wasPressed())
                ClientNetworking.send(identifier, PacketByteUtil.create());
        });
    }
}

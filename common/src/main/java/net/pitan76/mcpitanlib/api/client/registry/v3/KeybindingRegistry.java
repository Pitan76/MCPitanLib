package net.pitan76.mcpitanlib.api.client.registry.v3;

import net.pitan76.mcpitanlib.api.client.option.CompatKeyBinding;
import net.pitan76.mcpitanlib.api.event.v0.ClientTickEventRegistry;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;

public class KeybindingRegistry {
    public static void registerWithNetwork(CompatKeyBinding keyBinding, CompatIdentifier networkId) {
        net.pitan76.mcpitanlib.api.client.registry.v2.KeybindingRegistry.registerWithNetwork(keyBinding.toMinecraft(), networkId.toMinecraft());
    }

    public static void registerOnLevelWithNetwork(CompatKeyBinding keyBinding, CompatIdentifier networkId) {
        net.pitan76.mcpitanlib.api.client.registry.v2.KeybindingRegistry.registerOnLevelWithNetwork(keyBinding.toMinecraft(), networkId.toMinecraft());
    }

    public static void registerWithNetwork(String translationKey, int code, CompatIdentifier category, CompatIdentifier networkId) {
        registerWithNetwork(CompatKeyBinding.of(translationKey, code, category), networkId);
    }

    public static void registerOnLevelWithNetwork(String translationKey, int code, CompatIdentifier category, CompatIdentifier networkId) {
        registerOnLevelWithNetwork(CompatKeyBinding.of(translationKey, code, category), networkId);
    }

    public static void register(CompatKeyBinding keyBinding) {
        net.pitan76.mcpitanlib.api.client.registry.KeybindingRegistry.register(keyBinding.toMinecraft());
    }

    public static void register(CompatKeyBinding keyBinding, ClientTickEventRegistry.Client client) {
        register(keyBinding);
        ClientTickEventRegistry.registerPost(client);
    }

    public static void registerOnLevel(CompatKeyBinding keyBinding, ClientTickEventRegistry.ClientLevel level) {
        register(keyBinding);
        ClientTickEventRegistry.registerLevelPost(level);
    }
}

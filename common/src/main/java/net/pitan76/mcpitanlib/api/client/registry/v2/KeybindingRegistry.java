package net.pitan76.mcpitanlib.api.client.registry.v2;

import net.minecraft.client.option.KeyBinding;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;

public class KeybindingRegistry extends net.pitan76.mcpitanlib.api.client.registry.KeybindingRegistry {

    public static void registerWithNetwork(KeyBinding keyBinding, CompatIdentifier identifier) {
        registerWithNetwork(keyBinding, identifier.toMinecraft());
    }

    public static void registerOnLevelWithNetwork(KeyBinding keyBinding, CompatIdentifier identifier) {
        registerOnLevelWithNetwork(keyBinding, identifier.toMinecraft());
    }

    public static void registerWithNetwork(String translationKey, int code, String category, CompatIdentifier identifier) {
        registerWithNetwork(new KeyBinding(translationKey, code, KeyBinding.Category.create(CompatIdentifier.of(category).toMinecraft())), identifier);
    }

    public static void registerOnLevelWithNetwork(String translationKey, int code, String category, CompatIdentifier identifier) {
        // TODO: categoryの互換性
        registerOnLevelWithNetwork(new KeyBinding(translationKey, code, KeyBinding.Category.create(CompatIdentifier.of(category).toMinecraft())), identifier);
    }
}

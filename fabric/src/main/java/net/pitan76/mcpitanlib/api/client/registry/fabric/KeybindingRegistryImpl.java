package net.pitan76.mcpitanlib.api.client.registry.fabric;

import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.minecraft.client.KeyMapping;

public class KeybindingRegistryImpl {
    public static void register(KeyMapping keyBinding) {
        KeyMappingHelper.registerKeyMapping(keyBinding);
    }
}

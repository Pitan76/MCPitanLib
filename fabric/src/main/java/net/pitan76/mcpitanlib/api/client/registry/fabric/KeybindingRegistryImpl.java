package net.pitan76.mcpitanlib.api.client.registry.fabric;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;

@Environment(EnvType.CLIENT)
public class KeybindingRegistryImpl {
    public static void register(KeyBinding keyBinding) {
        KeyBindingHelper.registerKeyBinding(keyBinding);
    }
}

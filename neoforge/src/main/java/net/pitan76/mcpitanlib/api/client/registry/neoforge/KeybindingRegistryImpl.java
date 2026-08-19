package net.pitan76.mcpitanlib.api.client.registry.neoforge;

import net.minecraft.client.option.KeyBinding;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.pitan76.mcpitanlib.MCPitanLib;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@EventBusSubscriber(modid = MCPitanLib.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class KeybindingRegistryImpl {

    private static final List<KeyBinding> keyBindings = new CopyOnWriteArrayList<>();

    public static void register(KeyBinding keyBinding) {
        keyBindings.add(keyBinding);
    }

    @SubscribeEvent
    public static void onRegisterKeyMappings(RegisterKeyMappingsEvent event) {
        for (KeyBinding keyBinding : keyBindings) {
            event.register(keyBinding);
        }
    }
}

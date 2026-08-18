package net.pitan76.mcpitanlib.api.client.registry.neoforge;

import net.minecraft.client.KeyMapping;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@EventBusSubscriber(modid = "mcpitanlib", value = Dist.CLIENT)
public class KeybindingRegistryImpl {

    private static final List<KeyMapping> keyMappings = new CopyOnWriteArrayList<>();

    public static void register(KeyMapping keyBinding) {
        keyMappings.add(keyBinding);
    }

    @SubscribeEvent
    public static void onRegisterKeyMappings(RegisterKeyMappingsEvent event) {
        for (KeyMapping keyMapping : keyMappings) {
            event.register(keyMapping);
        }
    }
}
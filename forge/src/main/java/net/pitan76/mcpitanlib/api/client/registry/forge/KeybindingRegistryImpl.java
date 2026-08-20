package net.pitan76.mcpitanlib.api.client.registry.forge;

import net.minecraft.client.option.KeyBinding;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.ClientRegistry;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.pitan76.mcpitanlib.MCPitanLib;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@EventBusSubscriber(modid = MCPitanLib.MOD_ID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
public class KeybindingRegistryImpl {

    private static final List<KeyBinding> keyBindings = new CopyOnWriteArrayList<>();

    public static void register(KeyBinding keyBinding) {
        keyBindings.add(keyBinding);
    }

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            for (KeyBinding keyBinding : keyBindings) {
                ClientRegistry.registerKeyBinding(keyBinding);
            }
        });
    }
}

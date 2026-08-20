package net.pitan76.mcpitanlib.api.client.registry.forge;

import net.minecraft.client.option.KeyBinding;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.pitan76.mcpitanlib.MCPitanLib;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Mod.EventBusSubscriber(modid = MCPitanLib.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class KeybindingRegistryImpl {
    private static final List<KeyBinding> keyBindings = new CopyOnWriteArrayList<KeyBinding>();
    private static volatile boolean clientSetupDone = false;

    public static void register(KeyBinding keyBinding) {
        if (clientSetupDone) {
            ClientRegistry.registerKeyBinding(keyBinding);
            return;
        }

        keyBindings.add(keyBinding);
    }

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(new Runnable() {
            @Override
            public void run() {
                for (KeyBinding keyBinding : keyBindings) {
                    ClientRegistry.registerKeyBinding(keyBinding);
                }

                keyBindings.clear();
                clientSetupDone = true;
            }
        });
    }
}

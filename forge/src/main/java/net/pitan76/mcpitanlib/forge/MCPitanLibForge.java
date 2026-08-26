package net.pitan76.mcpitanlib.forge;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.pitan76.mcpitanlib.MCPitanLib;
import net.pitan76.mcpitanlib.api.network.forge.ForgeNetworkRegistry;
import net.pitan76.mcpitanlib.api.potion.BrewingRecipeUtil;

@Mod(MCPitanLib.MOD_ID)
public class MCPitanLibForge {
    public MCPitanLibForge() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        bus.register(net.pitan76.mcpitanlib.core.registry.forge.RegistryImpl.class);
        bus.register(net.pitan76.mcpitanlib.core.registry.forge.CreativeTabEventRegistryImpl.class);
        bus.register(net.pitan76.mcpitanlib.api.client.registry.forge.CompatRegistryClientImpl.class);
        bus.register(net.pitan76.mcpitanlib.api.client.registry.forge.KeybindingRegistryImpl.class);

        MinecraftForge.EVENT_BUS.register(net.pitan76.mcpitanlib.core.registry.forge.FuelRegistryImpl.class);
        MinecraftForge.EVENT_BUS.register(net.pitan76.mcpitanlib.api.client.event.forge.ItemTooltipRegistryImpl.class);
        MinecraftForge.EVENT_BUS.register(net.pitan76.mcpitanlib.api.command.forge.CommandRegistryImpl.class);
        MinecraftForge.EVENT_BUS.register(net.pitan76.mcpitanlib.api.event.v0.event.forge.ServerConnectionEventImpl.class);
        MinecraftForge.EVENT_BUS.register(net.pitan76.mcpitanlib.api.event.v0.forge.AttackEntityEventRegistryImpl.class);
        MinecraftForge.EVENT_BUS.register(net.pitan76.mcpitanlib.api.event.v0.forge.ClientTickEventRegistryImpl.class);
        MinecraftForge.EVENT_BUS.register(net.pitan76.mcpitanlib.api.event.v0.forge.EventRegistryServerLifecycleImpl.class);
        MinecraftForge.EVENT_BUS.register(net.pitan76.mcpitanlib.api.event.v0.forge.InteractionEventRegistryImpl.class);
        MinecraftForge.EVENT_BUS.register(net.pitan76.mcpitanlib.api.event.v0.forge.LivingHurtEventRegistryImpl.class);
        MinecraftForge.EVENT_BUS.register(net.pitan76.mcpitanlib.api.event.v1.forge.AttackEntityEventRegistryImpl.class);
        MinecraftForge.EVENT_BUS.register(net.pitan76.mcpitanlib.api.event.v1.forge.LivingHurtEventRegistryImpl.class);

        ForgeNetworkRegistry.init();
        MCPitanLib.init();

        bus.addListener((FMLCommonSetupEvent event) -> event.enqueueWork(BrewingRecipeUtil::executeDeferredRecipes));
    }
}
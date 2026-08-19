package net.pitan76.mcpitanlib.forge;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.pitan76.mcpitanlib.MCPitanLib;
import net.pitan76.mcpitanlib.api.network.forge.ForgeNetworkRegistry;

@Mod(MCPitanLib.MOD_ID)
public class MCPitanLibForge {
    public MCPitanLibForge() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        bus.register(net.pitan76.mcpitanlib.core.registry.forge.RegistryImpl.class);
        bus.register(net.pitan76.mcpitanlib.core.registry.forge.CreativeTabEventRegistryImpl.class);
        bus.register(net.pitan76.mcpitanlib.api.client.registry.forge.CompatRegistryClientImpl.class);
        bus.register(net.pitan76.mcpitanlib.api.client.registry.forge.KeybindingRegistryImpl.class);

        net.minecraftforge.common.MinecraftForge.EVENT_BUS.register(net.pitan76.mcpitanlib.core.registry.forge.FuelRegistryImpl.class);
        net.minecraftforge.common.MinecraftForge.EVENT_BUS.register(net.pitan76.mcpitanlib.api.client.event.forge.ItemTooltipRegistryImpl.class);
        net.minecraftforge.common.MinecraftForge.EVENT_BUS.register(net.pitan76.mcpitanlib.api.command.forge.CommandRegistryImpl.class);
        net.minecraftforge.common.MinecraftForge.EVENT_BUS.register(net.pitan76.mcpitanlib.api.event.v0.event.forge.ServerConnectionEventImpl.class);
        net.minecraftforge.common.MinecraftForge.EVENT_BUS.register(net.pitan76.mcpitanlib.api.event.v0.forge.AttackEntityEventRegistryImpl.class);
        net.minecraftforge.common.MinecraftForge.EVENT_BUS.register(net.pitan76.mcpitanlib.api.event.v0.forge.ClientTickEventRegistryImpl.class);
        net.minecraftforge.common.MinecraftForge.EVENT_BUS.register(net.pitan76.mcpitanlib.api.event.v0.forge.EventRegistryServerLifecycleImpl.class);
        net.minecraftforge.common.MinecraftForge.EVENT_BUS.register(net.pitan76.mcpitanlib.api.event.v0.forge.InteractionEventRegistryImpl.class);
        net.minecraftforge.common.MinecraftForge.EVENT_BUS.register(net.pitan76.mcpitanlib.api.event.v0.forge.LivingHurtEventRegistryImpl.class);
        net.minecraftforge.common.MinecraftForge.EVENT_BUS.register(net.pitan76.mcpitanlib.api.event.v1.forge.AttackEntityEventRegistryImpl.class);
        net.minecraftforge.common.MinecraftForge.EVENT_BUS.register(net.pitan76.mcpitanlib.api.event.v1.forge.LivingHurtEventRegistryImpl.class);

        ForgeNetworkRegistry.init();
        MCPitanLib.init();
    }
}
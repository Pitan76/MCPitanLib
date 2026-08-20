package net.pitan76.mcpitanlib.api.client.event.forge;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.pitan76.mcpitanlib.MCPitanLib;
import net.pitan76.mcpitanlib.api.client.event.listener.ItemTooltipListener;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Mod.EventBusSubscriber(modid = MCPitanLib.MOD_ID, value = Dist.CLIENT)
public class ItemTooltipRegistryImpl {
    private static final List<ItemTooltipListener> listeners = new CopyOnWriteArrayList<ItemTooltipListener>();

    public static void registerItemTooltip(ItemTooltipListener listener) {
        listeners.add(listener);
    }

    @SubscribeEvent
    public static void onItemTooltip(ItemTooltipEvent event) {
        for (ItemTooltipListener listener : listeners) {
            listener.onTooltip(event.getItemStack(), event.getToolTip(), event.getFlags());
        }
    }
}

package net.pitan76.mcpitanlib.api.client.event.fabric;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.pitan76.mcpitanlib.api.client.event.listener.ItemTooltipListener;

import java.util.List;

@Environment(EnvType.CLIENT)
public class ItemTooltipRegistryImpl {
    public static void registerItemTooltip(final ItemTooltipListener listener) {
        ItemTooltipCallback.EVENT.register(new ItemTooltipCallback() {
            @Override
            public void getTooltip(ItemStack stack, TooltipContext context, List<Text> texts) {
                listener.onTooltip(stack, texts, context);
            }
        });
    }
}

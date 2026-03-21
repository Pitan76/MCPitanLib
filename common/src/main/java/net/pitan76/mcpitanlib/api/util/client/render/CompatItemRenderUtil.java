package net.pitan76.mcpitanlib.api.util.client.render;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.pitan76.mcpitanlib.api.client.render.block.entity.event.BlockEntityRenderEvent;

/**
 * Cross-version utility for rendering items in block entity renderers.
 */
public class CompatItemRenderUtil {
    /**
     * Renders an ItemStack in FIXED transform mode.
     */
    public static void renderItemFixed(ItemStack stack, BlockEntityRenderEvent<?> e, World world) {
        renderItem(stack, CompatItemDisplayContext.FIXED, e, world);
    }

    /**
     * Renders an ItemStack
     */
    public static void renderItem(ItemStack stack, CompatItemDisplayContext displayContext, BlockEntityRenderEvent<?> e, World world) {
        ItemRenderer renderer = e.getItemRenderer() != null ? e.getItemRenderer() : MinecraftClient.getInstance().getItemRenderer();
        renderer.renderItem(stack, displayContext.getContext(), e.getLight(), e.getOverlay(), e.matrices, e.vertexConsumers, 0);
    }
}

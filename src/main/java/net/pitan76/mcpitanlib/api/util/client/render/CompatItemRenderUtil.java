package net.pitan76.mcpitanlib.api.util.client.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.pitan76.mcpitanlib.api.client.render.block.entity.event.BlockEntityRenderEvent;

/**
 * Cross-version utility for rendering items in block entity renderers.
 */
public class CompatItemRenderUtil {
    /**
     * Renders an ItemStack in FIXED transform mode.
     */
    public static void renderItemFixed(ItemStack stack, BlockEntityRenderEvent<?> e, Level world) {
        renderItem(stack, CompatItemDisplayContext.FIXED, e, world);
    }

    /**
     * Renders an ItemStack
     */
    public static void renderItem(ItemStack stack, CompatItemDisplayContext displayContext, BlockEntityRenderEvent<?> e, Level world) {
        ItemModelResolver manager = e.ctx != null ? e.ctx.getItemModelManager() : Minecraft.getInstance().getItemModelResolver();

        ItemStackRenderState state = new ItemStackRenderState();
        manager.updateForTopItem(state, stack, displayContext.getContext(), world, null, 0);

        int light = e.getLight();
        if (light == 0) light = 0xF000F0; // full-bright fallback if not populated by MCPitanLib
        int overlay = e.getOverlay();

        state.submit(e.matrices, e.getQueue(), light, overlay, 0);
    }
}

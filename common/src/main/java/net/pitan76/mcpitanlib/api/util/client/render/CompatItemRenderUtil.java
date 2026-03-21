package net.pitan76.mcpitanlib.api.util.client.render;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.item.ItemModelManager;
import net.minecraft.client.render.item.ItemRenderState;
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
        ItemModelManager manager = e.ctx != null ? e.ctx.getItemModelManager() : MinecraftClient.getInstance().getItemModelManager();

        ItemRenderState state = new ItemRenderState();
        manager.update(state, stack, displayContext.getContext(), world, null, 0);

        int light = e.getLight();
        if (light == 0) light = 0xF000F0; // full-bright fallback if not populated by MCPitanLib
        int overlay = e.getOverlay();

        state.render(e.matrices, e.vertexConsumers, light, overlay);
    }
}

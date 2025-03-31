package net.pitan76.mcpitanlib.api.util.client;

import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.pitan76.mcpitanlib.api.client.registry.CompatRegistryClient;

public class BlockEntityRendererUtil {
    public static BlockEntityRendererFactory.Context convert(CompatRegistryClient.BlockEntityRendererFactory.Context ctx) {
        return new BlockEntityRendererFactory.Context(ctx.getRenderDispatcher(), ctx.getRenderManager(), ctx.getItemRenderer(), ctx.getEntityRenderDispatcher(), ctx.getLayerRenderDispatcher(), ctx.getTextRenderer());
    }

    public static CompatRegistryClient.BlockEntityRendererFactory.Context convert(BlockEntityRendererFactory.Context ctx) {
        return new CompatRegistryClient.BlockEntityRendererFactory.Context(ctx.getRenderDispatcher(), ctx.getRenderManager(), ctx.getItemRenderer(), ctx.getEntityRenderDispatcher(), ctx.getLayerRenderDispatcher(), ctx.getTextRenderer());
    }
}

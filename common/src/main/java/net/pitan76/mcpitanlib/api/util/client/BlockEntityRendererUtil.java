package net.pitan76.mcpitanlib.api.util.client;

import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.pitan76.mcpitanlib.api.client.registry.CompatRegistryClient;

public class BlockEntityRendererUtil {
    public static BlockEntityRendererProvider.Context convert(CompatRegistryClient.BlockEntityRendererFactory.Context ctx) {
        return new BlockEntityRendererProvider.Context(ctx.getRenderDispatcher(), ctx.getRenderManager(), ctx.getItemModelManager(), ctx.getEntityRenderDispatcher(), ctx.getLayerRenderDispatcher(), ctx.getTextRenderer(), ctx.getSpriteHolder(), ctx.getPlayerSkinRenderCache());
    }

    public static CompatRegistryClient.BlockEntityRendererFactory.Context convert(BlockEntityRendererProvider.Context ctx) {
        return new CompatRegistryClient.BlockEntityRendererFactory.Context(ctx.blockEntityRenderDispatcher(), ctx.blockModelResolver(), ctx.itemModelResolver(), ctx.entityRenderer(), ctx.entityModelSet(), ctx.font(), ctx.sprites(), ctx.playerSkinRenderCache());
    }
}

package net.pitan76.mcpitanlib.api.client.render.block.entity.v2;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import net.pitan76.mcpitanlib.api.client.registry.CompatRegistryClient;
import net.pitan76.mcpitanlib.api.client.render.block.entity.event.BlockEntityRenderEvent;
import net.pitan76.mcpitanlib.api.client.render.block.entity.event.CompatBlockEntityRendererConstructArgs;
import net.pitan76.mcpitanlib.api.tile.CompatBlockEntity;

public abstract class CompatBlockEntityRenderer<T extends CompatBlockEntity> extends net.pitan76.mcpitanlib.api.client.render.block.entity.CompatBlockEntityRenderer<T> {
    public CompatBlockEntityRenderer(CompatBlockEntityRendererConstructArgs args) {
        super(args.dispatcher);
    }

    public CompatBlockEntityRenderer(CompatRegistryClient.BlockEntityRendererFactory.Context ctx) {
        super(ctx.getRenderDispatcher());
    }

    abstract void render(BlockEntityRenderEvent<T> event);

    @Override
    public void render(T entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        render(new BlockEntityRenderEvent<>(entity, tickDelta, matrices, vertexConsumers, light, overlay));
    }
}

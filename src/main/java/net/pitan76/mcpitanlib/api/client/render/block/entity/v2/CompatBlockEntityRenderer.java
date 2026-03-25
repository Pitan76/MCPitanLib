package net.pitan76.mcpitanlib.api.client.render.block.entity.v2;

import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.pitan76.mcpitanlib.api.client.registry.CompatRegistryClient2;
import net.pitan76.mcpitanlib.api.client.render.block.entity.event.CompatBlockEntityRendererConstructArgs;
import net.pitan76.mcpitanlib.api.tile.CompatBlockEntity;

public abstract class CompatBlockEntityRenderer<T extends CompatBlockEntity> implements net.pitan76.mcpitanlib.api.client.render.block.entity.CompatBlockEntityRenderer<T, BlockEntityRenderState> {

    @Deprecated
    public CompatRegistryClient2.BlockEntityRendererFactory.Context ctx;

    public CompatBlockEntityRenderer(CompatBlockEntityRendererConstructArgs args) {

    }

    public CompatBlockEntityRenderer(CompatRegistryClient2.BlockEntityRendererFactory.Context ctx) {
        this.ctx = ctx;
    }

    @Override
    public BlockEntityRenderState createRenderState() {
        return new BlockEntityRenderState();
    }
}

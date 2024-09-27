package net.pitan76.mcpitanlib.api.client.render.block.entity.v2;

import net.pitan76.mcpitanlib.api.client.registry.CompatRegistryClient;
import net.pitan76.mcpitanlib.api.client.render.block.entity.event.CompatBlockEntityRendererConstructArgs;
import net.pitan76.mcpitanlib.api.tile.CompatBlockEntity;

public abstract class CompatBlockEntityRenderer<T extends CompatBlockEntity> implements net.pitan76.mcpitanlib.api.client.render.block.entity.CompatBlockEntityRenderer<T> {
    public CompatBlockEntityRenderer(CompatBlockEntityRendererConstructArgs args) {

    }

    public CompatBlockEntityRenderer(CompatRegistryClient.BlockEntityRendererFactory.Context ctx) {

    }
}

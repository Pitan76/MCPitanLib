package net.pitan76.mcpitanlib.api.client.render.block.entity.event;

import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;

public class CompatBlockEntityRendererConstructArgs {
    public final BlockEntityRenderDispatcher dispatcher;

    public CompatBlockEntityRendererConstructArgs(BlockEntityRenderDispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    public CompatBlockEntityRendererConstructArgs() {
        this(null);
    }
}

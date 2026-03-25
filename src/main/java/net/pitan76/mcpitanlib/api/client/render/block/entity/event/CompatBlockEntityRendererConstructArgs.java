package net.pitan76.mcpitanlib.api.client.render.block.entity.event;

import net.minecraft.client.render.block.entity.BlockEntityRenderManager;
import net.pitan76.mcpitanlib.api.util.client.ClientUtil;

public class CompatBlockEntityRendererConstructArgs {
    public final BlockEntityRenderManager dispatcher;

    public CompatBlockEntityRendererConstructArgs(BlockEntityRenderManager dispatcher) {
        this.dispatcher = dispatcher;
    }

    public CompatBlockEntityRendererConstructArgs() {
        this(ClientUtil.getClient().getBlockEntityRenderDispatcher());
    }
}

package net.pitan76.mcpitanlib.api.block;

import net.minecraft.world.level.block.RenderShape;

public class CompatBlockRenderType {

    public static final CompatBlockRenderType MODEL = of(RenderShape.MODEL);
    public static final CompatBlockRenderType ENTITYBLOCK_ANIMATED = of(RenderShape.MODEL);
    public static final CompatBlockRenderType INVISIBLE = of(RenderShape.INVISIBLE);

    @Deprecated
    public final RenderShape renderType;

    public CompatBlockRenderType(RenderShape renderType) {
        this.renderType = renderType;
    }

    public static CompatBlockRenderType of(RenderShape renderType) {
        return new CompatBlockRenderType(renderType);
    }

    public RenderShape toMinecraft() {
        return renderType;
    }
}

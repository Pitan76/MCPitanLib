package net.pitan76.mcpitanlib.api.block;

import net.minecraft.block.BlockRenderType;

public class CompatBlockRenderType {

    public static final CompatBlockRenderType MODEL = of(BlockRenderType.MODEL);
    public static final CompatBlockRenderType ENTITYBLOCK_ANIMATED = of(BlockRenderType.ENTITYBLOCK_ANIMATED);
    public static final CompatBlockRenderType INVISIBLE = of(BlockRenderType.INVISIBLE);

    @Deprecated
    public final BlockRenderType renderType;

    public CompatBlockRenderType(BlockRenderType renderType) {
        this.renderType = renderType;
    }

    public static CompatBlockRenderType of(BlockRenderType renderType) {
        return new CompatBlockRenderType(renderType);
    }

    public BlockRenderType toMinecraft() {
        return renderType;
    }
}

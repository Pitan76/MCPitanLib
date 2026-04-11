package net.pitan76.mcpitanlib.api.client.render;

import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;

public class CompatRenderLayer {
    public static final CompatRenderLayer CUTOUT = new CompatRenderLayer(RenderTypes.cutoutMovingBlock());
    public static final CompatRenderLayer CUTOUT_MIPPED = new CompatRenderLayer(RenderTypes.cutoutMovingBlock());
    public static final CompatRenderLayer TRANSLUCENT = new CompatRenderLayer(RenderTypes.glintTranslucent());
    public static final CompatRenderLayer TRANSLUCENT_MOVING_BLOCK = new CompatRenderLayer(RenderTypes.translucentMovingBlock());
    public static final CompatRenderLayer SOLID = new CompatRenderLayer(RenderTypes.solidMovingBlock());
    public static final CompatRenderLayer LINES = new CompatRenderLayer(RenderTypes.lines());
    public static final CompatRenderLayer LINE_STRIP = new CompatRenderLayer(RenderTypes.linesTranslucent());
    public static final CompatRenderLayer GLINT = new CompatRenderLayer(RenderTypes.glint());

    public final RenderType layer;

    public CompatRenderLayer(RenderType layer) {
        this.layer = layer;
    }

    public RenderType raw() {
        return layer;
    }

    public static CompatRenderLayer getEntityCutout(CompatIdentifier id) {
        return new CompatRenderLayer(RenderTypes.entityCutoutCull(id.toMinecraft()));
    }

    public static CompatRenderLayer getEntityCutoutNoCull(CompatIdentifier id) {
        return new CompatRenderLayer(RenderTypes.entityCutout(id.toMinecraft()));
    }

    public static CompatRenderLayer getEntityTranslucent(CompatIdentifier id) {
        return new CompatRenderLayer(RenderTypes.entityTranslucent(id.toMinecraft()));
    }

    public static CompatRenderLayer getArmorCutoutNoCull(CompatIdentifier id) {
        return new CompatRenderLayer(RenderTypes.armorCutoutNoCull(id.toMinecraft()));
    }

    public static CompatRenderLayer getEntitySolid(CompatIdentifier id) {
        return new CompatRenderLayer(RenderTypes.entitySolid(id.toMinecraft()));
    }
}

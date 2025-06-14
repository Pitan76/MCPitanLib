package net.pitan76.mcpitanlib.api.client.render;

import net.minecraft.client.render.BuiltBuffer;
import net.minecraft.client.render.RenderLayer;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;

public class CompatRenderLayer {
    public static final CompatRenderLayer CUTOUT = new CompatRenderLayer(RenderLayer.getCutout());
    public static final CompatRenderLayer CUTOUT_MIPPED = new CompatRenderLayer(RenderLayer.getCutoutMipped());
    public static final CompatRenderLayer TRANSLUCENT = new CompatRenderLayer(RenderLayer.getTranslucent());
    public static final CompatRenderLayer TRANSLUCENT_MOVING_BLOCK = new CompatRenderLayer(RenderLayer.getTranslucentMovingBlock());
    public static final CompatRenderLayer SOLID = new CompatRenderLayer(RenderLayer.getSolid());
    public static final CompatRenderLayer LINES = new CompatRenderLayer(RenderLayer.getLines());
    public static final CompatRenderLayer LINE_STRIP = new CompatRenderLayer(RenderLayer.getLineStrip());
    public static final CompatRenderLayer GLINT = new CompatRenderLayer(RenderLayer.getGlint());

    public final RenderLayer layer;

    public CompatRenderLayer(RenderLayer layer) {
        this.layer = layer;
    }

    public RenderLayer raw() {
        return layer;
    }

    public static CompatRenderLayer getEntityCutout(CompatIdentifier id) {
        return new CompatRenderLayer(RenderLayer.getEntityCutout(id.toMinecraft()));
    }

    public static CompatRenderLayer getEntityCutoutNoCull(CompatIdentifier id) {
        return new CompatRenderLayer(RenderLayer.getEntityCutoutNoCull(id.toMinecraft()));
    }

    public static CompatRenderLayer getEntityTranslucent(CompatIdentifier id) {
        return new CompatRenderLayer(RenderLayer.getEntityTranslucent(id.toMinecraft()));
    }

    public static CompatRenderLayer getArmorCutoutNoCull(CompatIdentifier id) {
        return new CompatRenderLayer(RenderLayer.getArmorCutoutNoCull(id.toMinecraft()));
    }

    public static CompatRenderLayer getEntitySolid(CompatIdentifier id) {
        return new CompatRenderLayer(RenderLayer.getEntitySolid(id.toMinecraft()));
    }

    public void draw(BuiltBuffer buffer) {
        layer.draw(buffer);
    }
}

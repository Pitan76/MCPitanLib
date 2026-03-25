package net.pitan76.mcpitanlib.api.client.render;

import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderLayers;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;

public class CompatRenderLayer {
    public static final CompatRenderLayer CUTOUT = new CompatRenderLayer(RenderLayers.cutout());
    public static final CompatRenderLayer CUTOUT_MIPPED = new CompatRenderLayer(RenderLayers.cutout());
    public static final CompatRenderLayer TRANSLUCENT = new CompatRenderLayer(RenderLayers.glintTranslucent());
    public static final CompatRenderLayer TRANSLUCENT_MOVING_BLOCK = new CompatRenderLayer(RenderLayers.translucentMovingBlock());
    public static final CompatRenderLayer SOLID = new CompatRenderLayer(RenderLayers.solid());
    public static final CompatRenderLayer LINES = new CompatRenderLayer(RenderLayers.lines());
    public static final CompatRenderLayer LINE_STRIP = new CompatRenderLayer(RenderLayers.linesTranslucent());
    public static final CompatRenderLayer GLINT = new CompatRenderLayer(RenderLayers.glint());

    public final RenderLayer layer;

    public CompatRenderLayer(RenderLayer layer) {
        this.layer = layer;
    }

    public RenderLayer raw() {
        return layer;
    }

    public static CompatRenderLayer getEntityCutout(CompatIdentifier id) {
        return new CompatRenderLayer(RenderLayers.entityCutout(id.toMinecraft()));
    }

    public static CompatRenderLayer getEntityCutoutNoCull(CompatIdentifier id) {
        return new CompatRenderLayer(RenderLayers.entityCutoutNoCull(id.toMinecraft()));
    }

    public static CompatRenderLayer getEntityTranslucent(CompatIdentifier id) {
        return new CompatRenderLayer(RenderLayers.entityTranslucent(id.toMinecraft()));
    }

    public static CompatRenderLayer getArmorCutoutNoCull(CompatIdentifier id) {
        return new CompatRenderLayer(RenderLayers.armorCutoutNoCull(id.toMinecraft()));
    }

    public static CompatRenderLayer getEntitySolid(CompatIdentifier id) {
        return new CompatRenderLayer(RenderLayers.entitySolid(id.toMinecraft()));
    }
}

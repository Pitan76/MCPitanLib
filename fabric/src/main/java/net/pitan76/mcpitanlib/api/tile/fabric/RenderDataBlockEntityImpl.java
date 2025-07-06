package net.pitan76.mcpitanlib.api.tile.fabric;

import net.fabricmc.fabric.api.blockview.v2.RenderDataBlockEntity;

@Deprecated
public interface RenderDataBlockEntityImpl extends RenderDataBlockEntity {

    default Object getRenderData() {
        if (this instanceof net.pitan76.mcpitanlib.api.tile.RenderDataBlockEntity) {
            return ((net.pitan76.mcpitanlib.api.tile.RenderDataBlockEntity) this).getCompatRenderData();
        }
        return null;
    }
}

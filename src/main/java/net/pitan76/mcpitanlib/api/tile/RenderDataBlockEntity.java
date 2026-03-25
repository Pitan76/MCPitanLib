package net.pitan76.mcpitanlib.api.tile;

import org.jetbrains.annotations.Nullable;

@Deprecated
public interface RenderDataBlockEntity extends RenderDataBlockEntity {

    @Nullable
    Object getCompatRenderData();

    default boolean hasCompatRenderData() {
        return getCompatRenderData() != null;
    }

    default Object getRenderData() {
        if (this instanceof net.pitan76.mcpitanlib.api.tile.RenderDataBlockEntity) {
            return ((net.pitan76.mcpitanlib.api.tile.RenderDataBlockEntity) this).getCompatRenderData();
        }
        return null;
    }
}

package net.pitan76.mcpitanlib.api.tile;

import org.jetbrains.annotations.Nullable;

@Deprecated
public interface RenderDataBlockEntity {

    @Nullable
    Object getCompatRenderData();

    default boolean hasCompatRenderData() {
        return getCompatRenderData() != null;
    }
}

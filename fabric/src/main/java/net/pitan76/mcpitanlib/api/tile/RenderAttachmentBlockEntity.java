package net.pitan76.mcpitanlib.api.tile;

import net.fabricmc.fabric.api.blockview.v2.RenderDataBlockEntity;
import org.jetbrains.annotations.Nullable;

public interface RenderAttachmentBlockEntity extends RenderDataBlockEntity {

    @Override
    @Nullable
    @Deprecated
    default Object getRenderData() {
        return getCompatRenderData();
    }

    default boolean hasCompatRenderData() {
        return getCompatRenderData() != null;
    }

    @Nullable
    Object getCompatRenderData();
}

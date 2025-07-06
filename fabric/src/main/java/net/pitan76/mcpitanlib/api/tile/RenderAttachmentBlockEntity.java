package net.pitan76.mcpitanlib.api.tile;

import org.jetbrains.annotations.Nullable;

public interface RenderAttachmentBlockEntity extends net.fabricmc.fabric.api.rendering.data.v1.RenderAttachmentBlockEntity {

    @Override
    @Nullable
    @Deprecated
    default Object getRenderAttachmentData() {
        return getCompatRenderData();
    }

    default boolean hasCompatRenderData() {
        return getCompatRenderData() != null;
    }

    @Nullable
    Object getCompatRenderData();
}

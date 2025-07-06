package net.pitan76.mcpitanlib.api.tile.fabric;

import net.fabricmc.fabric.api.rendering.data.v1.RenderAttachmentBlockEntity;
import org.jetbrains.annotations.Nullable;

@Deprecated
public interface RenderDataBlockEntityImpl extends RenderAttachmentBlockEntity {

    @Override
    @Nullable
    default Object getRenderAttachmentData() {
        if (this instanceof net.pitan76.mcpitanlib.api.tile.RenderDataBlockEntity) {
            return ((net.pitan76.mcpitanlib.api.tile.RenderDataBlockEntity) this).getCompatRenderData();
        }
        return null;
    }
}

package net.pitan76.mcpitanlib.api.client.render;

import net.minecraft.client.model.geom.builders.MeshDefinition;

public class EntityModelLayerContext {
    private final MeshDefinition data;
    private final int width;
    private final int height;

    public EntityModelLayerContext(MeshDefinition data, int width, int height) {
        this.data = data;
        this.width = width;
        this.height = height;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public MeshDefinition getData() {
        return data;
    }
}
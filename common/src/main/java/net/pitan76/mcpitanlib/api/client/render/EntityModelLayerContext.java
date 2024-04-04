package net.pitan76.mcpitanlib.api.client.render;

import net.minecraft.client.model.ModelData;

public class EntityModelLayerContext {
    private final ModelData data;
    private final int width;
    private final int height;

    public EntityModelLayerContext(ModelData data, int width, int height) {
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

    public ModelData getData() {
        return data;
    }
}
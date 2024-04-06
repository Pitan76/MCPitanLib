package net.pitan76.mcpitanlib.api.client.render;


public class EntityModelLayerContext {
    private final int width;
    private final int height;

    public EntityModelLayerContext(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}

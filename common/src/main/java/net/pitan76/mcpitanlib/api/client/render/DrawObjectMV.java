package net.pitan76.mcpitanlib.api.client.render;

import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.util.math.MatrixStack;
import net.pitan76.mcpitanlib.api.util.client.render.VertexConsumerUtil;

public class DrawObjectMV {
    private final MatrixStack stack;
    private final VertexConsumer buffer;

    public DrawObjectMV(MatrixStack stack, VertexConsumer buffer) {
        this.stack = stack;
        this.buffer = buffer;
    }

    public DrawObjectMV(MatrixStack stack) {
        this(stack, null);
    }

    public DrawObjectMV(VertexConsumer buffer) {
        this(null, buffer);
    }

    public MatrixStack getStack() {
        return stack;
    }

    public VertexConsumer getBuffer() {
        return buffer;
    }

    public DrawObjectMV vertex(float x, float y, float z) {
        return VertexConsumerUtil.vertex(this, x, y, z);
    }

    public DrawObjectMV normal(float x, float y, float z) {
        return VertexConsumerUtil.normal(this, x, y, z);
    }

    public DrawObjectMV color(float red, float green, float blue, float alpha) {
        return VertexConsumerUtil.color(this, red, green, blue, alpha);
    }

    public DrawObjectMV color(int red, int green, int blue, int alpha) {
        return VertexConsumerUtil.color(this, red, green, blue, alpha);
    }

    public DrawObjectMV colorARGB(int argb) {
        return VertexConsumerUtil.colorARGB(this, argb);
    }

    public DrawObjectMV colorRGB(int rgb) {
        return VertexConsumerUtil.colorRGB(this, rgb);
    }

    public DrawObjectMV light(int light) {
        return VertexConsumerUtil.light(this, light);
    }

    public DrawObjectMV overlay(int overlay) {
        return VertexConsumerUtil.overlay(this, overlay);
    }
}

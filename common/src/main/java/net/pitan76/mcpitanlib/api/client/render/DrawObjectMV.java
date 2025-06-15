package net.pitan76.mcpitanlib.api.client.render;

import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Matrix3f;
import net.minecraft.util.math.Matrix4f;
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

    public DrawObjectMV overlayDefaultUV() {
        return VertexConsumerUtil.overlayDefaultUV(this);
    }

    public Matrix4f matrix4f;
    public Matrix3f matrix3f;

    public Matrix4f getMatrix4f() {
        if (matrix4f == null)
            matrix4f = stack.peek().getPositionMatrix();

        return matrix4f;
    }

    public Matrix3f getMatrix3f() {
        if (matrix3f == null)
            matrix3f = stack.peek().getNormalMatrix();

        return matrix3f;
    }

    public DrawObjectMV vertexWithMatrix4f(float x, float y, float z) {
        VertexConsumerUtil.vertex(buffer, getMatrix4f(), x, y, z);
        return this;
    }

    public DrawObjectMV vertexWithMatrix(float x, float y, float z) {
        VertexConsumerUtil.vertex(buffer, stack, x, y, z);
        return this;
    }

    public DrawObjectMV normalWithMatrix(float x, float y, float z) {
        VertexConsumerUtil.normal(buffer, stack, x, y, z);
        return this;
    }

    public DrawObjectMV texture(float u, float v) {
        VertexConsumerUtil.texture(buffer, u, v);
        return this;
    }

    public DrawObjectMV next() {
        VertexConsumerUtil.next(buffer);
        return this;
    }

    public void renderQuad(float x1, float y1, float z1, float x2, float y2, float z2,
                            float normalX, float normalY, float normalZ, int r, int g, int b, int alpha, int u, int v, int overlay, int light) {
        VertexConsumerUtil.renderQuad(buffer, stack, getMatrix4f(), getMatrix3f(),
                x1, y1, z1, x2, y2, z2,
                normalX, normalY, normalZ, r, g, b, alpha, u, v, overlay, light);
    }
}

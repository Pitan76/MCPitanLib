package net.pitan76.mcpitanlib.api.client.render;

import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.util.math.MatrixStack;
import net.pitan76.mcpitanlib.api.util.client.render.VertexConsumerUtil;
import org.joml.Matrix3f;
import org.joml.Matrix4f;

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

    DrawObjectMV vertexWithMatrix4f(float x, float y, float z) {
        VertexConsumerUtil.vertex(buffer, getMatrix4f(), x, y, z);
        return this;
    }

    DrawObjectMV vertexWithMatrix(float x, float y, float z) {
        VertexConsumerUtil.vertex(buffer, stack, x, y, z);
        return this;
    }

    DrawObjectMV normalWithMatrix(float x, float y, float z) {
        VertexConsumerUtil.normal(buffer, stack, x, y, z);
        return this;
    }
    
    DrawObjectMV texture(float u, float v) {
        VertexConsumerUtil.texture(buffer, u, v);
        return this;
    }
    
    DrawObjectMV next() {
        VertexConsumerUtil.next(buffer);
        return this;
    }

    private void renderQuad(float x1, float y1, float z1, float x2, float y2, float z2,
                            float normalX, float normalY, float normalZ, int r, int g, int b) {

        if (Math.abs(normalY) > 0.5f) {
            float y = y1;

            if (normalY > 0) {
                vertexWithMatrix4f(x1, y, z1).color(r, g, b, 255).texture(0, 0)
                        .light(15728880).normalWithMatrix(normalX, normalY, normalZ).next();
                vertexWithMatrix4f(x1, y, z2).color(r, g, b, 255).texture(0, 0)
                        .light(15728880).normalWithMatrix(normalX, normalY, normalZ).next();
                vertexWithMatrix4f(x2, y, z2).color(r, g, b, 255).texture(0, 0)
                        .light(15728880).normalWithMatrix(normalX, normalY, normalZ).next();
                vertexWithMatrix4f(x2, y, z1).color(r, g, b, 255).texture(0, 0)
                        .light(15728880).normalWithMatrix(normalX, normalY, normalZ).next();
            } else {
                vertexWithMatrix4f(x1, y, z1).color(r, g, b, 255).texture(0, 0)
                        .light(15728880).normal(normalX, normalY, normalZ).next();
                vertexWithMatrix4f(x2, y, z1).color(r, g, b, 255).texture(0, 0)
                        .light(15728880).normal(normalX, normalY, normalZ).next();
                vertexWithMatrix4f(x2, y, z2).color(r, g, b, 255).texture(0, 0)
                        .light(15728880).normal(normalX, normalY, normalZ).next();
                vertexWithMatrix4f(x1, y, z2).color(r, g, b, 255).texture(0, 0)
                        .light(15728880).normal(normalX, normalY, normalZ).next();
            }

        } else if (Math.abs(normalZ) > 0.5f) {
            float z = z1;

            if (normalZ > 0) {
                // TODO: Fix
                vertexWithMatrix4f(x1, y1, z).color(r, g, b, 255).texture(0, 0).light(15728880).normal(normalX, normalY, normalZ).next();
                vertexWithMatrix4f(x1, y2, z).color(r, g, b, 255).texture(0, 0).light(15728880).normal(normalX, normalY, normalZ).next();
                vertexWithMatrix4f(x2, y1, z).color(r, g, b, 255).texture(0, 0).light(15728880).normal(normalX, normalY, normalZ).next();

                vertexWithMatrix4f(x2, y1, z).color(r, g, b, 255).texture(0, 0).light(15728880).normal(normalX, normalY, normalZ).next();
                vertexWithMatrix4f(x1, y2, z).color(r, g, b, 255).texture(0, 0).light(15728880).normal(normalX, normalY, normalZ).next();
                vertexWithMatrix4f(x2, y2, z).color(r, g, b, 255).texture(0, 0).light(15728880).normal(normalX, normalY, normalZ).next();

            } else {
                vertexWithMatrix4f(x1, y1, z).color(r, g, b, 255).texture(0, 0).light(15728880).normal(normalX, normalY, normalZ).next();
                vertexWithMatrix4f(x2, y1, z).color(r, g, b, 255).texture(0, 0).light(15728880).normal(normalX, normalY, normalZ).next();
                vertexWithMatrix4f(x1, y2, z).color(r, g, b, 255).texture(0, 0).light(15728880).normal(normalX, normalY, normalZ).next();

                vertexWithMatrix4f(x2, y1, z).color(r, g, b, 255).texture(0, 0).light(15728880).normal(normalX, normalY, normalZ).next();
                vertexWithMatrix4f(x2, y2, z).color(r, g, b, 255).texture(0, 0).light(15728880).normal(normalX, normalY, normalZ).next();
                vertexWithMatrix4f(x1, y2, z).color(r, g, b, 255).texture(0, 0).light(15728880).normal(normalX, normalY, normalZ).next();
            }

        } else if (Math.abs(normalX) > 0.5f) {
            float x = x1;

            if (normalX > 0) {
                vertexWithMatrix4f(x, y1, z1).color(r, g, b, 255).texture(0, 0).light(15728880).normal(normalX, normalY, normalZ).next();
                vertexWithMatrix4f(x, y2, z1).color(r, g, b, 255).texture(0, 0).light(15728880).normal(normalX, normalY, normalZ).next();
                vertexWithMatrix4f(x, y1, z2).color(r, g, b, 255).texture(0, 0).light(15728880).normal(normalX, normalY, normalZ).next();

                vertexWithMatrix4f(x, y1, z2).color(r, g, b, 255).texture(0, 0).light(15728880).normal(normalX, normalY, normalZ).next();
                vertexWithMatrix4f(x, y2, z1).color(r, g, b, 255).texture(0, 0).light(15728880).normal(normalX, normalY, normalZ).next();
                vertexWithMatrix4f(x, y2, z2).color(r, g, b, 255).texture(0, 0).light(15728880).normal(normalX, normalY, normalZ).next();

            } else {
                vertexWithMatrix4f(x, y1, z1).color(r, g, b, 255).texture(0, 0).light(15728880).normal(normalX, normalY, normalZ).next();
                vertexWithMatrix4f(x, y1, z2).color(r, g, b, 255).texture(0, 0).light(15728880).normal(normalX, normalY, normalZ).next();
                vertexWithMatrix4f(x, y2, z1).color(r, g, b, 255).texture(0, 0).light(15728880).normal(normalX, normalY, normalZ).next();

                vertexWithMatrix4f(x, y1, z2).color(r, g, b, 255).texture(0, 0).light(15728880).normal(normalX, normalY, normalZ).next();
                vertexWithMatrix4f(x, y2, z2).color(r, g, b, 255).texture(0, 0).light(15728880).normal(normalX, normalY, normalZ).next();
                vertexWithMatrix4f(x, y2, z1).color(r, g, b, 255).texture(0, 0).light(15728880).normal(normalX, normalY, normalZ).next();
            }
        }
    }
}

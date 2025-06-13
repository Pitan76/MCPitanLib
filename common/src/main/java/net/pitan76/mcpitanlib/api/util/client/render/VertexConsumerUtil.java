package net.pitan76.mcpitanlib.api.util.client.render;

import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.util.math.MatrixStack;
import net.pitan76.mcpitanlib.api.client.render.DrawObjectMV;
import org.joml.Matrix3f;
import org.joml.Matrix4f;

public class VertexConsumerUtil {
    public static VertexConsumer vertex(VertexConsumer vertexConsumer, float x, float y, float z) {
        return vertexConsumer.vertex(x, y, z);
    }

    public static VertexConsumer vertex(VertexConsumer vertexConsumer, MatrixStack stack, float x, float y, float z) {
        return vertexConsumer.vertex(stack.peek(), x, y, z);
    }

    public static VertexConsumer normal(VertexConsumer vertexConsumer, float x, float y, float z) {
        return vertexConsumer.normal(x, y, z);
    }

    public static VertexConsumer color(VertexConsumer vertexConsumer, float red, float green, float blue, float alpha) {
        return vertexConsumer.color(red, green, blue, alpha);
    }

    public static VertexConsumer color(VertexConsumer vertexConsumer, int red, int green, int blue, int alpha) {
        return vertexConsumer.color(red, green, blue, alpha);
    }

    public static VertexConsumer colorARGB(VertexConsumer vertexConsumer, int argb) {
        return vertexConsumer.color(argb);
    }

    public static VertexConsumer colorRGB(VertexConsumer vertexConsumer, int rgb) {
        return vertexConsumer.colorRgb(rgb);
    }

    public static VertexConsumer light(VertexConsumer vertexConsumer, int light) {
        return vertexConsumer.light(light);
    }

    public static VertexConsumer overlay(VertexConsumer vertexConsumer, int overlay) {
        return vertexConsumer.overlay(overlay);
    }

    public static DrawObjectMV vertex(DrawObjectMV drawObject, float x, float y, float z) {
        vertex(drawObject.getBuffer(), drawObject.getStack(), x, y, z);
        return drawObject;
    }

    public static DrawObjectMV normal(DrawObjectMV drawObject, float x, float y, float z) {
        normal(drawObject.getBuffer(), x, y, z);
        return drawObject;
    }

    public static DrawObjectMV color(DrawObjectMV drawObject, float red, float green, float blue, float alpha) {
        color(drawObject.getBuffer(), red, green, blue, alpha);
        return drawObject;
    }

    public static DrawObjectMV color(DrawObjectMV drawObject, int red, int green, int blue, int alpha) {
        color(drawObject.getBuffer(), red, green, blue, alpha);
        return drawObject;
    }

    public static DrawObjectMV colorARGB(DrawObjectMV drawObject, int argb) {
        colorARGB(drawObject.getBuffer(), argb);
        return drawObject;
    }

    public static DrawObjectMV colorRGB(DrawObjectMV drawObject, int rgb) {
        colorRGB(drawObject.getBuffer(), rgb);
        return drawObject;
    }

    public static DrawObjectMV light(DrawObjectMV drawObject, int light) {
        light(drawObject.getBuffer(), light);
        return drawObject;
    }

    public static DrawObjectMV overlay(DrawObjectMV drawObject, int overlay) {
        overlay(drawObject.getBuffer(), overlay);
        return drawObject;
    }

    public static VertexConsumer texture(VertexConsumer vertexConsumer, float u, float v) {
        return vertexConsumer.texture(u, v);
    }

    public static VertexConsumer vertex(VertexConsumer vertexConsumer, Matrix4f matrix4f, float x, float y, float z) {
        return vertexConsumer.vertex(matrix4f, x, y, z);
    }

    public static VertexConsumer normal(VertexConsumer vertexConsumer, MatrixStack stack, float x, float y, float z) {
        return vertexConsumer.normal(stack.peek(), x, y, z);
    }

    public static VertexConsumer next(VertexConsumer vertexConsumer) {
        return vertexConsumer;
    }
}

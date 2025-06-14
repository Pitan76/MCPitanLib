package net.pitan76.mcpitanlib.api.util.client.render;

import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Matrix3f;
import net.minecraft.util.math.Matrix4f;
import net.pitan76.mcpitanlib.api.client.render.DrawObjectMV;

public class VertexConsumerUtil {
    public static VertexConsumer vertex(VertexConsumer vertexConsumer, float x, float y, float z) {
        return vertexConsumer.vertex(x, y, z);
    }

    public static VertexConsumer vertex(VertexConsumer vertexConsumer, MatrixStack stack, float x, float y, float z) {
        return vertexConsumer.vertex(stack.peek().getModel(), x, y, z);
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
        int red = (argb >> 16) & 0xFF;
        int green = (argb >> 8) & 0xFF;
        int blue = argb & 0xFF;
        int alpha = (argb >> 24) & 0xFF;

        return color(vertexConsumer, red, green, blue, alpha);
    }

    public static VertexConsumer colorRGB(VertexConsumer vertexConsumer, int rgb) {
        int red = (rgb >> 16) & 0xFF;
        int green = (rgb >> 8) & 0xFF;
        int blue = rgb & 0xFF;
        int alpha = 255; // Default alpha value

        return color(vertexConsumer, red, green, blue, alpha);
    }

    public static VertexConsumer light(VertexConsumer vertexConsumer, int light) {
        return vertexConsumer.light(light);
    }

    public static VertexConsumer overlay(VertexConsumer vertexConsumer, int overlay) {
        return vertexConsumer.overlay(overlay);
    }

    public static VertexConsumer overlayDefaultUV(VertexConsumer vertexConsumer) {
        return vertexConsumer.overlay(OverlayTexture.DEFAULT_UV);
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

    public static DrawObjectMV overlayDefaultUV(DrawObjectMV drawObject) {
        overlayDefaultUV(drawObject.getBuffer());
        return drawObject;
    }

    public static VertexConsumer texture(VertexConsumer vertexConsumer, float u, float v) {
        return vertexConsumer.texture(u, v);
    }

    public static VertexConsumer vertex(VertexConsumer vertexConsumer, Matrix4f matrix4f, float x, float y, float z) {
        return vertexConsumer.vertex(matrix4f, x, y, z);
    }

    public static VertexConsumer normal(VertexConsumer vertexConsumer, MatrixStack stack, float x, float y, float z) {
        return vertexConsumer.normal(stack.peek().getNormal(), x, y, z);
    }

    public static VertexConsumer next(VertexConsumer vertexConsumer) {
        return vertexConsumer;
    }

    public static void renderQuad(VertexConsumer vertexConsumer, MatrixStack stack, Matrix4f matrix4f, Matrix3f matrix3f,
                            float x1, float y1, float z1, float x2, float y2, float z2,
                            float normalX, float normalY, float normalZ, int r, int g, int b, int alpha, int u, int v, int overlay, int light) {

        float[][] vertexes = new float[4][3];

        if (Math.abs(normalY) > 0.5f) {
            if (normalY > 0) {
                vertexes = new float[][]{
                        {x1, y1, z1},
                        {x1, y1, z2},
                        {x2, y1, z2},
                        {x2, y1, z1}
                };

            } else {
                vertexes = new float[][]{
                        {x1, y1, z1},
                        {x2, y1, z1},
                        {x2, y1, z2},
                        {x1, y1, z2}
                };
            }
        } else if (Math.abs(normalZ) > 0.5f) {
            if (normalZ > 0) {
                vertexes = new float[][]{
                        {x1, y1, z1},
                        {x2, y1, z1},
                        {x2, y2, z1},
                        {x1, y2, z1}
                };
            } else {
                vertexes = new float[][]{
                        {x1, y1, z1},
                        {x1, y2, z1},
                        {x2, y2, z1},
                        {x2, y1, z1}
                };
            }
        } else if (Math.abs(normalX) > 0.5f) {
            if (normalX > 0) {
                vertexes = new float[][]{
                        {x1, y1, z1},
                        {x1, y2, z1},
                        {x1, y2, z2},
                        {x1, y1, z2}
                };
            } else {
                vertexes = new float[][]{
                        {x1, y1, z1},
                        {x1, y1, z2},
                        {x1, y2, z2},
                        {x1, y2, z1}
                };
            }
        }

        for (float[] vertex : vertexes) {
            if (vertex.length != 3) continue;

            vertex(vertexConsumer, matrix4f, vertex[0], vertex[1], vertex[2]);
            color(vertexConsumer, r, g, b, alpha);
            texture(vertexConsumer, u, v);
            light(vertexConsumer, light);
            normal(vertexConsumer, stack, normalX, normalY, normalZ);
            next(vertexConsumer);
        }
    }

    public static void renderQuad(DrawObjectMV drawObject, Matrix4f matrix4f, Matrix3f matrix3f,
                            float x1, float y1, float z1, float x2, float y2, float z2,
                            float normalX, float normalY, float normalZ, int r, int g, int b, int alpha, int u, int v, int overlay, int light) {
        renderQuad(drawObject.getBuffer(), drawObject.getStack(), matrix4f, matrix3f,
                x1, y1, z1, x2, y2, z2,
                normalX, normalY, normalZ, r, g, b, alpha, u, v, overlay, light);

    }

    public static void renderQuad(DrawObjectMV drawObject,
                            float x1, float y1, float z1, float x2, float y2, float z2,
                            float normalX, float normalY, float normalZ, int r, int g, int b, int alpha, int u, int v, int overlay, int light) {

        Matrix4f matrix4f = drawObject.getMatrix4f();
        Matrix3f matrix3f = drawObject.getMatrix3f();

        renderQuad(drawObject.getBuffer(), drawObject.getStack(), matrix4f, matrix3f,
                x1, y1, z1, x2, y2, z2,
                normalX, normalY, normalZ, r, g, b, alpha, u, v, overlay, light);

    }
}


package net.pitan76.mcpitanlib.api.util.client.render;

import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexRendering;
import net.minecraft.client.util.math.MatrixStack;
import net.pitan76.mcpitanlib.api.client.render.DrawObjectMV;
import org.joml.Matrix3f;
import org.joml.Matrix4f;

public class VertexRenderingUtil {
    public static void drawBox(MatrixStack matrices, VertexConsumer buffer, double minX, double minY, double minZ, double maxX, double maxY, double maxZ, float red, float green, float blue, float alpha) {
        VertexRendering.drawBox(matrices, buffer, minX, minY, minZ, maxX, maxY, maxZ, red, green, blue, alpha);
    }

    public static void drawBox(MatrixStack matrices, VertexConsumer buffer, double minX, double minY, double minZ, double maxX, double maxY, double maxZ, float red, float green, float blue, float alpha, float xAxisRed, float yAxisGreen, float zAxisBlue) {
        VertexRendering.drawBox(matrices, buffer, minX, minY, minZ, maxX, maxY, maxZ, red, green, blue, alpha, xAxisRed, yAxisGreen, zAxisBlue);
    }

    public static void drawFilledBox(MatrixStack matrices, VertexConsumer buffer, double minX, double minY, double minZ, double maxX, double maxY, double maxZ, float red, float green, float blue, float alpha) {
        VertexRendering.drawFilledBox(matrices, buffer, minX, minY, minZ, maxX, maxY, maxZ, red, green, blue, alpha);
    }

    public static void drawBox(DrawObjectMV drawObject, double minX, double minY, double minZ, double maxX, double maxY, double maxZ, float red, float green, float blue, float alpha) {
        drawBox(drawObject.getStack(), drawObject.getBuffer(), minX, minY, minZ, maxX, maxY, maxZ, red, green, blue, alpha);
    }

    public static void drawBox(DrawObjectMV drawObject, double minX, double minY, double minZ, double maxX, double maxY, double maxZ, float red, float green, float blue, float alpha, float xAxisRed, float yAxisGreen, float zAxisBlue) {
        drawBox(drawObject.getStack(), drawObject.getBuffer(), minX, minY, minZ, maxX, maxY, maxZ, red, green, blue, alpha, xAxisRed, yAxisGreen, zAxisBlue);
    }

    public static void drawFilledBox(DrawObjectMV drawObject, double minX, double minY, double minZ, double maxX, double maxY, double maxZ, float red, float green, float blue, float alpha) {
        drawFilledBox(drawObject.getStack(), drawObject.getBuffer(), minX, minY, minZ, maxX, maxY, maxZ, red, green, blue, alpha);
    }

    public static void renderQuad(VertexConsumer vertexConsumer, MatrixStack stack, Matrix4f matrix4f, Matrix3f matrix3f, float x1, float y1, float z1, float x2, float y2, float z2, float normalX, float normalY, float normalZ, int r, int g, int b, int alpha, int u, int v, int overlay, int light) {
        VertexConsumerUtil.renderQuad(vertexConsumer, stack, matrix4f, matrix3f, x1, y1, z1, x2, y2, z2, normalX, normalY, normalZ, r, g, b, alpha, u, v, overlay, light);
    }

    public static void renderQuad(DrawObjectMV drawObject, float x1, float y1, float z1, float x2, float y2, float z2, float normalX, float normalY, float normalZ, int r, int g, int b, int alpha, int u, int v, int overlay, int light) {
        renderQuad(drawObject.getBuffer(), drawObject.getStack(), drawObject.getMatrix4f(), drawObject.getMatrix3f(), x1, y1, z1, x2, y2, z2, normalX, normalY, normalZ, r, g, b, alpha, u, v, overlay, light);
    }
}

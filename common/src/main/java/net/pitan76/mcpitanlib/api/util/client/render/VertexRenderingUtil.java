package net.pitan76.mcpitanlib.api.util.client.render;

import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.pitan76.mcpitanlib.api.client.render.DrawObjectMV;

public class VertexRenderingUtil {
    public static void drawBox(MatrixStack matrices, VertexConsumer buffer, double minX, double minY, double minZ, double maxX, double maxY, double maxZ, float red, float green, float blue, float alpha) {
        WorldRenderer.drawBox(matrices, buffer, minX, minY, minZ, maxX, maxY, maxZ, red, green, blue, alpha);
    }

    public static void drawBox(MatrixStack matrices, VertexConsumer buffer, double minX, double minY, double minZ, double maxX, double maxY, double maxZ, float red, float green, float blue, float alpha, float xAxisRed, float yAxisGreen, float zAxisBlue) {
        WorldRenderer.drawBox(matrices, buffer, minX, minY, minZ, maxX, maxY, maxZ, red, green, blue, alpha, xAxisRed, yAxisGreen, zAxisBlue);
    }

    public static void drawFilledBox(MatrixStack matrices, VertexConsumer buffer, double minX, double minY, double minZ, double maxX, double maxY, double maxZ, float red, float green, float blue, float alpha) {
        WorldRenderer.method_3258(matrices, buffer, minX, minY, minZ, maxX, maxY, maxZ, red, green, blue, alpha);
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
}

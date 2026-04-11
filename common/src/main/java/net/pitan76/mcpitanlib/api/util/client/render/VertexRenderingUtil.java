package net.pitan76.mcpitanlib.api.util.client.render;

import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.ShapeRenderer;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.world.phys.AABB;
import net.pitan76.mcpitanlib.api.client.render.DrawObjectMV;
import org.joml.Matrix3f;
import org.joml.Matrix4f;

import java.util.Optional;

public class VertexRenderingUtil {
    public static void drawBox(PoseStack matrices, VertexConsumer buffer, double minX, double minY, double minZ, double maxX, double maxY, double maxZ, float red, float green, float blue, float alpha) {
        PoseStack.Pose entry = matrices.last();

        drawLine(buffer, entry, minX, minY, minZ, maxX, minY, minZ, red, green, blue, alpha);
        drawLine(buffer, entry, minX, minY, maxZ, maxX, minY, maxZ, red, green, blue, alpha);
        drawLine(buffer, entry, minX, maxY, minZ, maxX, maxY, minZ, red, green, blue, alpha);
        drawLine(buffer, entry, minX, maxY, maxZ, maxX, maxY, maxZ, red, green, blue, alpha);

        drawLine(buffer, entry, minX, minY, minZ, minX, maxY, minZ, red, green, blue, alpha);
        drawLine(buffer, entry, maxX, minY, minZ, maxX, maxY, minZ, red, green, blue, alpha);
        drawLine(buffer, entry, minX, minY, maxZ, minX, maxY, maxZ, red, green, blue, alpha);
        drawLine(buffer, entry, maxX, minY, maxZ, maxX, maxY, maxZ, red, green, blue, alpha);

        drawLine(buffer, entry, minX, minY, minZ, minX, minY, maxZ, red, green, blue, alpha);
        drawLine(buffer, entry, maxX, minY, minZ, maxX, minY, maxZ, red, green, blue, alpha);
        drawLine(buffer, entry, minX, maxY, minZ, minX, maxY, maxZ, red, green, blue, alpha);
        drawLine(buffer, entry, maxX, maxY, minZ, maxX, maxY, maxZ, red, green, blue, alpha);
    }

    public static void drawBox(PoseStack matrices, VertexConsumer buffer, double minX, double minY, double minZ, double maxX, double maxY, double maxZ, float red, float green, float blue, float alpha, float xAxisRed, float yAxisGreen, float zAxisBlue) {
        PoseStack.Pose entry = matrices.last();

        drawLine(buffer, entry, minX, minY, minZ, maxX, minY, minZ, xAxisRed, green, blue, alpha);
        drawLine(buffer, entry, minX, minY, maxZ, maxX, minY, maxZ, xAxisRed, green, blue, alpha);
        drawLine(buffer, entry, minX, maxY, minZ, maxX, maxY, minZ, xAxisRed, green, blue, alpha);
        drawLine(buffer, entry, minX, maxY, maxZ, maxX, maxY, maxZ, xAxisRed, green, blue, alpha);

        drawLine(buffer, entry, minX, minY, minZ, minX, maxY, minZ, red, yAxisGreen, blue, alpha);
        drawLine(buffer, entry, maxX, minY, minZ, maxX, maxY, minZ, red, yAxisGreen, blue, alpha);
        drawLine(buffer, entry, minX, minY, maxZ, minX, maxY, maxZ, red, yAxisGreen, blue, alpha);
        drawLine(buffer, entry, maxX, minY, maxZ, maxX, maxY, maxZ, red, yAxisGreen, blue, alpha);

        drawLine(buffer, entry, minX, minY, minZ, minX, minY, maxZ, red, green, zAxisBlue, alpha);
        drawLine(buffer, entry, maxX, minY, minZ, maxX, minY, maxZ, red, green, zAxisBlue, alpha);
        drawLine(buffer, entry, minX, maxY, minZ, minX, maxY, maxZ, red, green, zAxisBlue, alpha);
        drawLine(buffer, entry, maxX, maxY, minZ, maxX, maxY, maxZ, red, green, zAxisBlue, alpha);
    }

    public static void drawFilledBox(PoseStack matrices, VertexConsumer buffer, double minX, double minY, double minZ, double maxX, double maxY, double maxZ, float red, float green, float blue, float alpha) {
        PoseStack.Pose entry = matrices.last();
        Matrix4f positionMatrix = entry.pose();

        drawQuad(buffer, positionMatrix, minX, minY, minZ, maxX, minY, minZ, maxX, minY, maxZ, minX, minY, maxZ, red, green, blue, alpha);
        drawQuad(buffer, positionMatrix, minX, maxY, minZ, minX, maxY, maxZ, maxX, maxY, maxZ, maxX, maxY, minZ, red, green, blue, alpha);
        drawQuad(buffer, positionMatrix, minX, minY, minZ, minX, maxY, minZ, maxX, maxY, minZ, maxX, minY, minZ, red, green, blue, alpha);
        drawQuad(buffer, positionMatrix, minX, minY, maxZ, maxX, minY, maxZ, maxX, maxY, maxZ, minX, maxY, maxZ, red, green, blue, alpha);
        drawQuad(buffer, positionMatrix, minX, minY, minZ, minX, minY, maxZ, minX, maxY, maxZ, minX, maxY, minZ, red, green, blue, alpha);
        drawQuad(buffer, positionMatrix, maxX, minY, minZ, maxX, maxY, minZ, maxX, maxY, maxZ, maxX, minY, maxZ, red, green, blue, alpha);
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

    public static void renderQuad(VertexConsumer vertexConsumer, PoseStack stack, Matrix4f matrix4f, Matrix3f matrix3f, float x1, float y1, float z1, float x2, float y2, float z2, float normalX, float normalY, float normalZ, int r, int g, int b, int alpha, int u, int v, int overlay, int light) {
        VertexConsumerUtil.renderQuad(vertexConsumer, stack, matrix4f, matrix3f, x1, y1, z1, x2, y2, z2, normalX, normalY, normalZ, r, g, b, alpha, u, v, overlay, light);
    }

    public static void renderQuad(DrawObjectMV drawObject, float x1, float y1, float z1, float x2, float y2, float z2, float normalX, float normalY, float normalZ, int r, int g, int b, int alpha, int u, int v, int overlay, int light) {
        renderQuad(drawObject.getBuffer(), drawObject.getStack(), drawObject.getMatrix4f(), drawObject.getMatrix3f(), x1, y1, z1, x2, y2, z2, normalX, normalY, normalZ, r, g, b, alpha, u, v, overlay, light);
    }

    // TODO: all version impl
    private static void drawQuad(VertexConsumer buffer, Matrix4f matrix, double x1, double y1, double z1, double x2, double y2, double z2, double x3, double y3, double z3, double x4, double y4, double z4, float red, float green, float blue, float alpha) {
        VertexConsumerUtil.vertex(buffer, matrix, (float)x1, (float)y1, (float)z1).setColor(red, green, blue, alpha);
        VertexConsumerUtil.vertex(buffer, matrix, (float)x2, (float)y2, (float)z2).setColor(red, green, blue, alpha);
        VertexConsumerUtil.vertex(buffer, matrix, (float)x3, (float)y3, (float)z3).setColor(red, green, blue, alpha);
        VertexConsumerUtil.vertex(buffer, matrix, (float)x4, (float)y4, (float)z4).setColor(red, green, blue, alpha);
    }

    // TODO: all version impl
    private static void drawLine(VertexConsumer consumer, PoseStack.Pose entry, double x1, double y1, double z1, double x2, double y2, double z2, float red, float green, float blue, float alpha) {
        float dx = (float)(x2 - x1);
        float dy = (float)(y2 - y1);
        float dz = (float)(z2 - z1);
        float len = (float)Math.sqrt(dx * dx + dy * dy + dz * dz);
        float nx = len == 0.0f ? 0.0f : dx / len;
        float ny = len == 0.0f ? 1.0f : dy / len;
        float nz = len == 0.0f ? 0.0f : dz / len;

        VertexConsumerUtil.vertex(consumer, entry.pose(), (float)x1, (float)y1, (float)z1);
        VertexConsumerUtil.color(consumer, red, green, blue, alpha);
        VertexConsumerUtil.normal(consumer, nx, ny, nz);
        consumer.setLineWidth(2.0f); //
        VertexConsumerUtil.next(consumer);

        VertexConsumerUtil.vertex(consumer, entry.pose(), (float)x2, (float)y2, (float)z2);
        VertexConsumerUtil.color(consumer, red, green, blue, alpha);
        VertexConsumerUtil.normal(consumer, nx, ny, nz);
        consumer.setLineWidth(2.0f); //
        VertexConsumerUtil.next(consumer);
    }
}

package net.pitan76.mcpitanlib.api.util.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.pitan76.mcpitanlib.api.util.MathUtil;

import static net.pitan76.mcpitanlib.api.util.MathUtil.getRotationDegrees;

public class MatrixStackUtil {
    public static void multiply(PoseStack matrixStack, MathUtil.RotationAxisType type, float deg) {
        matrixStack.mulPose(getRotationDegrees(type, deg));
    }

    public static void push(PoseStack matrices) {
        matrices.pushPose();
    }

    public static void pop(PoseStack matrices) {
        matrices.popPose();
    }

    public static void translate(PoseStack matrices, double x, double y, double z) {
        matrices.translate(x, y, z);
    }

    public static void scale(PoseStack matrices, float x, float y, float z) {
        matrices.scale(x, y, z);
    }

    public static PoseStack.Pose peek(PoseStack matrices) {
        return matrices.last();
    }
}

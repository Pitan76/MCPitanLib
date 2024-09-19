package net.pitan76.mcpitanlib.api.util.client;

import net.minecraft.client.util.math.MatrixStack;
import net.pitan76.mcpitanlib.api.util.MathUtil;

import static net.pitan76.mcpitanlib.api.util.MathUtil.getRotationDegrees;

public class MatrixStackUtil {
    public static void multiply(MatrixStack matrixStack, MathUtil.RotationAxisType type, float deg) {
        matrixStack.multiply(getRotationDegrees(type, deg));
    }

    public static void push(MatrixStack matrices) {
        matrices.push();
    }

    public static void pop(MatrixStack matrices) {
        matrices.pop();
    }

    public static void translate(MatrixStack matrices, double x, double y, double z) {
        matrices.translate(x, y, z);
    }

    public static void scale(MatrixStack matrices, float x, float y, float z) {
        matrices.scale(x, y, z);
    }
}

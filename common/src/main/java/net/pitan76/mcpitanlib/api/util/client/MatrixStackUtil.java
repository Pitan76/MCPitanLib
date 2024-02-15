package net.pitan76.mcpitanlib.api.util.client;

import net.minecraft.client.util.math.MatrixStack;
import net.pitan76.mcpitanlib.api.util.MathUtil;

import static net.pitan76.mcpitanlib.api.util.MathUtil.getRotationDegrees;

public class MatrixStackUtil {
    public static void multiply(MatrixStack matrixStack, MathUtil.RotationAxisType type, float deg) {
        matrixStack.multiply(getRotationDegrees(type, deg));
    }
}

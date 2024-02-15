package net.pitan76.mcpitanlib.api.util.client;

import net.pitan76.mcpitanlib.api.util.MathUtil;
import net.minecraft.client.util.math.MatrixStack;

import static net.pitan76.mcpitanlib.api.util.MathUtil.*;

public class MatrixStackUtil {
    public static void multiply(MatrixStack matrixStack, MathUtil.RotationAxisType type, float deg) {
        matrixStack.multiply(getRotationDegrees(type, deg));
    }
}

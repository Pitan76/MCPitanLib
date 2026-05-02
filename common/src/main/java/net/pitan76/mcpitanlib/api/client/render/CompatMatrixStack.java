package net.pitan76.mcpitanlib.api.client.render;

import net.pitan76.mcpitanlib.api.util.MathUtil;
import net.pitan76.mcpitanlib.api.util.client.MatrixStackUtil;

public class CompatMatrixStack {
    private final net.minecraft.client.util.math.MatrixStack matrices;

    public CompatMatrixStack(net.minecraft.client.util.math.MatrixStack matrices) {
        this.matrices = matrices;
    }

    public static CompatMatrixStack of(net.minecraft.client.util.math.MatrixStack matrixStack) {
        return new CompatMatrixStack(matrixStack);
    }

    @Deprecated
    public net.minecraft.client.util.math.MatrixStack getRaw() {
        return matrices;
    }

    public CompatMatrixStack push() {
        MatrixStackUtil.push(getRaw());
        return this;
    }

    public CompatMatrixStack pop() {
        MatrixStackUtil.pop(getRaw());
        return this;
    }

    public CompatMatrixStack translate(double x, double y, double z) {
        MatrixStackUtil.translate(getRaw(), x, y, z);
        return this;
    }

    public CompatMatrixStack translate(float x, float y, float z) {
        getRaw().translate(x, y, z);
        return this;
    }

    public CompatMatrixStack scale(float x, float y, float z) {
        MatrixStackUtil.scale(getRaw(), x, y, z);
        return this;
    }

    public CompatMatrixStack multiply(MathUtil.RotationAxisType type, float deg) {
        MatrixStackUtil.multiply(getRaw(), type, deg);
        return this;
    }
}

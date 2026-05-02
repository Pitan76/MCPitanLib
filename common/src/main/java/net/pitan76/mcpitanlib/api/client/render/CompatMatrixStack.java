package net.pitan76.mcpitanlib.api.client.render;

import net.pitan76.mcpitanlib.api.util.MathUtil;
import net.pitan76.mcpitanlib.api.util.client.MatrixStackUtil;

public class CompatMatrixStack {
    private final com.mojang.blaze3d.vertex.PoseStack matrices;

    public CompatMatrixStack(com.mojang.blaze3d.vertex.PoseStack matrices) {
        this.matrices = matrices;
    }

    public static CompatMatrixStack of(com.mojang.blaze3d.vertex.PoseStack matrixStack) {
        return new CompatMatrixStack(matrixStack);
    }

    @Deprecated
    public com.mojang.blaze3d.vertex.PoseStack getRaw() {
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

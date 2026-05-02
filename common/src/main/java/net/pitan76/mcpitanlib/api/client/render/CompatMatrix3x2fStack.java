package net.pitan76.mcpitanlib.api.client.render;

import net.pitan76.mcpitanlib.api.util.MathUtil;
import org.joml.Matrix3x2fStack;

public class CompatMatrix3x2fStack extends CompatMatrixStack {
    private final Matrix3x2fStack matrices;

    public CompatMatrix3x2fStack(Matrix3x2fStack matrices) {
        super(null);
        this.matrices = matrices;
    }

    @Deprecated
    public Matrix3x2fStack get3x2fRaw() {
        return matrices;
    }

    public CompatMatrix3x2fStack push() {
        get3x2fRaw().pushMatrix();
        return this;
    }

    public CompatMatrix3x2fStack pop() {
        get3x2fRaw().popMatrix();
        return this;
    }

    public CompatMatrix3x2fStack translate(double x, double y, double z) {
        get3x2fRaw().translate((float) x, (float) y);
        return this;
    }

    @Override
    public CompatMatrixStack translate(float x, float y, float z) {
        get3x2fRaw().translate(x, y);
        return this;
    }

    public CompatMatrix3x2fStack scale(float x, float y, float z) {
        get3x2fRaw().scale(x, y);
        return this;
    }

    public CompatMatrix3x2fStack multiply(MathUtil.RotationAxisType type, float deg) {
        return this;
    }
}

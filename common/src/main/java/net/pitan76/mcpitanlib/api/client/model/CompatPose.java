package net.pitan76.mcpitanlib.api.client.model;

import net.minecraft.client.model.ModelPart;

/**
 * パーツの位置と回転 (BlockBench の PartPose に相当)
 * 回転はラジアン
 */
public class CompatPose {
    public static final CompatPose ZERO = new CompatPose(0, 0, 0, 0, 0, 0);

    public final float x, y, z;
    public final float xRot, yRot, zRot;

    protected CompatPose(float x, float y, float z, float xRot, float yRot, float zRot) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.xRot = xRot;
        this.yRot = yRot;
        this.zRot = zRot;
    }

    public static CompatPose offset(float x, float y, float z) {
        return new CompatPose(x, y, z, 0, 0, 0);
    }

    public static CompatPose rotation(float xRot, float yRot, float zRot) {
        return new CompatPose(0, 0, 0, xRot, yRot, zRot);
    }

    public static CompatPose offsetAndRotation(float x, float y, float z, float xRot, float yRot, float zRot) {
        return new CompatPose(x, y, z, xRot, yRot, zRot);
    }

    public void applyTo(ModelPart part) {
        part.setPivot(x, y, z);
        part.pitch = xRot;
        part.yaw = yRot;
        part.roll = zRot;
    }
}

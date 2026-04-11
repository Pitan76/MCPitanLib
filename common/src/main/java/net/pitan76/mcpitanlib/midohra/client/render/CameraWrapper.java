package net.pitan76.mcpitanlib.midohra.client.render;

import net.minecraft.client.Camera;
import net.pitan76.mcpitanlib.midohra.util.math.BlockPos;
import net.pitan76.mcpitanlib.midohra.util.math.Vector3d;

import java.util.Objects;

public class CameraWrapper {
    private final Camera camera;

    protected CameraWrapper(Camera camera) {
        this.camera = camera;
    }

    protected CameraWrapper() {
        this.camera = null;
    }

    public static CameraWrapper of(Camera camera) {
        return new CameraWrapper(camera);
    }

    public static CameraWrapper of() {
        return new CameraWrapper();
    }

    public Camera get() {
        return camera;
    }

    public boolean isPresent() {
        return !isEmpty();
    }

    public boolean isEmpty() {
        return camera == null;
    }

    public Vector3d getCameraPos() {
        if (isEmpty()) return Vector3d.zero();
        return Vector3d.of(camera.position());
    }

    public BlockPos getBlockPos() {
        if (isEmpty()) return BlockPos.of(0, 0, 0);
        return BlockPos.of(camera.blockPosition());
    }

    public float getYaw() {
        if (isEmpty()) return 0f;
        return camera.yRot();
    }

    public float getPitch() {
        if (isEmpty()) return 0f;
        return camera.xRot();
    }

    @Override
    public int hashCode() {
        return camera != null ? camera.hashCode() : 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        CameraWrapper other = (CameraWrapper) obj;
        return Objects.equals(camera, other.camera);
    }
}

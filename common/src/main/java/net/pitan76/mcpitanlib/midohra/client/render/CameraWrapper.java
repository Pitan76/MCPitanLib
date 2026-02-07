package net.pitan76.mcpitanlib.midohra.client.render;

import net.minecraft.client.render.Camera;
import net.pitan76.mcpitanlib.midohra.util.math.BlockPos;
import net.pitan76.mcpitanlib.midohra.util.math.Vector3d;

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
        return Vector3d.of(camera.getPos());
    }

    public BlockPos getBlockPos() {
        if (isEmpty()) return BlockPos.of(0, 0, 0);
        return BlockPos.of(camera.getBlockPos());
    }

    public float getYaw() {
        if (isEmpty()) return 0f;
        return camera.getYaw();
    }

    public float getPitch() {
        if (isEmpty()) return 0f;
        return camera.getPitch();
    }







}

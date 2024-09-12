package net.pitan76.mcpitanlib.api.client.event.listener;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockState;
import net.minecraft.client.render.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.profiler.Profiler;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix4f;

public interface WorldRenderContext {

    WorldRenderer worldRenderer();

    MatrixStack matrixStack();

    float tickDelta();

    long limitTime();

    boolean blockOutlines();

    Camera camera();

    GameRenderer gameRenderer();

    LightmapTextureManager lightmapTextureManager();

    @Deprecated
    Matrix4f projectionMatrix();

    ClientWorld world();

    Profiler profiler();

    @Deprecated
    boolean advancedTranslucency();

    @Nullable VertexConsumerProvider consumers();
    @Nullable Frustum frustum();

    @Environment(EnvType.CLIENT)
    interface BlockOutlineContext {
        @Deprecated
        VertexConsumer vertexConsumer();

        Entity entity();

        double cameraX();

        double cameraY();

        double cameraZ();

        BlockPos blockPos();

        BlockState blockState();
    }
}

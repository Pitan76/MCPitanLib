package net.pitan76.mcpitanlib.api.client.color;

import net.minecraft.client.renderer.block.BlockAndTintGetter;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.pitan76.mcpitanlib.api.tile.RenderAttachmentBlockEntity;
import net.pitan76.mcpitanlib.midohra.world.BlockView;
import org.jetbrains.annotations.Nullable;

public class BlockColorEvent {
    private final BlockState state;
    private final BlockAndTintGetter world;
    private final BlockPos pos;
    private final int tintIndex;

    public BlockColorEvent(BlockState state, @Nullable BlockAndTintGetter world, @Nullable BlockPos pos, int tintIndex) {
        this.state = state;
        this.world = world;
        this.pos = pos;
        this.tintIndex = tintIndex;
    }

    public BlockState getState() {
        return state;
    }

    public BlockAndTintGetter getWorld() {
        return world;
    }

    public BlockPos getPos() {
        return pos;
    }

    public int getTintIndex() {
        return tintIndex;
    }

    public net.pitan76.mcpitanlib.midohra.util.math.BlockPos getMidohraPos() {
        return net.pitan76.mcpitanlib.midohra.util.math.BlockPos.of(getPos());
    }

    public net.pitan76.mcpitanlib.midohra.block.BlockState getMidohraState() {
        return net.pitan76.mcpitanlib.midohra.block.BlockState.of(getState());
    }

    public boolean hasWorld() {
        return getWorld() != null;
    }

    public boolean hasPos() {
        return getPos() != null;
    }

    public BlockView getMidohraWorld() {
        if (!hasWorld()) return null;
        return net.pitan76.mcpitanlib.midohra.world.BlockView.of(getWorld());
    }

    public BlockEntity getBlockEntity() {
        if (!hasWorld() || !hasPos()) return null;
        return getWorld().getBlockEntity(getPos());
    }

    public int getDefaultColor() {
        return 0xFFFFFF;
    }

    public Object getRenderData() {
        if (!hasWorld() || !hasPos()) return null;
        return getRenderDataD(getBlockEntity());
    }

    public static Object getRenderDataD(BlockEntity blockEntity) {
        if (blockEntity instanceof RenderAttachmentBlockEntity) {
            return ((RenderAttachmentBlockEntity) blockEntity).getCompatRenderData();
        }

        if (blockEntity instanceof net.pitan76.mcpitanlib.api.tile.RenderDataBlockEntity) {
            return ((net.pitan76.mcpitanlib.api.tile.RenderDataBlockEntity) blockEntity).getCompatRenderData();
        }

        return blockEntity.getRenderData();
    }
}
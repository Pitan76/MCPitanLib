package net.pitan76.mcpitanlib.api.client.color;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockRenderView;
import net.pitan76.mcpitanlib.midohra.world.BlockView;
import org.jetbrains.annotations.Nullable;

public class BlockColorEvent {
    private final BlockState state;
    private final BlockRenderView world;
    private final BlockPos pos;
    private final int tintIndex;

    public BlockColorEvent(BlockState state, @Nullable BlockRenderView world, @Nullable BlockPos pos, int tintIndex) {
        this.state = state;
        this.world = world;
        this.pos = pos;
        this.tintIndex = tintIndex;
    }

    public BlockState getState() {
        return state;
    }

    public BlockRenderView getWorld() {
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

    @ExpectPlatform
    protected static Object getRenderDataD(BlockEntity blockEntity) {
        if (blockEntity instanceof net.pitan76.mcpitanlib.api.tile.RenderDataBlockEntity) {
            return ((net.pitan76.mcpitanlib.api.tile.RenderDataBlockEntity) blockEntity).getCompatRenderData();
        }

        return null;
    }
}
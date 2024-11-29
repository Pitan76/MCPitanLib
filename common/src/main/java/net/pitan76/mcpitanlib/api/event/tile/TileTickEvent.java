package net.pitan76.mcpitanlib.api.event.tile;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.pitan76.mcpitanlib.midohra.block.entity.BlockEntityWrapper;
import net.pitan76.mcpitanlib.midohra.holder.BlockStatePropertyHolder;
import net.pitan76.mcpitanlib.midohra.world.IWorldView;

public class TileTickEvent<T extends BlockEntity> implements BlockStatePropertyHolder {
    public World world;
    public BlockPos pos;
    public BlockState state;
    public T blockEntity;

    public TileTickEvent(World world, BlockPos pos, BlockState state, T blockEntity) {
        this.world = world;
        this.pos = pos;
        this.state = state;
        this.blockEntity = blockEntity;
    }

    public boolean isClient() {
        return world.isClient();
    }

    public boolean isServer() {
        return !isClient();
    }

    public boolean hasWorld() {
        return world != null;
    }

    public World getWorld() {
        return world;
    }

    public BlockPos getPos() {
        return pos;
    }

    public BlockState getState() {
        if (state == null)
            state = getWorldView().getBlockState(getPos());

        return state;
    }

    public T getBlockEntity() {
        return blockEntity;
    }

    public net.pitan76.mcpitanlib.midohra.world.World getMidohraWorld() {
        return net.pitan76.mcpitanlib.midohra.world.World.of(getWorld());
    }

    public net.pitan76.mcpitanlib.midohra.util.math.BlockPos getMidohraPos() {
        return net.pitan76.mcpitanlib.midohra.util.math.BlockPos.of(getPos());
    }

    public net.pitan76.mcpitanlib.midohra.block.BlockState getMidohraState() {
        if (state == null)
            return getWorldView().getBlockState(getMidohraPos());

        return net.pitan76.mcpitanlib.midohra.block.BlockState.of(getState());
    }

    public BlockEntityWrapper getBlockEntityWrapper() {
        return BlockEntityWrapper.of(getBlockEntity());
    }

    public IWorldView getWorldView() {
        return getMidohraWorld();
    }

    @Override
    public net.pitan76.mcpitanlib.midohra.block.BlockState getBlockState() {
        return getMidohraState();
    }
}

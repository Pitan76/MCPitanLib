package net.pitan76.mcpitanlib.api.event.tile;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.pitan76.mcpitanlib.midohra.block.BlockWrapper;
import net.pitan76.mcpitanlib.midohra.block.entity.BlockEntityWrapper;
import net.pitan76.mcpitanlib.midohra.holder.BlockStatePropertyHolder;
import net.pitan76.mcpitanlib.midohra.world.IWorldView;
import net.pitan76.mcpitanlib.midohra.world.ServerWorld;

import java.util.Optional;

public class TileTickEvent<T extends BlockEntity> implements BlockStatePropertyHolder {
    public Level world;
    public BlockPos pos;
    public BlockState state;
    public T blockEntity;

    public TileTickEvent(Level world, BlockPos pos, BlockState state, T blockEntity) {
        this.world = world;
        this.pos = pos;
        this.state = state;
        this.blockEntity = blockEntity;
    }

    public boolean isClient() {
        return world.isClientSide();
    }

    public boolean isServer() {
        return !isClient();
    }

    public boolean hasWorld() {
        return world != null;
    }

    public Level getWorld() {
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

    public Block getBlock() {
        return getState().getBlock();
    }

    public BlockWrapper getBlockWrapper() {
        return BlockWrapper.of(getBlock());
    }

    public Optional<ServerWorld> getOptionalServerWorld() {
        return getMidohraWorld().toServerWorld();
    }

    public ServerWorld getServerWorld() {
        return getOptionalServerWorld().orElse(null);
    }
}

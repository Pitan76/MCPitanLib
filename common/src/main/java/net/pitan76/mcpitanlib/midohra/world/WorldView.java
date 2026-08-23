package net.pitan76.mcpitanlib.midohra.world;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.material.FluidState;
import net.pitan76.mcpitanlib.api.util.world.WorldAccessUtil;
import net.pitan76.mcpitanlib.midohra.block.entity.BlockEntityWrapper;
import net.pitan76.mcpitanlib.midohra.util.math.BlockPos;

import java.util.Optional;

public class WorldView implements IWorldView, RedstoneView {
    private final net.minecraft.world.level.LevelReader world;

    protected WorldView(net.minecraft.world.level.LevelReader world) {
        this.world = world;
    }

    public static WorldView of(net.minecraft.world.level.LevelReader world) {
        return new WorldView(world);
    }

    protected net.minecraft.world.level.LevelReader getRaw() {
        return world;
    }

    public net.minecraft.world.level.LevelReader toMinecraft() {
        return getRaw();
    }

    public boolean isClient() {
        return WorldAccessUtil.isClient(getRaw());
    }

    public boolean isServer() {
        return !isClient();
    }

    public BlockEntityWrapper getBlockEntity(BlockPos pos) {
        return BlockEntityWrapper.of(WorldAccessUtil.getBlockEntity(getRaw(), pos.toMinecraft()));
    }

    public <T extends BlockEntity> Optional<T> getRawBlockEntity(BlockPos pos, BlockEntityType<T> type) {
        return WorldAccessUtil.getBlockEntity(getRaw(), pos.toMinecraft(), type);
    }

    public <T extends BlockEntity> BlockEntityWrapper getBlockEntity(BlockPos pos, BlockEntityType<T> type) {
        Optional<T> blockEntity = WorldAccessUtil.getBlockEntity(getRaw(), pos.toMinecraft(), type);
        return blockEntity.map(BlockEntityWrapper::of).orElse(BlockEntityWrapper.of());
    }

    @Override
    public BlockEntity getBlockEntity(net.minecraft.core.BlockPos pos) {
        return getRaw().getBlockEntity(pos);
    }

    @Override
    public net.minecraft.world.level.block.state.BlockState getBlockState(net.minecraft.core.BlockPos pos) {
        return getRaw().getBlockState(pos);
    }

    @Override
    public FluidState getFluidState(net.minecraft.core.BlockPos pos) {
        return getRaw().getFluidState(pos);
    }

    @Override
    @Deprecated
    public net.minecraft.world.level.SignalGetter getRedstoneView() {
        return getRaw();
    }

    @Override
    public int hashCode() {
        return getRaw() != null ? getRaw().hashCode() : 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof WorldView)) return false;
        WorldView other = (WorldView) obj;
        return getRaw() != null ? getRaw().equals(other.getRaw()) : other.getRaw() == null;
    }

    @Override
    public boolean isNull() {
        return getRaw() == null;
    }
}

package net.pitan76.mcpitanlib.api.event.block;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.pitan76.mcpitanlib.api.event.BaseEvent;
import net.pitan76.mcpitanlib.api.util.WorldUtil;
import net.pitan76.mcpitanlib.midohra.block.BlockWrapper;
import net.pitan76.mcpitanlib.midohra.block.entity.BlockEntityWrapper;
import net.pitan76.mcpitanlib.midohra.world.IWorldView;

public class BlockPlacedEvent extends BaseEvent {

    public Level world;
    public BlockPos pos;
    public BlockState state;
    public LivingEntity placer;
    public ItemStack stack;

    public BlockPlacedEvent(Level world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack itemStack) {
        this.world = world;
        this.pos = pos;
        this.state = state;
        this.placer = placer;
        this.stack = itemStack;
    }

    public BlockState getState() {
        return state;
    }

    public BlockPos getPos() {
        return pos;
    }

    public Level getWorld() {
        return world;
    }

    public ItemStack getStack() {
        return stack;
    }

    public LivingEntity getPlacer() {
        return placer;
    }

    public boolean isClient() {
        return world.isClientSide();
    }

    public BlockEntity getBlockEntity() {
        return WorldUtil.getBlockEntity(getWorld(), getPos());
    }

    public net.pitan76.mcpitanlib.midohra.world.World getMidohraWorld() {
        return net.pitan76.mcpitanlib.midohra.world.World.of(world);
    }

    public IWorldView getWorldView() {
        return getMidohraWorld();
    }

    public net.pitan76.mcpitanlib.midohra.block.BlockState getMidohraState() {
        return net.pitan76.mcpitanlib.midohra.block.BlockState.of(state);
    }

    public net.pitan76.mcpitanlib.midohra.util.math.BlockPos getMidohraPos() {
        return net.pitan76.mcpitanlib.midohra.util.math.BlockPos.of(pos);
    }

    public BlockWrapper getBlockWrapper() {
        return BlockWrapper.of(state.getBlock());
    }

    public BlockEntityWrapper getBlockEntityWrapper() {
        return BlockEntityWrapper.of(getBlockEntity());
    }
}

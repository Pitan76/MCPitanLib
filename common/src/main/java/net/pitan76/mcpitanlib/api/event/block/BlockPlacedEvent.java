package net.pitan76.mcpitanlib.api.event.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.pitan76.mcpitanlib.api.event.BaseEvent;
import net.pitan76.mcpitanlib.api.util.WorldUtil;

public class BlockPlacedEvent extends BaseEvent {

    public World world;
    public BlockPos pos;
    public BlockState state;
    public LivingEntity placer;
    public ItemStack stack;

    public BlockPlacedEvent(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack itemStack) {
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

    public World getWorld() {
        return world;
    }

    public ItemStack getStack() {
        return stack;
    }

    public LivingEntity getPlacer() {
        return placer;
    }

    public boolean isClient() {
        return world.isClient();
    }

    public BlockEntity getBlockEntity() {
        return WorldUtil.getBlockEntity(getWorld(), getPos());
    }
}

package net.pitan76.mcpitanlib.api.event.block;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.pitan76.mcpitanlib.api.entity.Player;
import net.pitan76.mcpitanlib.api.event.BaseEvent;
import net.pitan76.mcpitanlib.api.util.WorldUtil;

public class BlockBreakStartEvent extends BaseEvent {

    public BlockState state;
    public Level world;
    public BlockPos pos;
    public Player player;

    public BlockBreakStartEvent(BlockState state, Level world, BlockPos pos, Player player) {
        this.state = state;
        this.world = world;
        this.pos = pos;
        this.player = player;
    }

    public boolean isClient() {
        return WorldUtil.isClient(world);
    }

    public BlockState getState() {
        return state;
    }

    public Level getWorld() {
        return world;
    }

    public BlockPos getPos() {
        return pos;
    }

    public Player getPlayer() {
        return player;
    }

    public BlockPos getPlayerPos() {
        return player.getBlockPos();
    }

    public ItemStack getPlayerMainHandStack() {
        return player.getMainHandStack();
    }

    public ItemStack getPlayerOffHandStack() {
        return player.getOffHandStack();
    }

    public BlockEntity getBlockEntity() {
        return WorldUtil.getBlockEntity(getWorld(), getPos());
    }
}

package net.pitan76.mcpitanlib.api.event.block;

import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.pitan76.mcpitanlib.api.entity.Player;
import net.pitan76.mcpitanlib.api.event.BaseEvent;
import net.pitan76.mcpitanlib.api.util.WorldUtil;

public class BlockBreakStartEvent extends BaseEvent {

    public BlockState state;
    public World world;
    public BlockPos pos;
    public Player player;

    public BlockBreakStartEvent(BlockState state, World world, BlockPos pos, Player player) {
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

    public World getWorld() {
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
}

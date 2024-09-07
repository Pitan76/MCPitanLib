package net.pitan76.mcpitanlib.api.event.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.pitan76.mcpitanlib.api.entity.Player;
import net.pitan76.mcpitanlib.api.event.BaseEvent;
import net.pitan76.mcpitanlib.api.util.WorldUtil;

public class BlockBreakEvent extends BaseEvent {
    public World world;
    public BlockPos pos;
    public BlockState state;
    public Player player;

    public BlockBreakEvent(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        this.world = world;
        this.pos = pos;
        this.state = state;
        this.player = new Player(player);
    }

    public BlockPos getPos() {
        return pos;
    }

    public BlockState getState() {
        return state;
    }

    public Player getPlayer() {
        return player;
    }

    public PlayerEntity getPlayerEntity() {
        return player.getPlayerEntity();
    }

    public World getWorld() {
        return world;
    }

    public boolean isClient() {
        return world.isClient();
    }

    public BlockEntity getBlockEntity() {
        return WorldUtil.getBlockEntity(getWorld(), getPos());
    }
}

package net.pitan76.mcpitanlib.api.event.item;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.pitan76.mcpitanlib.api.entity.Player;
import net.pitan76.mcpitanlib.api.util.WorldUtil;

public class CanMineArgs {
    public BlockState state;
    public World world;
    public BlockPos pos;
    public Player miner;

    public CanMineArgs(BlockState state, World world, BlockPos pos, PlayerEntity miner) {
        this.state = state;
        this.world = world;
        this.pos = pos;
        this.miner = new Player(miner);
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

    public Player getMiner() {
        return miner;
    }

    public boolean isExistMiner() {
        return miner.getEntity() != null;
    }

    public boolean isClient() {
        return WorldUtil.isClient(world);
    }
}

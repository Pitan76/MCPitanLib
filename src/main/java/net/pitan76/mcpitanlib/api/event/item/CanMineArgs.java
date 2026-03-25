package net.pitan76.mcpitanlib.api.event.item;

import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.pitan76.mcpitanlib.api.entity.Player;
import net.pitan76.mcpitanlib.api.util.WorldUtil;

public class CanMineArgs {
    public BlockState state;
    public World world;
    public BlockPos pos;

    @Deprecated
    public Player miner;

    @Deprecated
    public ItemStack stack;

    public LivingEntity entity;

    public CanMineArgs(BlockState state, World world, BlockPos pos, PlayerEntity miner) {
        this.state = state;
        this.world = world;
        this.pos = pos;
        this.miner = new Player(miner);
        this.entity = miner;
    }

    public CanMineArgs(ItemStack stack, BlockState state, World world, BlockPos pos, LivingEntity entity) {
        this.stack = stack;
        this.state = state;
        this.world = world;
        this.pos = pos;
        this.entity = entity;

        if (entity instanceof PlayerEntity) {
            this.miner = new Player((PlayerEntity) entity);
        }
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

    public ItemStack getStack() {
        return stack != null ? stack : entity.getMainHandStack();
    }

    public LivingEntity getEntity() {
        return entity;
    }
}

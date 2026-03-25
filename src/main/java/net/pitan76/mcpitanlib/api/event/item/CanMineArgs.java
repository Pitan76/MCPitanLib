package net.pitan76.mcpitanlib.api.event.item;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.pitan76.mcpitanlib.api.entity.Player;
import net.pitan76.mcpitanlib.api.util.WorldUtil;

public class CanMineArgs {
    public BlockState state;
    public Level world;
    public BlockPos pos;

    @Deprecated
    public Player miner;

    @Deprecated
    public ItemStack stack;

    public LivingEntity entity;

    public CanMineArgs(BlockState state, Level world, BlockPos pos, Player miner) {
        this.state = state;
        this.world = world;
        this.pos = pos;
        this.miner = new Player(miner);
        this.entity = miner;
    }

    public CanMineArgs(ItemStack stack, BlockState state, Level world, BlockPos pos, LivingEntity entity) {
        this.stack = stack;
        this.state = state;
        this.world = world;
        this.pos = pos;
        this.entity = entity;

        if (entity instanceof Player) {
            this.miner = new Player((Player) entity);
        }
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
        return stack != null ? stack : entity.getMainHandItem();
    }

    public LivingEntity getEntity() {
        return entity;
    }
}

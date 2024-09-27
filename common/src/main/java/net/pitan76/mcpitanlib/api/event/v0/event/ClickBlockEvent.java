package net.pitan76.mcpitanlib.api.event.v0.event;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.pitan76.mcpitanlib.api.entity.Player;
import net.pitan76.mcpitanlib.api.util.BlockStateUtil;
import net.pitan76.mcpitanlib.api.util.WorldUtil;

public class ClickBlockEvent {
    public Player player;
    public Hand hand;
    public BlockPos pos;
    public Direction direction;

    public ClickBlockEvent(Player player, Hand hand, BlockPos pos, Direction direction) {
        this.player = player;
        this.hand = hand;
        this.pos = pos;
        this.direction = direction;
    }

    public ClickBlockEvent(PlayerEntity player, Hand hand, BlockPos pos, Direction direction) {
        this.player = new Player(player);
        this.hand = hand;
        this.pos = pos;
        this.direction = direction;
    }

    public Player getPlayer() {
        return player;
    }

    public Hand getHand() {
        return hand;
    }

    public BlockPos getPos() {
        return pos;
    }

    public Direction getDirection() {
        return direction;
    }

    public boolean isExistPlayer() {
        return player.getEntity() != null;
    }

    public ItemStack getStackInHand() {
        return player.getStackInHand(hand);
    }

    public boolean isEmptyStackInHand() {
        return getStackInHand().isEmpty();
    }

    public World getWorld() {
        return player.getWorld();
    }

    public BlockState getBlockState() {
        return WorldUtil.getBlockState(getWorld(), getPos());
    }

    public Block getBlock() {
        return BlockStateUtil.getBlock(getBlockState());
    }




}

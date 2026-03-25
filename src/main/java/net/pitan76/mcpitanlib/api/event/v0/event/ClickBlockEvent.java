package net.pitan76.mcpitanlib.api.event.v0.event;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.InteractionHand;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.pitan76.mcpitanlib.api.entity.Player;
import net.pitan76.mcpitanlib.api.util.BlockStateUtil;
import net.pitan76.mcpitanlib.api.util.WorldUtil;

public class ClickBlockEvent {
    public Player player;
    public InteractionHand hand;
    public BlockPos pos;
    public Direction direction;

    public ClickBlockEvent(Player player, InteractionHand hand, BlockPos pos, Direction direction) {
        this.player = player;
        this.hand = hand;
        this.pos = pos;
        this.direction = direction;
    }

    public ClickBlockEvent(Player player, InteractionHand hand, BlockPos pos, Direction direction) {
        this.player = new Player(player);
        this.hand = hand;
        this.pos = pos;
        this.direction = direction;
    }

    public Player getPlayer() {
        return player;
    }

    public InteractionHand getHand() {
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

    public Level getWorld() {
        return player.getWorld();
    }

    public BlockState getBlockState() {
        return WorldUtil.getBlockState(getWorld(), getPos());
    }

    public Block getBlock() {
        return BlockStateUtil.getBlock(getBlockState());
    }




}

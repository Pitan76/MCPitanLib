package net.pitan76.mcpitanlib.api.event.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.pitan76.mcpitanlib.api.entity.Player;
import net.pitan76.mcpitanlib.api.event.BaseEvent;
import net.pitan76.mcpitanlib.api.util.CompatActionResult;
import net.pitan76.mcpitanlib.api.util.WorldUtil;
import net.pitan76.mcpitanlib.midohra.block.BlockWrapper;
import net.pitan76.mcpitanlib.midohra.block.entity.BlockEntityWrapper;
import net.pitan76.mcpitanlib.midohra.item.ItemWrapper;
import net.pitan76.mcpitanlib.midohra.world.IWorldView;

public class BlockUseEvent extends BaseEvent {
    public BlockState state;
    public World world;
    public BlockPos pos;
    public Player player;
    public Hand hand;
    public BlockHitResult hit;
    public ItemStack stack;

    public BlockUseEvent(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        this.state = state;
        this.world = world;
        this.pos = pos;
        this.player = new Player(player);
        this.hand = hand;
        this.hit = hit;
        this.stack = this.player.getStackInHand(hand);
    }

    public BlockHitResult getHit() {
        return hit;
    }

    /**
     * @return the hit result as a midohra BlockHitResult
     */
    public net.pitan76.mcpitanlib.midohra.util.hit.BlockHitResult getMidohraHit() {
        return net.pitan76.mcpitanlib.midohra.util.hit.BlockHitResult.of(hit);
    }

    /**
     * @return the side of the block that was clicked
     */
    public net.pitan76.mcpitanlib.midohra.util.math.Direction getSide() {
        return getMidohraHit().getSideM();
    }

    public ItemStack getStack() {
        return stack;
    }

    public Player getPlayer() {
        return player;
    }

    public Hand getHand() {
        return hand;
    }

    public World getWorld() {
        return world;
    }

    public BlockState getState() {
        return state;
    }

    public BlockPos getPos() {
        return pos;
    }

    public boolean isClient() {
        return world.isClient();
    }

    public CompatActionResult success() {
        return CompatActionResult.SUCCESS;
    }

    public CompatActionResult fail() {
        return CompatActionResult.FAIL;
    }

    public CompatActionResult pass() {
        return CompatActionResult.PASS;
    }

    public CompatActionResult consume() {
        return CompatActionResult.CONSUME;
    }

    /**
     * check if the block has a block entity
     * @return true if the block has a block entity
     */
    public boolean hasBlockEntity() {
        return WorldUtil.hasBlockEntity(world, pos);
    }

    /**
     * get the block entity of the block
     * @return the block entity of the block
     */
    public BlockEntity getBlockEntity() {
        return WorldUtil.getBlockEntity(world, pos);
    }

    public boolean isSneaking() {
        return player.isSneaking();
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

    public net.pitan76.mcpitanlib.midohra.item.ItemStack getStackM() {
        return net.pitan76.mcpitanlib.midohra.item.ItemStack.of(stack);
    }

    public Item getItem() {
        return stack.getItem();
    }

    public ItemWrapper getItemWrapper() {
        return ItemWrapper.of(getItem());
    }

    public BlockUseEvent(net.pitan76.mcpitanlib.midohra.block.BlockState state, net.pitan76.mcpitanlib.midohra.world.World world, net.pitan76.mcpitanlib.midohra.util.math.BlockPos pos, Player player, Hand hand, BlockHitResult hit) {
        this(state.toMinecraft(), world.toMinecraft(), pos.toMinecraft(), player.getEntity(), hand, hit);
    }
}

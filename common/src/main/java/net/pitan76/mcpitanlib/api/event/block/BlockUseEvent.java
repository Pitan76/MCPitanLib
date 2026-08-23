package net.pitan76.mcpitanlib.api.event.block;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
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
    public Level world;
    public BlockPos pos;
    public Player player;
    public InteractionHand hand;
    public BlockHitResult hit;
    public ItemStack stack;

    public BlockUseEvent(BlockState state, Level world, BlockPos pos, net.minecraft.world.entity.player.Player player, InteractionHand hand, BlockHitResult hit) {
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

    public InteractionHand getHand() {
        return hand;
    }

    public Level getWorld() {
        return world;
    }

    public BlockState getState() {
        return state;
    }

    public BlockPos getPos() {
        return pos;
    }

    public boolean isClient() {
        return world.isClientSide();
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

    public BlockUseEvent(net.pitan76.mcpitanlib.midohra.block.BlockState state, net.pitan76.mcpitanlib.midohra.world.World world, net.pitan76.mcpitanlib.midohra.util.math.BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        this(state.toMinecraft(), world.toMinecraft(), pos.toMinecraft(), player.getEntity(), hand, hit);
    }
}

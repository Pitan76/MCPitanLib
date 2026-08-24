package net.pitan76.mcpitanlib.api.event.item;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.Level;
import net.pitan76.mcpitanlib.api.entity.Player;
import net.pitan76.mcpitanlib.api.event.BaseEvent;
import net.pitan76.mcpitanlib.api.util.CompatActionResult;
import net.pitan76.mcpitanlib.api.util.WorldUtil;
import net.pitan76.mcpitanlib.midohra.block.BlockWrapper;
import net.pitan76.mcpitanlib.midohra.block.entity.BlockEntityWrapper;
import net.pitan76.mcpitanlib.midohra.item.ItemWrapper;
import net.pitan76.mcpitanlib.midohra.world.IWorldView;
import org.jetbrains.annotations.Nullable;

public class ItemUseOnBlockEvent extends BaseEvent {
    public Player player;
    public InteractionHand hand;
    public BlockHitResult hit;
    public ItemStack stack;
    public Level world;
    public BlockPos blockPos;

    public ItemUseOnBlockEvent(net.minecraft.world.entity.player.Player player, InteractionHand hand, BlockHitResult hit) {
        this(player.level(), player, hand, player.getItemInHand(hand), hit);
    }

    public ItemUseOnBlockEvent(Level world, @Nullable net.minecraft.world.entity.player.Player player, InteractionHand hand, ItemStack stack, BlockHitResult hit) {
        if (player != null)
            this.player = new Player(player);
        this.hand = hand;
        this.hit = hit;
        this.stack = stack;
        this.world = world;
        this.blockPos = hit.getBlockPos();
    }

    public ItemUseOnBlockEvent(Player player, InteractionHand hand, BlockHitResult hit) {
        this(player.getWorld(), player.getEntity(), hand, player.getStackInHand(hand), hit);
    }

    public UseOnContext toIUC() {
        return new UseOnContext(player.getPlayerEntity(), hand, hit);
    }

    public boolean isClient() {
        return world.isClientSide();
    }

    public Player getPlayer() {
        return player;
    }

    public Level getWorld() {
        return world;
    }

    public BlockHitResult getHit() {
        return hit;
    }

    public BlockPos getBlockPos() {
        return blockPos;
    }

    public InteractionHand getHand() {
        return hand;
    }

    public ItemStack getStack() {
        return stack;
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

    public BlockEntity getBlockEntity() {
        return WorldUtil.getBlockEntity(world, blockPos);
    }

    public boolean hasBlockEntity() {
        return WorldUtil.hasBlockEntity(world, blockPos);
    }

    public BlockState getBlockState() {
        return WorldUtil.getBlockState(world, blockPos);
    }

    public Vec3 getPos() {
        return hit.getLocation();
    }

    public Direction getSide() {
        return hit.getDirection();
    }

    public net.pitan76.mcpitanlib.midohra.util.math.Direction getSideM() {
        return net.pitan76.mcpitanlib.midohra.util.math.Direction.of(getSide());
    }

    public net.pitan76.mcpitanlib.midohra.world.World getMidohraWorld() {
        return net.pitan76.mcpitanlib.midohra.world.World.of(world);
    }

    public IWorldView getWorldView() {
        return getMidohraWorld();
    }

    public net.pitan76.mcpitanlib.midohra.block.BlockState getMidohraState() {
        return net.pitan76.mcpitanlib.midohra.block.BlockState.of(getBlockState());
    }

    public net.pitan76.mcpitanlib.midohra.util.math.BlockPos getMidohraPos() {
        return net.pitan76.mcpitanlib.midohra.util.math.BlockPos.of(getBlockPos());
    }

    public BlockWrapper getBlockWrapper() {
        return getMidohraState().getBlock();
    }

    public BlockEntityWrapper getBlockEntityWrapper() {
        return BlockEntityWrapper.of(getBlockEntity());
    }

    public boolean isSneaking() {
        return player.isSneaking();
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
}

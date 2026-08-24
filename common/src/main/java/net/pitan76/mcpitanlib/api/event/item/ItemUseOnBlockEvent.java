package net.pitan76.mcpitanlib.api.event.item;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
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
    public Hand hand;
    public BlockHitResult hit;
    public ItemStack stack;
    public World world;
    public BlockPos blockPos;

    public ItemUseOnBlockEvent(PlayerEntity player, Hand hand, BlockHitResult hit) {
        this(player.getWorld(), player, hand, player.getStackInHand(hand), hit);
    }

    public ItemUseOnBlockEvent(World world, @Nullable PlayerEntity player, Hand hand, ItemStack stack, BlockHitResult hit) {
        if (player != null)
            this.player = new Player(player);
        this.hand = hand;
        this.hit = hit;
        this.stack = stack;
        this.world = world;
        this.blockPos = hit.getBlockPos();
    }

    public ItemUseOnBlockEvent(Player player, Hand hand, BlockHitResult hit) {
        this(player.getWorld(), player.getEntity(), hand, player.getStackInHand(hand), hit);
    }

    public ItemUsageContext toIUC() {
        return new ItemUsageContext(player.getPlayerEntity(), hand, hit);
    }

    public boolean isClient() {
        return world.isClient();
    }

    public Player getPlayer() {
        return player;
    }

    public World getWorld() {
        return world;
    }

    public BlockHitResult getHit() {
        return hit;
    }

    public BlockPos getBlockPos() {
        return blockPos;
    }

    public Hand getHand() {
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

    public Vec3d getPos() {
        return hit.getPos();
    }

    public Direction getSide() {
        return hit.getSide();
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

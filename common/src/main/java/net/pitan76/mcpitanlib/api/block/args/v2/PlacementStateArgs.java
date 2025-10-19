package net.pitan76.mcpitanlib.api.block.args.v2;

import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.property.Property;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.pitan76.mcpitanlib.api.entity.Player;
import net.pitan76.mcpitanlib.api.event.BaseEvent;
import net.pitan76.mcpitanlib.api.event.item.ItemUseOnBlockEvent;
import net.pitan76.mcpitanlib.api.util.BlockStateUtil;
import net.pitan76.mcpitanlib.midohra.holder.BlockStatePropertyHolder;
import net.pitan76.mcpitanlib.midohra.block.BlockState;
import net.pitan76.mcpitanlib.midohra.block.BlockWrapper;
import net.pitan76.mcpitanlib.midohra.block.entity.BlockEntityWrapper;
import net.pitan76.mcpitanlib.midohra.util.hit.HitResultType;
import net.pitan76.mcpitanlib.midohra.util.math.Direction;
import net.pitan76.mcpitanlib.midohra.world.IWorldView;
import net.pitan76.mcpitanlib.midohra.world.World;
import net.pitan76.mcpitanlib.mixin.ItemUsageContextMixin;
import org.jetbrains.annotations.Nullable;

public class PlacementStateArgs extends BaseEvent implements BlockStatePropertyHolder {
    public ItemPlacementContext ctx;

    @Nullable
    public Block block;

    public PlacementStateArgs(ItemPlacementContext ctx) {
        this.ctx = ctx;
    }

    public PlacementStateArgs(ItemPlacementContext ctx, @Nullable Block block) {
        this.ctx = ctx;
        this.block = block;
    }

    public boolean canPlace() {
        return ctx.canPlace();
    }

    public BlockPos getRawPos() {
        return ctx.getBlockPos();
    }

    public net.pitan76.mcpitanlib.midohra.util.math.BlockPos getPos() {
        return net.pitan76.mcpitanlib.midohra.util.math.BlockPos.of(getRawPos());
    }

    public Player getPlayer() {
        return new Player(ctx.getPlayer());
    }

    public Direction[] getPlacementDirections() {
        net.minecraft.util.math.Direction[] rawDirs = getRawPlacementDirections();
        Direction[] directions = new Direction[rawDirs.length];
        for (int i = 0; i < directions.length; i++) {
            directions[i] = Direction.of(rawDirs[i]);
        }

        return directions;
    }

    public net.minecraft.util.math.Direction[] getRawPlacementDirections() {
        return ctx.getPlacementDirections();
    }

    public Hand getHand() {
        return ctx.getHand();
    }

    public Direction getSide() {
        return Direction.of(getRawSide());
    }

    public net.minecraft.util.math.Direction getRawSide() {
        return ctx.getSide();
    }

    public Direction getHorizontalPlayerFacing() {
        return Direction.of(getRawHorizontalPlayerFacing());
    }

    public net.minecraft.util.math.Direction getRawHorizontalPlayerFacing() {
        return ctx.getPlayerFacing();
    }

    public float getPlayerYaw() {
        return ctx.getPlayerYaw();
    }

    public World getWorld() {
        return World.of(ctx.getWorld());
    }

    public IWorldView getWorldView() {
        return getWorld();
    }

    public boolean isClient() {
        return getWorld().isClient();
    }

    public Vec3d getHitPos() {
        return ctx.getHitPos();
    }

    public boolean canReplaceExisting() {
        return ctx.canReplaceExisting();
    }

    @Deprecated
    public ItemUsageContextMixin getIUCAccessor() {
        return (ItemUsageContextMixin) ctx;
    }

    public BlockHitResult getHitResult() {
        return getIUCAccessor().getHit();
    }

    public ItemUseOnBlockEvent toItemUseOnBlockEvent() {
        return new ItemUseOnBlockEvent(getWorld().getRaw(), getPlayer().getPlayerEntity(), getHand(), ctx.getStack(), getHitResult());
    }

    public ItemPlacementContext getCtx() {
        return ctx;
    }

    public @Nullable Block getRawBlock() {
        return block;
    }

    public boolean isBlockExist() {
        return block != null;
    }

    public net.minecraft.block.BlockState getRawBlockState() {
        return BlockStateUtil.getDefaultState(block);
    }

    public BlockEntity getRawBlockEntity() {
        return getWorld().getBlockEntity(getRawPos());
    }

    public BlockWrapper getBlock() {
        return BlockWrapper.of(block);
    }

    public <T extends Comparable<T>, V extends T> net.minecraft.block.BlockState with(Property<T> property, V value) {
        if (block == null)
            return null;

        return BlockStateUtil.with(BlockStateUtil.getDefaultState(block), property, value);
    }

    @Override
    public BlockState getBlockState() {
        return BlockState.of(getRawBlockState());
    }

    public BlockEntityWrapper getBlockEntity() {
        return getWorld().getBlockEntity(getPos());
    }

    public net.pitan76.mcpitanlib.midohra.util.hit.BlockHitResult getHitResultM() {
        return net.pitan76.mcpitanlib.midohra.util.hit.BlockHitResult.of(getHitResult());
    }

    public HitResultType getHitResultTypeM() {
        return HitResultType.from(getHitResult().getType());
    }
}

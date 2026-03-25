package net.pitan76.mcpitanlib.api.block.args.v2;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.Vec3;
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
import net.pitan76.mcpitanlib.mixin.UseOnContextMixin;
import org.jetbrains.annotations.Nullable;

public class PlacementStateArgs extends BaseEvent implements BlockStatePropertyHolder {
    public BlockPlaceContext ctx;

    @Nullable
    public Block block;

    public PlacementStateArgs(BlockPlaceContext ctx) {
        this.ctx = ctx;
    }

    public PlacementStateArgs(BlockPlaceContext ctx, @Nullable Block block) {
        this.ctx = ctx;
        this.block = block;
    }

    public boolean canPlace() {
        return ctx.canPlace();
    }

    public BlockPos getRawPos() {
        return ctx.getClickedPos();
    }

    public net.pitan76.mcpitanlib.midohra.util.math.BlockPos getPos() {
        return net.pitan76.mcpitanlib.midohra.util.math.BlockPos.of(getRawPos());
    }

    public Player getPlayer() {
        return new Player(ctx.getPlayer());
    }

    public Direction[] getPlacementDirections() {
        net.minecraft.core.Direction[] rawDirs = getRawPlacementDirections();
        Direction[] directions = new Direction[rawDirs.length];
        for (int i = 0; i < directions.length; i++) {
            directions[i] = Direction.of(rawDirs[i]);
        }

        return directions;
    }

    public net.minecraft.core.Direction[] getRawPlacementDirections() {
        return ctx.getNearestLookingDirections();
    }

    public InteractionHand getHand() {
        return ctx.getHand();
    }

    public Direction getSide() {
        return Direction.of(getRawSide());
    }

    public net.minecraft.core.Direction getRawSide() {
        return ctx.getClickedFace();
    }

    public Direction getHorizontalPlayerFacing() {
        return Direction.of(getRawHorizontalPlayerFacing());
    }

    public net.minecraft.core.Direction getRawHorizontalPlayerFacing() {
        return ctx.getHorizontalDirection();
    }

    public float getPlayerYaw() {
        return ctx.getRotation();
    }

    public World getWorld() {
        return World.of(ctx.getLevel());
    }

    public IWorldView getWorldView() {
        return getWorld();
    }

    public boolean isClient() {
        return getWorld().isClient();
    }

    public Vec3 getHitPos() {
        return ctx.getClickLocation();
    }

    public boolean canReplaceExisting() {
        return ctx.replacingClickedOnBlock();
    }

    @Deprecated
    public UseOnContextMixin getIUCAccessor() {
        return (UseOnContextMixin) ctx;
    }

    public BlockHitResult getHitResult() {
        return getIUCAccessor().getHitResult();
    }

    public ItemUseOnBlockEvent toItemUseOnBlockEvent() {
        return new ItemUseOnBlockEvent(getWorld().getRaw(), getPlayer().getPlayerEntity(), getHand(), ctx.getItemInHand(), getHitResult());
    }

    public BlockPlaceContext getCtx() {
        return ctx;
    }

    public @Nullable Block getRawBlock() {
        return block;
    }

    public boolean isBlockExist() {
        return block != null;
    }

    public net.minecraft.world.level.block.state.BlockState getRawBlockState() {
        return BlockStateUtil.getDefaultState(block);
    }

    public BlockEntity getRawBlockEntity() {
        return getWorld().getBlockEntity(getRawPos());
    }

    public BlockWrapper getBlock() {
        return BlockWrapper.of(block);
    }

    public <T extends Comparable<T>, V extends T> net.minecraft.world.level.block.state.BlockState with(Property<T> property, V value) {
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

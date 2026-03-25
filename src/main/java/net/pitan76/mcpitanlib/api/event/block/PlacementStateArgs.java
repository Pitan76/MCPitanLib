package net.pitan76.mcpitanlib.api.event.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.Level;
import net.pitan76.mcpitanlib.api.entity.Player;
import net.pitan76.mcpitanlib.api.event.BaseEvent;
import net.pitan76.mcpitanlib.api.event.item.ItemUseOnBlockEvent;
import net.pitan76.mcpitanlib.api.state.property.IProperty;
import net.pitan76.mcpitanlib.api.util.BlockStateUtil;
import net.pitan76.mcpitanlib.api.util.WorldUtil;
import net.pitan76.mcpitanlib.mixin.UseOnContextMixin;
import org.jetbrains.annotations.Nullable;

public class PlacementStateArgs extends BaseEvent {
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

    public BlockPos getPos() {
        return ctx.getClickedPos();
    }

    public Player getPlayer() {
        return new Player(ctx.getPlayer());
    }

    public Direction[] getPlacementDirections() {
        return ctx.getNearestLookingDirections();
    }

    public InteractionHand getHand() {
        return ctx.getHand();
    }

    public Direction getSide() {
        return ctx.getClickedFace();
    }

    public Direction getHorizontalPlayerFacing() {
        return ctx.getHorizontalDirection();
    }

    public float getPlayerYaw() {
        return ctx.getRotation();
    }

    public Level getWorld() {
        return ctx.getLevel();
    }

    public boolean isClient() {
        return getWorld().isClientSide();
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
        return new ItemUseOnBlockEvent(getWorld(), getPlayer().getPlayerEntity(), getHand(), ctx.getItemInHand(), getHitResult());
    }

    public BlockPlaceContext getCtx() {
        return ctx;
    }

    public <T extends Comparable<T>, V extends T> BlockState withBlockState(Property<T> property, V value) {
        if (block == null)
            return null;

        return BlockStateUtil.with(BlockStateUtil.getDefaultState(block), property, value);
    }

    public <T extends Comparable<T>, V extends T> net.pitan76.mcpitanlib.midohra.block.BlockState with(IProperty<T> property, V value) {
        return net.pitan76.mcpitanlib.midohra.block.BlockState.of(withBlockState(property.getProperty(), value));
    }

    public BlockState getBlockState() {
        return BlockStateUtil.getDefaultState(block);
    }

    public net.pitan76.mcpitanlib.midohra.block.BlockState getMidohraBlockState() {
        return net.pitan76.mcpitanlib.midohra.block.BlockState.of(getBlockState());
    }

    public BlockEntity getBlockEntity() {
        return WorldUtil.getBlockEntity(getWorld(), getPos());
    }
}

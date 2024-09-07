package net.pitan76.mcpitanlib.api.event.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.property.Property;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.pitan76.mcpitanlib.api.entity.Player;
import net.pitan76.mcpitanlib.api.event.BaseEvent;
import net.pitan76.mcpitanlib.api.event.item.ItemUseOnBlockEvent;
import net.pitan76.mcpitanlib.api.util.BlockStateUtil;
import net.pitan76.mcpitanlib.api.util.WorldUtil;
import net.pitan76.mcpitanlib.mixin.ItemUsageContextMixin;
import org.jetbrains.annotations.Nullable;

public class PlacementStateArgs extends BaseEvent {
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

    public BlockPos getPos() {
        return ctx.getBlockPos();
    }

    public Player getPlayer() {
        return new Player(ctx.getPlayer());
    }

    public Direction[] getPlacementDirections() {
        return ctx.getPlacementDirections();
    }

    public Hand getHand() {
        return ctx.getHand();
    }

    public Direction getSide() {
        return ctx.getSide();
    }

    public Direction getHorizontalPlayerFacing() {
        return ctx.getPlayerFacing();
    }

    public float getPlayerYaw() {
        return ctx.getPlayerYaw();
    }

    public World getWorld() {
        return ctx.getWorld();
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
        return new ItemUseOnBlockEvent(getWorld(), getPlayer().getPlayerEntity(), getHand(), ctx.getStack(), getHitResult());
    }

    public ItemPlacementContext getCtx() {
        return ctx;
    }

    public <T extends Comparable<T>, V extends T> BlockState withBlockState(Property<T> property, V value) {
        if (block == null)
            return null;

        return BlockStateUtil.with(BlockStateUtil.getDefaultState(block), property, value);
    }

    public BlockEntity getBlockEntity() {
        return WorldUtil.getBlockEntity(getWorld(), getPos());
    }
}

package net.pitan76.mcpitanlib.midohra.util.hit;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;

public class BlockHitResult extends HitResult {
    public BlockHitResult(net.minecraft.world.phys.BlockHitResult raw) {
        super(raw);
    }

    public static BlockHitResult of(net.minecraft.world.phys.BlockHitResult raw) {
        return new BlockHitResult(raw);
    }

    @Deprecated
    public net.minecraft.world.phys.BlockHitResult getRaw() {
        return (net.minecraft.world.phys.BlockHitResult) super.getRaw();
    }

    public net.minecraft.world.phys.BlockHitResult withSide(Direction side) {
        return getRaw().withDirection(side);
    }

    public net.minecraft.world.phys.BlockHitResult withBlockPos(BlockPos blockPos) {
        return getRaw().withPosition(blockPos);
    }

    public net.minecraft.world.phys.BlockHitResult againstWorldBorder() {
        return getRaw().hitBorder();
    }

    public BlockPos getBlockPos() {
        return getRaw().getBlockPos();
    }

    public net.pitan76.mcpitanlib.midohra.util.math.BlockPos getBlockPosM() {
        return net.pitan76.mcpitanlib.midohra.util.math.BlockPos.of(getBlockPos());
    }

    public Direction getSide() {
        return getRaw().getDirection();
    }

    public net.pitan76.mcpitanlib.midohra.util.math.Direction getSideM() {
        return net.pitan76.mcpitanlib.midohra.util.math.Direction.of(getSide());
    }

    public boolean isInsideBlock() {
        return getRaw().isInside();
    }

    public boolean isAgainstWorldBorder() {
        return getRaw().isWorldBorderHit();
    }

    public HitResultType getType() {
        return HitResultType.from(getRaw().getType());
    }
}

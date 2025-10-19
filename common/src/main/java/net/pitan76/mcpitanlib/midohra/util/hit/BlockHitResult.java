package net.pitan76.mcpitanlib.midohra.util.hit;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

public class BlockHitResult extends HitResult {
    public BlockHitResult(net.minecraft.util.hit.BlockHitResult raw) {
        super(raw);
    }

    public static BlockHitResult of(net.minecraft.util.hit.BlockHitResult raw) {
        return new BlockHitResult(raw);
    }

    @Deprecated
    public net.minecraft.util.hit.BlockHitResult getRaw() {
        return (net.minecraft.util.hit.BlockHitResult) super.getRaw();
    }

    public net.minecraft.util.hit.BlockHitResult withSide(Direction side) {
        return getRaw().withSide(side);
    }

    public net.minecraft.util.hit.BlockHitResult withBlockPos(BlockPos blockPos) {
        return getRaw().withBlockPos(blockPos);
    }

    public net.minecraft.util.hit.BlockHitResult againstWorldBorder() {
        return null;
    }

    public BlockPos getBlockPos() {
        return getRaw().getBlockPos();
    }

    public net.pitan76.mcpitanlib.midohra.util.math.BlockPos getBlockPosM() {
        return net.pitan76.mcpitanlib.midohra.util.math.BlockPos.of(getBlockPos());
    }

    public Direction getSide() {
        return getRaw().getSide();
    }

    public net.pitan76.mcpitanlib.midohra.util.math.Direction getSideM() {
        return net.pitan76.mcpitanlib.midohra.util.math.Direction.of(getSide());
    }

    public boolean isInsideBlock() {
        return getRaw().isInsideBlock();
    }

    public boolean isAgainstWorldBorder() {
        return false;
    }

    public HitResultType getType() {
        return HitResultType.from(getRaw().getType());
    }
}

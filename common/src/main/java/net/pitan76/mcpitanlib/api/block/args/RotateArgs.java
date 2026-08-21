package net.pitan76.mcpitanlib.api.block.args;

import net.minecraft.block.BlockState;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.Direction;
import net.pitan76.mcpitanlib.api.util.math.CompatBlockRotation;
import net.pitan76.mcpitanlib.midohra.holder.BlockStatePropertyHolder;

public class RotateArgs implements BlockStatePropertyHolder {

    public BlockState state;
    public BlockRotation rotation;

    public RotateArgs(BlockState state, BlockRotation rotation) {
        this.state = state;
        this.rotation = rotation;
    }

    public BlockState getRawBlockState() {
        return state;
    }

    public BlockRotation getRawRotation() {
        return rotation;
    }

    public CompatBlockRotation getRotation() {
        return CompatBlockRotation.of(rotation);
    }

    public net.pitan76.mcpitanlib.midohra.block.BlockState getRotatedBlockState() {
        return getBlockState().rotate(getRotation());
    }

    @Override
    public net.pitan76.mcpitanlib.midohra.block.BlockState getBlockState() {
        return net.pitan76.mcpitanlib.midohra.block.BlockState.of(state);
    }

    public Direction rotate(Direction direction) {
        return rotation.rotate(direction);
    }

    public net.pitan76.mcpitanlib.midohra.util.math.Direction rotate(net.pitan76.mcpitanlib.midohra.util.math.Direction direction) {
        return net.pitan76.mcpitanlib.midohra.util.math.Direction.of(rotation.rotate(direction.toMinecraft()));
    }
}

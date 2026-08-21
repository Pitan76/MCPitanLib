package net.pitan76.mcpitanlib.api.block.args;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.core.Direction;
import net.pitan76.mcpitanlib.api.util.math.CompatBlockRotation;
import net.pitan76.mcpitanlib.midohra.holder.BlockStatePropertyHolder;

public class RotateArgs implements BlockStatePropertyHolder {

    public BlockState state;
    public Rotation rotation;

    public RotateArgs(BlockState state, Rotation rotation) {
        this.state = state;
        this.rotation = rotation;
    }

    public BlockState getRawBlockState() {
        return state;
    }

    public Rotation getRawRotation() {
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

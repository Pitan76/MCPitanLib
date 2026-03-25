package net.pitan76.mcpitanlib.api.block.args;

import net.minecraft.block.BlockState;
import net.pitan76.mcpitanlib.midohra.holder.BlockStatePropertyHolder;

public class RenderTypeArgs implements BlockStatePropertyHolder {
    public BlockState state;

    public RenderTypeArgs(BlockState state) {
        this.state = state;
    }

    public BlockState getRawBlockState() {
        return state;
    }

    @Override
    public net.pitan76.mcpitanlib.midohra.block.BlockState getBlockState() {
        return net.pitan76.mcpitanlib.midohra.block.BlockState.of(getRawBlockState());
    }
}

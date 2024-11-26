package net.pitan76.mcpitanlib.api.block.v2;

import net.pitan76.mcpitanlib.midohra.block.BlockState;

public class CompatStairsBlock extends net.pitan76.mcpitanlib.api.block.CompatStairsBlock {
    public CompatStairsBlock(net.minecraft.block.BlockState baseBlockState, CompatibleBlockSettings settings) {
        super(baseBlockState, settings);
    }

    public CompatStairsBlock(BlockState baseBlockState, CompatibleBlockSettings settings) {
        this(baseBlockState.toMinecraft(), settings);
    }
}

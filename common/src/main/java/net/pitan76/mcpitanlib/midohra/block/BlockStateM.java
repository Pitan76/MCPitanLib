package net.pitan76.mcpitanlib.midohra.block;

import net.minecraft.block.Block;
import net.pitan76.mcpitanlib.api.util.BlockStateUtil;

public class BlockStateM extends BlockState {

    public BlockStateM(net.minecraft.block.BlockState state) {
        super(state);
    }

    public BlockStateM(Block block) {
        this(BlockStateUtil.getDefaultState(block));
    }

    public BlockStateM(BlockWrapper wrapper) {
        this(wrapper.get());
    }

    public static BlockStateM of(net.minecraft.block.BlockState state) {
        return new BlockStateM(state);
    }

    public static BlockStateM of(Block block) {
        if (block == null)
            return of((net.minecraft.block.BlockState) null);

        return of(BlockStateUtil.getDefaultState(block));
    }
}

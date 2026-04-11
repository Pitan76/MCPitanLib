package net.pitan76.mcpitanlib.midohra.block;

import net.minecraft.world.level.block.Block;
import net.pitan76.mcpitanlib.api.util.BlockStateUtil;

public class BlockStateM extends BlockState {

    public BlockStateM(net.minecraft.world.level.block.state.BlockState state) {
        super(state);
    }

    public BlockStateM(Block block) {
        this(BlockStateUtil.getDefaultState(block));
    }

    public BlockStateM(BlockWrapper wrapper) {
        this(wrapper.get());
    }

    public static BlockStateM of(net.minecraft.world.level.block.state.BlockState state) {
        return new BlockStateM(state);
    }

    public static BlockStateM of(Block block) {
        if (block == null)
            return of((net.minecraft.world.level.block.state.BlockState) null);

        return of(BlockStateUtil.getDefaultState(block));
    }
}

package net.pitan76.mcpitanlib.midohra.block;

import net.pitan76.mcpitanlib.api.sound.CompatBlockSoundGroup;
import net.pitan76.mcpitanlib.api.util.BlockStateUtil;

public class BlockState {
    private final net.minecraft.block.BlockState state;

    private BlockWrapper blockWrapper;

    protected BlockState(net.minecraft.block.BlockState state) {
        this.state = state;
    }

    public static BlockState of(net.minecraft.block.BlockState state) {
        return new BlockState(state);
    }

    public static BlockState of(net.minecraft.block.Block block) {
        if (block == null)
            return of((net.minecraft.block.BlockState) null);

        return of(BlockStateUtil.getDefaultState(block));
    }

    public boolean isExist() {
        return !isEmpty();
    }

    public boolean isEmpty() {
        return state == null;
    }

    public boolean isAir() {
        return isEmpty() || BlockStateUtil.isAir(state);
    }

    public CompatBlockSoundGroup getSoundGroup() {
        return BlockStateUtil.getCompatSoundGroup(state);
    }

    public BlockWrapper getBlock() {
        if (blockWrapper == null)
            blockWrapper = BlockWrapper.of(BlockStateUtil.getBlock(state));

        return blockWrapper;
    }

    public String getName() {
        return getBlock().getName();
    }

    @Deprecated
    public net.minecraft.block.BlockState toMinecraft() {
        return state;
    }
}

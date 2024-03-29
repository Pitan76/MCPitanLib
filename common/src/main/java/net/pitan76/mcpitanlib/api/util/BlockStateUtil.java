package net.pitan76.mcpitanlib.api.util;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.sound.BlockSoundGroup;

public class BlockStateUtil {
    public static Block getBlock(BlockState state) {
        return state.getBlock();
    }

    public static boolean isAir(BlockState state) {
        return state.isAir();
    }

    public static boolean isOpaque(BlockState state) {
        return state.isOpaque();
    }

    public static BlockSoundGroup getSoundGroup(BlockState state) {
        return state.getSoundGroup();
    }
}

package net.pitan76.mcpitanlib.api.client.color;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.client.color.block.BlockColor;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockAndTintGetter;
import org.jetbrains.annotations.Nullable;

public interface CompatBlockColorProvider extends BlockColor {
    default int getColor(BlockState state, @Nullable BlockAndTintGetter world, @Nullable BlockPos pos, int tintIndex) {
        return getColor(new BlockColorEvent(state, world, pos, tintIndex));
    }

    int getColor(BlockColorEvent e);
}

package net.pitan76.mcpitanlib.api.client.color;

import net.minecraft.block.BlockState;
import net.minecraft.client.color.block.BlockColorProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockRenderView;
import org.jetbrains.annotations.Nullable;

public interface CompatBlockColorProvider extends BlockColorProvider {
    default int getColor(BlockState state, @Nullable BlockRenderView world, @Nullable BlockPos pos, int tintIndex) {
        return getColor(new BlockColorEvent(state, world, pos, tintIndex));
    }

    int getColor(BlockColorEvent e);
}

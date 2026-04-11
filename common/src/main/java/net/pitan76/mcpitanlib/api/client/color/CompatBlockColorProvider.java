package net.pitan76.mcpitanlib.api.client.color;

import net.minecraft.client.color.block.BlockTintSource;
import net.minecraft.client.renderer.block.BlockAndTintGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.core.BlockPos;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public interface CompatBlockColorProvider {
    default int getColor(BlockState state, @Nullable BlockAndTintGetter world, @Nullable BlockPos pos, int tintIndex) {
        return getColor(new BlockColorEvent(state, world, pos, tintIndex));
    }

    int getColor(BlockColorEvent e);

    class BuiltBlockTintSource implements BlockTintSource {
        private final CompatBlockColorProvider provider;
        private final int tintIndex;

        public BuiltBlockTintSource(CompatBlockColorProvider provider, int tintIndex) {
            this.provider = provider;
            this.tintIndex = tintIndex;
        }

        @Override
        public int color(BlockState state) {
            return provider.getColor(new BlockColorEvent(state, null, null, tintIndex));
        }

        @Override
        public int colorInWorld(BlockState state, BlockAndTintGetter level, BlockPos pos) {
            return provider.getColor(new BlockColorEvent(state, level, pos, tintIndex));
        }


        @Override
        public int colorAsTerrainParticle(BlockState state, BlockAndTintGetter level, BlockPos pos) {
            return provider.getColor(new BlockColorEvent(state, level, pos, tintIndex));
        }
    }

    default List<BlockTintSource> toTintSource() {
        List<BlockTintSource> tintSources = new ArrayList<>();

        // TODO: 16は適当な数。実際にはtintIndexの最大値に合わせて増やす必要があるかもしれない
        for (int i = 0; i < 16; i++) {
            tintSources.add(new BuiltBlockTintSource(this, i));
        }
        return tintSources;
    }
}

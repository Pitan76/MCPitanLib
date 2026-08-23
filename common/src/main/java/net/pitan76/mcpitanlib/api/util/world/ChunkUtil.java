package net.pitan76.mcpitanlib.api.util.world;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraft.world.level.chunk.LevelChunkSection;

/**
 * チャンク・チャンクセクション用。バージョン差はここで吸収する。
 */
public class ChunkUtil {
    public static LevelChunk getChunk(Level world, BlockPos pos) {
        return world.getChunkAt(pos);
    }

    public static LevelChunk getChunk(Level world, int chunkX, int chunkZ) {
        return world.getChunk(chunkX, chunkZ);
    }

    // 1.17未満は常に0
    public static int getBottomY(ChunkAccess chunk) {
        return chunk.getMinY();
    }

    // 1.17未満は常に16
    public static int getSectionsCount(ChunkAccess chunk) {
        return chunk.getSections().length;
    }

    // 1.17未満は y >> 4
    public static int getSectionIndex(ChunkAccess chunk, int y) {
        return chunk.getSectionIndex(y);
    }

    public static int getSectionBottomY(ChunkAccess chunk, int index) {
        return getBottomY(chunk) + (index << 4);
    }

    // 範囲外ならnull
    public static LevelChunkSection getSectionByIndex(ChunkAccess chunk, int index) {
        LevelChunkSection[] sections = chunk.getSections();
        if (index < 0 || index >= sections.length) return null;

        return sections[index];
    }

    public static LevelChunkSection getSectionByY(ChunkAccess chunk, int y) {
        return getSectionByIndex(chunk, getSectionIndex(chunk, y));
    }

    public static LevelChunkSection[] getSections(ChunkAccess chunk) {
        return chunk.getSections();
    }

    /**
     * セクションが空 (全てair) かどうか。1.17未満はnullが入り得るので、nullも空として扱う。
     */
    public static boolean isEmpty(LevelChunkSection section) {
        return section == null || section.hasOnlyAir();
    }

    // ローカル座標(0～15)。sectionがnullならair
    public static net.minecraft.world.level.block.state.BlockState getBlockState(LevelChunkSection section, int localX, int localY, int localZ) {
        if (section == null) return net.minecraft.world.level.block.Blocks.AIR.defaultBlockState();

        return section.getBlockState(localX, localY, localZ);
    }
}

package net.pitan76.mcpitanlib.api.util.world;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkSection;
import net.minecraft.world.chunk.WorldChunk;

/**
 * チャンク・チャンクセクション用。バージョン差はここで吸収する。
 */
public class ChunkUtil {
    public static WorldChunk getChunk(World world, BlockPos pos) {
        return world.getWorldChunk(pos);
    }

    public static WorldChunk getChunk(World world, int chunkX, int chunkZ) {
        return world.getChunk(chunkX, chunkZ);
    }

    // 1.17未満は常に0
    public static int getBottomY(Chunk chunk) {
        return 0;
    }

    // 1.17未満は常に16
    public static int getSectionsCount(Chunk chunk) {
        return chunk.getSectionArray().length;
    }

    // 1.17未満は y >> 4
    public static int getSectionIndex(Chunk chunk, int y) {
        return y >> 4;
    }

    public static int getSectionBottomY(Chunk chunk, int index) {
        return getBottomY(chunk) + (index << 4);
    }

    // 範囲外ならnull
    public static ChunkSection getSectionByIndex(Chunk chunk, int index) {
        ChunkSection[] sections = chunk.getSectionArray();
        if (index < 0 || index >= sections.length) return null;

        return sections[index];
    }

    public static ChunkSection getSectionByY(Chunk chunk, int y) {
        return getSectionByIndex(chunk, getSectionIndex(chunk, y));
    }

    public static ChunkSection[] getSections(Chunk chunk) {
        return chunk.getSectionArray();
    }

    /**
     * セクションが空 (全てair) かどうか。1.17未満はnullが入り得るので、nullも空として扱う。
     */
    public static boolean isEmpty(ChunkSection section) {
        return section == null || section.isEmpty();
    }

    // ローカル座標(0～15)。sectionがnullならair
    public static net.minecraft.block.BlockState getBlockState(ChunkSection section, int localX, int localY, int localZ) {
        if (section == null) return net.minecraft.block.Blocks.AIR.getDefaultState();

        return section.getBlockState(localX, localY, localZ);
    }
}

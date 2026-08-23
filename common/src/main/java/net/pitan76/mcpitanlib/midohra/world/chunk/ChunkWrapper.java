package net.pitan76.mcpitanlib.midohra.world.chunk;

import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.LevelChunkSection;
import net.pitan76.mcpitanlib.api.util.world.ChunkUtil;
import net.pitan76.mcpitanlib.midohra.util.math.BlockPos;
import net.pitan76.mcpitanlib.midohra.world.World;

import java.util.ArrayList;
import java.util.List;

public class ChunkWrapper {
    private final ChunkAccess chunk;

    protected ChunkWrapper(ChunkAccess chunk) {
        this.chunk = chunk;
    }

    public static ChunkWrapper of(ChunkAccess chunk) {
        return new ChunkWrapper(chunk);
    }

    public static ChunkWrapper of(World world, BlockPos pos) {
        return of(ChunkUtil.getChunk(world.getRaw(), pos.toMinecraft()));
    }

    public static ChunkWrapper of(World world, int chunkX, int chunkZ) {
        return of(ChunkUtil.getChunk(world.getRaw(), chunkX, chunkZ));
    }

    public ChunkAccess getRaw() {
        return chunk;
    }

    public ChunkAccess toMinecraft() {
        return getRaw();
    }

    public boolean isNull() {
        return chunk == null;
    }

    public int getBottomY() {
        return ChunkUtil.getBottomY(chunk);
    }

    public int getSectionsCount() {
        return ChunkUtil.getSectionsCount(chunk);
    }

    public int getSectionIndex(int y) {
        return ChunkUtil.getSectionIndex(chunk, y);
    }

    // 範囲外でもnullではなく、isEmpty()がtrueになるラッパーを返す
    public ChunkSectionWrapper getSectionByIndex(int index) {
        LevelChunkSection section = ChunkUtil.getSectionByIndex(chunk, index);
        return ChunkSectionWrapper.of(section, ChunkUtil.getSectionBottomY(chunk, index));
    }

    public ChunkSectionWrapper getSectionByY(int y) {
        return getSectionByIndex(getSectionIndex(y));
    }

    public ChunkSectionWrapper getSectionAt(BlockPos pos) {
        return getSectionByY(pos.getY());
    }

    public List<ChunkSectionWrapper> getSections() {
        List<ChunkSectionWrapper> sections = new ArrayList<>();
        for (int i = 0; i < getSectionsCount(); i++) {
            sections.add(getSectionByIndex(i));
        }
        return sections;
    }
}

package net.pitan76.mcpitanlib.midohra.world.chunk;

import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkSection;
import net.pitan76.mcpitanlib.api.util.world.ChunkUtil;
import net.pitan76.mcpitanlib.midohra.util.math.BlockPos;
import net.pitan76.mcpitanlib.midohra.world.World;

import java.util.ArrayList;
import java.util.List;

public class ChunkWrapper {
    private final Chunk chunk;

    protected ChunkWrapper(Chunk chunk) {
        this.chunk = chunk;
    }

    public static ChunkWrapper of(Chunk chunk) {
        return new ChunkWrapper(chunk);
    }

    public static ChunkWrapper of(World world, BlockPos pos) {
        return of(ChunkUtil.getChunk(world.getRaw(), pos.toMinecraft()));
    }

    public static ChunkWrapper of(World world, int chunkX, int chunkZ) {
        return of(ChunkUtil.getChunk(world.getRaw(), chunkX, chunkZ));
    }

    public Chunk getRaw() {
        return chunk;
    }

    public Chunk toMinecraft() {
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
        ChunkSection section = ChunkUtil.getSectionByIndex(chunk, index);
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

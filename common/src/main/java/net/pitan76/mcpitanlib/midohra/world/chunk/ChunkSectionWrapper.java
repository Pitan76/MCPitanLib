package net.pitan76.mcpitanlib.midohra.world.chunk;

import net.minecraft.world.level.chunk.LevelChunkSection;
import net.pitan76.mcpitanlib.api.util.world.ChunkUtil;
import net.pitan76.mcpitanlib.midohra.block.BlockState;

/**
 * チャンクセクション(16x16x16)のラッパー。isEmpty()がtrueなら16x16x16を丸ごとスキップできる。
 */
public class ChunkSectionWrapper {
    private final LevelChunkSection section;
    private final int bottomY;

    protected ChunkSectionWrapper(LevelChunkSection section, int bottomY) {
        this.section = section;
        this.bottomY = bottomY;
    }

    public static ChunkSectionWrapper of(LevelChunkSection section, int bottomY) {
        return new ChunkSectionWrapper(section, bottomY);
    }

    public LevelChunkSection getRaw() {
        return section;
    }

    public LevelChunkSection toMinecraft() {
        return getRaw();
    }

    // 1.17未満ではセクション配列にnullが入り得る
    public boolean isNull() {
        return section == null;
    }

    public boolean isEmpty() {
        return ChunkUtil.isEmpty(section);
    }

    public int getBottomY() {
        return bottomY;
    }

    public int getTopY() {
        return bottomY + 15;
    }

    // ローカル座標(0～15)
    public BlockState getBlockState(int localX, int localY, int localZ) {
        return BlockState.of(ChunkUtil.getBlockState(section, localX, localY, localZ));
    }

    // ワールド座標。Yがこのセクションの範囲内であることは呼び出し側の責任
    public BlockState getBlockStateAt(int x, int y, int z) {
        return getBlockState(x & 15, y & 15, z & 15);
    }

    public BlockState getBlockStateAt(net.pitan76.mcpitanlib.midohra.util.math.BlockPos pos) {
        return getBlockStateAt(pos.getX(), pos.getY(), pos.getZ());
    }
}

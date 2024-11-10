package net.pitan76.mcpitanlib.core.serialization.codecs;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.pitan76.mcpitanlib.api.block.CompatChestBlock;
import net.pitan76.mcpitanlib.api.block.CompatPillarBlock;
import net.pitan76.mcpitanlib.api.block.CompatSlabBlock;
import net.pitan76.mcpitanlib.api.block.CompatStairsBlock;
import net.pitan76.mcpitanlib.api.block.v2.CompatBlock;
import net.pitan76.mcpitanlib.api.block.v2.CompatibleBlockSettings;
import net.pitan76.mcpitanlib.core.serialization.CompatMapCodec;

import java.util.function.BiFunction;
import java.util.function.Function;

public class CompatBlockMapCodecUtil {

    @Deprecated
    protected static <B extends Block> CompatMapCodec<B> of(MapCodec<B> codec) {
        return new CompatMapCodec<>(codec);
    }

    public static <B extends CompatBlock> RecordCodecBuilder<B, CompatibleBlockSettings> createSettingsCodec() {
        return CompatibleBlockSettings.CODEC.fieldOf("properties").forGetter(CompatBlock::getCompatSettings);
    }

    public static <B extends CompatBlock> CompatMapCodec<B> createCodec(Function<CompatibleBlockSettings, B> blockFromSettings) {
        return of(RecordCodecBuilder.mapCodec((instance) -> instance.group(createSettingsCodec()).apply(instance, blockFromSettings)));
    }

    public static <B extends CompatStairsBlock> CompatMapCodec<B> createCodecOfStairsBlock(BiFunction<BlockState, CompatibleBlockSettings, B> createFunction) {
        return of(RecordCodecBuilder.mapCodec((instance) -> instance.group(
                BlockState.CODEC.fieldOf("base_state").forGetter(CompatStairsBlock::getBaseBlockState),
                CompatibleBlockSettings.CODEC.fieldOf("properties").forGetter(CompatStairsBlock::getCompatSettings)
        ).apply(instance, createFunction)));
    }

    public static <B extends CompatSlabBlock> CompatMapCodec<B> createCodecOfSlabBlock(Function<CompatibleBlockSettings, B> createFunction) {
        return of(RecordCodecBuilder.mapCodec((instance) -> instance.group(
                CompatibleBlockSettings.CODEC.fieldOf("properties").forGetter(CompatSlabBlock::getCompatSettings)
        ).apply(instance, createFunction)));
    }

    public static <B extends CompatPillarBlock> CompatMapCodec<B> createCodecOfPillarBlock(Function<CompatibleBlockSettings, B> createFunction) {
        return of(RecordCodecBuilder.mapCodec((instance) -> instance.group(
                CompatibleBlockSettings.CODEC.fieldOf("properties").forGetter(CompatPillarBlock::getCompatSettings)
        ).apply(instance, createFunction)));
    }

    public static <B extends CompatChestBlock> CompatMapCodec<B> createCodecOfChestBlock(Function<CompatibleBlockSettings, B> createFunction) {
        return of(RecordCodecBuilder.mapCodec((instance) -> instance.group(
                CompatibleBlockSettings.CODEC.fieldOf("properties").forGetter(CompatChestBlock::getCompatSettings)
        ).apply(instance, createFunction)));
    }
}

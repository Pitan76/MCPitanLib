package net.pitan76.mcpitanlib.core.serialization;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.pitan76.mcpitanlib.api.block.CompatStairsBlock;
import net.pitan76.mcpitanlib.api.block.CompatibleBlockSettings;
import net.pitan76.mcpitanlib.api.block.ExtendBlock;

import java.util.function.BiFunction;
import java.util.function.Function;

public class CompatMapCodec<T> {
    @Deprecated
    public MapCodec<T> codec;

    @Deprecated
    public CompatMapCodec(MapCodec<T> codec) {
        this.codec = codec;
    }

    @Deprecated
    public static <B extends Block> CompatMapCodec<B> of(MapCodec<B> codec) {
        return new CompatMapCodec<>(codec);
    }

    public CompatMapCodec() {

    }

    public static  <B extends Block> CompatMapCodec<B> of() {
        return new CompatMapCodec<>();
    }

    @Deprecated
    public MapCodec<T> getCodec() {
        return codec;
    }

    public static <B extends Block> RecordCodecBuilder<B, AbstractBlock.Settings> createSettingsCodec() {
        return AbstractBlock.Settings.CODEC.fieldOf("properties").forGetter(AbstractBlock::getSettings);
    }

    public static <B extends Block> CompatMapCodec<B> createCodec(Function<AbstractBlock.Settings, B> blockFromSettings) {
        return of(RecordCodecBuilder.mapCodec((instance) -> instance.group(createSettingsCodec()).apply(instance, blockFromSettings)));
    }

    public static <B extends ExtendBlock> RecordCodecBuilder<B, CompatibleBlockSettings> createCompatSettingsCodec() {
        return CompatibleBlockSettings.CODEC.fieldOf("properties").forGetter(ExtendBlock::getCompatSettings);
    }

    public static <B extends ExtendBlock> CompatMapCodec<B> createCodecOfExtendBlock(Function<CompatibleBlockSettings, B> blockFromSettings) {
        return of(RecordCodecBuilder.mapCodec((instance) -> instance.group(createCompatSettingsCodec()).apply(instance, blockFromSettings)));
    }

    public static <B extends CompatStairsBlock> CompatMapCodec<B> createCodecOfCompatStairsBlock(BiFunction<BlockState, CompatibleBlockSettings, B> createFunction) {
        return of(RecordCodecBuilder.mapCodec((instance) -> instance.group(
                BlockState.CODEC.fieldOf("base_state").forGetter(CompatStairsBlock::getBaseBlockState),
                CompatibleBlockSettings.CODEC.fieldOf("properties").forGetter(CompatStairsBlock::getCompatSettings)
        ).apply(instance, createFunction)));
    }
}

package net.pitan76.mcpitanlib.core.serialization;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.pitan76.mcpitanlib.api.block.CompatStairsBlock;
import net.pitan76.mcpitanlib.api.block.v2.CompatBlock;
import net.pitan76.mcpitanlib.api.block.v2.CompatibleBlockSettings;
import net.pitan76.mcpitanlib.api.block.ExtendBlock;
import net.pitan76.mcpitanlib.core.serialization.codecs.CompatBlockMapCodecUtil;

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

    @Deprecated
    public static <B extends ExtendBlock> RecordCodecBuilder<B, net.pitan76.mcpitanlib.api.block.CompatibleBlockSettings> createCompatSettingsCodec() {
        return net.pitan76.mcpitanlib.api.block.CompatibleBlockSettings.CODEC.fieldOf("properties").forGetter(ExtendBlock::getCompatSettings);
    }

    @Deprecated
    public static <B extends ExtendBlock> CompatMapCodec<B> createCodecOfExtendBlock(Function<net.pitan76.mcpitanlib.api.block.CompatibleBlockSettings, B> blockFromSettings) {
        return of(RecordCodecBuilder.mapCodec((instance) -> instance.group(createCompatSettingsCodec()).apply(instance, blockFromSettings)));
    }

    public static <B extends CompatBlock> RecordCodecBuilder<B, CompatibleBlockSettings> createCompatSettingsV2Codec() {
        return CompatBlockMapCodecUtil.createSettingsCodec();
    }

    public static <B extends CompatBlock> CompatMapCodec<B> createCodecOfCompatBlock(Function<CompatibleBlockSettings, B> blockFromSettings) {
        return CompatBlockMapCodecUtil.createCodec(blockFromSettings);
    }

    public static <B extends CompatStairsBlock> CompatMapCodec<B> createCodecOfCompatStairsBlock(BiFunction<BlockState, net.pitan76.mcpitanlib.api.block.CompatibleBlockSettings, B> createFunction) {
        return of(RecordCodecBuilder.mapCodec((instance) -> instance.group(
                BlockState.CODEC.fieldOf("base_state").forGetter(CompatStairsBlock::getBaseBlockState),
                net.pitan76.mcpitanlib.api.block.CompatibleBlockSettings.CODEC.fieldOf("properties").forGetter(CompatStairsBlock::getCompatSettings)
        ).apply(instance, createFunction)));
    }
}

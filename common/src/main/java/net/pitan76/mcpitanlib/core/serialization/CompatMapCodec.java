package net.pitan76.mcpitanlib.core.serialization;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.pitan76.mcpitanlib.api.block.CompatibleBlockSettings;
import net.pitan76.mcpitanlib.api.block.ExtendBlock;

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

    public static <B extends Block> CompatMapCodec<B> createCodec(Function<AbstractBlock.Settings, B> blockFromSettings) {
        return of();
    }

    public static <B extends ExtendBlock> CompatMapCodec<B> createCodecOfExtendBlock(Function<CompatibleBlockSettings, B> blockFromSettings) {
        return of();
    }
}

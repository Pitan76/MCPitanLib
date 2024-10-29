package net.pitan76.mcpitanlib.api.block.v2;

import net.pitan76.mcpitanlib.api.block.CompatibleMaterial;
import net.pitan76.mcpitanlib.api.sound.CompatBlockSoundGroup;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;
import net.pitan76.mcpitanlib.api.util.color.CompatDyeColor;
import net.pitan76.mcpitanlib.api.util.color.CompatMapColor;
import net.pitan76.mcpitanlib.midohra.block.BlockState;

import java.util.function.ToIntFunction;

public class BlockSettingsBuilder {

    public CompatIdentifier id;
    public float hardness;
    public float resistance;
    public CompatBlockSoundGroup blockSoundGroup;
    public CompatibleMaterial material;
    public CompatMapColor mapColor;
    public CompatDyeColor dyeColor;
    public boolean requiresTool;
    public boolean dropsNothing;
    public ToIntFunction<BlockState> luminance;

    public BlockSettingsBuilder(CompatIdentifier id) {

    }

    public BlockSettingsBuilder hardness(float hardness) {
        this.hardness = hardness;
        return this;
    }

    public BlockSettingsBuilder resistance(float resistance) {
        this.resistance = resistance;
        return this;
    }

    public BlockSettingsBuilder strength(float hardness, float resistance) {
        this.hardness = hardness;
        this.resistance = resistance;
        return this;
    }

    public BlockSettingsBuilder sounds(CompatBlockSoundGroup blockSoundGroup) {
        this.blockSoundGroup = blockSoundGroup;
        return this;
    }

    public BlockSettingsBuilder material(CompatibleMaterial material) {
        this.material = material;
        return this;
    }

    public BlockSettingsBuilder mapColor(CompatMapColor mapColor) {
        this.mapColor = mapColor;
        return this;
    }

    public BlockSettingsBuilder dyeColor(CompatDyeColor dyeColor) {
        this.dyeColor = dyeColor;
        return this;
    }

    public BlockSettingsBuilder requiresTool() {
        this.requiresTool = true;
        return this;
    }

    public BlockSettingsBuilder dropsNothing() {
        this.dropsNothing = true;
        return this;
    }

    public BlockSettingsBuilder luminance(ToIntFunction<BlockState> luminance) {
        this.luminance = luminance;
        return this;
    }

    public CompatibleBlockSettings build() {
        CompatibleBlockSettings settings = CompatibleBlockSettings.of(id);
        if (material != null)
            settings = CompatibleBlockSettings.of(id, material);

        if (mapColor != null) {
            settings = settings.mapColor(mapColor.get());
        } else if (dyeColor != null) {
            settings = settings.mapColor(dyeColor.get().getMapColor());
        }

        if (requiresTool) settings.requiresTool();
        if (dropsNothing) settings.dropsNothing();
        if (luminance != null) settings.luminance((state) -> luminance.applyAsInt(BlockState.of(state)));

        return settings.strength(hardness, resistance).sounds(blockSoundGroup);
    }
}

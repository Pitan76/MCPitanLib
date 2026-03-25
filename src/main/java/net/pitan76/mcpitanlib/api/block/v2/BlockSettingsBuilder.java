package net.pitan76.mcpitanlib.api.block.v2;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.pitan76.mcpitanlib.api.block.CompatibleMaterial;
import net.pitan76.mcpitanlib.api.sound.CompatBlockSoundGroup;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;
import net.pitan76.mcpitanlib.api.util.color.CompatDyeColor;
import net.pitan76.mcpitanlib.api.util.color.CompatMapColor;
import net.pitan76.mcpitanlib.midohra.block.BlockState;
import net.pitan76.mcpitanlib.midohra.block.BlockWrapper;

import java.util.function.ToIntFunction;

public class BlockSettingsBuilder {

    public CompatIdentifier id;
    public float hardness = -1;
    public float resistance = -1;
    public CompatBlockSoundGroup blockSoundGroup;
    public CompatibleMaterial material;
    public CompatMapColor mapColor;
    public CompatDyeColor dyeColor;
    public boolean requiresTool;
    public boolean dropsNothing;
    public ToIntFunction<BlockState> luminance;

    protected BlockWrapper copyFromBlock = null;

    public BlockSettingsBuilder(CompatIdentifier id) {
        this.id = id;
    }

    public BlockSettingsBuilder() {

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
        return build(id);
    }

    public BlockBehaviour.Properties _build() {
        return build().build();
    }

    public CompatibleBlockSettings build(CompatIdentifier id) {
        CompatibleBlockSettings settings;

        if (copyFromBlock != null) {
            settings = CompatibleBlockSettings.copy(id, copyFromBlock.get());
        } else {
            settings = CompatibleBlockSettings.of(id);
        }

        if (material != null)
            settings = CompatibleBlockSettings.of(id, material);

        if (mapColor != null) {
            settings = settings.mapColor(mapColor.getColor());
        } else if (dyeColor != null) {
            settings = settings.mapColor(dyeColor.getColor().getMapColor());
        }

        if (requiresTool) settings.requiresTool();
        if (dropsNothing) settings.dropsNothing();
        if (luminance != null) settings.luminance((state) -> luminance.applyAsInt(BlockState.of(state)));

        if (hardness != -1 && resistance != -1) settings.strength(hardness, resistance);
        else if (hardness != -1) settings.strength(hardness);

        if (blockSoundGroup != null) settings.sounds(blockSoundGroup);

        return settings;
    }

    public BlockBehaviour.Properties _build(CompatIdentifier id) {
        return build(id).build();
    }

    public BlockSettingsBuilder copy(CompatIdentifier id) {
        BlockSettingsBuilder builder = new BlockSettingsBuilder();

        builder.copyFromBlock = this.copyFromBlock;

        builder.id = id;
        builder.hardness = this.hardness;
        builder.resistance = this.resistance;
        builder.blockSoundGroup = this.blockSoundGroup;
        builder.material = this.material;
        builder.mapColor = this.mapColor;
        builder.dyeColor = this.dyeColor;
        builder.requiresTool = this.requiresTool;
        builder.dropsNothing = this.dropsNothing;
        builder.luminance = this.luminance;
        return builder;
    }

    public BlockSettingsBuilder copy() {
        return copy(this.id);
    }

    public static BlockSettingsBuilder of(CompatIdentifier id) {
        return new BlockSettingsBuilder(id);
    }

    public static BlockSettingsBuilder of() {
        return new BlockSettingsBuilder();
    }

    public static BlockSettingsBuilder copyBlock(BlockWrapper block) {
        BlockSettingsBuilder builder = new BlockSettingsBuilder(block.getId());
        builder.copyFromBlock = block;

        return builder;
    }

    public static BlockSettingsBuilder copyBlock(CompatIdentifier id) {
        return copyBlock(BlockWrapper.of(id));
    }
}

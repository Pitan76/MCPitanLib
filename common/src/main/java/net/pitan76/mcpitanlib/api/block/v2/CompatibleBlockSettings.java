package net.pitan76.mcpitanlib.api.block.v2;

import com.mojang.serialization.Codec;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.MapColor;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.DyeColor;
import net.pitan76.mcpitanlib.api.block.CompatibleMaterial;
import net.pitan76.mcpitanlib.api.sound.CompatBlockSoundGroup;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;

import java.util.function.Function;
import java.util.function.ToIntFunction;

@SuppressWarnings("deprecation")
public class CompatibleBlockSettings extends net.pitan76.mcpitanlib.api.block.CompatibleBlockSettings {
    protected CompatIdentifier identifier = null;
    public boolean changedTranslationKey = false;

    public static final Codec<CompatibleBlockSettings> CODEC = Codec.unit(CompatibleBlockSettings::new);

    @Deprecated
    protected CompatibleBlockSettings() {
        super();
    }

    public CompatibleBlockSettings(CompatIdentifier identifier) {
        super();
        setId(identifier);
    }

    public static CompatibleBlockSettings of(CompatIdentifier id) {
        return new CompatibleBlockSettings(id);
    }

    @Deprecated
    public CompatibleBlockSettings setId(CompatIdentifier identifier) {
        this.identifier = identifier;
        return this;
    }

    private static CompatibleBlockSettings copy(CompatibleMaterial material, CompatibleBlockSettings settings) {
        settings.mapColor(material.getColor());
        if (material.isLiquid())
            settings.settings.liquid();
        if (material.isSolid())
            settings.settings.solid();
        if (material.isReplaceable())
            settings.settings.replaceable();
        if (material.isSolid())
            settings.settings.solid();
        if (material.isBurnable())
            settings.settings.burnable();
        settings.settings.pistonBehavior(material.getPistonBehavior());
        return settings;
    }

    public CompatibleBlockSettings(CompatIdentifier id, CompatibleMaterial material, MapColor mapColor) {
        this(id);
        copy(material, this);
        mapColor(mapColor);
    }

    public CompatibleBlockSettings(CompatIdentifier id, CompatibleMaterial material, DyeColor dyeColor) {
        this(id);
        copy(material, this);
        mapColor(dyeColor);
    }

    public CompatibleBlockSettings(CompatIdentifier id, CompatibleMaterial material) {
        this(id);
        copy(material, this);
    }

    public CompatibleBlockSettings(CompatIdentifier id, CompatibleMaterial material, Function<BlockState, MapColor> mapColor) {
        this(id);
        copy(material, this);
        mapColor(mapColor);
    }

    public static CompatibleBlockSettings of(CompatIdentifier id, CompatibleMaterial material, MapColor mapColor) {
        return new CompatibleBlockSettings(id, material, mapColor);
    }

    public static CompatibleBlockSettings of(CompatIdentifier id, CompatibleMaterial material, DyeColor dyeColor) {
        return new CompatibleBlockSettings(id, material, dyeColor);
    }

    public static CompatibleBlockSettings of(CompatIdentifier id, CompatibleMaterial material) {
        return new CompatibleBlockSettings(id, material);
    }

    public static CompatibleBlockSettings of(CompatIdentifier id, CompatibleMaterial material, Function<BlockState, MapColor> mapColor) {
        return new CompatibleBlockSettings(id, material, mapColor);
    }

    public CompatibleBlockSettings(CompatIdentifier id, AbstractBlock block) {
        super(block);
        setId(id);
    }

    public static CompatibleBlockSettings copy(CompatIdentifier id, AbstractBlock block) {
        return new CompatibleBlockSettings(id, block);
    }

    public CompatibleBlockSettings air() {
        super.air();
        return this;
    }

    public CompatibleBlockSettings blockVision(AbstractBlock.ContextPredicate predicate) {
        super.blockVision(predicate);
        return this;
    }

    public CompatibleBlockSettings postProcess(AbstractBlock.ContextPredicate predicate) {
        super.postProcess(predicate);
        return this;
    }

    public CompatibleBlockSettings solidBlock(AbstractBlock.ContextPredicate predicate) {
        super.solidBlock(predicate);
        return this;
    }

    public CompatibleBlockSettings suffocates(AbstractBlock.ContextPredicate predicate) {
        super.suffocates(predicate);
        return this;
    }

    public CompatibleBlockSettings mapColor(MapColor color) {
        super.mapColor(color);
        return this;
    }

    public CompatibleBlockSettings mapColor(DyeColor color) {
        super.mapColor(color);
        return this;
    }

    public CompatibleBlockSettings mapColor(Function<BlockState, MapColor> color) {
        super.mapColor(color);
        return this;
    }

    @Deprecated
    public CompatibleBlockSettings dropsLike(Block source) {
        super.dropsLike(source);
        return this;
    }

    public CompatibleBlockSettings breakInstantly() {
        super.breakInstantly();
        return this;
    }

    public CompatibleBlockSettings dropsNothing() {
        super.dropsNothing();
        return this;
    }

    public CompatibleBlockSettings dynamicBounds() {
        super.dynamicBounds();
        return this;
    }

    public CompatibleBlockSettings hardness(float hardness) {
        super.hardness(hardness);
        return this;
    }

    public CompatibleBlockSettings noBlockBreakParticles() {
        super.noBlockBreakParticles();
        return this;
    }

    public CompatibleBlockSettings requiresTool() {
        super.requiresTool();
        return this;
    }

    public CompatibleBlockSettings noCollision() {
        super.noCollision();
        return this;
    }

    public CompatibleBlockSettings nonOpaque() {
        super.nonOpaque();
        return this;
    }

    public CompatibleBlockSettings resistance(float resistance) {
        super.resistance(resistance);
        return this;
    }

    public CompatibleBlockSettings strength(float strength) {
        super.strength(strength);
        return this;
    }

    public CompatibleBlockSettings strength(float hardness, float resistance) {
        super.strength(hardness, resistance);
        return this;
    }

    public CompatibleBlockSettings ticksRandomly() {
        super.ticksRandomly();
        return this;
    }

    public CompatibleBlockSettings sounds(CompatBlockSoundGroup blockSoundGroup) {
        super.sounds(blockSoundGroup);
        return this;
    }

    public CompatibleBlockSettings luminance(ToIntFunction<BlockState> luminance) {
        super.luminance(luminance);
        return this;
    }

    public CompatibleBlockSettings jumpVelocityMultiplier(float jumpVelocityMultiplier) {
        super.jumpVelocityMultiplier(jumpVelocityMultiplier);
        return this;
    }

    public CompatibleBlockSettings slipperiness(float slipperiness) {
        super.slipperiness(slipperiness);
        return this;
    }

    public CompatibleBlockSettings velocityMultiplier(float velocityMultiplier) {
        super.velocityMultiplier(velocityMultiplier);
        return this;
    }

    public CompatibleBlockSettings emissiveLighting(AbstractBlock.ContextPredicate predicate) {
        super.emissiveLighting(predicate);
        return this;
    }

    public CompatibleBlockSettings offset(AbstractBlock.OffsetType offsetType) {
        super.offset(offsetType);
        return this;
    }

    public CompatibleBlockSettings allowsSpawning(AbstractBlock.TypedContextPredicate<net.minecraft.entity.EntityType<?>> predicate) {
        super.allowsSpawning(predicate);
        return this;
    }

    public AbstractBlock.Settings build() {
        super.build();

        if (identifier != null) {
            settings.registryKey(RegistryKey.of(RegistryKeys.BLOCK, identifier.toMinecraft()));
        }

        return settings;
    }

    @Deprecated
    @Override
    public CompatibleBlockSettings sounds(BlockSoundGroup blockSoundGroup) {
        super.sounds(blockSoundGroup);
        return this;
    }


}

package net.pitan76.mcpitanlib.api.block;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.item.DyeColor;
import net.pitan76.mcpitanlib.api.sound.CompatBlockSoundGroup;

import java.util.function.Function;
import java.util.function.ToIntFunction;

@Deprecated
public class CompatibleBlockSettings {
    public static final Codec<CompatibleBlockSettings> CODEC = MapCodec.unitCodec(CompatibleBlockSettings::of);

    protected final BlockBehaviour.Properties settings;

    public CompatibleBlockSettings() {
        this.settings = BlockBehaviour.Properties.of();
    }

    @Deprecated
    public static CompatibleBlockSettings of() {
        return new CompatibleBlockSettings();
    }

    @Deprecated
    private static CompatibleBlockSettings copyCompatibleMaterial(CompatibleMaterial material, CompatibleBlockSettings settings) {
        settings.mapColor(material.getColor());
        if (material.isLiquid())
            settings.settings.liquid();
        if (material.isSolid())
            settings.settings.forceSolidOn();
        if (material.isReplaceable())
            settings.settings.replaceable();
        if (material.isSolid())
            settings.settings.forceSolidOn();
        if (material.isBurnable())
            settings.settings.ignitedByLava();
        settings.settings.pushReaction(material.getPistonBehavior());
        return settings;
    }

    public CompatibleBlockSettings(CompatibleMaterial material, MapColor mapColor) {
        this.settings = BlockBehaviour.Properties.of();
        copyCompatibleMaterial(material, this);
        mapColor(mapColor);
    }

    public CompatibleBlockSettings(CompatibleMaterial material, DyeColor dyeColor) {
        this.settings = BlockBehaviour.Properties.of();
        copyCompatibleMaterial(material, this);
        mapColor(dyeColor);
    }

    public CompatibleBlockSettings(CompatibleMaterial material) {
        this.settings = BlockBehaviour.Properties.of();
        copyCompatibleMaterial(material, this);
    }

    public CompatibleBlockSettings(CompatibleMaterial material, Function<BlockState, MapColor> mapColor) {
        this.settings = BlockBehaviour.Properties.of();
        copyCompatibleMaterial(material, this);
        mapColor(mapColor);
    }

    @Deprecated
    public static CompatibleBlockSettings of(CompatibleMaterial material, MapColor mapColor) {
        return new CompatibleBlockSettings(material, mapColor);
    }

    @Deprecated
    public static CompatibleBlockSettings of(CompatibleMaterial material, DyeColor dyeColor) {
        return new CompatibleBlockSettings(material, dyeColor);
    }

    @Deprecated
    public static CompatibleBlockSettings of(CompatibleMaterial material) {
        return new CompatibleBlockSettings(material);
    }

    @Deprecated
    public static CompatibleBlockSettings of(CompatibleMaterial material, Function<BlockState, MapColor> mapColor) {
        return new CompatibleBlockSettings(material, mapColor);
    }

    public CompatibleBlockSettings(BlockBehaviour block) {
        this.settings = BlockBehaviour.Properties.ofFullCopy(block);
    }

    @Deprecated
    public static CompatibleBlockSettings copy(BlockBehaviour block) {
        return new CompatibleBlockSettings(block);
    }

    public CompatibleBlockSettings air() {
        settings.air();
        return this;
    }

    public CompatibleBlockSettings blockVision(BlockBehaviour.StatePredicate predicate) {
        settings.isViewBlocking(predicate);
        return this;
    }

    public CompatibleBlockSettings postProcess(BlockBehaviour.PostProcess predicate) {
        settings.postProcess(predicate);
        return this;
    }

    public CompatibleBlockSettings solidBlock(BlockBehaviour.StatePredicate predicate) {
        settings.isRedstoneConductor(predicate);
        return this;
    }

    public CompatibleBlockSettings suffocates(BlockBehaviour.StatePredicate predicate) {
        settings.isSuffocating(predicate);
        return this;
    }

    public CompatibleBlockSettings mapColor(MapColor color) {
        settings.mapColor(color);
        return this;
    }

    public CompatibleBlockSettings mapColor(DyeColor color) {
        settings.mapColor(color);
        return this;
    }

    public CompatibleBlockSettings mapColor(Function<BlockState, MapColor> color) {
        settings.mapColor(color);
        return this;
    }

    @Deprecated
    public CompatibleBlockSettings dropsLike(Block source) {
        return this;
    }

    public CompatibleBlockSettings breakInstantly() {
        settings.instabreak();
        return this;
    }

    public CompatibleBlockSettings dropsNothing() {
        settings.noLootTable();
        return this;
    }

    public CompatibleBlockSettings dynamicBounds() {
        settings.dynamicShape();
        return this;
    }

    public CompatibleBlockSettings hardness(float hardness) {
        settings.destroyTime(hardness);
        return this;
    }

    public CompatibleBlockSettings noBlockBreakParticles() {
        settings.noTerrainParticles();
        return this;
    }

    public CompatibleBlockSettings requiresTool() {
        settings.requiresCorrectToolForDrops();
        return this;
    }

    public CompatibleBlockSettings noCollision() {
        settings.noCollision();
        return this;
    }

    public CompatibleBlockSettings nonOpaque() {
        settings.noOcclusion();
        return this;
    }

    public CompatibleBlockSettings resistance(float resistance) {
        settings.explosionResistance(resistance);
        return this;
    }

    public CompatibleBlockSettings strength(float strength) {
        settings.strength(strength);
        return this;
    }

    public CompatibleBlockSettings strength(float hardness, float resistance) {
        settings.strength(hardness, resistance);
        return this;
    }

    public CompatibleBlockSettings ticksRandomly() {
        settings.randomTicks();
        return this;
    }

    public CompatibleBlockSettings sounds(SoundType blockSoundGroup) {
        settings.sound(blockSoundGroup);
        return this;
    }

    public CompatibleBlockSettings sounds(CompatBlockSoundGroup blockSoundGroup) {
        return sounds(blockSoundGroup.get());
    }

    public CompatibleBlockSettings luminance(ToIntFunction<BlockState> luminance) {
        settings.lightLevel(luminance);
        return this;
    }

    public CompatibleBlockSettings jumpVelocityMultiplier(float jumpVelocityMultiplier) {
        settings.jumpFactor(jumpVelocityMultiplier);
        return this;
    }

    public CompatibleBlockSettings slipperiness(float slipperiness) {
        settings.friction(slipperiness);
        return this;
    }

    public CompatibleBlockSettings velocityMultiplier(float velocityMultiplier) {
        settings.speedFactor(velocityMultiplier);
        return this;
    }

    public CompatibleBlockSettings emissiveLighting(BlockBehaviour.StatePredicate predicate) {
        settings.emissiveRendering(predicate);
        return this;
    }

    public CompatibleBlockSettings offset(BlockBehaviour.OffsetType offsetType) {
        settings.offsetType(offsetType);
        return this;
    }

    public CompatibleBlockSettings allowsSpawning(BlockBehaviour.StateArgumentPredicate<net.minecraft.world.entity.EntityType<?>> predicate) {
        settings.isValidSpawn(predicate);
        return this;
    }

    public BlockBehaviour.Properties build() {
        return settings;

    }
}

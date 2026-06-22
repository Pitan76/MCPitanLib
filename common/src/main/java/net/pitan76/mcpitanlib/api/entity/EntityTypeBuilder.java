package net.pitan76.mcpitanlib.api.entity;

import com.google.common.collect.ImmutableSet;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.resources.ResourceKey;
import net.pitan76.mcpitanlib.MCPitanLib;
import net.pitan76.mcpitanlib.api.tag.v2.CompatTagKey;
import net.pitan76.mcpitanlib.api.tag.v2.CompatTagKeyType;

import java.util.Optional;

public class EntityTypeBuilder<T extends Entity> {

    private MobCategory spawnGroup;
    private ExtendEntityType.EntityFactory<T> factory;
    private EntityDimensions entityDimensions;
    private boolean saveable;
    private boolean summonable;
    private boolean fireImmune;
    private boolean spawnableFarFromPlayer;
    private TagKey<Block> canSpawnBlocks;
    private int maxTrackDistance;
    private int trackTickInterval;
    private Boolean alwaysUpdateVelocity = null;
    private String translationKey = "entity." + MCPitanLib.MOD_ID;
    private Optional<ResourceKey<LootTable>> lootTable = Optional.empty();

    @Deprecated
    // Recommend: create()
    public EntityTypeBuilder() {
        setSaveable(true);
        setSummonable(true);
        setFireImmune(false);
        setChangingDimensions(-1.0f, -1.0f);
        spawnableFarFromPlayer = false;
        maxTrackDistance = 5;
        trackTickInterval = 3;
        canSpawnBlocks = CompatTagKey.of(CompatTagKeyType.BLOCK, MCPitanLib.compatId("empty_can_spawn_blocks")).getTagKey();
    }

    @Deprecated
    public EntityTypeBuilder(MobCategory spawnGroup, ExtendEntityType.EntityFactory<T> factory) {
        this();
        this.spawnGroup = spawnGroup;
        this.factory = factory;
    }

    public static <T extends Entity> EntityTypeBuilder<T> create() {
        return new EntityTypeBuilder<>();
    }

    public static <T extends Entity> EntityTypeBuilder<T> create(MobCategory spawnGroup, ExtendEntityType.EntityFactory<T> factory) {
        return new EntityTypeBuilder<>(spawnGroup, factory);
    }

    public EntityType<T> build() {
        return new ExtendEntityType<>(factory, spawnGroup, saveable, summonable, fireImmune, spawnableFarFromPlayer, canSpawnBlocks, entityDimensions, maxTrackDistance, trackTickInterval, translationKey, lootTable, alwaysUpdateVelocity);
    }

    public EntityTypeBuilder<T> setSpawnGroup(MobCategory spawnGroup) {
        this.spawnGroup = spawnGroup;
        return this;
    }

    public EntityTypeBuilder<T> setEntityFactory(ExtendEntityType.EntityFactory<T> factory) {
        this.factory = factory;
        return this;
    }

    public EntityTypeBuilder<T> setDimensions(EntityDimensions entityDimensions) {
        this.entityDimensions = entityDimensions;
        return this;
    }

    public EntityTypeBuilder<T> setFixedDimensions(float width, float height) {
        return setDimensions(EntityDimensions.fixed(width, height));
    }

    public EntityTypeBuilder<T> setChangingDimensions(float width, float height) {
        return setDimensions(EntityDimensions.scalable(width, height));
    }

    public EntityTypeBuilder<T> setSaveable(boolean saveable) {
        this.saveable = saveable;
        return this;
    }

    public EntityTypeBuilder<T> setSummonable(boolean summonable) {
        this.summonable = summonable;
        return this;
    }

    public EntityTypeBuilder<T> setFireImmune(boolean fireImmune) {
        this.fireImmune = fireImmune;
        return this;
    }

    public EntityTypeBuilder<T> setSpawnableFarFromPlayer(boolean spawnableFarFromPlayer) {
        this.spawnableFarFromPlayer = spawnableFarFromPlayer;
        return this;
    }

    public EntityTypeBuilder<T> setCanSpawnBlocks(ImmutableSet<Block> canSpawnBlocks) {
//        this.canSpawnBlocks = canSpawnBlocks;
        return this;
    }

    public EntityTypeBuilder<T> setMaxTrackDistance(int maxTrackDistance) {
        this.maxTrackDistance = maxTrackDistance;
        return this;
    }

    public EntityTypeBuilder<T> setTrackTickInterval(int trackTickInterval) {
        this.trackTickInterval = trackTickInterval;
        return this;
    }

    public EntityTypeBuilder<T> setAlwaysUpdateVelocity(Boolean alwaysUpdateVelocity) {
        this.alwaysUpdateVelocity = alwaysUpdateVelocity;
        return this;
    }

    public void setTranslationKey(String translationKey) {
        this.translationKey = translationKey;
    }

    public void setLootTable(ResourceKey<LootTable> lootTable) {
        this.lootTable = Optional.ofNullable(lootTable);
    }
}

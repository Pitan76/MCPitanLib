package net.pitan76.mcpitanlib.api.entity;

import com.google.common.collect.ImmutableSet;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.world.World;

public class ExtendEntityType<T extends Entity> extends EntityType<T> {
    private final Boolean alwaysUpdateVelocity;

    @Deprecated
    public ExtendEntityType(EntityFactory<T> factory, SpawnGroup spawnGroup, boolean saveable, boolean summonable, boolean fireImmune, boolean spawnableFarFromPlayer, ImmutableSet<Block> canSpawnBlocks, EntityDimensions entityDimensions, float spawnBoxScale, int maxTrackDistance, int trackTickInterval, Boolean alwaysUpdateVelocity) {
        super((factory::create), spawnGroup, saveable, summonable, fireImmune, spawnableFarFromPlayer, canSpawnBlocks, entityDimensions, spawnBoxScale, maxTrackDistance, trackTickInterval, FeatureFlags.DEFAULT_ENABLED_FEATURES);
        this.alwaysUpdateVelocity = alwaysUpdateVelocity;
    }

    public ExtendEntityType(EntityFactory<T> factory, SpawnGroup spawnGroup, boolean saveable, boolean summonable, boolean fireImmune, boolean spawnableFarFromPlayer, ImmutableSet<Block> canSpawnBlocks, EntityDimensions entityDimensions, int maxTrackDistance, int trackTickInterval, Boolean alwaysUpdateVelocity) {
        super((factory::create), spawnGroup, saveable, summonable, fireImmune, spawnableFarFromPlayer, canSpawnBlocks, entityDimensions, 5, maxTrackDistance, trackTickInterval, FeatureFlags.DEFAULT_ENABLED_FEATURES);
        this.alwaysUpdateVelocity = alwaysUpdateVelocity;
    }

    @Override
    public boolean alwaysUpdateVelocity() {
        if (alwaysUpdateVelocity != null)
            return alwaysUpdateVelocity;

        return super.alwaysUpdateVelocity();
    }

    public interface EntityFactory<T extends Entity> {
        T create(EntityType<T> type, World world);
    }
}

package net.pitan76.mcpitanlib.api.entity;

import com.google.common.collect.ImmutableSet;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.Level;

import java.util.Optional;

public class ExtendEntityType<T extends Entity> extends EntityType<T> {
    private final Boolean alwaysUpdateVelocity;

    @Deprecated
    public ExtendEntityType(EntityFactory<T> factory, MobCategory spawnGroup, boolean saveable, boolean summonable, boolean fireImmune, boolean spawnableFarFromPlayer, ImmutableSet<Block> canSpawnBlocks, EntityDimensions entityDimensions, float spawnBoxScale, int maxTrackDistance, int trackTickInterval, String translationKey, Optional<ResourceKey<LootTable>> lootTable, Boolean alwaysUpdateVelocity) {
        super((factory::create), spawnGroup, saveable, summonable, fireImmune, spawnableFarFromPlayer, canSpawnBlocks, entityDimensions, spawnBoxScale, maxTrackDistance, trackTickInterval, translationKey, lootTable, FeatureFlags.DEFAULT_FLAGS, spawnGroup.isFriendly());
        this.alwaysUpdateVelocity = alwaysUpdateVelocity;
    }

    public ExtendEntityType(EntityFactory<T> factory, MobCategory spawnGroup, boolean saveable, boolean summonable, boolean fireImmune, boolean spawnableFarFromPlayer, ImmutableSet<Block> canSpawnBlocks, EntityDimensions entityDimensions, int maxTrackDistance, int trackTickInterval, String translationKey, Optional<ResourceKey<LootTable>> lootTable, Boolean alwaysUpdateVelocity) {
        super((factory::create), spawnGroup, saveable, summonable, fireImmune, spawnableFarFromPlayer, canSpawnBlocks, entityDimensions, 5, maxTrackDistance, trackTickInterval, translationKey, lootTable, FeatureFlags.DEFAULT_FLAGS, spawnGroup.isFriendly());
        this.alwaysUpdateVelocity = alwaysUpdateVelocity;
    }

    @Override
    public boolean trackDeltas() {
        if (alwaysUpdateVelocity != null)
            return alwaysUpdateVelocity;

        return super.trackDeltas();
    }

    public interface EntityFactory<T extends Entity> {
        T create(EntityType<T> type, Level world);
    }
}

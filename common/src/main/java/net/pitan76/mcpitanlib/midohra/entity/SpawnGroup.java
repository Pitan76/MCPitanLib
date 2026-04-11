package net.pitan76.mcpitanlib.midohra.entity;

import net.pitan76.mcpitanlib.api.util.CompatStringIdentifiable;

public class SpawnGroup implements CompatStringIdentifiable {
    private final net.minecraft.world.entity.MobCategory spawnGroup;

    public static final SpawnGroup MONSTER = of(net.minecraft.world.entity.MobCategory.MONSTER);
    public static final SpawnGroup CREATURE = of(net.minecraft.world.entity.MobCategory.CREATURE);
    public static final SpawnGroup AMBIENT = of(net.minecraft.world.entity.MobCategory.AMBIENT);
    public static final SpawnGroup AXOLOTLS = of(net.minecraft.world.entity.MobCategory.AXOLOTLS);
    public static final SpawnGroup UNDERGROUND_WATER_CREATURE = of(net.minecraft.world.entity.MobCategory.UNDERGROUND_WATER_CREATURE);
    public static final SpawnGroup WATER_CREATURE = of(net.minecraft.world.entity.MobCategory.WATER_CREATURE);
    public static final SpawnGroup WATER_AMBIENT = of(net.minecraft.world.entity.MobCategory.WATER_AMBIENT);
    public static final SpawnGroup MISC = of(net.minecraft.world.entity.MobCategory.MISC);

    protected SpawnGroup(net.minecraft.world.entity.MobCategory spawnGroup) {
        this.spawnGroup = spawnGroup;
    }

    public static SpawnGroup of(net.minecraft.world.entity.MobCategory spawnGroup) {
        return new SpawnGroup(spawnGroup);
    }

    public static SpawnGroup byName(String name) {
        for (net.minecraft.world.entity.MobCategory group : net.minecraft.world.entity.MobCategory.values()) {
            if (group.getName().equals(name)) {
                return of(group);
            }
        }

        throw new IllegalArgumentException("No spawn group with name: " + name);
    }

    public net.minecraft.world.entity.MobCategory getRaw() {
        return spawnGroup;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        SpawnGroup other = (SpawnGroup) obj;
        return getRaw() == other.getRaw();
    }

    @Override
    public int hashCode() {
        return getRaw().hashCode();
    }

    @Override
    public String asString_compat() {
        return getRaw().getName();
    }

    public int getCapacity() {
        return getRaw().getMaxInstancesPerChunk();
    }

    public boolean isPeaceful() {
        return getRaw().isFriendly();
    }

    public boolean isRare() {
        return getRaw().isPersistent();
    }

    public int getImmediateDespawnRange() {
        return getRaw().getDespawnDistance();
    }

    public int getDespawningStartRange() {
        return getRaw().getNoDespawnDistance();
    }
}

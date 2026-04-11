package net.pitan76.mcpitanlib.midohra.entity;

import net.pitan76.mcpitanlib.api.util.CompatStringIdentifiable;

public class SpawnGroup implements CompatStringIdentifiable {
    private final net.minecraft.entity.SpawnGroup spawnGroup;

    public static final SpawnGroup MONSTER = of(net.minecraft.entity.SpawnGroup.MONSTER);
    public static final SpawnGroup CREATURE = of(net.minecraft.entity.SpawnGroup.CREATURE);
    public static final SpawnGroup AMBIENT = of(net.minecraft.entity.SpawnGroup.AMBIENT);
    public static final SpawnGroup AXOLOTLS = of(net.minecraft.entity.SpawnGroup.AXOLOTLS);
    public static final SpawnGroup UNDERGROUND_WATER_CREATURE = of(net.minecraft.entity.SpawnGroup.UNDERGROUND_WATER_CREATURE);
    public static final SpawnGroup WATER_CREATURE = of(net.minecraft.entity.SpawnGroup.WATER_CREATURE);
    public static final SpawnGroup WATER_AMBIENT = of(net.minecraft.entity.SpawnGroup.WATER_AMBIENT);
    public static final SpawnGroup MISC = of(net.minecraft.entity.SpawnGroup.MISC);

    protected SpawnGroup(net.minecraft.entity.SpawnGroup spawnGroup) {
        this.spawnGroup = spawnGroup;
    }

    public static SpawnGroup of(net.minecraft.entity.SpawnGroup spawnGroup) {
        return new SpawnGroup(spawnGroup);
    }

    public static SpawnGroup byName(String name) {
        for (net.minecraft.entity.SpawnGroup group : net.minecraft.entity.SpawnGroup.values()) {
            if (group.getName().equals(name)) {
                return of(group);
            }
        }

        throw new IllegalArgumentException("No spawn group with name: " + name);
    }

    public net.minecraft.entity.SpawnGroup getRaw() {
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
        return getRaw().getCapacity();
    }

    public boolean isPeaceful() {
        return getRaw().isPeaceful();
    }

    public boolean isRare() {
        return getRaw().isRare();
    }

    public int getImmediateDespawnRange() {
        return getRaw().getImmediateDespawnRange();
    }

    public int getDespawningStartRange() {
        return getRaw().getDespawnStartRange();
    }
}

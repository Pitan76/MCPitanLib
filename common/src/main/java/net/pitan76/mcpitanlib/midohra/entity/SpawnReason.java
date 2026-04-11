package net.pitan76.mcpitanlib.midohra.entity;

public class SpawnReason {
    private final net.minecraft.world.entity.EntitySpawnReason raw;

    public static final SpawnReason NATURAL = of(net.minecraft.world.entity.EntitySpawnReason.NATURAL);
    public static final SpawnReason CHUNK_GENERATION = of(net.minecraft.world.entity.EntitySpawnReason.CHUNK_GENERATION);
    public static final SpawnReason SPAWNER = of(net.minecraft.world.entity.EntitySpawnReason.SPAWNER);
    public static final SpawnReason STRUCTURE = of(net.minecraft.world.entity.EntitySpawnReason.STRUCTURE);
    public static final SpawnReason BREEDING = of(net.minecraft.world.entity.EntitySpawnReason.BREEDING);
    public static final SpawnReason MOB_SUMMONED = of(net.minecraft.world.entity.EntitySpawnReason.MOB_SUMMONED);
    public static final SpawnReason JOCKEY = of(net.minecraft.world.entity.EntitySpawnReason.JOCKEY);
    public static final SpawnReason EVENT = of(net.minecraft.world.entity.EntitySpawnReason.EVENT);
    public static final SpawnReason CONVERSION = of(net.minecraft.world.entity.EntitySpawnReason.CONVERSION);
    public static final SpawnReason REINFORCEMENT = of(net.minecraft.world.entity.EntitySpawnReason.REINFORCEMENT);
    public static final SpawnReason TRIGGERED = of(net.minecraft.world.entity.EntitySpawnReason.TRIGGERED);
    public static final SpawnReason BUCKET = of(net.minecraft.world.entity.EntitySpawnReason.BUCKET);
    public static final SpawnReason SPAWN_ITEM_USE = of(net.minecraft.world.entity.EntitySpawnReason.SPAWN_ITEM_USE);
    public static final SpawnReason COMMAND = of(net.minecraft.world.entity.EntitySpawnReason.COMMAND);
    public static final SpawnReason DISPENSER = of(net.minecraft.world.entity.EntitySpawnReason.DISPENSER);
    public static final SpawnReason PATROL = of(net.minecraft.world.entity.EntitySpawnReason.PATROL);
    public static final SpawnReason TRIAL_SPAWNER = of(net.minecraft.world.entity.EntitySpawnReason.TRIAL_SPAWNER);
    public static final SpawnReason LOAD = of(net.minecraft.world.entity.EntitySpawnReason.LOAD);
    public static final SpawnReason DIMENSION_TRAVEL = of(net.minecraft.world.entity.EntitySpawnReason.DIMENSION_TRAVEL);

    protected SpawnReason(net.minecraft.world.entity.EntitySpawnReason raw) {
        this.raw = raw;
    }

    public static SpawnReason of(net.minecraft.world.entity.EntitySpawnReason raw) {
        return new SpawnReason(raw);
    }

    public net.minecraft.world.entity.EntitySpawnReason getRaw() {
        return raw;
    }

    @Override
    public int hashCode() {
        return raw.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        SpawnReason other = (SpawnReason) obj;
        return raw == other.raw;
    }
}

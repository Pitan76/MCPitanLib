package net.pitan76.mcpitanlib.midohra.entity;

public class SpawnReason {
    private final net.minecraft.entity.SpawnReason raw;

    public static final SpawnReason NATURAL = of(net.minecraft.entity.SpawnReason.NATURAL);
    public static final SpawnReason CHUNK_GENERATION = of(net.minecraft.entity.SpawnReason.CHUNK_GENERATION);
    public static final SpawnReason SPAWNER = of(net.minecraft.entity.SpawnReason.SPAWNER);
    public static final SpawnReason STRUCTURE = of(net.minecraft.entity.SpawnReason.STRUCTURE);
    public static final SpawnReason BREEDING = of(net.minecraft.entity.SpawnReason.BREEDING);
    public static final SpawnReason MOB_SUMMONED = of(net.minecraft.entity.SpawnReason.MOB_SUMMONED);
    public static final SpawnReason JOCKEY = of(net.minecraft.entity.SpawnReason.JOCKEY);
    public static final SpawnReason EVENT = of(net.minecraft.entity.SpawnReason.EVENT);
    public static final SpawnReason CONVERSION = of(net.minecraft.entity.SpawnReason.CONVERSION);
    public static final SpawnReason REINFORCEMENT = of(net.minecraft.entity.SpawnReason.REINFORCEMENT);
    public static final SpawnReason TRIGGERED = of(net.minecraft.entity.SpawnReason.TRIGGERED);
    public static final SpawnReason BUCKET = of(net.minecraft.entity.SpawnReason.BUCKET);
    public static final SpawnReason SPAWN_ITEM_USE = of(net.minecraft.entity.SpawnReason.SPAWN_ITEM_USE);
    public static final SpawnReason COMMAND = of(net.minecraft.entity.SpawnReason.COMMAND);
    public static final SpawnReason DISPENSER = of(net.minecraft.entity.SpawnReason.DISPENSER);
    public static final SpawnReason PATROL = of(net.minecraft.entity.SpawnReason.PATROL);
    public static final SpawnReason TRIAL_SPAWNER = of(net.minecraft.entity.SpawnReason.TRIAL_SPAWNER);
    public static final SpawnReason LOAD = of(net.minecraft.entity.SpawnReason.LOAD);
    public static final SpawnReason DIMENSION_TRAVEL = of(net.minecraft.entity.SpawnReason.DIMENSION_TRAVEL);

    protected SpawnReason(net.minecraft.entity.SpawnReason raw) {
        this.raw = raw;
    }

    public static SpawnReason of(net.minecraft.entity.SpawnReason raw) {
        return new SpawnReason(raw);
    }

    public net.minecraft.entity.SpawnReason getRaw() {
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

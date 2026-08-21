package net.pitan76.mcpitanlib.api.util.block.properties;

import net.minecraft.world.level.block.state.properties.ChestType;
import net.pitan76.mcpitanlib.api.util.CompatStringIdentifiable;

public class CompatChestType implements CompatStringIdentifiable {
    private final ChestType chestType;

    public static final CompatChestType SINGLE = of(ChestType.SINGLE);
    public static final CompatChestType LEFT = of(ChestType.LEFT);
    public static final CompatChestType RIGHT = of(ChestType.RIGHT);

    public CompatChestType(ChestType chestType) {
        this.chestType = chestType;
    }

    public static CompatChestType of(ChestType chestType) {
        return new CompatChestType(chestType);
    }

    public ChestType getChestType() {
        return chestType;
    }

    public String getName() {
        return chestType.name();
    }

    @Override
    public String asString_compat() {
        return getName();
    }

    @Override
    public int hashCode() {
        return chestType.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        CompatChestType other = (CompatChestType) obj;
        return chestType == other.chestType;
    }
}

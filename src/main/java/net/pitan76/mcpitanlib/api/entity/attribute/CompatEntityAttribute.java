package net.pitan76.mcpitanlib.api.entity.attribute;

import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.registry.entry.RegistryEntry;

public class CompatEntityAttribute {
    public final RegistryEntry<EntityAttribute> raw;

    @Deprecated
    public CompatEntityAttribute(RegistryEntry<EntityAttribute> attribute) {
        this.raw = attribute;
    }

    @Deprecated
    public RegistryEntry<EntityAttribute> raw() {
        return raw;
    }

    public String getId() {
        return raw.getIdAsString();
    }

    public EntityAttribute getValue() {
        return raw.value();
    }

    public boolean isNull() {
        return raw == null;
    }

    @Deprecated
    public static CompatEntityAttribute of(RegistryEntry<EntityAttribute> attribute) {
        return new CompatEntityAttribute(attribute);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CompatEntityAttribute)) return false;
        CompatEntityAttribute that = (CompatEntityAttribute) obj;
        return raw.equals(that.raw);
    }

    @Override
    public int hashCode() {
        if (raw == null) return super.hashCode();
        return raw.hashCode();
    }
}

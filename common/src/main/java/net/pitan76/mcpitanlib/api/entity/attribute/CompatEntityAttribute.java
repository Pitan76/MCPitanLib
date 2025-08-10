package net.pitan76.mcpitanlib.api.entity.attribute;

import net.minecraft.entity.attribute.EntityAttribute;

public class CompatEntityAttribute {
    public final EntityAttribute raw;

    @Deprecated
    public CompatEntityAttribute(EntityAttribute attribute) {
        this.raw = attribute;
    }

    @Deprecated
    public EntityAttribute raw() {
        return raw;
    }

    public String getId() {
        return raw.getTranslationKey();
    }

    public EntityAttribute getValue() {
        return raw();
    }

    public boolean isNull() {
        return raw == null;
    }

    @Deprecated
    public static CompatEntityAttribute of(EntityAttribute attribute) {
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

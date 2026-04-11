package net.pitan76.mcpitanlib.api.entity.attribute;

import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.core.Holder;

public class CompatEntityAttribute {
    public final Holder<Attribute> raw;

    @Deprecated
    public CompatEntityAttribute(Holder<Attribute> attribute) {
        this.raw = attribute;
    }

    @Deprecated
    public Holder<Attribute> raw() {
        return raw;
    }

    public String getId() {
        return raw.getRegisteredName();
    }

    public Attribute getValue() {
        return raw.value();
    }

    public boolean isNull() {
        return raw == null;
    }

    @Deprecated
    public static CompatEntityAttribute of(Holder<Attribute> attribute) {
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

package net.pitan76.mcpitanlib.api.entity.attribute;

import net.pitan76.mcpitanlib.api.util.CompatIdentifier;

public class CompatEntityAttributeModifier {
    private final net.minecraft.entity.attribute.EntityAttributeModifier raw;

    @Deprecated
    public CompatEntityAttributeModifier(net.minecraft.entity.attribute.EntityAttributeModifier raw) {
        this.raw = raw;
    }

    @Deprecated
    public static CompatEntityAttributeModifier of(net.minecraft.entity.attribute.EntityAttributeModifier raw) {
        return new CompatEntityAttributeModifier(raw);
    }

    public static CompatEntityAttributeModifier of(CompatIdentifier id, double value, AttrModifierOperation operation) {
        return new CompatEntityAttributeModifier(new net.minecraft.entity.attribute.EntityAttributeModifier(id.toString(), value, operation.raw()));
    }

    @Deprecated
    public net.minecraft.entity.attribute.EntityAttributeModifier raw() {
        return raw;
    }

    public AttrModifierOperation getOperation() {
        return AttrModifierOperation.of(raw().getOperation());
    }

    public double getValue() {
        return raw().getValue();
    }

    public CompatIdentifier getId() {
        return CompatIdentifier.empty();
    }

    @Override
    public String toString() {
        return raw().toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CompatEntityAttributeModifier)) return false;
        CompatEntityAttributeModifier that = (CompatEntityAttributeModifier) obj;
        return raw().equals(that.raw());
    }

    @Override
    public int hashCode() {
        if (raw() == null) return super.hashCode();
        return raw().hashCode();
    }
}

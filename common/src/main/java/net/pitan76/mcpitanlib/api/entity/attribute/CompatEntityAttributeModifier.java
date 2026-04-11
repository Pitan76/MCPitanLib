package net.pitan76.mcpitanlib.api.entity.attribute;

import net.pitan76.mcpitanlib.api.util.CompatIdentifier;

public class CompatEntityAttributeModifier {
    private final net.minecraft.world.entity.ai.attributes.AttributeModifier raw;

    @Deprecated
    public CompatEntityAttributeModifier(net.minecraft.world.entity.ai.attributes.AttributeModifier raw) {
        this.raw = raw;
    }

    @Deprecated
    public static CompatEntityAttributeModifier of(net.minecraft.world.entity.ai.attributes.AttributeModifier raw) {
        return new CompatEntityAttributeModifier(raw);
    }

    public static CompatEntityAttributeModifier of(CompatIdentifier id, double value, AttrModifierOperation operation) {
        return new CompatEntityAttributeModifier(new net.minecraft.world.entity.ai.attributes.AttributeModifier(id.toMinecraft(), value, operation.raw()));
    }

    @Deprecated
    public net.minecraft.world.entity.ai.attributes.AttributeModifier raw() {
        return raw;
    }

    public AttrModifierOperation getOperation() {
        return AttrModifierOperation.of(raw().operation());
    }

    public double getValue() {
        return raw().amount();
    }

    public CompatIdentifier getId() {
        return CompatIdentifier.fromMinecraft(raw().id());
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

package net.pitan76.mcpitanlib.api.entity.attribute;

import net.minecraft.world.entity.ai.attributes.AttributeModifier;

public class AttrModifierOperation {
    private final AttributeModifier.Operation raw;

    public static final AttrModifierOperation ADD = new AttrModifierOperation(AttributeModifier.Operation.ADD_VALUE);
    public static final AttrModifierOperation MUL_TOTAL = new AttrModifierOperation(AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
    public static final AttrModifierOperation MUL_BASE = new AttrModifierOperation(AttributeModifier.Operation.ADD_MULTIPLIED_BASE);

    @Deprecated
    public AttrModifierOperation(AttributeModifier.Operation raw) {
        this.raw = raw;
    }

    public static AttrModifierOperation of(AttributeModifier.Operation raw) {
        if (raw == AttributeModifier.Operation.ADD_VALUE) {
            return ADD;
        } else if (raw == AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL) {
            return MUL_TOTAL;
        } else if (raw == AttributeModifier.Operation.ADD_MULTIPLIED_BASE) {
            return MUL_BASE;
        }

        return new AttrModifierOperation(raw);
    }

    public AttributeModifier.Operation raw() {
        return raw;
    }

    public String getName() {
        return raw().name();
    }

    public int getId() {
        return raw().id();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof AttrModifierOperation)) return false;
        AttrModifierOperation that = (AttrModifierOperation) obj;
        return raw().equals(that.raw());
    }

    @Override
    public int hashCode() {
        if (raw() == null) return super.hashCode();
        return raw().hashCode();
    }
}

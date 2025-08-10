package net.pitan76.mcpitanlib.api.entity.attribute;

import net.minecraft.entity.attribute.EntityAttributeModifier;

public class AttrModifierOperation {
    private final EntityAttributeModifier.Operation raw;

    public static final AttrModifierOperation ADD = new AttrModifierOperation(EntityAttributeModifier.Operation.ADDITION);
    public static final AttrModifierOperation MUL_TOTAL = new AttrModifierOperation(EntityAttributeModifier.Operation.MULTIPLY_TOTAL);
    public static final AttrModifierOperation MUL_BASE = new AttrModifierOperation(EntityAttributeModifier.Operation.MULTIPLY_BASE);

    @Deprecated
    public AttrModifierOperation(EntityAttributeModifier.Operation raw) {
        this.raw = raw;
    }

    public static AttrModifierOperation of(EntityAttributeModifier.Operation raw) {
        if (raw == EntityAttributeModifier.Operation.ADDITION) {
            return ADD;
        } else if (raw == EntityAttributeModifier.Operation.MULTIPLY_TOTAL) {
            return MUL_TOTAL;
        } else if (raw == EntityAttributeModifier.Operation.MULTIPLY_BASE) {
            return MUL_BASE;
        }

        return new AttrModifierOperation(raw);
    }

    public EntityAttributeModifier.Operation raw() {
        return raw;
    }

    public String getName() {
        return raw().name();
    }

    public int getId() {
        return raw().getId();
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

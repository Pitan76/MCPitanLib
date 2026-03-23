package net.pitan76.mcpitanlib.midohra.entity;

import net.pitan76.mcpitanlib.api.text.TextComponent;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;
import net.pitan76.mcpitanlib.api.util.EntityTypeUtil;

public class EntityTypeWrapper {
    private final net.minecraft.entity.EntityType<?> entityType;

    protected EntityTypeWrapper() {
        this.entityType = null;
    }

    protected EntityTypeWrapper(net.minecraft.entity.EntityType<?> entityType) {
        this.entityType = entityType;
    }

    public static EntityTypeWrapper of(net.minecraft.entity.EntityType<?> entityType) {
        return new EntityTypeWrapper(entityType);
    }

    public static EntityTypeWrapper of() {
        return new EntityTypeWrapper();
    }

    public boolean isExist() {
        return !isEmpty();
    }

    public boolean isEmpty() {
        return entityType == null;
    }

    public net.minecraft.entity.EntityType<?> get() {
        return entityType;
    }

    public CompatIdentifier getId() {
        if (isEmpty()) return CompatIdentifier.empty();
        return EntityTypeUtil.toCompatID(entityType);
    }

    public TextComponent getName() {
        if (isEmpty()) return TextComponent.of("");
        return new TextComponent(EntityTypeUtil.getName(entityType));
    }

    public String getTranslationKey() {
        if (isEmpty()) return "";
        return EntityTypeUtil.getTranslationKey(entityType);
    }

    public boolean rawEquals(EntityTypeWrapper other) {
        return entityType == other.entityType;
    }

    @Override
    public int hashCode() {
        return get() != null ? get().hashCode() : 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        EntityTypeWrapper other = (EntityTypeWrapper) obj;
        return rawEquals(other);
    }
}

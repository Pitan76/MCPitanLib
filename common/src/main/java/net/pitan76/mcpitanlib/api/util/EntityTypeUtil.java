package net.pitan76.mcpitanlib.api.util;

import net.minecraft.entity.EntityType;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

public class EntityTypeUtil {
    public static Identifier toID(EntityType<?> entityType) {
        return Registries.ENTITY_TYPE.getId(entityType);
    }

    public static EntityType<?> fromId(Identifier identifier) {
        return Registries.ENTITY_TYPE.get(identifier);
    }

    public static boolean isExist(Identifier identifier) {
        return Registries.ENTITY_TYPE.containsId(identifier);
    }

    public static CompatIdentifier toCompatID(EntityType<?> entityType) {
        return CompatIdentifier.fromMinecraft(toID(entityType));
    }

    public static EntityType<?> fromId(CompatIdentifier identifier) {
        return fromId(identifier.toMinecraft());
    }

    public static boolean isExist(CompatIdentifier identifier) {
        return isExist(identifier.toMinecraft());
    }

    public static int getRawId(EntityType<?> type) {
        return Registries.ENTITY_TYPE.getRawId(type);
    }

    public static EntityType<?> fromIndex(int index) {
        return Registries.ENTITY_TYPE.get(index);
    }
}

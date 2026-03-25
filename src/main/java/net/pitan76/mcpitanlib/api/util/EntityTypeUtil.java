package net.pitan76.mcpitanlib.api.util;

import net.minecraft.world.entity.EntityType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

public class EntityTypeUtil {
    public static Identifier toID(EntityType<?> entityType) {
        return BuiltInRegistries.ENTITY_TYPE.getKey(entityType);
    }

    public static EntityType<?> fromId(Identifier identifier) {
        return BuiltInRegistries.ENTITY_TYPE.getValue(identifier);
    }

    public static boolean isExist(Identifier identifier) {
        return BuiltInRegistries.ENTITY_TYPE.containsKey(identifier);
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
        return BuiltInRegistries.ENTITY_TYPE.getId(type);
    }

    public static EntityType<?> fromIndex(int index) {
        return BuiltInRegistries.ENTITY_TYPE.byId(index);
    }

    public static Component getName(EntityType<?> entityType) {
        return entityType.getDescription();
    }

    public static String getTranslationKey(EntityType<?> entityType) {
        return entityType.getDescriptionId();
    }
}

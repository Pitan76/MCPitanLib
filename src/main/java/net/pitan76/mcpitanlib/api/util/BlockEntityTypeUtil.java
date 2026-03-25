package net.pitan76.mcpitanlib.api.util;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;

public class BlockEntityTypeUtil {
    public static Identifier toID(BlockEntityType<?> entityType) {
        return BuiltInRegistries.BLOCK_ENTITY_TYPE.getKey(entityType);
    }

    public static BlockEntityType<?> fromId(Identifier identifier) {
        return BuiltInRegistries.BLOCK_ENTITY_TYPE.getValue(identifier);
    }

    public static boolean isExist(Identifier identifier) {
        return BuiltInRegistries.BLOCK_ENTITY_TYPE.containsKey(identifier);
    }

    public static CompatIdentifier toCompatID(BlockEntityType<?> entityType) {
        return CompatIdentifier.fromMinecraft(toID(entityType));
    }

    public static BlockEntityType<?> fromId(CompatIdentifier identifier) {
        return fromId(identifier.toMinecraft());
    }

    public static boolean isExist(CompatIdentifier identifier) {
        return isExist(identifier.toMinecraft());
    }

    public static int getRawId(BlockEntityType<?> type) {
        return BuiltInRegistries.BLOCK_ENTITY_TYPE.getId(type);
    }

    public static BlockEntityType<?> fromIndex(int index) {
        return BuiltInRegistries.BLOCK_ENTITY_TYPE.byId(index);
    }
}

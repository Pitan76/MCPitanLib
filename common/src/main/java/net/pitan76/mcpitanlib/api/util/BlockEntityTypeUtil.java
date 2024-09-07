package net.pitan76.mcpitanlib.api.util;

import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

public class BlockEntityTypeUtil {
    public static Identifier toID(BlockEntityType<?> entityType) {
        return Registries.BLOCK_ENTITY_TYPE.getId(entityType);
    }

    public static BlockEntityType<?> fromId(Identifier identifier) {
        return Registries.BLOCK_ENTITY_TYPE.get(identifier);
    }

    public static boolean isExist(Identifier identifier) {
        return Registries.BLOCK_ENTITY_TYPE.containsId(identifier);
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
        return Registries.BLOCK_ENTITY_TYPE.getRawId(type);
    }

    public static BlockEntityType<?> fromIndex(int index) {
        return Registries.BLOCK_ENTITY_TYPE.get(index);
    }
}

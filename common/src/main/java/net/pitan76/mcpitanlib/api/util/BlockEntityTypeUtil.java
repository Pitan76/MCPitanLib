package net.pitan76.mcpitanlib.api.util;

import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class BlockEntityTypeUtil {
    public static Identifier toID(BlockEntityType<?> entityType) {
        return Registry.BLOCK_ENTITY_TYPE.getId(entityType);
    }

    public static BlockEntityType<?> fromId(Identifier identifier) {
        return Registry.BLOCK_ENTITY_TYPE.get(identifier);
    }

    public static boolean isExist(Identifier identifier) {
        return Registry.BLOCK_ENTITY_TYPE.containsId(identifier);
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
        return Registry.BLOCK_ENTITY_TYPE.getRawId(type);
    }

    public static BlockEntityType<?> fromIndex(int index) {
        return Registry.BLOCK_ENTITY_TYPE.get(index);
    }
}

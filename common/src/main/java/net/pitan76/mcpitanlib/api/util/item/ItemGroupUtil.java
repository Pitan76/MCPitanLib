package net.pitan76.mcpitanlib.api.util.item;

import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;

public class ItemGroupUtil {
    public static Identifier toID(ItemGroup itemGroup) {
        return Registries.ITEM_GROUP.getId(itemGroup);
    }

    public static ItemGroup fromId(Identifier identifier) {
        return Registries.ITEM_GROUP.get(identifier);
    }

    public static boolean isExist(Identifier identifier) {
        return Registries.ITEM_GROUP.containsId(identifier);
    }

    public static CompatIdentifier toCompatID(ItemGroup itemGroup) {
        return CompatIdentifier.fromMinecraft(toID(itemGroup));
    }

    public static ItemGroup fromId(CompatIdentifier identifier) {
        return fromId(identifier.toMinecraft());
    }

    public static boolean isExist(CompatIdentifier identifier) {
        return isExist(identifier.toMinecraft());
    }

    public static int getRawId(ItemGroup itemGroup) {
        return Registries.ITEM_GROUP.getRawId(itemGroup);
    }

    public static ItemGroup fromIndex(int index) {
        return Registries.ITEM_GROUP.get(index);
    }
}

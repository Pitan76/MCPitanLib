package net.pitan76.mcpitanlib.api.util.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;

public class ItemGroupUtil {
    public static Identifier toID(CreativeModeTab itemGroup) {
        return BuiltInRegistries.CREATIVE_MODE_TAB.getKey(itemGroup);
    }

    public static CreativeModeTab fromId(Identifier identifier) {
        return BuiltInRegistries.CREATIVE_MODE_TAB.getValue(identifier);
    }

    public static boolean isExist(Identifier identifier) {
        return BuiltInRegistries.CREATIVE_MODE_TAB.containsKey(identifier);
    }

    public static CompatIdentifier toCompatID(CreativeModeTab itemGroup) {
        return CompatIdentifier.fromMinecraft(toID(itemGroup));
    }

    public static CreativeModeTab fromId(CompatIdentifier identifier) {
        return fromId(identifier.toMinecraft());
    }

    public static boolean isExist(CompatIdentifier identifier) {
        return isExist(identifier.toMinecraft());
    }

    public static int getRawId(CreativeModeTab itemGroup) {
        return BuiltInRegistries.CREATIVE_MODE_TAB.getId(itemGroup);
    }

    public static CreativeModeTab fromIndex(int index) {
        return BuiltInRegistries.CREATIVE_MODE_TAB.byId(index);
    }
}

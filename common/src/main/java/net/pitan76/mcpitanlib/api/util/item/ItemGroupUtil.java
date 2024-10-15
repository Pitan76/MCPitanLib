package net.pitan76.mcpitanlib.api.util.item;

import net.minecraft.item.ItemGroup;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;

public class ItemGroupUtil {
    public static Identifier toID(ItemGroup itemGroup) {
        if (itemGroup.getDisplayName() instanceof TranslatableText) {
            TranslatableText translatableText = (TranslatableText) itemGroup.getDisplayName();
                String[] strings = translatableText.getKey().split("\\.");

                if (strings.length == 3)
                    return new Identifier(strings[1], strings[2]);
        }

        return CompatIdentifier.empty().toMinecraft();
    }

    public static ItemGroup fromId(Identifier identifier) {
        return null;
    }

    public static boolean isExist(Identifier identifier) {
        return false;
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
        return itemGroup.getIndex();
    }

    public static ItemGroup fromIndex(int index) {
        return ItemGroup.GROUPS[index];
    }
}

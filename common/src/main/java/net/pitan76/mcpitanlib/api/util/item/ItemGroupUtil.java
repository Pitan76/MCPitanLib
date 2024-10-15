package net.pitan76.mcpitanlib.api.util.item;

import net.minecraft.item.ItemGroup;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableTextContent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;

public class ItemGroupUtil {
    public static Identifier toID(ItemGroup itemGroup) {
        if (itemGroup.getDisplayName() instanceof MutableText) {
            MutableText mutableText = (MutableText) itemGroup.getDisplayName();
            if (mutableText.getContent() instanceof TranslatableTextContent) {
                TranslatableTextContent translatableTextContent = (TranslatableTextContent) mutableText.getContent();
                String[] strings = translatableTextContent.getKey().split("\\.");

                if (strings.length == 3)
                    return new Identifier(strings[1], strings[2]);

            }
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

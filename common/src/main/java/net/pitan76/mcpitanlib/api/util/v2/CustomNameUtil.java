package net.pitan76.mcpitanlib.api.util.v2;

import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.pitan76.mcpitanlib.api.text.TextComponent;
import net.pitan76.mcpitanlib.api.util.TextUtil;

public class CustomNameUtil {

    public static void setCustomName(ItemStack stack, Text name) {
        stack.setCustomName(name);
    }

    public static void setCustomName(ItemStack stack, String name) {
        setCustomNameFromString(stack, name);
    }

    public static void setCustomNameFromString(ItemStack stack, String name) {
        setCustomName(stack, TextUtil.literal(name));
    }

    public static void setCustomNameFromTranslatable(ItemStack stack, String key) {
        setCustomName(stack, TextUtil.translatable(key));
    }

    public static void setCustomName(ItemStack stack, TextComponent name) {
        setCustomName(stack, name.getText());
    }

    public static Text getCustomName(ItemStack stack) {
        return stack.getName();
    }

    public static String getCustomNameAsString(ItemStack stack) {
        return getCustomName(stack).getString();
    }

    public static TextComponent getCustomNameAsTextComponent(ItemStack stack) {
        return new TextComponent(getCustomName(stack));
    }

    public static boolean hasCustomName(ItemStack stack) {
        return stack.hasCustomName();
    }

    public static void removeCustomName(ItemStack stack) {
        stack.removeCustomName();
    }
}

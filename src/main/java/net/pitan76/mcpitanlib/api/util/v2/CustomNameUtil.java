package net.pitan76.mcpitanlib.api.util.v2;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.network.chat.Component;
import net.pitan76.mcpitanlib.api.text.TextComponent;
import net.pitan76.mcpitanlib.api.util.TextUtil;

public class CustomNameUtil {

    public static void setCustomName(ItemStack stack, Component name) {
        stack.set(DataComponents.CUSTOM_NAME, name);
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

    public static Component getCustomName(ItemStack stack) {
        return stack.get(DataComponents.CUSTOM_NAME);
    }

    public static String getCustomNameAsString(ItemStack stack) {
        return getCustomName(stack).getString();
    }

    public static TextComponent getCustomNameAsTextComponent(ItemStack stack) {
        return new TextComponent(getCustomName(stack));
    }

    public static boolean hasCustomName(ItemStack stack) {
        return stack.has(DataComponents.CUSTOM_NAME);
    }

    public static void removeCustomName(ItemStack stack) {
        stack.remove(DataComponents.CUSTOM_NAME);
    }
}

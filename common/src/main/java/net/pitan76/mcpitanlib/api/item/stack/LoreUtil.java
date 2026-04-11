package net.pitan76.mcpitanlib.api.item.stack;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.component.ItemLore;
import net.minecraft.world.item.ItemStack;
import net.minecraft.network.chat.Component;

import java.util.List;

public class LoreUtil {
    public static boolean hasLore(ItemStack stack) {
        return stack.has(DataComponents.LORE);
    }

    public static List<Component> getLore(ItemStack stack) {
        if (!hasLore(stack)) return List.of();
        return stack.get(DataComponents.LORE).lines();
    }

    public static List<String> getLoreAsStringList(ItemStack stack) {
        return getLore(stack).stream()
                .map(Component::getString)
                .toList();
    }

    public static String getLoreAsString(ItemStack stack) {
        return getLoreAsStringList(stack).stream()
                .reduce("", (a, b) -> a + "\n" + b);
    }

    public static void setLore(ItemStack stack, List<Component> lore) {
        stack.set(DataComponents.LORE, new ItemLore(lore));
    }

    public static void setLoreStringList(ItemStack stack, List<String> lore) {
        setLore(stack, lore.stream()
                .map(Component::nullToEmpty)
                .toList());
    }

    public static void setLore(ItemStack stack, String lore) {
        setLore(stack, lore.lines()
                .map(Component::nullToEmpty)
                .toList());
    }
}

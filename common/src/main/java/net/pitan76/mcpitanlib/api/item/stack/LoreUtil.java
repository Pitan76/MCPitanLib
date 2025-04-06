package net.pitan76.mcpitanlib.api.item.stack;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.LoreComponent;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;

import java.util.List;

public class LoreUtil {
    public static boolean hasLore(ItemStack stack) {
        return stack.contains(DataComponentTypes.LORE);
    }

    public static List<Text> getLore(ItemStack stack) {
        if (!hasLore(stack)) return List.of();
        return stack.get(DataComponentTypes.LORE).lines();
    }

    public static List<String> getLoreAsStringList(ItemStack stack) {
        return getLore(stack).stream()
                .map(Text::getString)
                .toList();
    }

    public static String getLoreAsString(ItemStack stack) {
        return getLoreAsStringList(stack).stream()
                .reduce("", (a, b) -> a + "\n" + b);
    }

    public static void setLore(ItemStack stack, List<Text> lore) {
        stack.set(DataComponentTypes.LORE, new LoreComponent(lore));
    }

    public static void setLoreStringList(ItemStack stack, List<String> lore) {
        setLore(stack, lore.stream()
                .map(Text::of)
                .toList());
    }

    public static void setLore(ItemStack stack, String lore) {
        setLore(stack, lore.lines()
                .map(Text::of)
                .toList());
    }
}

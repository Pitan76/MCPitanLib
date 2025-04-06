package net.pitan76.mcpitanlib.api.item.stack;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.text.Text;
import net.pitan76.mcpitanlib.api.nbt.NbtTypeBytes;
import net.pitan76.mcpitanlib.api.util.NbtUtil;

import java.util.List;
import java.util.regex.Pattern;

public class LoreUtil {
    public static boolean hasLore(ItemStack stack) {
        return stack.getSubNbt("display") != null && stack.getSubNbt("display").contains("Lore");
    }

    public static List<Text> getLore(ItemStack stack) {
        if (!hasLore(stack)) return List.of();
        return NbtUtil.getList(stack.getSubNbt("display"), "Lore", NbtTypeBytes.STRING).stream()
                    .map(nbt -> {
                        String str = NbtUtil.asString(nbt);
                        if (str == null) return Text.empty();

                        Pattern pattern = Pattern.compile("\"text\":\"([^\"]+)\"");
                        java.util.regex.Matcher matcher = pattern.matcher(str);
                        if (matcher.find()) {
                            String text = matcher.group(1);
                            return Text.of(text);
                        }

                        return Text.of(str);
                    }).toList();
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
        NbtList nbtList = NbtUtil.createNbtList();

        for (Text text : lore) {
            String str = text.getString();
            str = "{\"text\":\"" + str + "\"}";

            nbtList.add(NbtUtil.createString(str));
        }

        NbtCompound displayNbt = stack.getOrCreateSubNbt("display");
        displayNbt.put("Lore", nbtList);
        stack.setSubNbt("display", displayNbt);
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

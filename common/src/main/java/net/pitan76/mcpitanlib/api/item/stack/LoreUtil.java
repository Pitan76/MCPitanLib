package net.pitan76.mcpitanlib.api.item.stack;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.text.Text;
import net.pitan76.mcpitanlib.api.nbt.NbtTypeBytes;
import net.pitan76.mcpitanlib.api.util.NbtUtil;
import net.pitan76.mcpitanlib.api.util.TextUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class LoreUtil {
    public static boolean hasLore(ItemStack stack) {
        return stack.getSubTag("display") != null && stack.getSubTag("display").contains("Lore");
    }

    public static List<Text> getLore(ItemStack stack) {
        if (!hasLore(stack)) return new ArrayList<>();
        return NbtUtil.getList(stack.getSubTag("display"), "Lore", NbtTypeBytes.STRING).stream()
                    .map(nbt -> {
                        String str = NbtUtil.asString(nbt);
                        if (str == null) return TextUtil.empty();

                        Pattern pattern = Pattern.compile("\"text\":\"([^\"]+)\"");
                        java.util.regex.Matcher matcher = pattern.matcher(str);
                        if (matcher.find()) {
                            String text = matcher.group(1);
                            return Text.of(text);
                        }

                        return Text.of(str);
                    }).collect(Collectors.toList());
    }

    public static List<String> getLoreAsStringList(ItemStack stack) {
        return getLore(stack).stream()
                .map(Text::getString)
                .collect(Collectors.toList());
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

        NbtCompound displayNbt = stack.getOrCreateSubTag("display");
        displayNbt.put("Lore", nbtList);
        stack.putSubTag("display", displayNbt);
    }

    public static void setLoreStringList(ItemStack stack, List<String> lore) {
        setLore(stack, lore.stream()
                .map(Text::of)
                .collect(Collectors.toList()));
    }

    public static void setLore(ItemStack stack, String lore) {
        List<Text> loreList = new ArrayList<>();
        for (String line : lore.split("\n")) {
            loreList.add(Text.of(line));
        }
        
        setLore(stack, loreList);
    }
}

package net.pitan76.mcpitanlib.api.text;

import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.ChatFormatting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextConverter {

    public static MutableComponent convert(String string) {
        return convert(string, false);
    }

    /**
     * Convert string to MutableText with formatting
     *
     * @param text String
     * @param translatable boolean
     * @return MutableText
     */
    public static MutableComponent convert(String text, boolean translatable) {
        String[] splits = split(text);
        MutableComponent result = Component.literal("");
        ChatFormatting[] currentFormatting = {};

        for (String part : splits) {
            // Section
            if (part.startsWith("§") && part.length() == 2) {
                char code = part.charAt(1);
                // Reset
                if (code == 'r') {
                    currentFormatting = new ChatFormatting[]{};
                    continue;
                }

                ChatFormatting format = ChatFormatting.getByCode(code);
                if (format == null)
                    continue;

                // Bold, Italic, Underline, Strikethrough, Obfuscated
                if (code >= 'k' && code <= 'o') {
                    ArrayList<ChatFormatting> list = new ArrayList<>(Arrays.asList(currentFormatting));
                    list.add(format);
                    currentFormatting = list.toArray(new ChatFormatting[0]);
                    continue;
                }

                // Color
                currentFormatting = new ChatFormatting[]{format};
                continue;
            }

            // Translatable
            if (translatable) {
                Pattern pattern = Pattern.compile("\\{(.+?)\\}");
                Matcher matcher = pattern.matcher(part);
                MutableComponent tempText = Component.literal("");
                int lastIndex = 0;

                while (matcher.find()) {
                    // {translatable key} より前の文字列を追加
                    if (matcher.start() > lastIndex) {
                        tempText.append(Component.literal(part.substring(lastIndex, matcher.start())).withStyle(currentFormatting));
                    }

                    // {translatable key} を追加
                    String key = matcher.group(1);
                    tempText.append(Component.translatable(key).withStyle(currentFormatting));

                    lastIndex = matcher.end();
                }

                // 最後の文字列を追加
                if (lastIndex < part.length()) {
                    tempText.append(Component.literal(part.substring(lastIndex)).withStyle(currentFormatting));
                }

                result.append(tempText);
                continue;
            }

            result.append(Component.literal(part).withStyle(currentFormatting));
        }

        return result;
    }

    public static String[] split(String text) {
        Matcher matcher = Pattern.compile("(?i)§[0-9a-fk-or]|[^§]+").matcher(text);
        List<String> parts = new ArrayList<>();

        while (matcher.find())
            parts.add(matcher.group());

        return parts.toArray(new String[0]);
    }
}

package net.pitan76.mcpitanlib.api.text;

import net.minecraft.text.LiteralText;
import net.minecraft.text.MutableText;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextConverter {

    public static MutableText convert(String string) {
        return convert(string, false);
    }

    /**
     * Convert string to MutableText with formatting
     *
     * @param text String
     * @param translatable boolean
     * @return MutableText
     */
    public static MutableText convert(String text, boolean translatable) {
        String[] splits = split(text);
        MutableText result = new LiteralText("");
        Formatting[] currentFormatting = {};

        for (String part : splits) {
            // Section
            if (part.startsWith("§") && part.length() == 2) {
                char code = part.charAt(1);
                // Reset
                if (code == 'r') {
                    currentFormatting = new Formatting[]{};
                    continue;
                }

                Formatting format = Formatting.byCode(code);
                if (format == null)
                    continue;

                // Bold, Italic, Underline, Strikethrough, Obfuscated
                if (code >= 'k' && code <= 'o') {
                    ArrayList<Formatting> list = new ArrayList<>(Arrays.asList(currentFormatting));
                    list.add(format);
                    currentFormatting = list.toArray(new Formatting[0]);
                    continue;
                }

                // Color
                currentFormatting = new Formatting[]{format};
                continue;
            }

            // Translatable
            if (translatable) {
                Pattern pattern = Pattern.compile("\\{(.+?)\\}");
                Matcher matcher = pattern.matcher(part);
                MutableText tempText = new LiteralText("");
                int lastIndex = 0;

                while (matcher.find()) {
                    // {translatable key} より前の文字列を追加
                    if (matcher.start() > lastIndex) {
                        tempText.append(new LiteralText(part.substring(lastIndex, matcher.start())).formatted(currentFormatting));
                    }

                    // {translatable key} を追加
                    String key = matcher.group(1);
                    tempText.append(new TranslatableText(key).formatted(currentFormatting));

                    lastIndex = matcher.end();
                }

                // 最後の文字列を追加
                if (lastIndex < part.length()) {
                    tempText.append(new LiteralText(part.substring(lastIndex)).formatted(currentFormatting));
                }

                result.append(tempText);
                continue;
            }

            result.append(new LiteralText(part).formatted(currentFormatting));
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

package net.pitan76.mcpitanlib.api.text;

import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.ArrayList;
import java.util.Arrays;
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
        MutableText result = Text.literal("");
        Formatting[] currentFormatting = {};

        for (String part : splits) {
            // Section
            if (part.startsWith("§")) {
                char code = part.charAt(1);
                // Reset
                if (code == 'r') {
                    currentFormatting = new Formatting[]{};
                    continue;
                }

                // Bold, Italic, Underline, Strikethrough, Obfuscated
                if (code >= 'k' && code <= 'o') {
                    ArrayList<Formatting> list = new ArrayList<>(Arrays.asList(currentFormatting));
                    list.add(Formatting.byCode(code));
                    currentFormatting = list.toArray(new Formatting[0]);
                    continue;
                }

                // Color
                currentFormatting = new Formatting[]{Formatting.byCode(code)};
                continue;
            }

            // Translatable
            if (translatable) {
                Pattern pattern = Pattern.compile("\\{(.+?)\\}");
                Matcher matcher = pattern.matcher(part);
                if (matcher.find()) {
                    String key = matcher.group(1);
                    result.append(Text.translatable(key).formatted(currentFormatting));
                    continue;
                }
            }

            result.append(Text.literal(part).formatted(currentFormatting));
        }

        return result;
    }

    public static String[] split(String text) {
        Matcher matcher = Pattern.compile("((?i)§[0-9a-fk-or])|([^§]+)").matcher(text);
        StringBuilder sb = new StringBuilder();

        while (matcher.find())
            sb.append(matcher.group()).append("\0"); // 区切り文字としてヌル文字を使う

        return sb.toString().split("\0"); // ヌル文字で分割
    }
}

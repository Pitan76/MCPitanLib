package net.pitan76.mcpitanlib.api.text;

import net.minecraft.network.chat.Style;

public class CompatStyle {
    private final Style style;

    public CompatStyle(Style style) {
        this.style = style;
    }

    public CompatStyle() {
        this(Style.EMPTY);
    }

    public static CompatStyle of(Style style) {
        return new CompatStyle(style);
    }

    public static CompatStyle of() {
        return new CompatStyle();
    }

    public CompatStyle withColor(CompatTextColor color) {
        return new CompatStyle(style.withColor(color.getRaw()));
    }

    public CompatStyle withColor(CompatFormatting formatting) {
        return new CompatStyle(style.withColor(formatting.getRaw()));
    }

    public CompatStyle withColor(int rgbColor) {
        return new CompatStyle(style.withColor(rgbColor));
    }

    public CompatStyle withBold(boolean bold) {
        return new CompatStyle(style.withBold(bold));
    }

    public CompatStyle withItalic(boolean italic) {
        return new CompatStyle(style.withItalic(italic));
    }

    public CompatStyle withUnderline(boolean underline) {
        return new CompatStyle(style.withUnderlined(underline));
    }

    public CompatStyle withStrikethrough(boolean strikethrough) {
        return new CompatStyle(style.withStrikethrough(strikethrough));
    }

    public CompatStyle withObfuscated(boolean obfuscated) {
        return new CompatStyle(style.withObfuscated(obfuscated));
    }

    public CompatTextColor getColor() {
        if (style.getColor() == null) return null;
        return new CompatTextColor(style.getColor());
    }

    public boolean isBold() {
        return style.isBold();
    }

    public boolean isItalic() {
        return style.isItalic();
    }

    public boolean isUnderlined() {
        return style.isUnderlined();
    }

    public boolean isStrikethrough() {
        return style.isStrikethrough();
    }

    public boolean isObfuscated() {
        return style.isObfuscated();
    }

    public boolean isEmpty() {
        return style.isEmpty();
    }

    public CompatStyle withFormatting(CompatFormatting formatting) {
        return withColor(formatting);
    }

    public Style getRaw() {
        return style;
    }

    @Override
    public int hashCode() {
        return style.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        CompatStyle that = (CompatStyle) obj;
        return style.equals(that.style);
    }
}

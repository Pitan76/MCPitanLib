package net.pitan76.mcpitanlib.api.util.client.widget;

import net.minecraft.client.gui.widget.TextFieldWidget;
import net.pitan76.mcpitanlib.api.client.render.handledscreen.RenderArgs;

public class TextFieldUtil {
    public static void setFocused(TextFieldWidget widget, boolean focused) {
        widget.setFocused(focused);
    }

    public static void render(TextFieldWidget widget, RenderArgs args) {
        widget.render(args.drawObjectDM.getContext(), args.mouseX, args.mouseY, args.delta);
    }

    public static void setEditable(TextFieldWidget widget, boolean editable) {
        widget.setEditable(editable);
    }

    public static void setMaxLength(TextFieldWidget widget, int maxLength) {
        widget.setMaxLength(maxLength);
    }

    public static void setSuggestion(TextFieldWidget widget, String suggestion) {
        widget.setSuggestion(suggestion);
    }
}

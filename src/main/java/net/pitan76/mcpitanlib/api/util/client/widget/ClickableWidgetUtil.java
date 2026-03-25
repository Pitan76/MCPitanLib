package net.pitan76.mcpitanlib.api.util.client.widget;

import net.minecraft.client.gui.widget.ClickableWidget;
import net.pitan76.mcpitanlib.api.client.render.handledscreen.RenderArgs;

public class ClickableWidgetUtil {
    public static void render(ClickableWidget widget, RenderArgs args) {
        widget.render(args.drawObjectDM.getContext(), args.mouseX, args.mouseY, args.delta);
    }
}

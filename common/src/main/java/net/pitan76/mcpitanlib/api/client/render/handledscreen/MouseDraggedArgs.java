package net.pitan76.mcpitanlib.api.client.render.handledscreen;

import net.minecraft.client.input.MouseButtonInfo;

public class MouseDraggedArgs extends MouseButtonArgs {

    public final double dx;
    public final double dy;

    public MouseDraggedArgs(double mouseX, double mouseY, MouseButtonInfo buttonInfo, double dx, double dy) {
        super(mouseX, mouseY, buttonInfo);
        this.dx = dx;
        this.dy = dy;
    }

    public MouseDraggedArgs(double mouseX, double mouseY, int button, double dx, double dy) {
        super(mouseX, mouseY, button);
        this.dx = dx;
        this.dy = dy;
    }

    public double getDeltaX() {
        return dx;
    }

    public double getDeltaY() {
        return dy;
    }
}

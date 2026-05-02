package net.pitan76.mcpitanlib.api.client.render.handledscreen;

public class MouseDraggedArgs extends MouseButtonArgs {

    public final double dx;
    public final double dy;

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

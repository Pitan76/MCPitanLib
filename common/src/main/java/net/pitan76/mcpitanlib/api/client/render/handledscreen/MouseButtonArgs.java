package net.pitan76.mcpitanlib.api.client.render.handledscreen;

public class MouseButtonArgs {
    public final double mouseX, mouseY;
    public int button;

    public MouseButtonArgs(double mouseX, double mouseY, int button) {
        this.mouseX = mouseX;
        this.mouseY = mouseY;
        this.button = button;
    }

    public double getX() {
        return mouseX;
    }

    public double getY() {
        return mouseY;
    }

    public int getButton() {
        return button;
    }
}

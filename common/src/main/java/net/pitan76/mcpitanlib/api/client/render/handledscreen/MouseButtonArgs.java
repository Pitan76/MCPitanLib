package net.pitan76.mcpitanlib.api.client.render.handledscreen;

import net.minecraft.client.input.MouseButtonInfo;

public class MouseButtonArgs {
    public final double mouseX, mouseY;
    public MouseButtonInfo buttonInfo = null;

    public MouseButtonArgs(double mouseX, double mouseY, MouseButtonInfo buttonInfo) {
        this.mouseX = mouseX;
        this.mouseY = mouseY;
        this.buttonInfo = buttonInfo;
    }

    public MouseButtonArgs(double mouseX, double mouseY, int button) {
        this.mouseX = mouseX;
        this.mouseY = mouseY;
        this.buttonInfo = new MouseButtonInfo(button, 0);
    }

    public double getX() {
        return mouseX;
    }

    public double getY() {
        return mouseY;
    }

    public int getButton() {
        if (getButtonInfo() == null) return -1;
        return getButtonInfo().button();
    }

    @Deprecated
    public MouseButtonInfo getButtonInfo() {
        return buttonInfo;
    }
}

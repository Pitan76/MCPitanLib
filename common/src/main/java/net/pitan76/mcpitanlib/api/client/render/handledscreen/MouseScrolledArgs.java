package net.pitan76.mcpitanlib.api.client.render.handledscreen;

public class MouseScrolledArgs {
    private final double mouseX;
    private final double mouseY;
    private final double scrollX;
    private final double scrollY;
    private final double amount;

    public MouseScrolledArgs(double mouseX, double mouseY, double scrollX, double scrollY) {
        this.mouseX = mouseX;
        this.mouseY = mouseY;
        this.scrollX = scrollX;
        this.scrollY = scrollY;
        this.amount = Math.sqrt(scrollX * scrollX + scrollY * scrollY);
    }

    public double getMouseX() {
        return mouseX;
    }

    public double getMouseY() {
        return mouseY;
    }

    public double getScrollX() {
        return scrollX;
    }

    public double getScrollY() {
        return scrollY;
    }

    public double getAmount() {
        return amount;
    }
}

package net.pitan76.mcpitanlib.api.client.render.handledscreen;

import net.minecraft.client.input.MouseInput;

public class MouseClickedArgs extends MouseButtonArgs {
    public boolean doubleClick;

    public MouseClickedArgs(double mouseX, double mouseY, MouseInput buttonInfo, boolean doubleClick) {
        super(mouseX, mouseY, buttonInfo);
        this.doubleClick = doubleClick;
    }

    public MouseClickedArgs(double mouseX, double mouseY, int button, boolean doubleClick) {
        super(mouseX, mouseY, button);
        this.doubleClick = doubleClick;
    }

    public MouseClickedArgs(double mouseX, double mouseY, int button) {
        this(mouseX, mouseY, button, false);
    }

    @Deprecated
    public boolean isDoubleClick() {
        return doubleClick;
    }
}

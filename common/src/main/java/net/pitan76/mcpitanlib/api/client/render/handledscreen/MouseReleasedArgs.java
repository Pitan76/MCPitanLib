package net.pitan76.mcpitanlib.api.client.render.handledscreen;

import net.minecraft.client.input.MouseInput;

public class MouseReleasedArgs extends MouseButtonArgs {

    public MouseReleasedArgs(double mouseX, double mouseY, MouseInput buttonInfo) {
        super(mouseX, mouseY, buttonInfo);
    }

    public MouseReleasedArgs(double mouseX, double mouseY, int button) {
        super(mouseX, mouseY, button);
    }
}

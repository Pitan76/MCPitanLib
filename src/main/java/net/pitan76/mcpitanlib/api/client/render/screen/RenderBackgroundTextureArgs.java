package net.pitan76.mcpitanlib.api.client.render.screen;

import net.pitan76.mcpitanlib.api.client.render.DrawObjectDM;
import net.pitan76.mcpitanlib.api.client.render.handledscreen.RenderArgs;

public class RenderBackgroundTextureArgs {
    public DrawObjectDM drawObjectDM;
    int vOffset;

    public RenderBackgroundTextureArgs(DrawObjectDM drawObjectDM, int vOffset) {
        this.drawObjectDM = drawObjectDM;
        this.vOffset = vOffset;
    }

    public RenderBackgroundTextureArgs(RenderArgs args) {
        this(args.drawObjectDM, 0);
    }

    public DrawObjectDM getDrawObjectDM() {
        return drawObjectDM;
    }

    public int getvOffset() {
        return vOffset;
    }

    public void setDrawObjectDM(DrawObjectDM drawObjectDM) {
        this.drawObjectDM = drawObjectDM;
    }

    public void setvOffset(int vOffset) {
        this.vOffset = vOffset;
    }
}

package net.pitan76.mcpitanlib.core.util;

import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.util.math.MatrixStack;

public class DrawableHelper1192 extends DrawableHelper {

    protected static DrawableHelper1192 drawableHelper = new DrawableHelper1192();

    @Override
    protected void fillGradient(MatrixStack matrices, int startX, int startY, int endX, int endY, int colorStart, int colorEnd) {
        super.fillGradient(matrices, startX, startY, endX, endY, colorStart, colorEnd);
    }

    public static DrawableHelper fillGradient2(MatrixStack matrices, int startX, int startY, int endX, int endY, int colorStart, int colorEnd) {
        drawableHelper.fillGradient(matrices, startX, startY, endX, endY, colorStart, colorEnd);
        return drawableHelper;
    }
}

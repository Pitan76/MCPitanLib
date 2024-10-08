package net.pitan76.mcpitanlib.guilib.api.render;

import net.pitan76.mcpitanlib.api.client.render.DrawObjectDM;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;
import net.pitan76.mcpitanlib.api.util.client.RenderUtil.RendererUtil;
import net.pitan76.mcpitanlib.guilib.GuiTextures;

public class PartsRenderer {
    public static void drawBottom2TopProgressBar(DrawObjectDM drawObjectDM, int x, int y, int u, int v, int u2, int v2, int width, int height, int percentage, CompatIdentifier texture) {
        RendererUtil.drawTexture(drawObjectDM, texture, x, y, u, v, width, height);

        if (percentage > 0) {
            int progress = (int) ((double) percentage / 100 * height);
            RendererUtil.drawTexture(drawObjectDM, texture, x, y + height - progress, u2, v2 + height - progress, width, progress);
        }
    }

    public static void drawBottom2TopProgressBar(DrawObjectDM drawObjectDM, int x, int y, int u1, int v1, int u2, int v2, int width, int height, double value, double maxValue, CompatIdentifier texture) {
        int percentage = (int) (value / maxValue * 100);
        drawBottom2TopProgressBar(drawObjectDM, x, y, u1, v1, u2, v2, width, height, percentage, texture);
    }

    public static void drawTop2BottomProgressBar(DrawObjectDM drawObjectDM, int x, int y, int u, int v, int u2, int v2, int width, int height, int percentage, CompatIdentifier texture) {
        RendererUtil.drawTexture(drawObjectDM, texture, x, y, u, v, width, height);

        if (percentage > 0) {
            int progress = (int) ((double) percentage / 100 * height);
            RendererUtil.drawTexture(drawObjectDM, texture, x, y, u2, v2, width, progress);
        }
    }

    public static void drawTop2BottomProgressBar(DrawObjectDM drawObjectDM, int x, int y, int u1, int v1, int u2, int v2, int width, int height, double value, double maxValue, CompatIdentifier texture) {
        int percentage = (int) (value / maxValue * 100);
        drawTop2BottomProgressBar(drawObjectDM, x, y, u1, v1, u2, v2, width, height, percentage, texture);
    }

    public static void drawLeft2RightProgressBar(DrawObjectDM drawObjectDM, int x, int y, int u, int v, int u2, int v2, int width, int height, int percentage, CompatIdentifier texture) {
        RendererUtil.drawTexture(drawObjectDM, texture, x, y, u, v, width, height);

        if (percentage > 0) {
            int progress = (int) ((double) percentage / 100 * width);
            RendererUtil.drawTexture(drawObjectDM, texture, x, y, u2, v2, progress, height);
        }
    }

    public static void drawLeft2RightProgressBar(DrawObjectDM drawObjectDM, int x, int y, int u1, int v1, int u2, int v2, int width, int height, double value, double maxValue, CompatIdentifier texture) {
        int percentage = (int) (value / maxValue * 100);
        drawLeft2RightProgressBar(drawObjectDM, x, y, u1, v1, u2, v2, width, height, percentage, texture);
    }

    public static void drawRight2LeftProgressBar(DrawObjectDM drawObjectDM, int x, int y, int u, int v, int u2, int v2, int width, int height, int percentage, CompatIdentifier texture) {
        RendererUtil.drawTexture(drawObjectDM, texture, x, y, u, v, width, height);

        if (percentage > 0) {
            int progress = (int) ((double) percentage / 100 * width);
            RendererUtil.drawTexture(drawObjectDM, texture, x + width - progress, y, u2 + width - progress, v2, progress, height);
        }
    }

    public static void drawRight2LeftProgressBar(DrawObjectDM drawObjectDM, int x, int y, int u1, int v1, int u2, int v2, int width, int height, double value, double maxValue, CompatIdentifier texture) {
        int percentage = (int) (value / maxValue * 100);
        drawRight2LeftProgressBar(drawObjectDM, x, y, u1, v1, u2, v2, width, height, percentage, texture);
    }

    public static void drawBurningBar(DrawObjectDM drawObjectDM, int x, int y, int burnTime, int maxBurnTime) {
        drawBottom2TopProgressBar(drawObjectDM, x, y, 0, 166, 0, 182, 16, 14, burnTime, maxBurnTime, GuiTextures.BASE_FURNACE_BACKGROUND);
    }

    public static void drawHorizontalArrowBar(DrawObjectDM drawObjectDM, int x, int y, double value, double maxValue) {
        drawLeft2RightProgressBar(drawObjectDM, x, y, 0, 166, 16, 182, 24, 16, value, maxValue, GuiTextures.BASE_FURNACE_BACKGROUND);
    }
}

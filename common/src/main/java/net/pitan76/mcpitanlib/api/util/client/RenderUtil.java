package net.pitan76.mcpitanlib.api.util.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.resources.Identifier;

public class RenderUtil {
    public static void setShaderToPositionTexProgram() {
        // -1.21.4
    }

    public static void setShaderColor(float red, float green, float blue, float alpha) {
        //RenderSystem.setShaderColor(red, green, blue, alpha);
    }

    public static void setShaderTexture(int texture, Identifier id) {
        // -1.21.4
    }

    public static void enableDepthTest() {
        // -1.21.4
    }

    public static void enableTexture() {
        // ～1.19.2
    }

    public static void disableTexture() {
        // ～1.19.2
    }

    public static class RendererUtil extends ScreenUtil.RendererUtil {}
}
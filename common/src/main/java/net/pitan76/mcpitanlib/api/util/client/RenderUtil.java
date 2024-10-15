package net.pitan76.mcpitanlib.api.util.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gl.ShaderProgram;
import net.minecraft.client.gl.ShaderProgramKey;
import net.minecraft.client.gl.ShaderProgramKeys;
import net.minecraft.client.gui.screen.ingame.AbstractFurnaceScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.util.Identifier;

public class RenderUtil {
    public static void setShaderToPositionTexProgram() {
        try (ShaderProgram program = RenderSystem.setShader(ShaderProgramKeys.POSITION_TEX)) {
            RenderSystem.setShader(program);
        }
    }

    public static void setShaderColor(float red, float green, float blue, float alpha) {
        RenderSystem.setShaderColor(red, green, blue, alpha);
    }

    public static void setShaderTexture(int texture, Identifier id) {
        RenderSystem.setShaderTexture(texture, id);
    }

    public static void enableDepthTest() {
        RenderSystem.enableDepthTest();
    }

    public static void enableTexture() {
        // ～1.19.2
    }

    public static void disableTexture() {
        // ～1.19.2
    }

    public static class RendererUtil extends ScreenUtil.RendererUtil {}
}
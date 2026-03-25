package net.pitan76.mcpitanlib.api.util.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.pitan76.mcpitanlib.api.client.gui.widget.CompatibleTexturedButtonWidget;
import net.pitan76.mcpitanlib.api.client.gui.widget.RedrawableTexturedButtonWidget;
import net.pitan76.mcpitanlib.api.client.render.DrawObjectDM;
import net.pitan76.mcpitanlib.api.text.TextComponent;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@Environment(EnvType.CLIENT)
public class ScreenUtil {
    public static void setBackground(Identifier GUI, float f, float g, float h, float i) {
        RenderUtil.setShaderToPositionTexProgram();
        RenderUtil.setShaderColor(f, g, h, i);
        RenderUtil.setShaderTexture(0, GUI);

        // GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        // MinecraftClient.getInstance().getTextureManager().bindTexture(GUI);
    }

    public static void setBackground(Identifier GUI) {
        setBackground(GUI, 1.0F, 1.0F, 1.0F, 1.0F);
    }

    // ～1.19.2
    public static void setRepeatEvents(boolean isRepeatEvents) {
    }

    // ～1.19.4
    public static void setPassEvents(Screen screen, boolean isPassEvents) {
    }

    public static Button createButtonWidget(int x, int y, int width, int height, Component message, Button.OnPress onPress) {
        return createButtonWidget(x, y, width, height, message, onPress, null);
    }

    public static Button createButtonWidget(int x, int y, int width, int height, Component message, Button.OnPress onPress, @Nullable Button.CreateNarration tooltipSupplier) {
        Button.Builder builder = Button.builder(message, onPress).bounds(x, y, width , height);
        if (tooltipSupplier != null)
            builder.createNarration(tooltipSupplier);

        return builder.build();
    }

    public static CompatibleTexturedButtonWidget createTexturedButtonWidget(int x, int y, int width, int height, int u, int v, Identifier texture, Button.OnPress pressAction) {
        return createTexturedButtonWidget(x, y, width, height, u, v, height, texture, pressAction);
    }

    public static CompatibleTexturedButtonWidget createTexturedButtonWidget(int x, int y, int width, int height, int u, int v, int hoveredVOffset, Identifier texture, Button.OnPress pressAction) {
        return createTexturedButtonWidget(x, y, width, height, u, v, hoveredVOffset, texture, 256, 256, pressAction);
    }

    public static CompatibleTexturedButtonWidget createTexturedButtonWidget(int x, int y, int width, int height, int u, int v, int hoveredVOffset, Identifier texture, int textureWidth, int textureHeight, Button.OnPress pressAction) {
        return createTexturedButtonWidget(x, y, width, height, u, v, hoveredVOffset, texture, textureWidth, textureHeight, pressAction, Texts.empty());
    }

    public static CompatibleTexturedButtonWidget createTexturedButtonWidget(int x, int y, int width, int height, int u, int v, int hoveredVOffset, Identifier texture, int textureWidth, int textureHeight, Button.OnPress pressAction, Component message) {
        return new CompatibleTexturedButtonWidget(x, y, width, height, u, v, hoveredVOffset, texture, textureWidth, textureHeight, pressAction, message);
    }

    public static RedrawableTexturedButtonWidget createRedrawableTexturedButtonWidget(int x, int y, int width, int height, int u, int v, Identifier texture, Button.OnPress pressAction) {
        return createRedrawableTexturedButtonWidget(x, y, width, height, u, v, height, texture, pressAction);
    }

    public static RedrawableTexturedButtonWidget createRedrawableTexturedButtonWidget(int x, int y, int width, int height, int u, int v, int hoveredVOffset, Identifier texture, Button.OnPress pressAction) {
        return createRedrawableTexturedButtonWidget(x, y, width, height, u, v, hoveredVOffset, texture, 256, 256, pressAction);
    }

    public static RedrawableTexturedButtonWidget createRedrawableTexturedButtonWidget(int x, int y, int width, int height, int u, int v, int hoveredVOffset, Identifier texture, int textureWidth, int textureHeight, Button.OnPress pressAction) {
        return createRedrawableTexturedButtonWidget(x, y, width, height, u, v, hoveredVOffset, texture, textureWidth, textureHeight, pressAction, Texts.empty());
    }

    public static RedrawableTexturedButtonWidget createRedrawableTexturedButtonWidget(int x, int y, int width, int height, int u, int v, int hoveredVOffset, Identifier texture, int textureWidth, int textureHeight, Button.OnPress pressAction, Component message) {
        return new RedrawableTexturedButtonWidget(x, y, width, height, u, v, hoveredVOffset, texture, textureWidth, textureHeight, pressAction, message);
    }

    public static CompatibleTexturedButtonWidget createTexturedButtonWidget(int x, int y, int width, int height, int u, int v, CompatIdentifier texture, Button.OnPress pressAction) {
        return createTexturedButtonWidget(x, y, width, height, u, v, height, texture.toMinecraft(), pressAction);
    }

    public static CompatibleTexturedButtonWidget createTexturedButtonWidget(int x, int y, int width, int height, int u, int v, int hoveredVOffset, CompatIdentifier texture, Button.OnPress pressAction) {
        return createTexturedButtonWidget(x, y, width, height, u, v, hoveredVOffset, texture.toMinecraft(), pressAction);
    }

    public static CompatibleTexturedButtonWidget createTexturedButtonWidget(int x, int y, int width, int height, int u, int v, int hoveredVOffset, CompatIdentifier texture, int textureWidth, int textureHeight, Button.OnPress pressAction) {
        return createTexturedButtonWidget(x, y, width, height, u, v, hoveredVOffset, texture.toMinecraft(), textureWidth, textureHeight, pressAction);
    }

    public static CompatibleTexturedButtonWidget createTexturedButtonWidget(int x, int y, int width, int height, int u, int v, int hoveredVOffset, CompatIdentifier texture, int textureWidth, int textureHeight, Button.OnPress pressAction, Component message) {
        return createTexturedButtonWidget(x, y, width, height, u, v, hoveredVOffset, texture.toMinecraft(), textureWidth, textureHeight, pressAction, message);
    }

    public static RedrawableTexturedButtonWidget createRedrawableTexturedButtonWidget(int x, int y, int width, int height, int u, int v, CompatIdentifier texture, Button.OnPress pressAction) {
        return createRedrawableTexturedButtonWidget(x, y, width, height, u, v, texture.toMinecraft(), pressAction);
    }

    public static RedrawableTexturedButtonWidget createRedrawableTexturedButtonWidget(int x, int y, int width, int height, int u, int v, int hoveredVOffset, CompatIdentifier texture, Button.OnPress pressAction) {
        return createRedrawableTexturedButtonWidget(x, y, width, height, u, v, hoveredVOffset, texture.toMinecraft(), pressAction);
    }

    public static RedrawableTexturedButtonWidget createRedrawableTexturedButtonWidget(int x, int y, int width, int height, int u, int v, int hoveredVOffset, CompatIdentifier texture, int textureWidth, int textureHeight, Button.OnPress pressAction) {
        return createRedrawableTexturedButtonWidget(x, y, width, height, u, v, hoveredVOffset, texture.toMinecraft(), textureWidth, textureHeight, pressAction);
    }

    public static RedrawableTexturedButtonWidget createRedrawableTexturedButtonWidget(int x, int y, int width, int height, int u, int v, int hoveredVOffset, CompatIdentifier texture, int textureWidth, int textureHeight, Button.OnPress pressAction, Component message) {
        return createRedrawableTexturedButtonWidget(x, y, width, height, u, v, hoveredVOffset, texture.toMinecraft(), textureWidth, textureHeight, pressAction, message);
    }



    public static class Texts {
        public static Component empty() {
            return CommonComponents.EMPTY;
        }
    }

    public static class TextFieldUtil extends net.pitan76.mcpitanlib.api.util.client.widget.TextFieldUtil {
        // Nothing
    }

    public static class ClickableWidgetUtil extends net.pitan76.mcpitanlib.api.util.client.widget.ClickableWidgetUtil {
        // Nothing
    }

    public static class RendererUtil {
        public static int drawText(Font renderer, DrawObjectDM drawObjectDM, Component text, int x, int y, int color) {
            drawObjectDM.getContext().drawString(renderer, text, x, y, fixColor(color), false);
            return -1;
        }

        public static int drawText(Font renderer, DrawObjectDM drawObjectDM, String text, int x, int y, int color) {
            drawObjectDM.getContext().drawString(renderer, text, x, y, fixColor(color), false);
            return -1;
        }

        public static int drawText(Font renderer, DrawObjectDM drawObjectDM, FormattedCharSequence text, int x, int y, int color) {
            drawObjectDM.getContext().drawString(renderer, text, x, y, fixColor(color), false);
            return -1;
        }

        public static void drawTexture(DrawObjectDM drawObjectDM, Identifier texture, int x, int y, float u, float v, int width, int height, int textureWidth, int textureHeight) {
            drawObjectDM.getContext().blit(RenderPipelines.GUI_TEXTURED, texture, x, y, u, v, width, height, textureWidth, textureHeight);
        }

        public static void drawTexture(DrawObjectDM drawObjectDM, Identifier texture, int x, int y, float u, float v, int width, int height) {
            drawObjectDM.getContext().blit(RenderPipelines.GUI_TEXTURED, texture, x, y, u, v, width, height, 256, 256);
        }

        public static Font getTextRenderer() {
            return Minecraft.getInstance().font;
        }

        public static void drawTexture(DrawObjectDM drawObjectDM, CompatIdentifier texture, int x, int y, float u, float v, int width, int height, int textureWidth, int textureHeight) {
            drawTexture(drawObjectDM, texture.toMinecraft(), x, y, u, v, width, height, textureWidth, textureHeight);
        }

        public static void drawTexture(DrawObjectDM drawObjectDM, CompatIdentifier texture, int x, int y, float u, float v, int width, int height) {
            drawTexture(drawObjectDM, texture.toMinecraft(), x, y, u, v, width, height);
        }

        public static int drawText(Font renderer, DrawObjectDM drawObjectDM, TextComponent text, int x, int y, int color) {
            return drawText(renderer, drawObjectDM, text.getText(), x, y, color);
        }

        public static int drawText(Font renderer, DrawObjectDM drawObjectDM, Component text, int x, int y) {
            return drawText(renderer, drawObjectDM, text, x, y, -12566464);
        }

        public static int drawText(Font renderer, DrawObjectDM drawObjectDM, TextComponent text, int x, int y) {
            return drawText(renderer, drawObjectDM, text, x, y, -12566464);
        }

        public static void drawTooltip(DrawObjectDM drawObjectDM, Font textRenderer, Component text, int x, int y) {
            drawObjectDM.getContext().setTooltipForNextFrame(textRenderer, text, x, y);
        }

        public static void drawTooltip(DrawObjectDM drawObjectDM, Font textRenderer, List<Component> texts, int x, int y) {
            drawObjectDM.getContext().setComponentTooltipForNextFrame(textRenderer, texts, x, y);
        }

        public static void drawBorder(DrawObjectDM drawObjectDM, int x, int y, int width, int height, int color) {
            // TODO: drawBorderを一時的に廃止
            //drawObjectDM.getContext().drawBorder(x, y, width, height, color);
        }

        public static void drawTooltip(DrawObjectDM drawObjectDM, Component text, int x, int y) {
            drawObjectDM.getContext().setTooltipForNextFrame(getTextRenderer(), text, x, y);
        }

        public static void drawTooltip(DrawObjectDM drawObjectDM, TextComponent text, int x, int y) {
            drawTooltip(drawObjectDM, text.getText(), x, y);
        }

        public static void drawTooltip(DrawObjectDM drawObjectDM, List<Component> texts, int x, int y) {
            drawObjectDM.getContext().setComponentTooltipForNextFrame(getTextRenderer(), texts, x, y);
        }
    }

    public static int getWidth(Component text) {
        return RendererUtil.getTextRenderer().width(text);
    }

    // RGBからARGBに変換する (1.21.6からは透明になるため)
    private static int fixColor(int color) {
        if ((color >>> 24) == 0) {
            return 0xFF000000 | color;
        } else {
            return color;
        }
    }
}

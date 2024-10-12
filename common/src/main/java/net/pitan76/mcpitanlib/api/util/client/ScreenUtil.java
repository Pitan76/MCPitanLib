package net.pitan76.mcpitanlib.api.util.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.OrderedText;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
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

    public static ButtonWidget createButtonWidget(int x, int y, int width, int height, Text message, ButtonWidget.PressAction onPress) {
        return createButtonWidget(x, y, width, height, message, onPress, null);
    }

    public static ButtonWidget createButtonWidget(int x, int y, int width, int height, Text message, ButtonWidget.PressAction onPress, @Nullable ButtonWidget.NarrationSupplier tooltipSupplier) {
        ButtonWidget.Builder builder = ButtonWidget.builder(message, onPress).dimensions(x, y, width , height);
        if (tooltipSupplier != null)
            builder.narrationSupplier(tooltipSupplier);

        return builder.build();
    }

    public static CompatibleTexturedButtonWidget createTexturedButtonWidget(int x, int y, int width, int height, int u, int v, Identifier texture, ButtonWidget.PressAction pressAction) {
        return createTexturedButtonWidget(x, y, width, height, u, v, height, texture, pressAction);
    }

    public static CompatibleTexturedButtonWidget createTexturedButtonWidget(int x, int y, int width, int height, int u, int v, int hoveredVOffset, Identifier texture, ButtonWidget.PressAction pressAction) {
        return createTexturedButtonWidget(x, y, width, height, u, v, hoveredVOffset, texture, 256, 256, pressAction);
    }

    public static CompatibleTexturedButtonWidget createTexturedButtonWidget(int x, int y, int width, int height, int u, int v, int hoveredVOffset, Identifier texture, int textureWidth, int textureHeight, ButtonWidget.PressAction pressAction) {
        return createTexturedButtonWidget(x, y, width, height, u, v, hoveredVOffset, texture, textureWidth, textureHeight, pressAction, Texts.empty());
    }

    public static CompatibleTexturedButtonWidget createTexturedButtonWidget(int x, int y, int width, int height, int u, int v, int hoveredVOffset, Identifier texture, int textureWidth, int textureHeight, ButtonWidget.PressAction pressAction, Text message) {
        return new CompatibleTexturedButtonWidget(x, y, width, height, u, v, hoveredVOffset, texture, textureWidth, textureHeight, pressAction, message);
    }

    public static RedrawableTexturedButtonWidget createRedrawableTexturedButtonWidget(int x, int y, int width, int height, int u, int v, Identifier texture, ButtonWidget.PressAction pressAction) {
        return createRedrawableTexturedButtonWidget(x, y, width, height, u, v, height, texture, pressAction);
    }

    public static RedrawableTexturedButtonWidget createRedrawableTexturedButtonWidget(int x, int y, int width, int height, int u, int v, int hoveredVOffset, Identifier texture, ButtonWidget.PressAction pressAction) {
        return createRedrawableTexturedButtonWidget(x, y, width, height, u, v, hoveredVOffset, texture, 256, 256, pressAction);
    }

    public static RedrawableTexturedButtonWidget createRedrawableTexturedButtonWidget(int x, int y, int width, int height, int u, int v, int hoveredVOffset, Identifier texture, int textureWidth, int textureHeight, ButtonWidget.PressAction pressAction) {
        return createRedrawableTexturedButtonWidget(x, y, width, height, u, v, hoveredVOffset, texture, textureWidth, textureHeight, pressAction, Texts.empty());
    }

    public static RedrawableTexturedButtonWidget createRedrawableTexturedButtonWidget(int x, int y, int width, int height, int u, int v, int hoveredVOffset, Identifier texture, int textureWidth, int textureHeight, ButtonWidget.PressAction pressAction, Text message) {
        return new RedrawableTexturedButtonWidget(x, y, width, height, u, v, hoveredVOffset, texture, textureWidth, textureHeight, pressAction, message);
    }

    public static CompatibleTexturedButtonWidget createTexturedButtonWidget(int x, int y, int width, int height, int u, int v, CompatIdentifier texture, ButtonWidget.PressAction pressAction) {
        return createTexturedButtonWidget(x, y, width, height, u, v, height, texture.toMinecraft(), pressAction);
    }

    public static CompatibleTexturedButtonWidget createTexturedButtonWidget(int x, int y, int width, int height, int u, int v, int hoveredVOffset, CompatIdentifier texture, ButtonWidget.PressAction pressAction) {
        return createTexturedButtonWidget(x, y, width, height, u, v, hoveredVOffset, texture.toMinecraft(), pressAction);
    }

    public static CompatibleTexturedButtonWidget createTexturedButtonWidget(int x, int y, int width, int height, int u, int v, int hoveredVOffset, CompatIdentifier texture, int textureWidth, int textureHeight, ButtonWidget.PressAction pressAction) {
        return createTexturedButtonWidget(x, y, width, height, u, v, hoveredVOffset, texture.toMinecraft(), textureWidth, textureHeight, pressAction);
    }

    public static CompatibleTexturedButtonWidget createTexturedButtonWidget(int x, int y, int width, int height, int u, int v, int hoveredVOffset, CompatIdentifier texture, int textureWidth, int textureHeight, ButtonWidget.PressAction pressAction, Text message) {
        return createTexturedButtonWidget(x, y, width, height, u, v, hoveredVOffset, texture.toMinecraft(), textureWidth, textureHeight, pressAction, message);
    }

    public static RedrawableTexturedButtonWidget createRedrawableTexturedButtonWidget(int x, int y, int width, int height, int u, int v, CompatIdentifier texture, ButtonWidget.PressAction pressAction) {
        return createRedrawableTexturedButtonWidget(x, y, width, height, u, v, texture.toMinecraft(), pressAction);
    }

    public static RedrawableTexturedButtonWidget createRedrawableTexturedButtonWidget(int x, int y, int width, int height, int u, int v, int hoveredVOffset, CompatIdentifier texture, ButtonWidget.PressAction pressAction) {
        return createRedrawableTexturedButtonWidget(x, y, width, height, u, v, hoveredVOffset, texture.toMinecraft(), pressAction);
    }

    public static RedrawableTexturedButtonWidget createRedrawableTexturedButtonWidget(int x, int y, int width, int height, int u, int v, int hoveredVOffset, CompatIdentifier texture, int textureWidth, int textureHeight, ButtonWidget.PressAction pressAction) {
        return createRedrawableTexturedButtonWidget(x, y, width, height, u, v, hoveredVOffset, texture.toMinecraft(), textureWidth, textureHeight, pressAction);
    }

    public static RedrawableTexturedButtonWidget createRedrawableTexturedButtonWidget(int x, int y, int width, int height, int u, int v, int hoveredVOffset, CompatIdentifier texture, int textureWidth, int textureHeight, ButtonWidget.PressAction pressAction, Text message) {
        return createRedrawableTexturedButtonWidget(x, y, width, height, u, v, hoveredVOffset, texture.toMinecraft(), textureWidth, textureHeight, pressAction, message);
    }



    public static class Texts {
        public static Text empty() {
            return ScreenTexts.EMPTY;
        }
    }

    public static class TextFieldUtil extends net.pitan76.mcpitanlib.api.util.client.widget.TextFieldUtil {
        // Nothing
    }

    public static class ClickableWidgetUtil extends net.pitan76.mcpitanlib.api.util.client.widget.ClickableWidgetUtil {
        // Nothing
    }

    public static class RendererUtil {
        public static int drawText(TextRenderer renderer, DrawObjectDM drawObjectDM, Text text, int x, int y, int color) {
            return drawObjectDM.getContext().drawText(renderer, text, x, y, color, false);
        }

        public static int drawText(TextRenderer renderer, DrawObjectDM drawObjectDM, String text, int x, int y, int color) {
            return drawObjectDM.getContext().drawText(renderer, text, x, y, color, false);
        }

        public static int drawText(TextRenderer renderer, DrawObjectDM drawObjectDM, OrderedText text, int x, int y, int color) {
            return drawObjectDM.getContext().drawText(renderer, text, x, y, color, false);
        }

        public static void drawTexture(DrawObjectDM drawObjectDM, Identifier texture, int x, int y, float u, float v, int width, int height, int textureWidth, int textureHeight) {
            drawObjectDM.getContext().drawTexture(texture, x, y, u, v, width, height, textureWidth, textureHeight);
        }

        public static void drawTexture(DrawObjectDM drawObjectDM, Identifier texture, int x, int y, float u, float v, int width, int height) {
            drawObjectDM.getContext().drawTexture(texture, x, y, u, v, width, height, 256, 256);
        }

        public static TextRenderer getTextRenderer() {
            return MinecraftClient.getInstance().textRenderer;
        }

        public static void drawTexture(DrawObjectDM drawObjectDM, CompatIdentifier texture, int x, int y, float u, float v, int width, int height, int textureWidth, int textureHeight) {
            drawTexture(drawObjectDM, texture.toMinecraft(), x, y, u, v, width, height, textureWidth, textureHeight);
        }

        public static void drawTexture(DrawObjectDM drawObjectDM, CompatIdentifier texture, int x, int y, float u, float v, int width, int height) {
            drawTexture(drawObjectDM, texture.toMinecraft(), x, y, u, v, width, height);
        }

        public static int drawText(TextRenderer renderer, DrawObjectDM drawObjectDM, TextComponent text, int x, int y, int color) {
            return drawText(renderer, drawObjectDM, text.getText(), x, y, color);
        }

        public static int drawText(TextRenderer renderer, DrawObjectDM drawObjectDM, Text text, int x, int y) {
            return drawText(renderer, drawObjectDM, text, x, y, 4210752);
        }

        public static int drawText(TextRenderer renderer, DrawObjectDM drawObjectDM, TextComponent text, int x, int y) {
            return drawText(renderer, drawObjectDM, text, x, y, 4210752);
        }

        public static void drawTooltip(DrawObjectDM drawObjectDM, TextRenderer textRenderer, Text text, int x, int y) {
            drawObjectDM.getContext().drawTooltip(textRenderer, text, x, y);
        }

        public static void drawTooltip(DrawObjectDM drawObjectDM, TextRenderer textRenderer, List<Text> texts, int x, int y) {
            drawObjectDM.getContext().drawTooltip(textRenderer, texts, x, y);
        }

        public static void drawBorder(DrawObjectDM drawObjectDM, int x, int y, int width, int height, int color) {
            drawObjectDM.getContext().drawBorder(x, y, width, height, color);
        }

        public static void drawTooltip(DrawObjectDM drawObjectDM, Text text, int x, int y) {
            drawObjectDM.getContext().drawTooltip(getTextRenderer(), text, x, y);
        }

        public static void drawTooltip(DrawObjectDM drawObjectDM, TextComponent text, int x, int y) {
            drawTooltip(drawObjectDM, text.getText(), x, y);
        }

        public static void drawTooltip(DrawObjectDM drawObjectDM, List<Text> texts, int x, int y) {
            drawObjectDM.getContext().drawTooltip(getTextRenderer(), texts, x, y);
        }
    }

    public static int getWidth(Text text) {
        return RendererUtil.getTextRenderer().getWidth(text);
    }
}

package net.pitan76.mcpitanlib.api.util.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.tooltip.TooltipComponent;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.render.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.OrderedText;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.ColorHelper;
import net.minecraft.util.math.Matrix4f;
import net.pitan76.mcpitanlib.api.client.gui.screen.CompatInventoryScreen;
import net.pitan76.mcpitanlib.api.client.gui.widget.CompatibleTexturedButtonWidget;
import net.pitan76.mcpitanlib.api.client.gui.widget.RedrawableTexturedButtonWidget;
import net.pitan76.mcpitanlib.api.client.render.DrawObjectDM;
import net.pitan76.mcpitanlib.api.text.TextComponent;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;

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
        MinecraftClient.getInstance().keyboard.setRepeatEvents(isRepeatEvents);
    }

    // ～1.19.4
    public static void setPassEvents(Screen screen, boolean isPassEvents) {
        screen.passEvents = isPassEvents;
    }

    public static ButtonWidget createButtonWidget(int x, int y, int width, int height, Text message, ButtonWidget.PressAction onPress) {
        return createButtonWidget(x, y, width, height, message, onPress, ButtonWidget.EMPTY);
    }

    public static ButtonWidget createButtonWidget(int x, int y, int width, int height, Text message, ButtonWidget.PressAction onPress, ButtonWidget.TooltipSupplier tooltipSupplier) {
        return new ButtonWidget(x, y, width , height, message, onPress, tooltipSupplier);
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
            return renderer.draw(drawObjectDM.getStack(), text, x, y, color);
        }

        public static int drawText(TextRenderer renderer, DrawObjectDM drawObjectDM, String text, int x, int y, int color) {
            return renderer.draw(drawObjectDM.getStack(), text, x, y, color);
        }

        public static int drawText(TextRenderer renderer, DrawObjectDM drawObjectDM, OrderedText text, int x, int y, int color) {
            return renderer.draw(drawObjectDM.getStack(), text, x, y, color);
        }

        public static void drawTexture(DrawObjectDM drawObjectDM, Identifier texture, int x, int y, float u, float v, int width, int height, int textureWidth, int textureHeight) {
            RenderUtil.setShaderTexture(0, texture);
            DrawableHelper.drawTexture(drawObjectDM.getStack(), x, y, u, v, width, height, textureWidth, textureHeight);
        }

        public static void drawTexture(DrawObjectDM drawObjectDM, Identifier texture, int x, int y, float u, float v, int width, int height) {
            RenderUtil.setShaderTexture(0, texture);
            DrawableHelper.drawTexture(drawObjectDM.getStack(), x, y, u, v, width, height, 256, 256);
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
            drawTooltip(drawObjectDM, textRenderer, List.of(text), x, y);
        }

        public static void drawTooltip(DrawObjectDM drawObjectDM, TextRenderer textRenderer, List<Text> texts, int x, int y) {
            MatrixStack matrices = drawObjectDM.getStack();

            if (!texts.isEmpty()) {
                int i = 0;
                int j = texts.size() == 1 ? -2 : 0;

                int k;
                for (Text text : texts) {
                    k = getWidth(text);
                    if (k > i) {
                        i = k;
                    }
                }

                int l = x + 12;
                int m = y - 12;
                k = i;
                int n = j;
                if (l + i > drawObjectDM.getWidth()) {
                    l -= 28 + i;
                }

                if (m + j + 6 > drawObjectDM.getHeight()) {
                    m = drawObjectDM.getHeight() - j - 6;
                }

                matrices.push();
                int o = -267386864;
                int p = 1347420415;
                int q = 1344798847;
                int r = 1;
                float f = ClientUtil.getItemRenderer().zOffset;
                ClientUtil.getItemRenderer().zOffset = 400.0F;
                Tessellator tessellator = Tessellator.getInstance();
                BufferBuilder bufferBuilder = tessellator.getBuffer();
                RenderSystem.setShader(GameRenderer::getPositionColorShader);
                bufferBuilder.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_COLOR);
                Matrix4f matrix4f = matrices.peek().getPositionMatrix();
                fillGradient(matrix4f, bufferBuilder, l - 3, m - 4, l + k + 3, m - 3, 400, -267386864, -267386864);
                fillGradient(matrix4f, bufferBuilder, l - 3, m + n + 3, l + k + 3, m + n + 4, 400, -267386864, -267386864);
                fillGradient(matrix4f, bufferBuilder, l - 3, m - 3, l + k + 3, m + n + 3, 400, -267386864, -267386864);
                fillGradient(matrix4f, bufferBuilder, l - 4, m - 3, l - 3, m + n + 3, 400, -267386864, -267386864);
                fillGradient(matrix4f, bufferBuilder, l + k + 3, m - 3, l + k + 4, m + n + 3, 400, -267386864, -267386864);
                fillGradient(matrix4f, bufferBuilder, l - 3, m - 3 + 1, l - 3 + 1, m + n + 3 - 1, 400, 1347420415, 1344798847);
                fillGradient(matrix4f, bufferBuilder, l + k + 2, m - 3 + 1, l + k + 3, m + n + 3 - 1, 400, 1347420415, 1344798847);
                fillGradient(matrix4f, bufferBuilder, l - 3, m - 3, l + k + 3, m - 3 + 1, 400, 1347420415, 1347420415);
                fillGradient(matrix4f, bufferBuilder, l - 3, m + n + 2, l + k + 3, m + n + 3, 400, 1344798847, 1344798847);
                RenderSystem.enableDepthTest();
                RenderSystem.disableTexture();
                RenderSystem.enableBlend();
                RenderSystem.defaultBlendFunc();
                BufferRenderer.drawWithShader(bufferBuilder.end());
                RenderSystem.disableBlend();
                RenderSystem.enableTexture();
                VertexConsumerProvider.Immediate immediate = VertexConsumerProvider.immediate(Tessellator.getInstance().getBuffer());
                matrices.translate(0.0, 0.0, 400.0);
                int s = m;

                int t;
                TooltipComponent tooltipComponent;
                for (t = 0; t < texts.size(); ++t) {
                    Text text = texts.get(t);
                    if (text != null) {
                        tooltipComponent = TooltipComponent.of(text.asOrderedText());
                        textRenderer.draw((OrderedText) tooltipComponent, (float) l, (float) s, -1, true, matrix4f, immediate, false, 0, 15728880);
                    }

                    if (t == 0) {
                        s += 2;
                    }

                    s += 10;
                }

                immediate.draw();
                matrices.pop();
                s = m;

                for (t = 0; t < texts.size(); ++t) {
                    Text text = texts.get(t);
                    if (text != null) {
                        tooltipComponent = TooltipComponent.of(text.asOrderedText());
                        textRenderer.draw((OrderedText) tooltipComponent, (float) l, (float) s, -1, true, matrix4f, immediate, false, 0, 15728880);
                    }

                    if (t == 0) {
                        s += 2;
                    }

                    s += 10;
                }

                ClientUtil.getItemRenderer().zOffset = f;
            }
        }

        protected static void fillGradient(Matrix4f matrix, BufferBuilder builder, int startX, int startY, int endX, int endY, int z, int colorStart, int colorEnd) {
            float f = (float)(colorStart >> 24 & 255) / 255.0F;
            float g = (float)(colorStart >> 16 & 255) / 255.0F;
            float h = (float)(colorStart >> 8 & 255) / 255.0F;
            float i = (float)(colorStart & 255) / 255.0F;
            float j = (float)(colorEnd >> 24 & 255) / 255.0F;
            float k = (float)(colorEnd >> 16 & 255) / 255.0F;
            float l = (float)(colorEnd >> 8 & 255) / 255.0F;
            float m = (float)(colorEnd & 255) / 255.0F;
            builder.vertex(matrix, (float)endX, (float)startY, (float)z).color(g, h, i, f).next();
            builder.vertex(matrix, (float)startX, (float)startY, (float)z).color(g, h, i, f).next();
            builder.vertex(matrix, (float)startX, (float)endY, (float)z).color(k, l, m, j).next();
            builder.vertex(matrix, (float)endX, (float)endY, (float)z).color(k, l, m, j).next();
        }

        public static void drawBorder(DrawObjectDM drawObjectDM, int x, int y, int width, int height, int color) {

        }

        public static void drawTooltip(DrawObjectDM drawObjectDM, Text text, int x, int y) {
            drawTooltip(drawObjectDM, List.of(text), x, y);
        }

        public static void drawTooltip(DrawObjectDM drawObjectDM, TextComponent text, int x, int y) {
            drawTooltip(drawObjectDM, text.getText(), x, y);
        }

        public static void drawTooltip(DrawObjectDM drawObjectDM, List<Text> texts, int x, int y) {
            drawTooltip(drawObjectDM, getTextRenderer(), texts, x, y);
        }
    }

    public static int getWidth(Text text) {
        return RendererUtil.getTextRenderer().getWidth(text);
    }
}

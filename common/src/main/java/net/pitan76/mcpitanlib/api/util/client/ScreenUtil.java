package net.pitan76.mcpitanlib.api.util.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.OrderedText;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.pitan76.mcpitanlib.api.client.gui.widget.CompatibleTexturedButtonWidget;
import net.pitan76.mcpitanlib.api.client.gui.widget.RedrawableTexturedButtonWidget;
import net.pitan76.mcpitanlib.api.client.render.DrawObjectDM;
import net.pitan76.mcpitanlib.api.text.TextComponent;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;
import net.pitan76.mcpitanlib.core.util.DrawableHelper1192;
import net.pitan76.mcpitanlib.midohra.item.ItemStack;

import java.util.List;
import java.util.stream.Collectors;

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
            if (drawObjectDM.hasScreen()) {
                drawObjectDM.getScreen().renderTooltip(drawObjectDM.getStack(), text, x, y);
            }
        }

        public static void drawTooltip(DrawObjectDM drawObjectDM, TextRenderer textRenderer, List<Text> texts, int x, int y) {
            if (drawObjectDM.hasScreen()) {
                drawObjectDM.getScreen().renderTooltip(drawObjectDM.getStack(), texts, x, y);
            }
        }

        public static void drawBorder(DrawObjectDM drawObjectDM, int x, int y, int width, int height, int color) {

        }

        public static void drawTooltip(DrawObjectDM drawObjectDM, Text text, int x, int y) {
            drawTooltip(drawObjectDM, getTextRenderer(), text, x, y);
        }

        public static void drawTooltip(DrawObjectDM drawObjectDM, TextComponent text, int x, int y) {
            drawTooltip(drawObjectDM, text.getText(), x, y);
        }

        public static void drawTooltip(DrawObjectDM drawObjectDM, List<Text> texts, int x, int y) {
            drawTooltip(drawObjectDM, getTextRenderer(), texts, x, y);
        }

        public static void drawTooltip2(DrawObjectDM drawObjectDM, List<TextComponent> texts, int x, int y) {
            drawTooltip(drawObjectDM, texts.stream().map(TextComponent::getText).collect(Collectors.toList()), x, y);
        }

        public static void drawItem(DrawObjectDM drawObjectDM, ItemStack stack, int x, int y) {
            drawItem(drawObjectDM, stack.toMinecraft(), x, y);
        }

        public static void drawItem(DrawObjectDM drawObjectDM, net.minecraft.item.ItemStack stack, int x, int y) {
            MatrixStack matrixStack = RenderSystem.getModelViewStack();
            matrixStack.translate(0.0F, 0.0F, 32.0F);
            RenderSystem.applyModelViewMatrix();
            drawObjectDM.getScreen().setZOffset(200);

            ItemRenderer itemRenderer = ClientUtil.getItemRenderer();
            itemRenderer.zOffset = 200.0F;
            itemRenderer.renderInGuiWithOverrides(stack, x, y);
            drawObjectDM.getScreen().setZOffset(0);
            itemRenderer.zOffset = 0.0F;
        }

        public static void drawItem(DrawObjectDM drawObjectDM, ItemStack stack, int x, int y, int seed) {
            drawItem(drawObjectDM, stack.toMinecraft(), x, y, seed);
        }

        public static void drawItem(DrawObjectDM drawObjectDM, net.minecraft.item.ItemStack stack, int x, int y, int seed) {
            MatrixStack matrixStack = RenderSystem.getModelViewStack();
            matrixStack.translate(0.0F, 0.0F, 32.0F);
            RenderSystem.applyModelViewMatrix();
            drawObjectDM.getScreen().setZOffset(200);

            ItemRenderer itemRenderer = ClientUtil.getItemRenderer();
            itemRenderer.zOffset = 200.0F;
            itemRenderer.renderInGuiWithOverrides(stack, x, y, seed);
            drawObjectDM.getScreen().setZOffset(0);
            itemRenderer.zOffset = 0.0F;
        }

        public static void drawItemWithoutEntity(DrawObjectDM drawObjectDM, ItemStack stack, int x, int y) {
            drawItemWithoutEntity(drawObjectDM, stack.toMinecraft(), x, y);
        }

        public static void drawItemWithoutEntity(DrawObjectDM drawObjectDM, net.minecraft.item.ItemStack stack, int x, int y) {
            MatrixStack matrixStack = RenderSystem.getModelViewStack();
            matrixStack.translate(0.0F, 0.0F, 32.0F);
            RenderSystem.applyModelViewMatrix();
            drawObjectDM.getScreen().setZOffset(200);

            ItemRenderer itemRenderer = ClientUtil.getItemRenderer();
            itemRenderer.zOffset = 200.0F;
            itemRenderer.renderInGuiWithOverrides(stack, x, y);
            drawObjectDM.getScreen().setZOffset(0);
            itemRenderer.zOffset = 0.0F;
        }

        public static void drawItemWithoutEntity(DrawObjectDM drawObjectDM, ItemStack stack, int x, int y, int seed) {
            drawItemWithoutEntity(drawObjectDM, stack.toMinecraft(), x, y, seed);
        }

        public static void drawItemWithoutEntity(DrawObjectDM drawObjectDM, net.minecraft.item.ItemStack stack, int x, int y, int seed) {
            MatrixStack matrixStack = RenderSystem.getModelViewStack();
            matrixStack.translate(0.0F, 0.0F, 32.0F);
            RenderSystem.applyModelViewMatrix();
            drawObjectDM.getScreen().setZOffset(200);

            ItemRenderer itemRenderer = ClientUtil.getItemRenderer();
            itemRenderer.zOffset = 200.0F;
            itemRenderer.renderInGuiWithOverrides(stack, x, y, seed);
            drawObjectDM.getScreen().setZOffset(0);
            itemRenderer.zOffset = 0.0F;
        }

        public static void drawItemInSlot(DrawObjectDM drawObjectDM, ItemStack stack, int x, int y) {
            drawItemInSlot(drawObjectDM, stack.toMinecraft(), x, y);
        }

        public static void drawItemInSlot(DrawObjectDM drawObjectDM, net.minecraft.item.ItemStack stack, int x, int y) {
            MatrixStack matrixStack = RenderSystem.getModelViewStack();
            matrixStack.translate(0.0F, 0.0F, 32.0F);
            RenderSystem.applyModelViewMatrix();
            drawObjectDM.getScreen().setZOffset(200);

            ItemRenderer itemRenderer = ClientUtil.getItemRenderer();
            itemRenderer.zOffset = 200.0F;
            itemRenderer.renderInGuiWithOverrides(stack, x, y);
            itemRenderer.renderGuiItemOverlay(ClientUtil.getTextRenderer(), stack, x, y - (stack.isEmpty() ? 0 : 8));
            drawObjectDM.getScreen().setZOffset(0);
            itemRenderer.zOffset = 0.0F;
        }

        public static void drawItemInSlot(DrawObjectDM drawObjectDM, ItemStack stack, int x, int y, String countText) {
            drawItemInSlot(drawObjectDM, stack.toMinecraft(), x, y, countText);
        }

        public static void drawItemInSlot(DrawObjectDM drawObjectDM, net.minecraft.item.ItemStack stack, int x, int y, String countText) {
            MatrixStack matrixStack = RenderSystem.getModelViewStack();
            matrixStack.translate(0.0F, 0.0F, 32.0F);
            RenderSystem.applyModelViewMatrix();
            drawObjectDM.getScreen().setZOffset(200);

            ItemRenderer itemRenderer = ClientUtil.getItemRenderer();
            itemRenderer.zOffset = 200.0F;
            itemRenderer.renderInGuiWithOverrides(stack, x, y);
            itemRenderer.renderGuiItemOverlay(ClientUtil.getTextRenderer(), stack, x, y - (stack.isEmpty() ? 0 : 8), countText);
            drawObjectDM.getScreen().setZOffset(0);
            itemRenderer.zOffset = 0.0F;
        }

        public static void enableScissor(DrawObjectDM drawObjectDM, int x0, int y0, int x1, int y1) {
            DrawableHelper.enableScissor(x0, y0, x1, y1);
        }

        public static void disableScissor(DrawObjectDM drawObjectDM) {
            DrawableHelper.disableScissor();
        }

        public static void fill(DrawObjectDM drawObjectDM, int x0, int y0, int x1, int y1, int color) {
            DrawableHelper.fill(drawObjectDM.getStack(), x0, y0, x1, y1, color);
        }

        public static void fillGradient(DrawObjectDM drawObjectDM, int x0, int y0, int x1, int y1, int color0, int color1) {
            DrawableHelper1192.fillGradient2(drawObjectDM.getStack(), x0, y0, x1, y1, color0, color1);
        }
    }

    public static int getWidth(Text text) {
        return RendererUtil.getTextRenderer().getWidth(text);
    }
}

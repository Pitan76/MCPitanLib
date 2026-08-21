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
import net.pitan76.mcpitanlib.api.util.TextUtil;
import net.pitan76.mcpitanlib.midohra.item.ItemStack;
import org.jetbrains.annotations.Nullable;

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
            drawObjectDM.getContext().text(renderer, text, x, y, fixColor(color), false);
            return -1;
        }

        public static int drawText(Font renderer, DrawObjectDM drawObjectDM, String text, int x, int y, int color) {
            drawObjectDM.getContext().text(renderer, text, x, y, fixColor(color), false);
            return -1;
        }

        public static int drawText(Font renderer, DrawObjectDM drawObjectDM, FormattedCharSequence text, int x, int y, int color) {
            drawObjectDM.getContext().text(renderer, text, x, y, fixColor(color), false);
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

        public static void drawTooltip2(DrawObjectDM drawObjectDM, List<TextComponent> texts, int x, int y) {
            drawTooltip(drawObjectDM, texts.stream().map(TextComponent::getText).collect(Collectors.toList()), x, y);
        }

        public static void drawItem(DrawObjectDM drawObjectDM, ItemStack stack, int x, int y) {
            drawItem(drawObjectDM, stack.toMinecraft(), x, y);
        }

        public static void drawItem(DrawObjectDM drawObjectDM, net.minecraft.world.item.ItemStack stack, int x, int y) {
            drawObjectDM.getContext().item(stack, x, y);
        }

        public static void drawItem(DrawObjectDM drawObjectDM, ItemStack stack, int x, int y, int seed) {
            drawItem(drawObjectDM, stack.toMinecraft(), x, y, seed);
        }

        public static void drawItem(DrawObjectDM drawObjectDM, net.minecraft.world.item.ItemStack stack, int x, int y, int seed) {
            drawObjectDM.getContext().item(stack, x, y, seed);
        }

        public static void drawItemWithoutEntity(DrawObjectDM drawObjectDM, ItemStack stack, int x, int y) {
            drawItemWithoutEntity(drawObjectDM, stack.toMinecraft(), x, y);
        }

        public static void drawItemWithoutEntity(DrawObjectDM drawObjectDM, net.minecraft.world.item.ItemStack stack, int x, int y) {
            drawObjectDM.getContext().fakeItem(stack, x, y, 0);
        }

        public static void drawItemWithoutEntity(DrawObjectDM drawObjectDM, ItemStack stack, int x, int y, int seed) {
            drawItemWithoutEntity(drawObjectDM, stack.toMinecraft(), x, y, seed);
        }

        public static void drawItemWithoutEntity(DrawObjectDM drawObjectDM, net.minecraft.world.item.ItemStack stack, int x, int y, int seed) {
            drawObjectDM.getContext().fakeItem(stack, x, y, seed);
        }

        public static void drawItemInSlot(DrawObjectDM drawObjectDM, ItemStack stack, int x, int y) {
            drawItemInSlot(drawObjectDM, stack.toMinecraft(), x, y);
        }

        public static void drawItemInSlot(DrawObjectDM drawObjectDM, net.minecraft.world.item.ItemStack stack, int x, int y) {
            drawObjectDM.getContext().itemDecorations(getTextRenderer(), stack, x, y);
        }

        public static void drawItemInSlot(DrawObjectDM drawObjectDM, ItemStack stack, int x, int y, String countText) {
            drawItemInSlot(drawObjectDM, stack.toMinecraft(), x, y, countText);
        }

        public static void drawItemInSlot(DrawObjectDM drawObjectDM, net.minecraft.world.item.ItemStack stack, int x, int y, String countText) {
            drawObjectDM.getContext().itemDecorations(getTextRenderer(), stack, x, y, countText);
        }

        public static void enableScissor(DrawObjectDM drawObjectDM, int x0, int y0, int x1, int y1) {
            drawObjectDM.getContext().enableScissor(x0, y0, x1, y1);
        }

        public static void disableScissor(DrawObjectDM drawObjectDM) {
            drawObjectDM.getContext().disableScissor();
        }

        public static void fill(DrawObjectDM drawObjectDM, int x0, int y0, int x1, int y1, int color) {
            drawObjectDM.getContext().fill(x0, y0, x1, y1, fixColor(color));
        }

        public static void fillGradient(DrawObjectDM drawObjectDM, int x0, int y0, int x1, int y1, int color0, int color1) {
            drawObjectDM.getContext().fillGradient(x0, y0, x1, y1, fixColor(color0), fixColor(color1));
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

    public static int getWidth(TextComponent text) {
        return getWidth(text.getText());
    }

    public static int getWidth(String text) {
        return getWidth(TextUtil.literal(text));
    }
}

package net.pitan76.mcpitanlib.api.client.gui.widget;

import com.google.common.collect.ImmutableList;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.narration.NarratableEntry;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.ContainerObjectSelectionList;
import net.pitan76.mcpitanlib.api.client.render.handledscreen.RenderArgs;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@Environment(EnvType.CLIENT)
public class SimpleListWidget extends ContainerObjectSelectionList<SimpleListWidget.WidgetEntry> {

    public SimpleListWidget(Minecraft client, int width, int height, int top, int bottom, int itemHeight) {
        this(client, width, bottom - top, top, itemHeight);
    }

    public SimpleListWidget(Minecraft client, int width, int height, int y, int itemHeight) {
        super(client, width, height, y, itemHeight);
        this.centerListVertically = false;
    }

    public void add(AbstractWidget widget) {
        super.addEntry(WidgetEntry.create(widget));
    }

    @Override
    public int getRowWidth() {
        return 400;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    @Override
    protected int scrollBarX() {
        return super.scrollBarX() + 32;
    }

    @Nullable
    public AbstractWidget getWidget(int index) {
        if (index < 0 || index >= this.children().size()) {
            return null;
        }
        return this.children().get(index).getWidget();
    }

    public Optional<AbstractWidget> getHoveredWidget(double mouseX, double mouseY) {
        for (WidgetEntry entry : this.children()) {
            if (entry.getWidget().isMouseOver(mouseX, mouseY)) {
                return Optional.of(entry.getWidget());
            }
        }
        return Optional.empty();
    }

    /*
    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        this.render(new RenderArgs(new DrawObjectDM(context), mouseX, mouseY, delta));
    }
    */

    public void render(RenderArgs args) {
        super.extractWidgetRenderState(args.drawObjectDM.getContext(), args.mouseX, args.mouseY, args.delta);
    }

    @Environment(EnvType.CLIENT)
    public static class WidgetEntry extends Entry<WidgetEntry> {
        protected final AbstractWidget widget;

        public WidgetEntry(AbstractWidget widget) {
            this.widget = widget;
        }

        public static WidgetEntry create(AbstractWidget widget) {
            return new WidgetEntry(widget);
        }

        @Deprecated
        @Override
        public void extractContent(GuiGraphicsExtractor context, int mouseX, int mouseY, boolean hovered, float deltaTicks) {
            widget.setX(getContentX() + (getContentWidth() - widget.getWidth()) / 2);
            widget.setY(getContentY());
            widget.extractRenderState(context, mouseX, mouseY, deltaTicks);
        }

        @Override
        public void visitWidgets(Consumer<AbstractWidget> consumer) {
            consumer.accept(widget);
        }

        @Deprecated
        @Override
        public List<? extends GuiEventListener> children() {
            return ImmutableList.of(widget);
        }

        @Deprecated
        @Override
        public List<? extends NarratableEntry> narratables() {
            return ImmutableList.of(widget);
        }

        public AbstractWidget getWidget() {
            return widget;
        }
    }
}

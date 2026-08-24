package net.pitan76.mcpitanlib.api.client.gui.widget;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.components.AbstractSliderButton;
import net.minecraft.network.chat.Component;
import net.pitan76.mcpitanlib.api.client.render.handledscreen.RenderArgs;
import net.pitan76.mcpitanlib.api.util.TextUtil;

import java.util.function.Consumer;
import java.util.function.Function;

@Environment(EnvType.CLIENT)
public class SimpleSliderWidget extends AbstractSliderButton {
    protected final Function<Double, Component> textGetter;
    protected final Consumer<Double> changeCallback;
    public SimpleListWidget listWidget = null;

    public SimpleSliderWidget(int x, int y, int width, int height, Component text, double defaultValue, ValueTextGetter<Double> valueTextGetter, Consumer<Double> changeCallback) {
        super(x, y, width, height, text, defaultValue);
        this.textGetter = (Double value) -> valueTextGetter.get(text, value);
        this.changeCallback = changeCallback;
        this.updateMessage();
    }
    public SimpleSliderWidget(int x, int y, int width, int height, double defaultValue, ValueTextGetter<Double> valueTextGetter, Consumer<Double> changeCallback) {
        this(x, y, width, height, TextUtil.empty(), defaultValue, valueTextGetter, changeCallback);
    }

    public SimpleSliderWidget(int x, int y, int width, Component text, double defaultValue, ValueTextGetter<Double> valueTextGetter, Consumer<Double> changeCallback) {
        this(x, y, width, 20, text, defaultValue, valueTextGetter, changeCallback);
    }

    public SimpleSliderWidget(int x, int y, int width, double defaultValue, ValueTextGetter<Double> valueTextGetter, Consumer<Double> changeCallback) {
        this(x, y, width, 20, defaultValue, valueTextGetter, changeCallback);
    }

    public SimpleSliderWidget(SimpleListWidget listWidget, int width, Component text, double defaultValue, ValueTextGetter<Double> valueTextGetter, Consumer<Double> changeCallback) {
        this(listWidget.getWidth() / 2 - 155, 0, width, 20, text, defaultValue, valueTextGetter, changeCallback);
        this.listWidget = listWidget;
    }

    public SimpleSliderWidget(SimpleListWidget listWidget, int width, double defaultValue, ValueTextGetter<Double> valueTextGetter, Consumer<Double> changeCallback) {
        this(listWidget, width, TextUtil.empty(), defaultValue, valueTextGetter, changeCallback);
    }

    /*
    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        this.render(new RenderArgs(new DrawObjectDM(context), mouseX, mouseY, delta));
    }
    */

    public void render(RenderArgs args) {
        super.extractRenderState(args.drawObjectDM.getContext(), args.mouseX, args.mouseY, args.delta);
    }
    
    @Override
    public void setValue(double value) {
        super.setValue(value);
    }

    public void setRawValue(double value) {
        super.value = value;
    }
    
    public double getValue() {
        return super.value;
    }

    @Override
    protected void updateMessage() {
        this.setMessage(this.textGetter.apply(this.getValue()));
    }

    @Override
    protected void applyValue() {
        this.changeCallback.accept(this.getValue());
    }

    @Environment(EnvType.CLIENT)
    @FunctionalInterface
    public interface ValueTextGetter<Double> {
        Component get(Component optionText, Double value);
    }
}

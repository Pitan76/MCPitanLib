package net.pitan76.mcpitanlib.api.util.color;

import net.minecraft.block.MapColor;

public class CompatBrightness {
    private final MapColor.Brightness brightness;

    public static final CompatBrightness LOW = of(MapColor.Brightness.LOW);
    public static final CompatBrightness NORMAL = of(MapColor.Brightness.NORMAL);
    public static final CompatBrightness HIGH = of(MapColor.Brightness.HIGH);
    public static final CompatBrightness LOWEST = of(MapColor.Brightness.LOWEST);

    public CompatBrightness(MapColor.Brightness brightness) {
        this.brightness = brightness;
    }

    public static CompatBrightness of(MapColor.Brightness brightness) {
        return new CompatBrightness(brightness);
    }

    public MapColor.Brightness get() {
        return brightness;
    }

    public String getName() {
        return get().name();
    }

    public int getId() {
        return get().id;
    }

    public int getBrightness() {
        return get().brightness;
    }




}

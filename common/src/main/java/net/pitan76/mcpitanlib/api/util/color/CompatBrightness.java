package net.pitan76.mcpitanlib.api.util.color;

public class CompatBrightness {
    private final int index;
    private final int brightness;
    private final String name;

    private int nextIndex = 0;

    public static final CompatBrightness LOW = of(180);
    public static final CompatBrightness NORMAL = of(220);
    public static final CompatBrightness HIGH = of(255);
    public static final CompatBrightness LOWEST = of(135);

    public CompatBrightness(int brightness) {
        this.brightness = brightness;
        this.index = nextIndex;
        if (this.index == 0) {
            this.name = "low";
        } else if (this.index == 1) {
            this.name = "normal";
        } else if (this.index == 2) {
            this.name = "high";
        } else {
            this.name = "lowest";
        }

        nextIndex++;
    }

    public static CompatBrightness of(int brightness) {
        return new CompatBrightness(brightness);
    }

    public int get() {
        return brightness;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return index;
    }

    public int getBrightness() {
        return brightness;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(brightness);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        CompatBrightness other = (CompatBrightness) obj;
        return brightness == other.brightness;
    }
}

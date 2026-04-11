package net.pitan76.mcpitanlib.api.client.option;

import net.minecraft.client.Options;

public class GameOptionsWrapper {
    public final Options raw;

    public GameOptionsWrapper(Options options) {
        this.raw = options;
    }

    public Options getRaw() {
        return raw;
    }

    public void write() {
        raw.save();
    }
}

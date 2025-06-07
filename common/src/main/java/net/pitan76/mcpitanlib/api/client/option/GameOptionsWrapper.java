package net.pitan76.mcpitanlib.api.client.option;

import net.minecraft.client.option.GameOptions;

public class GameOptionsWrapper {
    public final GameOptions raw;

    public GameOptionsWrapper(GameOptions options) {
        this.raw = options;
    }

    public GameOptions getRaw() {
        return raw;
    }

    public void write() {
        raw.write();
    }
}

package net.pitan76.mcpitanlib.api.util;

import net.minecraft.util.StringIdentifiable;

public interface CompatStringIdentifiable extends StringIdentifiable {
    @Deprecated
    @Override
    default String asString() {
        return asString_compat();
    }

    String asString_compat();

    default StringIdentifiable get() {
        return this;
    }
}

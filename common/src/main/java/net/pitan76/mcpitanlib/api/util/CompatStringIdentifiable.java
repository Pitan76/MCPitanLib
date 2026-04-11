package net.pitan76.mcpitanlib.api.util;

import net.minecraft.util.StringRepresentable;

public interface CompatStringIdentifiable extends StringRepresentable {
    @Deprecated
    @Override
    default String getSerializedName() {
        return asString_compat();
    }

    String asString_compat();

    default StringRepresentable get() {
        return this;
    }
}

package net.pitan76.mcpitanlib.api.util;

import net.minecraft.util.Identifier;

public class IdentifierUtil {
    public static Identifier id(String id) {
        return Identifier.of(id);
    }

    public static Identifier id(String namespace, String path) {
        return Identifier.of(namespace, path);
    }
}

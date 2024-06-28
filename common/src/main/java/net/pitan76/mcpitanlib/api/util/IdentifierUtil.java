package net.pitan76.mcpitanlib.api.util;

import net.minecraft.util.Identifier;

public class IdentifierUtil {
    public static Identifier id(String id) {
        return new Identifier(id);
    }

    public static Identifier id(String namespace, String path) {
        return new Identifier(namespace, path);
    }
}

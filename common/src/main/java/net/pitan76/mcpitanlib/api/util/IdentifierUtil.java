package net.pitan76.mcpitanlib.api.util;

import net.minecraft.util.Identifier;

public class IdentifierUtil {
    public static Identifier id(String id) {
        return new Identifier(id);
    }

    public static Identifier id(String namespace, String path) {
        return new Identifier(namespace, path);
    }

    public static String toString(Identifier identifier) {
        return identifier.toString();
    }

    public static String getNamespace(Identifier identifier) {
        return identifier.getNamespace();
    }

    public static String getPath(Identifier identifier) {
        return identifier.getPath();
    }
}

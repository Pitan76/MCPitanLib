package net.pitan76.mcpitanlib.api.util;

import net.minecraft.resources.Identifier;

public class IdentifierUtil {
    public static Identifier id(String id) {
        return Identifier.of(id);
    }

    public static Identifier id(String namespace, String path) {
        return Identifier.of(namespace, path);
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

    public static Identifier from(CompatIdentifier id) {
        if (id == null)
            return null;

        return id.toMinecraft();
    }
}

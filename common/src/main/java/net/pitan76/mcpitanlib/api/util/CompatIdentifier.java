package net.pitan76.mcpitanlib.api.util;

import net.minecraft.util.Identifier;

import java.util.Objects;

/**
 * This is the Identifier class unique to MCPitanLib.
 * It is different from Minecraft's Identifier class.
 */
public class CompatIdentifier {
    private final String namespace;
    private final String path;

    /**
     * Creates a new Identifier with the given namespace and path.
     * @param namespace The namespace of the Identifier.
     * @param path The path of the Identifier.
     */
    public CompatIdentifier(String namespace, String path) {
        this.namespace = namespace;
        this.path = path;
    }

    /**
     * Creates a new Identifier with the given id.
     * @param id The id of the Identifier.
     */
    public CompatIdentifier(String id) {
        if (!id.contains(":")) {
            this.namespace = "minecraft";
            this.path = id;
            return;
        }
        String[] split = id.split(":");
        this.namespace = split[0];
        this.path = split[1];
    }

    /**
     * Creates a new Identifier with the given id.
     * @param id The id of the Identifier.
     * @return The Identifier.
     */
    public static CompatIdentifier of(String id) {
        return new CompatIdentifier(id);
    }

    /**
     * Creates a new Identifier with the given namespace and path.
     * @param namespace The namespace of the Identifier.
     * @param path The path of the Identifier.
     * @return The Identifier.
     */
    public static CompatIdentifier of(String namespace, String path) {
        return new CompatIdentifier(namespace, path);
    }

    /**
     * Returns the string representation of the Identifier.
     * @return The string representation of the Identifier.
     */
    public String toString() {
        return this.namespace + ":" + this.path;
    }

    /**
     * Returns the namespace of the Identifier.
     * @return The namespace of the Identifier.
     */
    public String getNamespace() {
        return this.namespace;
    }

    /**
     * Returns the path of the Identifier.
     * @return The path of the Identifier.
     */
    public String getPath() {
        return this.path;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj != null && this.getClass() == obj.getClass()) {
            CompatIdentifier identifier = (CompatIdentifier)obj;
            return this.namespace.equals(identifier.namespace) && this.path.equals(identifier.path);
        } else if (obj instanceof Identifier) {
            return this.toString().equals(obj.toString());
        } else if (obj instanceof String) {
            return this.toString().equals(obj);
        } else {
            return false;
        }
    }

    public boolean equals(CompatIdentifier identifier) {
        return this.namespace.equals(identifier.namespace) && this.path.equals(identifier.path);
    }

    public boolean equals(String id) {
        return this.toString().equals(id);
    }

    public static boolean equals(CompatIdentifier id1, CompatIdentifier id2) {
        return id1.namespace.equals(id2.namespace) && id1.path.equals(id2.path);
    }

    public static boolean equals(CompatIdentifier id1, Identifier id2) {
        return id1.toString().equals(id2.toString());
    }

    public static boolean equals(Identifier id1, CompatIdentifier id2) {
        return id1.toString().equals(id2.toString());
    }

    public static boolean equals(Identifier id1, Identifier id2) {
        return id1.toString().equals(id2.toString());
    }

    @Override
    public int hashCode() {
        return 31 * this.namespace.hashCode() + this.path.hashCode();
    }

    // ----

    /**
     * Converts this MCPitanLib Identifier to a Minecraft Identifier.
     * @return The Minecraft Identifier.
     */
    public Identifier toMinecraft() {
        return IdentifierUtil.id(this.toString());
    }

    /**
     * Converts a Minecraft Identifier to an MCPitanLib Identifier.
     * @param id The Minecraft Identifier.
     * @return The Identifier.
     */
    public static CompatIdentifier fromMinecraft(Identifier id) {
        return of(id.toString());
    }

    public static CompatIdentifier empty() {
        return of("mcpitanlib:empty");
    }

    public boolean isEmpty() {
        return this.equals("mcpitanlib:empty");
    }
}

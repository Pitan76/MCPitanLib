package net.pitan76.mcpitanlib.api.tag;

import net.pitan76.mcpitanlib.core.tag.TagHooks;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;
import net.pitan76.mcpitanlib.api.util.*;

public class TagKey<T> {
    private final Tag.Identified<T> tagKey;

    @Deprecated
    public TagKey(Tag.Identified<T> tagKey) {
        this.tagKey = tagKey;
    }

    public static TagKey<?> create(Type type, Identifier identifier) {
        switch (type) {
            case BLOCK:
                return new TagKey<>(TagHooks.getBlockOptional(identifier));
            case ITEM:
                return new TagKey<>(TagHooks.getItemOptional(identifier));
            case FLUID:
                return new TagKey<>(TagHooks.getFluidOptional(identifier));
            case ENTITY_TYPE:
                return new TagKey<>(TagHooks.getEntityTypeOptional(identifier));
            default:
                throw new IllegalArgumentException();
        }
    }

    public static TagKey<?> create(Type type, CompatIdentifier id) {
        return create(type, id.toMinecraft());
    }

    @Deprecated
    public Tag.Identified<T> getTagKey() {
        return tagKey;
    }

    public enum Type {
        BLOCK,
        ITEM,
        FLUID,
        ENTITY_TYPE;
    }

    public boolean isOf(T value) {
        return tagKey.contains(value);
    }
}

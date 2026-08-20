package net.pitan76.mcpitanlib.core.tag;

import net.minecraft.tag.Tag;
import net.minecraft.tag.TagGroup;
import net.minecraft.util.Identifier;

import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

/**
 * TagGroupから遅延解決するTag.Identified。
 * Architectury APIのTagHooksが返していたものの代替で、FabricとForgeで共通に使える。
 */
public class CompatTagDelegate<T> implements Tag.Identified<T> {
    private final Identifier id;
    private final Supplier<TagGroup<T>> groupSupplier;

    public CompatTagDelegate(Identifier id, Supplier<TagGroup<T>> groupSupplier) {
        this.id = id;
        this.groupSupplier = groupSupplier;
    }

    @Override
    public Identifier getId() {
        return id;
    }

    private Tag<T> resolve() {
        TagGroup<T> group = groupSupplier == null ? null : groupSupplier.get();
        if (group == null) return null;

        return group.getTag(id);
    }

    @Override
    public boolean contains(T entry) {
        Tag<T> tag = resolve();
        return tag != null && tag.contains(entry);
    }

    @Override
    public List<T> values() {
        Tag<T> tag = resolve();
        return tag == null ? Collections.<T>emptyList() : tag.values();
    }
}

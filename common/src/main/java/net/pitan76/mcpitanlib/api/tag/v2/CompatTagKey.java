package net.pitan76.mcpitanlib.api.tag.v2;

import net.minecraft.util.registry.RegistryEntry;
import net.pitan76.mcpitanlib.api.tag.TagKey;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;

public class CompatTagKey<T> extends TagKey<T> {
    @Deprecated
    public CompatTagKey(net.minecraft.tag.TagKey<T> tagKey) {
        super(tagKey);
    }

    @Deprecated
    public static <T> CompatTagKey<T> of(net.minecraft.tag.TagKey<T> tagKey) {
        return new CompatTagKey<>(tagKey);
    }

    public static <T> CompatTagKey<T> of(CompatTagKeyType<T> type, CompatIdentifier identifier) {
        return of(net.minecraft.tag.TagKey.of(type.getRegistryKey(), identifier.toMinecraft()));
    }

    public boolean isOf(T value) {
        return RegistryEntry.of(value).isIn(getTagKey());
    }

    public CompatIdentifier getId() {
        return CompatIdentifier.fromMinecraft(getTagKey().id());
    }
}

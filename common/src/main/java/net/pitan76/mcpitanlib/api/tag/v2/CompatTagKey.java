package net.pitan76.mcpitanlib.api.tag.v2;

import net.minecraft.core.Holder;
import net.pitan76.mcpitanlib.api.tag.TagKey;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;

public class CompatTagKey<T> extends TagKey<T> {
    @Deprecated
    public CompatTagKey(net.minecraft.tags.TagKey<T> tagKey) {
        super(tagKey);
    }

    @Deprecated
    public static <T> CompatTagKey<T> of(net.minecraft.tags.TagKey<T> tagKey) {
        return new CompatTagKey<>(tagKey);
    }

    public static <T> CompatTagKey<T> of(CompatTagKeyType<T> type, CompatIdentifier identifier) {
        return of(net.minecraft.tags.TagKey.create(type.getRegistryKey(), identifier.toMinecraft()));
    }

    public boolean isOf(T value) {
        return Holder.direct(value).is(getTagKey());
    }

    public CompatIdentifier getId() {
        return CompatIdentifier.fromMinecraft(getTagKey().location());
    }
}

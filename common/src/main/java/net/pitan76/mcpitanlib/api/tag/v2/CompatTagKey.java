package net.pitan76.mcpitanlib.api.tag.v2;

import net.pitan76.mcpitanlib.core.tag.TagHooks;
import net.minecraft.tag.Tag;
import net.pitan76.mcpitanlib.api.tag.TagKey;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;

public class CompatTagKey<T> extends TagKey<T> {
    @Deprecated
    public CompatTagKey(Tag.Identified<T> tagKey) {
        super(tagKey);
    }

    @Deprecated
    public static <T> CompatTagKey<T> of(Tag.Identified<T> tagKey) {
        return new CompatTagKey<>(tagKey);
    }

    public static <T> CompatTagKey<T> of(CompatTagKeyType<T> type, CompatIdentifier identifier) {
        return of(TagHooks.getOptional(identifier.toMinecraft(), type::getTagGroup));
    }

    public boolean isOf(T value) {
        return getTagKey().contains(value);
    }

    public CompatIdentifier getId() {
        return CompatIdentifier.fromMinecraft(getTagKey().getId());
    }
}

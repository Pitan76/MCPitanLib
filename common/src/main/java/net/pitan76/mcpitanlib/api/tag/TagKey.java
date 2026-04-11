package net.pitan76.mcpitanlib.api.tag;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.item.Item;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.Holder;
import net.minecraft.resources.Identifier;
import net.pitan76.mcpitanlib.api.util.*;

public class TagKey<T> {
    private final net.minecraft.tags.TagKey tagKey;

    @Deprecated
    public TagKey(net.minecraft.tags.TagKey tagKey) {
        this.tagKey = tagKey;
    }

    public static TagKey<?> create(Type type, Identifier identifier) {
        return switch (type) {
            case BLOCK -> new TagKey<>(net.minecraft.tags.TagKey.create(Registries.BLOCK, identifier));
            case ITEM -> new TagKey<>(net.minecraft.tags.TagKey.create(Registries.ITEM, identifier));
            case FLUID -> new TagKey<>(net.minecraft.tags.TagKey.create(Registries.FLUID, identifier));
            case ENTITY_TYPE -> new TagKey<>(net.minecraft.tags.TagKey.create(Registries.ENTITY_TYPE, identifier));
        };
    }

    public static TagKey<?> create(Type type, CompatIdentifier id) {
        return create(type, id.toMinecraft());
    }

    @Deprecated
    public net.minecraft.tags.TagKey getTagKey() {
        return tagKey;
    }

    public enum Type {
        BLOCK,
        ITEM,
        FLUID,
        ENTITY_TYPE,
    }

    public boolean isOf(T value) {
        if (value instanceof Item)
            return getTagKey() == net.minecraft.tags.TagKey.create(Registries.ITEM, ItemUtil.toID((Item) value));
        if (value instanceof Block)
            return getTagKey() == net.minecraft.tags.TagKey.create(Registries.BLOCK, BlockUtil.toID((Block) value));
        if (value instanceof Fluid)
            return getTagKey() == net.minecraft.tags.TagKey.create(Registries.FLUID, FluidUtil.toID((Fluid) value));
        if (value instanceof EntityType<?>)
            return getTagKey() == net.minecraft.tags.TagKey.create(Registries.ENTITY_TYPE, EntityTypeUtil.toID((EntityType<?>) value));

        return Holder.direct(value).is(getTagKey());
    }
}

package net.pitan76.mcpitanlib.api.tag.v2.typed;

import net.minecraft.world.entity.EntityType;
import net.minecraft.tags.TagKey;
import net.pitan76.mcpitanlib.api.tag.v2.CompatTagKey;
import net.pitan76.mcpitanlib.api.tag.v2.CompatTagKeyType;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;

import java.util.List;

public class EntityTagKey extends CompatTagKey<EntityType<?>> {
    @Deprecated
    public EntityTagKey(TagKey<EntityType<?>> tagKey) {
        super(tagKey);
    }

    public static EntityTagKey of(CompatIdentifier identifier) {
        return new EntityTagKey(TagKey.create(CompatTagKeyType.ENTITY_TYPE.getRegistryKey(), identifier.toMinecraft()));
    }
}

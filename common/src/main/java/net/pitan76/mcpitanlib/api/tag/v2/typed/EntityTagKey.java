package net.pitan76.mcpitanlib.api.tag.v2.typed;

import net.pitan76.mcpitanlib.core.tag.TagHooks;
import net.minecraft.entity.EntityType;
import net.minecraft.tag.Tag;
import net.pitan76.mcpitanlib.api.tag.v2.CompatTagKey;
import net.pitan76.mcpitanlib.api.tag.v2.CompatTagKeyType;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;

public class EntityTagKey extends CompatTagKey<EntityType<?>> {
    @Deprecated
    public EntityTagKey(Tag.Identified<EntityType<?>> tagKey) {
        super(tagKey);
    }

    public static EntityTagKey of(CompatIdentifier identifier) {
        return new EntityTagKey(TagHooks.getOptional(identifier.toMinecraft(), CompatTagKeyType.ENTITY_TYPE::getTagGroup));
    }
}

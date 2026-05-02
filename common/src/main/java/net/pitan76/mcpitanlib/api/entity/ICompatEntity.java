package net.pitan76.mcpitanlib.api.entity;

import net.minecraft.entity.Entity;
import net.pitan76.mcpitanlib.api.util.EntityUtil;
import net.pitan76.mcpitanlib.midohra.entity.EntityTypeWrapper;
import net.pitan76.mcpitanlib.midohra.entity.EntityWrapper;

public interface ICompatEntity {
    default EntityWrapper getWrapper() {
        return this instanceof Entity ? EntityWrapper.of((Entity) this) : EntityWrapper.of();
    }

    default EntityTypeWrapper getTypeWrapper() {
        return EntityTypeWrapper.of(EntityUtil.getType((Entity) this));
    }
}

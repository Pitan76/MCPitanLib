package net.pitan76.mcpitanlib.api.tag.v2.typed;

import net.minecraft.world.level.material.Fluid;
import net.minecraft.tags.TagKey;
import net.pitan76.mcpitanlib.api.tag.v2.CompatTagKey;
import net.pitan76.mcpitanlib.api.tag.v2.CompatTagKeyType;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;

public class FluidTagKey extends CompatTagKey<Fluid> {
    @Deprecated
    public FluidTagKey(TagKey<Fluid> tagKey) {
        super(tagKey);
    }

    public static FluidTagKey of(CompatIdentifier identifier) {
        return new FluidTagKey(TagKey.create(CompatTagKeyType.FLUID.getRegistryKey(), identifier.toMinecraft()));
    }
}

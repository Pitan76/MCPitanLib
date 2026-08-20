package net.pitan76.mcpitanlib.api.tag.v2.typed;

import net.pitan76.mcpitanlib.core.tag.TagHooks;
import net.minecraft.fluid.Fluid;
import net.minecraft.tag.Tag;
import net.pitan76.mcpitanlib.api.tag.v2.CompatTagKey;
import net.pitan76.mcpitanlib.api.tag.v2.CompatTagKeyType;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;

public class FluidTagKey extends CompatTagKey<Fluid> {
    @Deprecated
    public FluidTagKey(Tag.Identified<Fluid> tagKey) {
        super(tagKey);
    }

    public static FluidTagKey of(CompatIdentifier identifier) {
        return new FluidTagKey(TagHooks.getOptional(identifier.toMinecraft(), CompatTagKeyType.FLUID::getTagGroup));
    }
}

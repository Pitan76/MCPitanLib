package net.pitan76.mcpitanlib.api.tag.v2.typed;

import net.minecraft.fluid.Fluid;
import net.minecraft.tag.TagKey;
import net.pitan76.mcpitanlib.api.tag.v2.CompatTagKey;
import net.pitan76.mcpitanlib.api.tag.v2.CompatTagKeyType;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;

public class FluidTagKey extends CompatTagKey<Fluid> {
    @Deprecated
    public FluidTagKey(TagKey<Fluid> tagKey) {
        super(tagKey);
    }

    public static FluidTagKey of(CompatIdentifier identifier) {
        return new FluidTagKey(TagKey.of(CompatTagKeyType.FLUID.getRegistryKey(), identifier.toMinecraft()));
    }
}

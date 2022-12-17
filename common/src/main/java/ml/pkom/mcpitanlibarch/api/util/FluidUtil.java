package ml.pkom.mcpitanlibarch.api.util;

import net.minecraft.fluid.Fluid;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

public class FluidUtil {
    public static Identifier toID(Fluid fluid) {
        return Registries.FLUID.getId(fluid);
    }

    public static Fluid fromId(Identifier identifier) {
        return Registries.FLUID.get(identifier);
    }
}

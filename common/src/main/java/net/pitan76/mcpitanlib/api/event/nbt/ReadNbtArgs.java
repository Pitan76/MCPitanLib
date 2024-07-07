package net.pitan76.mcpitanlib.api.event.nbt;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;

public class ReadNbtArgs extends NbtRWArgs {

    @Deprecated
    public ReadNbtArgs(NbtCompound nbt, RegistryWrapper.WrapperLookup wrapperLookup) {
        super(nbt, wrapperLookup);
    }

    public ReadNbtArgs(NbtCompound nbt) {
        super(nbt);
    }
}

package net.pitan76.mcpitanlib.api.event.nbt;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import net.pitan76.mcpitanlib.api.registry.CompatRegistryLookup;

public class WriteNbtArgs extends NbtRWArgs {

    @Deprecated
    public WriteNbtArgs(NbtCompound nbt, RegistryWrapper.WrapperLookup wrapperLookup) {
        super(nbt, wrapperLookup);
    }

    public WriteNbtArgs(NbtCompound nbt, CompatRegistryLookup registryLookup) {
        super(nbt, registryLookup);
    }

    public WriteNbtArgs(NbtCompound nbt) {
        super(nbt);
    }
}

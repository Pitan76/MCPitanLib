package net.pitan76.mcpitanlib.api.event.nbt;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import net.pitan76.mcpitanlib.api.registry.CompatRegistryLookup;

public class NbtRWArgs {
    public NbtCompound nbt;
    public CompatRegistryLookup registryLookup;

    public NbtRWArgs(NbtCompound nbt, CompatRegistryLookup registryLookup) {
        this.nbt = nbt;
        this.registryLookup = registryLookup;
    }

    @Deprecated
    public NbtRWArgs(NbtCompound nbt, RegistryWrapper.WrapperLookup wrapperLookup) {
        this(nbt, new CompatRegistryLookup(wrapperLookup));
    }

    public NbtRWArgs(NbtCompound nbt) {
        this(nbt, (CompatRegistryLookup) null);
    }

    public NbtCompound getNbt() {
        return nbt;
    }

    public CompatRegistryLookup getRegistryLookup() {
        return registryLookup;
    }

    public boolean hasRegistryLookup() {
        return registryLookup != null;
    }

    @Deprecated
    public RegistryWrapper.WrapperLookup getWrapperLookup() {
        return registryLookup.getRegistryLookup();
    }
}

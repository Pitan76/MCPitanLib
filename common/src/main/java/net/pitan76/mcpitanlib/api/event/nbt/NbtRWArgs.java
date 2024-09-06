package net.pitan76.mcpitanlib.api.event.nbt;

import net.minecraft.nbt.NbtCompound;
import net.pitan76.mcpitanlib.api.registry.CompatRegistryLookup;

public class NbtRWArgs {
    public NbtCompound nbt;
    public CompatRegistryLookup registryLookup;

    public NbtRWArgs(NbtCompound nbt, CompatRegistryLookup registryLookup) {
        this.nbt = nbt;
        this.registryLookup = registryLookup;
    }

    public NbtRWArgs(NbtCompound nbt) {
        this.nbt = nbt;
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
}

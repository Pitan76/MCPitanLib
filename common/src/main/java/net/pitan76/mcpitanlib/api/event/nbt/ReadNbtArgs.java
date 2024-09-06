package net.pitan76.mcpitanlib.api.event.nbt;

import net.minecraft.nbt.NbtCompound;
import net.pitan76.mcpitanlib.api.registry.CompatRegistryLookup;

public class ReadNbtArgs extends NbtRWArgs {

    public ReadNbtArgs(NbtCompound nbt) {
        super(nbt);
    }

    public ReadNbtArgs(NbtCompound nbt, CompatRegistryLookup registryLookup) {
        super(nbt, registryLookup);
    }
}

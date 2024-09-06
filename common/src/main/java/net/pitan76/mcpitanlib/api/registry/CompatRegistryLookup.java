package net.pitan76.mcpitanlib.api.registry;

import net.minecraft.nbt.NbtCompound;
import net.pitan76.mcpitanlib.api.event.nbt.NbtRWArgs;

public class CompatRegistryLookup {

    public CompatRegistryLookup() {

    }

    public NbtRWArgs getNbtRWArgs(NbtCompound nbt) {
        return new NbtRWArgs(nbt);
    }
}

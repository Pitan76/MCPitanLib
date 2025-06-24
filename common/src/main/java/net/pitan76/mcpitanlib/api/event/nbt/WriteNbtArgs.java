package net.pitan76.mcpitanlib.api.event.nbt;

import net.minecraft.nbt.NbtCompound;
import net.pitan76.mcpitanlib.api.registry.CompatRegistryLookup;

public class WriteNbtArgs extends NbtRWArgs {

    public WriteNbtArgs(NbtCompound nbt, CompatRegistryLookup registryLookup) {
        super(nbt, registryLookup);
    }

    public WriteNbtArgs(NbtCompound nbt) {
        super(nbt);
    }

    @Override
    public boolean isViewEmpty() {
        return false;
    }

    @Override
    public NbtRWArgs copy() {
        return new WriteNbtArgs(nbt.copy(), registryLookup);
    }
}

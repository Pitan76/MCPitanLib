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

    @Override
    public boolean isViewEmpty() {
        return false;
    }

    @Override
    public NbtRWArgs copy() {
        return new ReadNbtArgs(nbt.copy(), registryLookup);
    }

    public ReadNbtArgs(net.pitan76.mcpitanlib.midohra.nbt.NbtCompound nbt, CompatRegistryLookup registryLookup) {
        this(nbt.toMinecraft(), registryLookup);
    }

    public ReadNbtArgs(net.pitan76.mcpitanlib.midohra.nbt.NbtCompound nbt) {
        this(nbt.toMinecraft());
    }
}

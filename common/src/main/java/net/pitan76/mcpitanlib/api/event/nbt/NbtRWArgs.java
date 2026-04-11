package net.pitan76.mcpitanlib.api.event.nbt;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.HolderLookup;
import net.pitan76.mcpitanlib.api.registry.CompatRegistryLookup;

public class NbtRWArgs {
    public CompoundTag nbt;
    public CompatRegistryLookup registryLookup;

    public NbtRWArgs(CompoundTag nbt, CompatRegistryLookup registryLookup) {
        this.nbt = nbt;
        this.registryLookup = registryLookup;
    }

    @Deprecated
    public NbtRWArgs(CompoundTag nbt, HolderLookup.Provider wrapperLookup) {
        this(nbt, new CompatRegistryLookup(wrapperLookup));
    }

    public NbtRWArgs(CompoundTag nbt) {
        this(nbt, (CompatRegistryLookup) null);
    }

    public CompoundTag getNbt() {
        return nbt;
    }

    public CompatRegistryLookup getRegistryLookup() {
        return registryLookup;
    }

    public boolean hasRegistryLookup() {
        return registryLookup != null;
    }

    @Deprecated
    public HolderLookup.Provider getWrapperLookup() {
        if (registryLookup == null)
            registryLookup = new CompatRegistryLookup();

        return registryLookup.getRegistryLookup();
    }

    public boolean isNbtEmpty() {
        return nbt != null && !nbt.isEmpty();
    }

    public boolean isViewEmpty() {
        return false;
    }

    public boolean isEmpty() {
        return isNbtEmpty() || isViewEmpty();
    }

    public NbtRWArgs copy() {
        return new NbtRWArgs(nbt.copy(), registryLookup);
    }

    public net.pitan76.mcpitanlib.midohra.nbt.NbtCompound getNbtM() {
        return net.pitan76.mcpitanlib.midohra.nbt.NbtCompound.of(nbt);
    }
}

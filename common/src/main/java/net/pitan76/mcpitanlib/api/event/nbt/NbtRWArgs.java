package net.pitan76.mcpitanlib.api.event.nbt;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;

public class NbtRWArgs {
    public NbtCompound nbt;
    private final RegistryWrapper.WrapperLookup wrapperLookup;

    @Deprecated
    public NbtRWArgs(NbtCompound nbt, RegistryWrapper.WrapperLookup wrapperLookup) {
        this.nbt = nbt;
        this.wrapperLookup = wrapperLookup;
    }

    public NbtRWArgs(NbtCompound nbt) {
        this(nbt, null);
    }

    public NbtCompound getNbt() {
        return nbt;
    }

    @Deprecated
    public RegistryWrapper.WrapperLookup getWrapperLookup() {
        return wrapperLookup;
    }
}

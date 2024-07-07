package net.pitan76.mcpitanlib.api.event.nbt;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;

public class WriteNbtArgs extends NbtRWArgs {

    @Deprecated
    public WriteNbtArgs(NbtCompound nbt, RegistryWrapper.WrapperLookup wrapperLookup) {
        super(nbt, wrapperLookup);
    }

    public WriteNbtArgs(NbtCompound nbt) {
        super(nbt);
    }
}

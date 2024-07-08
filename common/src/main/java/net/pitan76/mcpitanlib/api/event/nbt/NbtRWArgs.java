package net.pitan76.mcpitanlib.api.event.nbt;

import net.minecraft.nbt.NbtCompound;

public class NbtRWArgs {
    public NbtCompound nbt;

    public NbtRWArgs(NbtCompound nbt) {
        this.nbt = nbt;
    }

    public NbtCompound getNbt() {
        return nbt;
    }
}

package net.pitan76.mcpitanlib.api.registry;

import net.minecraft.nbt.NbtCompound;
import net.pitan76.mcpitanlib.api.event.nbt.NbtRWArgs;
import net.pitan76.mcpitanlib.api.event.nbt.ReadNbtArgs;
import net.pitan76.mcpitanlib.api.event.nbt.WriteNbtArgs;

public class CompatRegistryLookup {

    public CompatRegistryLookup() {

    }

    public NbtRWArgs getNbtRWArgs(NbtCompound nbt) {
        return new NbtRWArgs(nbt);
    }

    public WriteNbtArgs createWriteNbtArgs(NbtCompound nbt) {
        return new WriteNbtArgs(nbt, registryLookup);
    }

    public ReadNbtArgs createReadNbtArgs(NbtCompound nbt) {
        return new ReadNbtArgs(nbt, registryLookup);
    }
}

package net.pitan76.mcpitanlib.api.registry;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.data.registries.VanillaRegistries;
import net.minecraft.core.HolderLookup;
import net.pitan76.mcpitanlib.api.event.nbt.NbtRWArgs;
import net.pitan76.mcpitanlib.api.event.nbt.ReadNbtArgs;
import net.pitan76.mcpitanlib.api.event.nbt.WriteNbtArgs;

public class CompatRegistryLookup {

    private final HolderLookup.Provider registryLookup;

    @Deprecated
    public CompatRegistryLookup(HolderLookup.Provider registryLookup) {
        this.registryLookup = registryLookup;
    }

    public CompatRegistryLookup() {
        this.registryLookup = VanillaRegistries.createLookup();
    }

    @Deprecated
    public HolderLookup.Provider getRegistryLookup() {
        if (registryLookup == null)
            return VanillaRegistries.createLookup();

        return registryLookup;
    }

    public NbtRWArgs getNbtRWArgs(CompoundTag nbt) {
        return new NbtRWArgs(nbt, registryLookup);
    }

    public WriteNbtArgs createWriteNbtArgs(CompoundTag nbt) {
        return new WriteNbtArgs(nbt, registryLookup);
    }

    public ReadNbtArgs createReadNbtArgs(CompoundTag nbt) {
        return new ReadNbtArgs(nbt, registryLookup);
    }
}

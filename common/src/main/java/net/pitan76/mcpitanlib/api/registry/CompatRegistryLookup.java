package net.pitan76.mcpitanlib.api.registry;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.BuiltinRegistries;
import net.minecraft.registry.RegistryWrapper;
import net.pitan76.mcpitanlib.api.event.nbt.NbtRWArgs;
import net.pitan76.mcpitanlib.api.event.nbt.ReadNbtArgs;
import net.pitan76.mcpitanlib.api.event.nbt.WriteNbtArgs;

public class CompatRegistryLookup {

    private final RegistryWrapper.WrapperLookup registryLookup;

    @Deprecated
    public CompatRegistryLookup(RegistryWrapper.WrapperLookup registryLookup) {
        this.registryLookup = registryLookup;
    }

    public CompatRegistryLookup() {
        this.registryLookup = BuiltinRegistries.createWrapperLookup();
    }

    @Deprecated
    public RegistryWrapper.WrapperLookup getRegistryLookup() {
        if (registryLookup == null)
            return BuiltinRegistries.createWrapperLookup();

        return registryLookup;
    }

    public NbtRWArgs getNbtRWArgs(NbtCompound nbt) {
        return new NbtRWArgs(nbt, registryLookup);
    }

    public WriteNbtArgs createWriteNbtArgs(NbtCompound nbt) {
        return new WriteNbtArgs(nbt, registryLookup);
    }

    public ReadNbtArgs createReadNbtArgs(NbtCompound nbt) {
        return new ReadNbtArgs(nbt, registryLookup);
    }
}

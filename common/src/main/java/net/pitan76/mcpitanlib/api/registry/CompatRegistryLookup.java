package net.pitan76.mcpitanlib.api.registry;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.BuiltinRegistries;
import net.minecraft.registry.RegistryWrapper;
import net.pitan76.mcpitanlib.api.event.nbt.NbtRWArgs;

public class CompatRegistryLookup {

    private RegistryWrapper.WrapperLookup registryLookup;

    public CompatRegistryLookup() {

    }

    @Deprecated
    public CompatRegistryLookup(RegistryWrapper.WrapperLookup registryLookup) {
        this.registryLookup = registryLookup;
    }

    public CompatRegistryLookup() {
        this.registryLookup = BuiltinRegistries.createWrapperLookup();
    }

    @Deprecated
    public RegistryWrapper.WrapperLookup getRegistryLookup() {
        return registryLookup;
    }

    public NbtRWArgs getNbtRWArgs(NbtCompound nbt) {
        return new NbtRWArgs(nbt, registryLookup);
    }
}

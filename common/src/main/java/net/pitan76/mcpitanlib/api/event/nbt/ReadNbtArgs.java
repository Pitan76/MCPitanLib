package net.pitan76.mcpitanlib.api.event.nbt;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.storage.NbtReadView;
import net.minecraft.storage.ReadView;
import net.pitan76.mcpitanlib.api.registry.CompatRegistryLookup;

public class ReadNbtArgs extends NbtRWArgs {
    @Deprecated
    public ReadView view;

    @Deprecated
    public ReadNbtArgs(NbtCompound nbt, RegistryWrapper.WrapperLookup wrapperLookup) {
        super(nbt, wrapperLookup);
    }

    public ReadNbtArgs(NbtCompound nbt) {
        super(nbt);
    }

    public ReadNbtArgs(NbtCompound nbt, CompatRegistryLookup registryLookup) {
        super(nbt, registryLookup);
    }

    @Deprecated
    public ReadNbtArgs(NbtCompound nbt, ReadView view) {
        this(nbt);
        this.view = view;
    }

    @Deprecated
    public ReadNbtArgs(NbtCompound nbt, ReadView view, CompatRegistryLookup registryLookup) {
        this(nbt, registryLookup);
        this.view = view;
    }

    @Override
    public boolean isViewEmpty() {
        return view == null;
    }

    @Override
    public NbtRWArgs copy() {
        return new ReadNbtArgs(nbt.copy(), view, registryLookup);
    }
}

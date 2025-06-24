package net.pitan76.mcpitanlib.api.event.nbt;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.storage.WriteView;
import net.pitan76.mcpitanlib.api.registry.CompatRegistryLookup;

public class WriteNbtArgs extends NbtRWArgs {
    @Deprecated
    public WriteView view;

    @Deprecated
    public WriteNbtArgs(NbtCompound nbt, RegistryWrapper.WrapperLookup wrapperLookup) {
        super(nbt, wrapperLookup);
    }

    public WriteNbtArgs(NbtCompound nbt, CompatRegistryLookup registryLookup) {
        super(nbt, registryLookup);
    }

    public WriteNbtArgs(NbtCompound nbt) {
        super(nbt);
    }

    @Deprecated
    public WriteNbtArgs(NbtCompound nbt, WriteView view) {
        this(nbt);
        this.view = view;
    }

    @Deprecated
    public WriteNbtArgs(NbtCompound nbt, WriteView view, CompatRegistryLookup registryLookup) {
        this(nbt, registryLookup);
        this.view = view;
    }

    @Override
    public boolean isViewEmpty() {
        return view == null;
    }

    @Override
    public NbtRWArgs copy() {
        return new WriteNbtArgs(nbt.copy(), view, registryLookup);
    }
}

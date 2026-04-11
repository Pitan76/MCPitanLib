package net.pitan76.mcpitanlib.api.event.nbt;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.level.storage.ValueOutput;
import net.pitan76.mcpitanlib.api.registry.CompatRegistryLookup;
import net.pitan76.mcpitanlib.core.mc1216.NbtDataConverter;

public class WriteNbtArgs extends NbtRWArgs {
    @Deprecated
    public ValueOutput view;

    @Deprecated
    public WriteNbtArgs(CompoundTag nbt, HolderLookup.Provider wrapperLookup) {
        super(nbt, wrapperLookup);
    }

    public WriteNbtArgs(CompoundTag nbt, CompatRegistryLookup registryLookup) {
        super(nbt, registryLookup);
        view = NbtDataConverter.nbt2writeData(nbt, registryLookup);
    }

    public WriteNbtArgs(CompoundTag nbt) {
        super(nbt);
        view = NbtDataConverter.nbt2writeData(nbt, (CompatRegistryLookup) null);
    }

    @Deprecated
    public WriteNbtArgs(CompoundTag nbt, ValueOutput view) {
        this(nbt);
        this.view = view;
    }

    @Deprecated
    public WriteNbtArgs(CompoundTag nbt, ValueOutput view, CompatRegistryLookup registryLookup) {
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

    public WriteNbtArgs(net.pitan76.mcpitanlib.midohra.nbt.NbtCompound nbt, CompatRegistryLookup registryLookup) {
        this(nbt.toMinecraft(), registryLookup);
    }

    public WriteNbtArgs(net.pitan76.mcpitanlib.midohra.nbt.NbtCompound nbt) {
        this(nbt.toMinecraft());
    }
}

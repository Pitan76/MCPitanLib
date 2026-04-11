package net.pitan76.mcpitanlib.api.event.nbt;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.level.storage.TagValueInput;
import net.minecraft.world.level.storage.ValueInput;
import net.pitan76.mcpitanlib.api.registry.CompatRegistryLookup;
import net.pitan76.mcpitanlib.core.mc1216.NbtDataConverter;

public class ReadNbtArgs extends NbtRWArgs {
    @Deprecated
    public ValueInput view;

    @Deprecated
    public ReadNbtArgs(CompoundTag nbt, HolderLookup.Provider wrapperLookup) {
        super(nbt, wrapperLookup);
    }

    public ReadNbtArgs(CompoundTag nbt) {
        super(nbt);
        if (registryLookup == null)
            registryLookup = new CompatRegistryLookup();
        view = NbtDataConverter.nbt2readData(nbt, registryLookup);
    }

    public ReadNbtArgs(CompoundTag nbt, CompatRegistryLookup registryLookup) {
        super(nbt, registryLookup);
        view = NbtDataConverter.nbt2readData(nbt, registryLookup);
    }

    @Deprecated
    public ReadNbtArgs(CompoundTag nbt, ValueInput view) {
        this(nbt);
        this.view = view;
    }

    @Deprecated
    public ReadNbtArgs(CompoundTag nbt, ValueInput view, CompatRegistryLookup registryLookup) {
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

    public ReadNbtArgs(net.pitan76.mcpitanlib.midohra.nbt.NbtCompound nbt, CompatRegistryLookup registryLookup) {
        this(nbt.toMinecraft(), registryLookup);
    }

    public ReadNbtArgs(net.pitan76.mcpitanlib.midohra.nbt.NbtCompound nbt) {
        this(nbt.toMinecraft());
    }
}

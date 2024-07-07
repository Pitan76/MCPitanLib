package net.pitan76.mcpitanlib.api.world;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.world.PersistentState;
import net.pitan76.mcpitanlib.api.event.nbt.ReadNbtArgs;
import net.pitan76.mcpitanlib.api.event.nbt.WriteNbtArgs;

public abstract class CompatiblePersistentState extends PersistentState {
    // 1.16
    public CompatiblePersistentState(String key) {
        super();
    }

    public CompatiblePersistentState() {
        super();
    }

    // 1.16
    @Deprecated
    public void readNbt(NbtCompound tag) {
        readNbt(new ReadNbtArgs(tag));
    }

    public abstract void readNbt(ReadNbtArgs args);

    @Deprecated
    @Override
    public NbtCompound writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        return writeNbt(new WriteNbtArgs(nbt, registryLookup));
    }

    public abstract NbtCompound writeNbt(WriteNbtArgs args);
}

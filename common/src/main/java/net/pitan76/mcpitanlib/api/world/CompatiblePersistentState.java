package net.pitan76.mcpitanlib.api.world;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.world.PersistentState;

public abstract class CompatiblePersistentState extends PersistentState {
    // 1.16
    public CompatiblePersistentState(String key) {
        super();
    }

    public CompatiblePersistentState() {
        super();
    }

    // 1.16
    public abstract void readNbt(NbtCompound tag);

    @Deprecated
    @Override
    public NbtCompound writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        return writeNbtOverride(nbt);
    }

    public abstract NbtCompound writeNbtOverride(NbtCompound tag);
}

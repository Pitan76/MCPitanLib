package net.pitan76.mcpitanlib.api.world;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.saveddata.SavedData;
import net.pitan76.mcpitanlib.api.event.nbt.ReadNbtArgs;
import net.pitan76.mcpitanlib.api.event.nbt.WriteNbtArgs;

public abstract class CompatiblePersistentState extends SavedData {
    // 1.16
    public CompatiblePersistentState(String key) {
        super();
    }

    public CompatiblePersistentState() {
        super();
    }

    // 1.16
    @Deprecated
    public void readNbt(CompoundTag tag) {
        readNbt(new ReadNbtArgs(tag));
    }

    public abstract void readNbt(ReadNbtArgs args);

    public abstract CompoundTag writeNbt(WriteNbtArgs args);

    @Deprecated
    @Override
    public void setDirty() {
        callMarkDirty();
    }

    public void callMarkDirty() {
        super.setDirty();
    }

    @Deprecated
    @Override
    public void setDirty(boolean dirty) {
        callSetDirty(dirty);
    }

    public void callSetDirty(boolean dirty) {
        super.setDirty(dirty);
    }
}

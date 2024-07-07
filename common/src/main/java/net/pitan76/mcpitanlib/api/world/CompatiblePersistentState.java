package net.pitan76.mcpitanlib.api.world;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.PersistentState;
import net.pitan76.mcpitanlib.api.event.nbt.ReadNbtArgs;
import net.pitan76.mcpitanlib.api.event.nbt.WriteNbtArgs;

public abstract class CompatiblePersistentState extends PersistentState {

    public static int count = 1;

    // 1.16
    public CompatiblePersistentState(String key) {
        super(key);
    }

    public CompatiblePersistentState() {
        this("mcpitanlib_" + count);
        count++;
    }

    // 1.16
    @Deprecated
    public void readNbt(NbtCompound tag) {
        readNbt(new ReadNbtArgs(tag));
    }

    public abstract void readNbt(ReadNbtArgs args);

    @Deprecated
    @Override
    public void fromTag(NbtCompound tag) {
        readNbt(tag);
    }

    @Deprecated
    @Override
    public NbtCompound writeNbt(NbtCompound nbt) {
        return writeNbt(new WriteNbtArgs(nbt));
    }

    public abstract NbtCompound writeNbt(WriteNbtArgs args);
}

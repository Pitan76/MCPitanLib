package net.pitan76.mcpitanlib.midohra.world;

import net.minecraft.nbt.CompoundTag;
import net.pitan76.mcpitanlib.api.event.nbt.WriteNbtArgs;
import net.pitan76.mcpitanlib.api.world.CompatiblePersistentState;
import net.pitan76.mcpitanlib.midohra.nbt.NbtCompound;

public abstract class CompatPersistentState extends CompatiblePersistentState {

    @Deprecated
    @Override
    public CompoundTag writeNbt(WriteNbtArgs args) {
        return writeNbtM(args).toMinecraft();
    }

    public NbtCompound writeNbtM(WriteNbtArgs args) {
        return null;
    }
}

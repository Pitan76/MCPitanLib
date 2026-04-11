package net.pitan76.mcpitanlib.midohra.world;

import net.pitan76.mcpitanlib.api.util.PersistentStateUtil;
import net.pitan76.mcpitanlib.api.world.CompatiblePersistentState;
import net.pitan76.mcpitanlib.midohra.nbt.NbtCompound;

import java.util.function.Function;
import java.util.function.Supplier;

public class PersistentStateManager {
    private final net.minecraft.world.PersistentStateManager raw;

    public PersistentStateManager(net.minecraft.world.PersistentStateManager storage) {
        this.raw = storage;
    }

    public static PersistentStateManager of(net.minecraft.world.PersistentStateManager storage) {
        return new PersistentStateManager(storage);
    }

    public net.minecraft.world.PersistentStateManager getRaw() {
        return raw;
    }

    @Override
    public int hashCode() {
        return getRaw().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        PersistentStateManager other = (PersistentStateManager) obj;
        return getRaw().equals(other.getRaw());
    }

    public <T extends CompatiblePersistentState> T getOrCreateCompatiblePersistentState(String id, Supplier<T> supplier, Function<NbtCompound, T> function) {
        return PersistentStateUtil.getOrCreate(getRaw(), id, supplier, (nbt) -> function.apply(NbtCompound.of(nbt)));
    }

    public PersistentStateWrapper getOrCreate(String id, Supplier<PersistentStateWrapper> supplier, Function<NbtCompound, PersistentStateWrapper> function) {
        return PersistentStateWrapper.of(
                PersistentStateUtil.getOrCreate(getRaw(), id, () -> supplier.get().getRaw() , (nbt) -> function.apply(NbtCompound.of(nbt)).getRaw()));
    }
}

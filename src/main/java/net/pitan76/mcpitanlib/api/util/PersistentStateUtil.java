package net.pitan76.mcpitanlib.api.util;

import com.mojang.serialization.Codec;
import net.minecraft.util.datafix.DataFixTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.saveddata.SavedData;
import net.minecraft.world.level.storage.DimensionDataStorage;
import net.minecraft.world.level.saveddata.SavedDataType;
import net.minecraft.world.level.Level;
import net.pitan76.mcpitanlib.api.event.nbt.ReadNbtArgs;
import net.pitan76.mcpitanlib.api.event.nbt.WriteNbtArgs;
import net.pitan76.mcpitanlib.api.world.CompatiblePersistentState;

import java.util.function.Function;
import java.util.function.Supplier;

public class PersistentStateUtil {
    public static <T extends SavedData> T getOrCreate(DimensionDataStorage manager, String id, Supplier<T> supplier, Function<CompoundTag, T> function) {
        Codec<T> codec = CompoundTag.CODEC.xmap(
                // NBT -> PersistentState
                (nbt) -> {
                    T state = supplier.get();

                    if (state instanceof CompatiblePersistentState)
                        ((CompatiblePersistentState) state).readNbt(new ReadNbtArgs(nbt));

                    return state;
                },
                // PersistentState -> NBT
                (state) -> {
                    if (state instanceof CompatiblePersistentState)
                        return ((CompatiblePersistentState) state).writeNbt(new WriteNbtArgs(new CompoundTag()));

                    return NbtUtil.create();
                }
        );

        SavedDataType<T> type = new SavedDataType<>(id, supplier, codec, DataFixTypes.LEVEL);
        return manager.computeIfAbsent(type);
    }

    public static DimensionDataStorage getManagerFromServer(MinecraftServer server) {
        return server.getLevel(Level.OVERWORLD).getDataStorage();
    }

    public static DimensionDataStorage getManagerFromWorld(ServerLevel world) {
        return world.getDataStorage();
    }

    public static void markDirty(SavedData state) {
        state.setDirty();
    }
}
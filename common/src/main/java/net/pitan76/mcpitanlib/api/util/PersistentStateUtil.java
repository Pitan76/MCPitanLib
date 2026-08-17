package net.pitan76.mcpitanlib.api.util;

import com.mojang.serialization.Codec;
import net.minecraft.util.datafix.DataFixTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.saveddata.SavedData;
import net.minecraft.world.level.saveddata.SavedDataType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.SavedDataStorage;
import net.pitan76.mcpitanlib.api.event.nbt.ReadNbtArgs;
import net.pitan76.mcpitanlib.api.event.nbt.WriteNbtArgs;
import net.pitan76.mcpitanlib.api.world.CompatiblePersistentState;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Supplier;

public class PersistentStateUtil {
    private static final Map<String, SavedDataType<?>> TYPE_CACHE = new ConcurrentHashMap<>();

    @SuppressWarnings("unchecked")
    public static <T extends SavedData> T getOrCreate(SavedDataStorage manager, String id, Supplier<T> supplier, Function<CompoundTag, T> function) {
        SavedDataType<T> type = (SavedDataType<T>) TYPE_CACHE.computeIfAbsent(id, key -> createType(key, supplier, function));
        return manager.computeIfAbsent(type);
    }

    private static <T extends SavedData> SavedDataType<T> createType(String id, Supplier<T> supplier, Function<CompoundTag, T> function) {
        Codec<T> codec = CompoundTag.CODEC.xmap(
                // NBT -> PersistentState
                (nbt) -> {
                    T state = function != null ? function.apply(nbt) : null;

                    if (state == null) {
                        state = supplier.get();

                        if (state instanceof CompatiblePersistentState)
                            ((CompatiblePersistentState) state).readNbt(new ReadNbtArgs(nbt));
                    }


                    return state;
                },
                // PersistentState -> NBT
                (state) -> {
                    CompoundTag tag;

                    if (state instanceof CompatiblePersistentState)
                        tag = ((CompatiblePersistentState) state).writeNbt(new WriteNbtArgs(new CompoundTag()));
                    else
                        tag = NbtUtil.create();


                    return tag;
                }
        );

        return new SavedDataType<>(IdentifierUtil.id("mcpitanlib", id), supplier, codec, DataFixTypes.LEVEL);
    }

    public static SavedDataStorage getManagerFromServer(MinecraftServer server) {
        return server.getLevel(Level.OVERWORLD).getDataStorage();
    }

    public static SavedDataStorage getManagerFromWorld(ServerLevel world) {
        return world.getDataStorage();
    }

    public static void markDirty(SavedData state) {
        state.setDirty();
    }
}
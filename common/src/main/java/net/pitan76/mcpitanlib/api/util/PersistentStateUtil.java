package net.pitan76.mcpitanlib.api.util;

import com.mojang.serialization.Codec;
import com.mojang.serialization.Decoder;
import com.mojang.serialization.Encoder;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.datafixer.DataFixTypes;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtOps;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.PersistentState;
import net.minecraft.world.PersistentStateManager;
import net.minecraft.world.PersistentStateType;
import net.minecraft.world.World;
import net.pitan76.mcpitanlib.api.event.nbt.ReadNbtArgs;
import net.pitan76.mcpitanlib.api.world.CompatiblePersistentState;

import java.io.IOException;
import java.util.function.Function;
import java.util.function.Supplier;

public class PersistentStateUtil {
    public static <T extends PersistentState> T getOrCreate(PersistentStateManager manager, String id, Supplier<T> supplier, Function<NbtCompound, T> function) {
        NbtCompound nbt;
        try {
            nbt = manager.readNbt(id, DataFixTypes.LEVEL, 0);
        } catch (IOException e) {
            nbt = new NbtCompound();
        }
        Codec<T> codec = Codec.of(Encoder.empty(), Decoder.unit(function.apply(nbt))).codec();
        PersistentStateType<T> type = new PersistentStateType<>(id, supplier, codec, DataFixTypes.LEVEL);
        return manager.getOrCreate(type);
    }

    public static PersistentStateManager getManagerFromServer(MinecraftServer server) {
        return server.getWorld(World.OVERWORLD).getPersistentStateManager();
    }

    public static PersistentStateManager getManagerFromWorld(ServerWorld world) {
        return world.getPersistentStateManager();
    }

    public static void markDirty(PersistentState state) {
        state.markDirty();
    }
}
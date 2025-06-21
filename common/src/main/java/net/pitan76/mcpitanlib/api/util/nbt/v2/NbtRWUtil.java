package net.pitan76.mcpitanlib.api.util.nbt.v2;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.pitan76.mcpitanlib.api.event.nbt.ReadNbtArgs;
import net.pitan76.mcpitanlib.api.event.nbt.WriteNbtArgs;
import net.pitan76.mcpitanlib.api.registry.CompatRegistryLookup;
import net.pitan76.mcpitanlib.api.util.NbtUtil;
import net.pitan76.mcpitanlib.api.util.collection.ItemStackList;
import net.pitan76.mcpitanlib.api.util.math.PosUtil;
import net.pitan76.mcpitanlib.api.util.math.Vec3dUtil;
import net.pitan76.mcpitanlib.api.util.math.Vec3iUtil;
import net.pitan76.mcpitanlib.api.util.nbt.InvRWUtil;
import net.pitan76.mcpitanlib.midohra.util.math.BlockPos;

public class NbtRWUtil extends net.pitan76.mcpitanlib.api.util.nbt.NbtRWUtil {
    public static void putInv(WriteNbtArgs args, ItemStackList stacks) {
        InvRWUtil.putInv(args, stacks);
    }

    public static void getInv(ReadNbtArgs args, ItemStackList stacks) {
        InvRWUtil.getInv(args, stacks);
    }

    public static WriteNbtArgs create(CompatRegistryLookup registryLookup) {
        return new WriteNbtArgs(NbtUtil.create(), registryLookup);
    }

    public static WriteNbtArgs create() {
        return create(null);
    }

    public static void put(WriteNbtArgs parent, WriteNbtArgs child, String key) {
        NbtUtil.put(parent.nbt, key, child.nbt);
    }

    public static WriteNbtArgs putWithCreate(WriteNbtArgs parent, String key) {
        NbtCompound nbt = NbtUtil.create();
        NbtUtil.put(parent.nbt, key, nbt);

        return new WriteNbtArgs(nbt, parent.registryLookup);
    }

    public static ReadNbtArgs get(ReadNbtArgs parent, String key) {
        NbtCompound nbt = NbtUtil.get(parent.nbt, key);
        return new ReadNbtArgs(nbt, parent.registryLookup);
    }

    public static ReadNbtArgs getOrDefault(ReadNbtArgs parent, String key, ReadNbtArgs defaultValue) {
        return NbtUtil.has(parent.nbt, key) ? get(parent, key) : defaultValue;
    }

    public static void putPos3i(WriteNbtArgs args, String key, int x, int y, int z) {
        WriteNbtArgs args2 = putWithCreate(args, key);
        putInt(args2, "x", x);
        putInt(args2, "y", y);
        putInt(args2, "z", z);
    }

    public static void putPos3d(WriteNbtArgs args, String key, double x, double y, double z) {
        WriteNbtArgs args2 = putWithCreate(args, key);
        putDouble(args2, "x", x);
        putDouble(args2, "y", y);
        putDouble(args2, "z", z);
    }

    public static Vec3i getPos3i(ReadNbtArgs args, String key) {
        ReadNbtArgs args2 = get(args, key);
        int x = getInt(args2, "x");
        int y = getInt(args2, "y");
        int z = getInt(args2, "z");
        return Vec3iUtil.create(x, y, z);
    }

    public static Vec3d getPos3d(ReadNbtArgs args, String key) {
        ReadNbtArgs args2 = get(args, key);
        double x = getDouble(args2, "x");
        double y = getDouble(args2, "y");
        double z = getDouble(args2, "z");
        return Vec3dUtil.create(x, y, z);
    }

    public static void putBlockPos(WriteNbtArgs args, String key, BlockPos pos) {
        putPos3i(args, key, pos.getX(), pos.getY(), pos.getZ());
    }

    public static BlockPos getBlockPos(ReadNbtArgs args, String key) {
        ReadNbtArgs args2 = get(args, key);
        int x = getInt(args2, "x");
        int y = getInt(args2, "y");
        int z = getInt(args2, "z");
        return BlockPos.of(x, y, z);
    }

    public static void putBlockPos(WriteNbtArgs args, String key, net.minecraft.util.math.BlockPos pos) {
        putPos3i(args, key, PosUtil.x(pos), PosUtil.y(pos), PosUtil.z(pos));
    }

    public static net.minecraft.util.math.BlockPos getBlockPosV(ReadNbtArgs args, String key) {
        ReadNbtArgs args2 = get(args, key);
        int x = getInt(args2, "x");
        int y = getInt(args2, "y");
        int z = getInt(args2, "z");
        return PosUtil.flooredBlockPos(x, y, z);
    }
}

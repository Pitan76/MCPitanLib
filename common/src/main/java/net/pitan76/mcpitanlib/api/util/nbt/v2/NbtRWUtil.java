package net.pitan76.mcpitanlib.api.util.nbt.v2;

import net.minecraft.world.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.storage.TagValueOutput;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.util.ProblemReporter;
import net.minecraft.world.phys.Vec3;
import net.minecraft.core.Vec3i;
import net.pitan76.mcpitanlib.api.event.nbt.ReadNbtArgs;
import net.pitan76.mcpitanlib.api.event.nbt.WriteNbtArgs;
import net.pitan76.mcpitanlib.api.registry.CompatRegistryLookup;
import net.pitan76.mcpitanlib.api.util.collection.ItemStackList;
import net.pitan76.mcpitanlib.api.util.math.PosUtil;
import net.pitan76.mcpitanlib.api.util.math.Vec3dUtil;
import net.pitan76.mcpitanlib.api.util.math.Vec3iUtil;
import net.pitan76.mcpitanlib.api.util.nbt.InvRWUtil;
import net.pitan76.mcpitanlib.midohra.util.math.BlockPos;

import java.util.Optional;

public class NbtRWUtil extends net.pitan76.mcpitanlib.api.util.nbt.NbtRWUtil {
    public static void putInv(WriteNbtArgs args, ItemStackList stacks) {
        InvRWUtil.putInv(args, stacks);
    }

    public static void getInv(ReadNbtArgs args, ItemStackList stacks) {
        InvRWUtil.getInv(args, stacks);
    }

    public static WriteNbtArgs create(CompatRegistryLookup registryLookup) {
        TagValueOutput view = _view(registryLookup);
        return new WriteNbtArgs(view.buildResult(), view, registryLookup);
    }

    public static WriteNbtArgs create() {
        return create(null);
    }

    public static void put(WriteNbtArgs parent, WriteNbtArgs child, String key) {
        if (child.view instanceof TagValueOutput childView)
            parent.view.store(key, CompoundTag.CODEC, childView.buildResult());
    }

    public static WriteNbtArgs putWithCreate(WriteNbtArgs parent, String key) {
        TagValueOutput subView = _view(parent.registryLookup);
        parent.view.store(key, CompoundTag.CODEC, subView.buildResult());
        return new WriteNbtArgs(subView.buildResult(), subView, parent.registryLookup);
    }

    public static ReadNbtArgs get(ReadNbtArgs parent, String key) {
        ValueInput view = parent.view.childOrEmpty(key);
        return new ReadNbtArgs(null, view, parent.registryLookup);
    }

    public static ReadNbtArgs getOrDefault(ReadNbtArgs parent, String key, ReadNbtArgs defaultValue) {
        Optional<ValueInput> view = parent.view.child(key);
        return view.map(readView -> new ReadNbtArgs(null, readView, parent.registryLookup)).orElse(defaultValue);
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

    public static Vec3 getPos3d(ReadNbtArgs args, String key) {
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

    public static void putBlockPos(WriteNbtArgs args, String key, net.minecraft.core.BlockPos pos) {
        putPos3i(args, key, PosUtil.x(pos), PosUtil.y(pos), PosUtil.z(pos));
    }

    public static net.minecraft.core.BlockPos getBlockPosV(ReadNbtArgs args, String key) {
        ReadNbtArgs args2 = get(args, key);
        int x = getInt(args2, "x");
        int y = getInt(args2, "y");
        int z = getInt(args2, "z");
        return PosUtil.flooredBlockPos(x, y, z);
    }

    private static TagValueOutput _view(CompatRegistryLookup registryLookup) {
        return TagValueOutput.createWithContext(ProblemReporter.DISCARDING, registryLookup.getRegistryLookup());
    }

    public static void putItemStack(WriteNbtArgs args, String key, ItemStack stack) {
        args.view.store(key, ItemStack.CODEC, stack);
    }

    public static Optional<ItemStack> getItemStack(ReadNbtArgs args, String key) {
        return args.view.read(key, ItemStack.CODEC);
    }
}

package net.pitan76.mcpitanlib.api.util.nbt;

import net.minecraft.nbt.CompoundTag;
import net.pitan76.mcpitanlib.api.event.nbt.NbtRWArgs;
import net.pitan76.mcpitanlib.api.event.nbt.ReadNbtArgs;
import net.pitan76.mcpitanlib.api.event.nbt.WriteNbtArgs;
import net.pitan76.mcpitanlib.api.util.NbtUtil;

public class NbtRWUtil {

    public static void putBoolean(WriteNbtArgs args, String key, boolean value) {
        args.view.putBoolean(key, value);
    }

    public static boolean getBoolean(ReadNbtArgs args, String key) {
        return args.view.getBooleanOr(key, false);
    }

    public static void putByte(WriteNbtArgs args, String key, byte value) {
        args.view.putByte(key, value);
    }

    public static byte getByte(ReadNbtArgs args, String key) {
        return args.view.getByteOr(key, (byte) 0);
    }

    public static void putInt(WriteNbtArgs args, String key, int value) {
        args.view.putInt(key, value);
    }

    public static int getInt(ReadNbtArgs args, String key) {
        return args.view.getIntOr(key, 0);
    }

    public static void putLong(WriteNbtArgs args, String key, long value) {
        args.view.putLong(key, value);
    }

    public static long getLong(ReadNbtArgs args, String key) {
        return args.view.getLongOr(key, 0L);
    }

    public static void putShort(WriteNbtArgs args, String key, short value) {
        args.view.putShort(key, value);
    }

    public static short getShort(ReadNbtArgs args, String key) {
        return (short) args.view.getShortOr(key, (short) 0);
    }

    public static void putFloat(WriteNbtArgs args, String key, float value) {
        args.view.putFloat(key, value);
    }

    public static float getFloat(ReadNbtArgs args, String key) {
        return args.view.getFloatOr(key, 0.0f);
    }

    public static void putDouble(WriteNbtArgs args, String key, double value) {
        args.view.putDouble(key, value);
    }

    public static double getDouble(ReadNbtArgs args, String key) {
        return args.view.getDoubleOr(key, 0.0);
    }

    public static void putString(WriteNbtArgs args, String key, String value) {
        args.view.putString(key, value);
    }

    public static String getString(ReadNbtArgs args, String key) {
        return args.view.getStringOr(key, "");
    }

    public static void putIntArray(WriteNbtArgs args, String key, int[] value) {
        args.view.putIntArray(key, value);
    }

    public static int[] getIntArray(ReadNbtArgs args, String key) {
        return args.view.getIntArray(key).orElse(new int[0]);
    }

    public static boolean getBooleanOrDefault(ReadNbtArgs args, String key, boolean defaultValue) {
        return args.view.getBooleanOr(key, defaultValue);
    }

    public static byte getByteOrDefault(ReadNbtArgs args, String key, byte defaultValue) {
        return args.view.getByteOr(key, defaultValue);
    }

    public static int getIntOrDefault(ReadNbtArgs args, String key, int defaultValue) {
        return args.view.getIntOr(key, defaultValue);
    }

    public static long getLongOrDefault(ReadNbtArgs args, String key, long defaultValue) {
        return args.view.getLongOr(key, defaultValue);
    }

    public static short getShortOrDefault(ReadNbtArgs args, String key, short defaultValue) {
        return (short) args.view.getShortOr(key, defaultValue);
    }

    public static float getFloatOrDefault(ReadNbtArgs args, String key, float defaultValue) {
        return args.view.getFloatOr(key, defaultValue);
    }

    public static double getDoubleOrDefault(ReadNbtArgs args, String key, double defaultValue) {
        return args.view.getDoubleOr(key, defaultValue);
    }

    public static String getStringOrDefault(ReadNbtArgs args, String key, String defaultValue) {
        return args.view.getStringOr(key, defaultValue);
    }

    public static int[] getIntArrayOrDefault(ReadNbtArgs args, String key, int[] defaultValue) {
        return args.view.getIntArray(key).orElse(defaultValue);
    }

    public static boolean isEmpty(NbtRWArgs args) {
        if (args instanceof WriteNbtArgs)
            return ((WriteNbtArgs) args).view.isEmpty();
        else
            return true;
    }

    public static void put(WriteNbtArgs args, String key, NbtRWArgs other) {
        putCompound(args, key, other.nbt);
    }

    public static NbtRWArgs get(ReadNbtArgs args, String key) {
        return new NbtRWArgs(getCompound(args, key));
    }

    public static void putCompound(WriteNbtArgs args, String key, CompoundTag other) {
        args.view.store(key, CompoundTag.CODEC, other);
    }

    public static CompoundTag getCompound(ReadNbtArgs args, String key) {
        return args.view.read(key, CompoundTag.CODEC).orElse(NbtUtil.create());
    }

    public static boolean has(NbtRWArgs args, String key) {
        return NbtUtil.has(args.getNbt(), key);
    }
}

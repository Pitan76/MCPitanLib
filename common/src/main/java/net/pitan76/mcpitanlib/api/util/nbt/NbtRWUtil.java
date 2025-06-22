package net.pitan76.mcpitanlib.api.util.nbt;

import net.minecraft.nbt.NbtCompound;
import net.pitan76.mcpitanlib.api.event.nbt.NbtRWArgs;
import net.pitan76.mcpitanlib.api.event.nbt.ReadNbtArgs;
import net.pitan76.mcpitanlib.api.event.nbt.WriteNbtArgs;
import net.pitan76.mcpitanlib.api.util.NbtUtil;

public class NbtRWUtil {

    public static void putBoolean(WriteNbtArgs args, String key, boolean value) {
        NbtUtil.putBoolean(args.getNbt(), key, value);
    }

    public static boolean getBoolean(ReadNbtArgs args, String key) {
        return NbtUtil.getBoolean(args.getNbt(), key);
    }

    public static void putByte(WriteNbtArgs args, String key, byte value) {
        NbtUtil.putByte(args.getNbt(), key, value);
    }

    public static byte getByte(ReadNbtArgs args, String key) {
        return NbtUtil.getByte(args.getNbt(), key);
    }

    public static void putInt(WriteNbtArgs args, String key, int value) {
        NbtUtil.putInt(args.getNbt(), key, value);
    }

    public static int getInt(ReadNbtArgs args, String key) {
        return NbtUtil.getInt(args.getNbt(), key);
    }

    public static void putLong(WriteNbtArgs args, String key, long value) {
        NbtUtil.putLong(args.getNbt(), key, value);
    }

    public static long getLong(ReadNbtArgs args, String key) {
        return NbtUtil.getLong(args.getNbt(), key);
    }

    public static void putShort(WriteNbtArgs args, String key, short value) {
        NbtUtil.putShort(args.getNbt(), key, value);
    }

    public static short getShort(ReadNbtArgs args, String key) {
        return NbtUtil.getShort(args.getNbt(), key);
    }

    public static void putFloat(WriteNbtArgs args, String key, float value) {
        NbtUtil.putFloat(args.getNbt(), key, value);
    }

    public static float getFloat(ReadNbtArgs args, String key) {
        return NbtUtil.getFloat(args.getNbt(), key);
    }

    public static void putDouble(WriteNbtArgs args, String key, double value) {
        NbtUtil.putDouble(args.getNbt(), key, value);
    }

    public static double getDouble(ReadNbtArgs args, String key) {
        return NbtUtil.getDouble(args.getNbt(), key);
    }
    public static void putString(WriteNbtArgs args, String key, String value) {
        NbtUtil.putString(args.getNbt(), key, value);
    }

    public static String getString(ReadNbtArgs args, String key) {
        return NbtUtil.getString(args.getNbt(), key);
    }

    public static void putIntArray(WriteNbtArgs args, String key, int[] value) {
        args.getNbt().putIntArray(key, value);
    }

    public static int[] getIntArray(ReadNbtArgs args, String key) {
        return args.nbt.getIntArray(key);
    }

    public static boolean getBooleanOrDefault(ReadNbtArgs args, String key, boolean defaultValue) {
        return NbtUtil.getBooleanOrDefault(args.getNbt(), key, defaultValue);
    }

    public static byte getByteOrDefault(ReadNbtArgs args, String key, byte defaultValue) {
        return NbtUtil.getByteOrDefault(args.getNbt(), key, defaultValue);
    }

    public static int getIntOrDefault(ReadNbtArgs args, String key, int defaultValue) {
        return NbtUtil.getIntOrDefault(args.getNbt(), key, defaultValue);
    }

    public static long getLongOrDefault(ReadNbtArgs args, String key, long defaultValue) {
        return NbtUtil.getLongOrDefault(args.getNbt(), key, defaultValue);
    }

    public static short getShortOrDefault(ReadNbtArgs args, String key, short defaultValue) {
        return NbtUtil.getShortOrDefault(args.getNbt(), key, defaultValue);
    }

    public static float getFloatOrDefault(ReadNbtArgs args, String key, float defaultValue) {
        return NbtUtil.getFloatOrDefault(args.getNbt(), key, defaultValue);
    }

    public static double getDoubleOrDefault(ReadNbtArgs args, String key, double defaultValue) {
        return NbtUtil.getDoubleOrDefault(args.getNbt(), key, defaultValue);
    }

    public static String getStringOrDefault(ReadNbtArgs args, String key, String defaultValue) {
        return NbtUtil.getStringOrDefault(args.getNbt(), key, defaultValue);
    }

    public static int[] getIntArrayOrDefault(ReadNbtArgs args, String key, int[] defaultValue) {
        if (!NbtUtil.has(args.nbt, key)) return defaultValue;
        return args.nbt.getIntArray(key);
    }

    public static boolean isEmpty(NbtRWArgs args) {
        return args.nbt.isEmpty();
    }

    public static void put(WriteNbtArgs args, String key, NbtRWArgs other) {
        putCompound(args, key, other.nbt);
    }

    public static NbtRWArgs get(ReadNbtArgs args, String key) {
        return new NbtRWArgs(getCompound(args, key));
    }

    public static void putCompound(WriteNbtArgs args, String key, NbtCompound other) {
        NbtUtil.put(args.nbt, key, other);
    }

    public static NbtCompound getCompound(ReadNbtArgs args, String key) {
        return NbtUtil.get(args.nbt, key);
    }
}

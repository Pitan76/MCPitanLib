package net.pitan76.mcpitanlib.api.util.nbt;

import net.minecraft.nbt.NbtCompound;
import net.pitan76.mcpitanlib.api.event.nbt.NbtRWArgs;
import net.pitan76.mcpitanlib.api.event.nbt.ReadNbtArgs;
import net.pitan76.mcpitanlib.api.event.nbt.WriteNbtArgs;
import net.pitan76.mcpitanlib.api.util.NbtUtil;

public class NbtRWUtil {

    public static void putBoolean(WriteNbtArgs args, String key, boolean value) {
        args.view.putBoolean(key, value);
    }

    public static boolean getBoolean(ReadNbtArgs args, String key) {
        return args.view.getBoolean(key, false);
    }

    public static void putByte(WriteNbtArgs args, String key, byte value) {
        args.view.putByte(key, value);
    }

    public static byte getByte(ReadNbtArgs args, String key) {
        return args.view.getByte(key, (byte) 0);
    }

    public static void putInt(WriteNbtArgs args, String key, int value) {
        args.view.putInt(key, value);
    }

    public static int getInt(ReadNbtArgs args, String key) {
        return args.view.getInt(key, 0);
    }

    public static void putLong(WriteNbtArgs args, String key, long value) {
        args.view.putLong(key, value);
    }

    public static long getLong(ReadNbtArgs args, String key) {
        return args.view.getLong(key, 0L);
    }

    public static void putShort(WriteNbtArgs args, String key, short value) {
        args.view.putShort(key, value);
    }

    public static short getShort(ReadNbtArgs args, String key) {
        return (short) args.view.getShort(key, (short) 0);
    }

    public static void putFloat(WriteNbtArgs args, String key, float value) {
        args.view.putFloat(key, value);
    }

    public static float getFloat(ReadNbtArgs args, String key) {
        return args.view.getFloat(key, 0.0f);
    }

    public static void putDouble(WriteNbtArgs args, String key, double value) {
        args.view.putDouble(key, value);
    }

    public static double getDouble(ReadNbtArgs args, String key) {
        return args.view.getDouble(key, 0.0);
    }

    public static void putString(WriteNbtArgs args, String key, String value) {
        args.view.putString(key, value);
    }

    public static String getString(ReadNbtArgs args, String key) {
        return args.view.getString(key, "");
    }

    public static void putIntArray(WriteNbtArgs args, String key, int[] value) {
        args.view.putIntArray(key, value);
    }

    public static int[] getIntArray(ReadNbtArgs args, String key) {
        return args.view.getOptionalIntArray(key).orElse(new int[0]);
    }

    public static boolean getBooleanOrDefault(ReadNbtArgs args, String key, boolean defaultValue) {
        return args.view.getBoolean(key, defaultValue);
    }

    public static byte getByteOrDefault(ReadNbtArgs args, String key, byte defaultValue) {
        return args.view.getByte(key, defaultValue);
    }

    public static int getIntOrDefault(ReadNbtArgs args, String key, int defaultValue) {
        return args.view.getInt(key, defaultValue);
    }

    public static long getLongOrDefault(ReadNbtArgs args, String key, long defaultValue) {
        return args.view.getLong(key, defaultValue);
    }

    public static short getShortOrDefault(ReadNbtArgs args, String key, short defaultValue) {
        return (short) args.view.getShort(key, defaultValue);
    }

    public static float getFloatOrDefault(ReadNbtArgs args, String key, float defaultValue) {
        return args.view.getFloat(key, defaultValue);
    }

    public static double getDoubleOrDefault(ReadNbtArgs args, String key, double defaultValue) {
        return args.view.getDouble(key, defaultValue);
    }

    public static String getStringOrDefault(ReadNbtArgs args, String key, String defaultValue) {
        return args.view.getString(key, defaultValue);
    }

    public static int[] getIntArrayOrDefault(ReadNbtArgs args, String key, int[] defaultValue) {
        return args.view.getOptionalIntArray(key).orElse(defaultValue);
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

    public static void putCompound(WriteNbtArgs args, String key, NbtCompound other) {
        args.view.put(key, NbtCompound.CODEC, other);
    }

    public static NbtCompound getCompound(ReadNbtArgs args, String key) {
        return args.view.read(key, NbtCompound.CODEC).orElse(NbtUtil.create());
    }
}

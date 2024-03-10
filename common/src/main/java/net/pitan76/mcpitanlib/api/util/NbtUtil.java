package net.pitan76.mcpitanlib.api.util;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;

import java.util.Set;

public class NbtUtil {

    /**
     * 新しいNbtCompoundを作成する。
     * @return NbtCompound
     */
    public static NbtCompound create() {
        return new NbtCompound();
    }

    /**
     * 値を設定する。
     * @param nbt NbtCompound
     * @param key キー
     * @param value 値
     */
    public static void put(NbtCompound nbt, String key, NbtCompound value) {
        nbt.put(key, value);
    }

    /**
     * 値を取得する。
     * @param nbt NbtCompound
     * @param key キー
     * @return 値
     */
    public static NbtCompound get(NbtCompound nbt, String key) {
        return nbt.getCompound(key);
    }

    /**
     * 値を削除する。
     * @param nbt NbtCompound
     * @param key キー
     */
    public static void remove(NbtCompound nbt, String key) {
        nbt.remove(key);
    }

    /**
     * 値が存在するかどうかを取得する。
     * @param nbt NbtCompound
     * @param key キー
     * @return 値が存在するかどうか
     */
    public static boolean has(NbtCompound nbt, String key) {
        return nbt.contains(key);
    }

    /**
     * 指定した型の値を取得する
     * @param nbt NbtCompound
     * @param key キー
     * @param clazz クラス
     * @param <T> 値
     */
    public static <T> T get(NbtCompound nbt, String key, Class<T> clazz) {
        if (clazz == Integer.class) {
            return (T) Integer.valueOf(nbt.getInt(key));
        }
        if (clazz == String.class) {
            return (T) nbt.getString(key);
        }
        if (clazz == Boolean.class) {
            return (T) Boolean.valueOf(nbt.getBoolean(key));
        }
        if (clazz == Float.class) {
            return (T) Float.valueOf(nbt.getFloat(key));
        }
        if (clazz == Double.class) {
            return (T) Double.valueOf(nbt.getDouble(key));
        }
        if (clazz == Long.class) {
            return (T) Long.valueOf(nbt.getLong(key));
        }
        if (clazz == NbtCompound.class) {
            return (T) nbt.getCompound(key);
        }
        if (clazz == NbtList.class) {
            return (T) nbt.getList(key, 9);
        }
        if (clazz == Byte.class) {
            return (T) Byte.valueOf(nbt.getByte(key));
        }
        if (clazz == Short.class) {
            return (T) Short.valueOf(nbt.getShort(key));
        }
        return null;
    }

    /**
     * 値を設定する。
     * @param nbt NbtCompound
     * @param key キー
     * @param value 値
     */
    public static <T> void set(NbtCompound nbt, String key, T value) {
        if (value instanceof Integer) {
            nbt.putInt(key, (Integer) value);
            return;
        }
        if (value instanceof String) {
            nbt.putString(key, (String) value);
            return;
        }
        if (value instanceof Boolean) {
            nbt.putBoolean(key, (Boolean) value);
            return;
        }
        if (value instanceof Float) {
            nbt.putFloat(key, (Float) value);
            return;
        }
        if (value instanceof Double) {
            nbt.putDouble(key, (Double) value);
            return;
        }
        if (value instanceof Long) {
            nbt.putLong(key, (Long) value);
            return;
        }
        if (value instanceof NbtCompound) {
            nbt.put(key, (NbtCompound) value);
            return;
        }
        if (value instanceof NbtList) {
            nbt.put(key, (NbtList) value);
            return;
        }
        if (value instanceof Byte) {
            nbt.putByte(key, (Byte) value);
            return;
        }
        if (value instanceof Short) {
            nbt.putShort(key, (Short) value);
            return;
        }
    }

    /**
     * キーの一覧を取得する。
     * @param nbt NbtCompound
     * @return キーの一覧
     */
    public static Set<String> getKeys(NbtCompound nbt) {
        return nbt.getKeys();
    }
}

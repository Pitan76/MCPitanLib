package net.pitan76.mcpitanlib.api.util;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.pitan76.mcpitanlib.api.registry.CompatRegistryLookup;
import net.pitan76.mcpitanlib.api.util.math.PosUtil;
import net.pitan76.mcpitanlib.api.util.math.Vec3dUtil;
import net.pitan76.mcpitanlib.api.util.math.Vec3iUtil;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

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
     * 値を設定する。
     * @param nbt NbtCompound
     * @param key キー
     * @param value 値
     */
    public static void put(NbtCompound nbt, String key, NbtElement value) {
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
            return (T) nbt.get(key);
        }
        if (clazz == Byte.class) {
            return (T) Byte.valueOf(nbt.getByte(key));
        }
        if (clazz == Short.class) {
            return (T) Short.valueOf(nbt.getShort(key));
        }
        if (clazz == UUID.class) {
            return (T) nbt.getUuid(key);
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
        if (value instanceof UUID) {
            nbt.putUuid(key, (UUID) value);
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

    /**
     * NbtListを取得する。
     * @return NbtList
     */
    public static NbtList getList(NbtCompound nbt, String key) {
        return (NbtList) nbt.get(key);
    }

    /**
     * NbtListを取得する。
     * @return NbtList
     */
    public static NbtList getList(NbtCompound nbt, String key, int type) {
        return nbt.getList(key, type);
    }

    /**
     * NbtCompoundのリストを取得する。
     * @return NbtList
     */
    public static NbtList getNbtCompoundList(NbtCompound nbt, String key) {
        return nbt.getList(key, NbtElement.COMPOUND_TYPE);
    }

    /**
     * NbtCompoundをコピーする。
     * @return NbtCompound
     */
    public static NbtCompound copy(NbtCompound nbt) {
        return nbt.copy();
    }

    // Helper methods

    public static void putInt(NbtCompound nbt, String key, int value) {
        set(nbt, key, value);
    }

    public static int getInt(NbtCompound nbt, String key) {
        if (has(nbt, key))
            return get(nbt, key, Integer.class);
        return 0;
    }

    public static void putString(NbtCompound nbt, String key, String value) {
        set(nbt, key, value);
    }

    public static String getString(NbtCompound nbt, String key) {
        if (has(nbt, key))
            return get(nbt, key, String.class);
        return "";
    }

    public static void putBoolean(NbtCompound nbt, String key, boolean value) {
        set(nbt, key, value);
    }

    public static boolean getBoolean(NbtCompound nbt, String key) {
        if (has(nbt, key))
            return get(nbt, key, Boolean.class);
        return false;
    }

    public static void putFloat(NbtCompound nbt, String key, float value) {
        set(nbt, key, value);
    }

    public static float getFloat(NbtCompound nbt, String key) {
        if (has(nbt, key))
            return get(nbt, key, Float.class);
        return 0;
    }

    public static void putDouble(NbtCompound nbt, String key, double value) {
        set(nbt, key, value);
    }

    public static double getDouble(NbtCompound nbt, String key) {
        if (has(nbt, key))
            return get(nbt, key, Double.class);
        return 0;
    }

    public static void putLong(NbtCompound nbt, String key, long value) {
        set(nbt, key, value);
    }

    public static long getLong(NbtCompound nbt, String key) {
        if (has(nbt, key))
            return get(nbt, key, Long.class);
        return 0;
    }

    public static void putByte(NbtCompound nbt, String key, byte value) {
        set(nbt, key, value);
    }

    public static byte getByte(NbtCompound nbt, String key) {
        if (has(nbt, key))
            return get(nbt, key, Byte.class);
        return 0;
    }

    public static void putShort(NbtCompound nbt, String key, short value) {
        set(nbt, key, value);
    }

    public static short getShort(NbtCompound nbt, String key) {
        if (has(nbt, key))
            return get(nbt, key, Short.class);
        return 0;
    }

    public static void putUuid(NbtCompound nbt, String key, UUID value) {
        set(nbt, key, value);
    }

    public static UUID getUuid(NbtCompound nbt, String key) {
        if (has(nbt, key))
            return get(nbt, key, UUID.class);
        return null;
    }

    /**
     * BlockPosを設定する。
     * key: {
     *   "x": pos.getX(),
     *   "y": pos.getY(),
     *   "z": pos.getZ()
     * }
     *
     * @param nbt NbtCompound
     * @param key キー
     * @param pos BlockPos
     */
    public static void setBlockPos(NbtCompound nbt, String key, BlockPos pos) {
        NbtCompound posNbt = create();
        putInt(posNbt, "x", pos.getX());
        putInt(posNbt, "y", pos.getY());
        putInt(posNbt, "z", pos.getZ());
        put(nbt, key, posNbt);
    }

    /**
     * BlockPosを取得する。
     *
     * @param nbt NbtCompound
     * @param key キー
     * @return BlockPos
     */
    public static BlockPos getBlockPos(NbtCompound nbt, String key) {
        NbtCompound posNbt = get(nbt, key);
        return PosUtil.flooredBlockPos(getInt(posNbt, "x"), getInt(posNbt, "y"), getInt(posNbt, "z"));
    }

    public static void putVec3i(NbtCompound nbt, String key, Vec3i vec3i) {
        NbtCompound vec3iNbt = create();
        putInt(vec3iNbt, "x", vec3i.getX());
        putInt(vec3iNbt, "y", vec3i.getY());
        putInt(vec3iNbt, "z", vec3i.getZ());
        put(nbt, key, vec3iNbt);
    }

    public static Vec3i getVec3i(NbtCompound nbt, String key) {
        NbtCompound vec3iNbt = get(nbt, key);
        return Vec3iUtil.create(getInt(vec3iNbt, "x"), getInt(vec3iNbt, "y"), getInt(vec3iNbt, "z"));
    }

    public static void putVec3d(NbtCompound nbt, String key, Vec3d vec3d) {
        NbtCompound vec3dNbt = create();
        putDouble(vec3dNbt, "x", vec3d.getX());
        putDouble(vec3dNbt, "y", vec3d.getY());
        putDouble(vec3dNbt, "z", vec3d.getZ());
        put(nbt, key, vec3dNbt);
    }

    public static Vec3d getVec3d(NbtCompound nbt, String key) {
        NbtCompound vec3dNbt = get(nbt, key);
        return Vec3dUtil.create(getDouble(vec3dNbt, "x"), getDouble(vec3dNbt, "y"), getDouble(vec3dNbt, "z"));
    }

    public static void putItemStack(NbtCompound nbt, String key, ItemStack stack, CompatRegistryLookup registryLookup) {
        NbtElement stackNbt = stack.toNbt(registryLookup.getRegistryLookup());
        put(nbt, key, stackNbt);
    }

    public static Optional<ItemStack> getItemStack(NbtCompound nbt, String key, CompatRegistryLookup registryLookup) {
        NbtElement stackNbt = get(nbt, key);
        return ItemStack.fromNbt(registryLookup.getRegistryLookup(), stackNbt);
    }

    public static void putSimpleItemStack(NbtCompound nbt, String key, ItemStack stack) {
        NbtCompound stackNbt = create();
        putString(stackNbt, "id", ItemUtil.toID(stack.getItem()).toString());
        putByte(stackNbt, "Count", (byte) ItemStackUtil.getCount(stack));

        NbtCompound tagNbt = create();
        NbtCompound componentsNbt = create();
        put(componentsNbt, "minecraft:custom_data", CustomDataUtil.getOrCreateNbt(stack));
        put(tagNbt, "components", componentsNbt);
        put(stackNbt, "tag", tagNbt);
        put(nbt, key, stackNbt);
    }

    public static Optional<ItemStack> getSimpleItemStack(NbtCompound nbt, String key) {
        if (!has(nbt, key)) return Optional.empty();
        NbtCompound stackNbt = get(nbt, key);

        if (!has(stackNbt, "id") || !has(stackNbt, "Count")) return Optional.empty();
        Item item = ItemUtil.fromId(CompatIdentifier.of(getString(stackNbt, "id")));
        int count = getByte(stackNbt, "Count");

        ItemStack stack = ItemStackUtil.create(item, count);

        if (has(stackNbt, "tag")) {
            NbtCompound tagNbt = get(stackNbt, "tag");
            if (has(tagNbt, "components")) {
                NbtCompound componentsNbt = get(tagNbt, "components");
                if (has(componentsNbt, "minecraft:custom_data")) {
                    CustomDataUtil.setNbt(stack, get(componentsNbt, "minecraft:custom_data"));
                }
            }
        }

        return Optional.of(stack);
    }

    public static NbtList createNbtList() {
        return new NbtList();
    }

    public static int getIntOrDefault(NbtCompound nbt, String key, int defaultValue) {
        if (has(nbt, key))
            return getInt(nbt, key);
        return defaultValue;
    }

    public static String getStringOrDefault(NbtCompound nbt, String key, String defaultValue) {
        if (has(nbt, key))
            return getString(nbt, key);
        return defaultValue;
    }

    public static boolean getBooleanOrDefault(NbtCompound nbt, String key, boolean defaultValue) {
        if (has(nbt, key))
            return getBoolean(nbt, key);
        return defaultValue;
    }

    public static float getFloatOrDefault(NbtCompound nbt, String key, float defaultValue) {
        if (has(nbt, key))
            return getFloat(nbt, key);
        return defaultValue;
    }

    public static double getDoubleOrDefault(NbtCompound nbt, String key, double defaultValue) {
        if (has(nbt, key))
            return getDouble(nbt, key);
        return defaultValue;
    }

    public static long getLongOrDefault(NbtCompound nbt, String key, long defaultValue) {
        if (has(nbt, key))
            return getLong(nbt, key);
        return defaultValue;
    }

    public static byte getByteOrDefault(NbtCompound nbt, String key, byte defaultValue) {
        if (has(nbt, key))
            return getByte(nbt, key);
        return defaultValue;
    }

    public static short getShortOrDefault(NbtCompound nbt, String key, short defaultValue) {
        if (has(nbt, key))
            return getShort(nbt, key);
        return defaultValue;
    }

    public static UUID getUuidOrDefault(NbtCompound nbt, String key, UUID defaultValue) {
        if (has(nbt, key))
            return getUuid(nbt, key);
        return defaultValue;
    }

    public static NbtElement getElement(NbtCompound nbt, String key) {
        return nbt.get(key);
    }

    public static void putElement(NbtCompound nbt, String key, NbtElement element) {
        nbt.put(key, element);
    }
}

package net.pitan76.mcpitanlib.api.util;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.DataResult;
import net.minecraft.nbt.ByteTag;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.DoubleTag;
import net.minecraft.nbt.FloatTag;
import net.minecraft.nbt.IntTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.LongTag;
import net.minecraft.nbt.ShortTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.nbt.*;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.Vec3;
import net.minecraft.core.Vec3i;
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
    public static CompoundTag create() {
        return new CompoundTag();
    }

    /**
     * NbtCompoundをSNBT (文字列化されたNBT) に変換する。
     * @param nbt NbtCompound
     * @return SNBT
     */
    public static String toSnbt(CompoundTag nbt) {
        return toSnbt((Tag) nbt);
    }

    /**
     * NbtElementをSNBT (文字列化されたNBT) に変換する。
     * @param element NbtElement
     * @return SNBT
     */
    public static String toSnbt(Tag element) {
        return element.toString();
    }

    /**
     * SNBT (文字列化されたNBT) をNbtCompoundに変換する。
     * パースに失敗した場合はIllegalArgumentExceptionを投げる。
     * @param snbt SNBT
     * @return NbtCompound
     */
    public static CompoundTag fromSnbt(String snbt) {
        try {
            return TagParser.parseCompoundFully(snbt);
        } catch (CommandSyntaxException e) {
            throw new IllegalArgumentException("Failed to parse SNBT: " + snbt, e);
        }
    }

    /**
     * SNBT (文字列化されたNBT) をNbtCompoundに変換する。
     * パースに失敗した場合はOptional.empty()を返す。
     * @param snbt SNBT
     * @return NbtCompound
     */
    public static Optional<CompoundTag> tryFromSnbt(String snbt) {
        try {
            return Optional.of(TagParser.parseCompoundFully(snbt));
        } catch (CommandSyntaxException e) {
            return Optional.empty();
        }
    }

    /**
     * 値を設定する。
     * @param nbt NbtCompound
     * @param key キー
     * @param value 値
     */
    public static void put(CompoundTag nbt, String key, CompoundTag value) {
        nbt.put(key, value);
    }

    /**
     * 値を設定する。
     * @param nbt NbtCompound
     * @param key キー
     * @param value 値
     */
    public static void put(CompoundTag nbt, String key, Tag value) {
        nbt.put(key, value);
    }

    /**
     * 値を取得する。
     * @param nbt NbtCompound
     * @param key キー
     * @return 値
     */
    public static CompoundTag get(CompoundTag nbt, String key) {
        return nbt.getCompoundOrEmpty(key);
    }

    /**
     * 値を削除する。
     * @param nbt NbtCompound
     * @param key キー
     */
    public static void remove(CompoundTag nbt, String key) {
        nbt.remove(key);
    }

    /**
     * 値が存在するかどうかを取得する。
     * @param nbt NbtCompound
     * @param key キー
     * @return 値が存在するかどうか
     */
    public static boolean has(CompoundTag nbt, String key) {
        return nbt.contains(key);
    }

    /**
     * 指定した型の値を取得する
     * @param nbt NbtCompound
     * @param key キー
     * @param clazz クラス
     * @param <T> 値
     */
    public static <T> T get(CompoundTag nbt, String key, Class<T> clazz) {
        if (clazz == Integer.class) {
            return (T) Integer.valueOf(nbt.getIntOr(key, 0));
        }
        if (clazz == String.class) {
            return (T) nbt.getString(key).orElse("");
        }
        if (clazz == Boolean.class) {
            return (T) Boolean.valueOf(nbt.getBooleanOr(key, false));
        }
        if (clazz == Float.class) {
            return (T) Float.valueOf(nbt.getFloatOr(key, 0f));
        }
        if (clazz == Double.class) {
            return (T) Double.valueOf(nbt.getDoubleOr(key, 0d));
        }
        if (clazz == Long.class) {
            return (T) Long.valueOf(nbt.getLongOr(key, 0l));
        }
        if (clazz == CompoundTag.class) {
            return (T) nbt.getCompoundOrEmpty(key);
        }
        if (clazz == ListTag.class) {
            return (T) nbt.get(key);
        }
        if (clazz == Byte.class) {
            return (T) Byte.valueOf(nbt.getByteOr(key, (byte) 0));
        }
        if (clazz == Short.class) {
            return (T) Short.valueOf(nbt.getShortOr(key, (short) 0));
        }
        if (clazz == UUID.class) {
            return (T) UUID.fromString(nbt.getString(key).orElse(""));
        }
        return null;
    }

    /**
     * 値を設定する。
     * @param nbt NbtCompound
     * @param key キー
     * @param value 値
     */
    public static <T> void set(CompoundTag nbt, String key, T value) {
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
        if (value instanceof CompoundTag) {
            nbt.put(key, (CompoundTag) value);
            return;
        }
        if (value instanceof ListTag) {
            nbt.put(key, (ListTag) value);
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
            nbt.putString(key, value.toString());
            return;
        }
    }

    /**
     * キーの一覧を取得する。
     * @param nbt NbtCompound
     * @return キーの一覧
     */
    public static Set<String> getKeys(CompoundTag nbt) {
        return nbt.keySet();
    }

    /**
     * NbtListを取得する。
     * @return NbtList
     */
    public static ListTag getList(CompoundTag nbt, String key) {
        return (ListTag) nbt.get(key);
    }

    /**
     * NbtListを取得する。
     * @return NbtList
     */
    public static ListTag getList(CompoundTag nbt, String key, int type) {
        return nbt.getListOrEmpty(key);
    }

    /**
     * NbtCompoundのリストを取得する。
     * @return NbtList
     */
    public static ListTag getNbtCompoundList(CompoundTag nbt, String key) {
        return nbt.getListOrEmpty(key);
    }

    /**
     * NbtCompoundをコピーする。
     * @return NbtCompound
     */
    public static CompoundTag copy(CompoundTag nbt) {
        return nbt.copy();
    }

    // Helper methods

    public static void putInt(CompoundTag nbt, String key, int value) {
        set(nbt, key, value);
    }

    public static int getInt(CompoundTag nbt, String key) {
        if (has(nbt, key))
            return get(nbt, key, Integer.class);
        return 0;
    }

    public static void putString(CompoundTag nbt, String key, String value) {
        set(nbt, key, value);
    }

    public static String getString(CompoundTag nbt, String key) {
        if (has(nbt, key))
            return get(nbt, key, String.class);
        return "";
    }

    public static void putBoolean(CompoundTag nbt, String key, boolean value) {
        set(nbt, key, value);
    }

    public static boolean getBoolean(CompoundTag nbt, String key) {
        if (has(nbt, key))
            return get(nbt, key, Boolean.class);
        return false;
    }

    public static void putFloat(CompoundTag nbt, String key, float value) {
        set(nbt, key, value);
    }

    public static float getFloat(CompoundTag nbt, String key) {
        if (has(nbt, key))
            return get(nbt, key, Float.class);
        return 0;
    }

    public static void putDouble(CompoundTag nbt, String key, double value) {
        set(nbt, key, value);
    }

    public static double getDouble(CompoundTag nbt, String key) {
        if (has(nbt, key))
            return get(nbt, key, Double.class);
        return 0;
    }

    public static void putLong(CompoundTag nbt, String key, long value) {
        set(nbt, key, value);
    }

    public static long getLong(CompoundTag nbt, String key) {
        if (has(nbt, key))
            return get(nbt, key, Long.class);
        return 0;
    }

    public static void putByte(CompoundTag nbt, String key, byte value) {
        set(nbt, key, value);
    }

    public static byte getByte(CompoundTag nbt, String key) {
        if (has(nbt, key))
            return get(nbt, key, Byte.class);
        return 0;
    }

    public static void putShort(CompoundTag nbt, String key, short value) {
        set(nbt, key, value);
    }

    public static short getShort(CompoundTag nbt, String key) {
        if (has(nbt, key))
            return get(nbt, key, Short.class);
        return 0;
    }

    public static void putUuid(CompoundTag nbt, String key, UUID value) {
        set(nbt, key, value);
    }

    public static UUID getUuid(CompoundTag nbt, String key) {
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
    public static void setBlockPos(CompoundTag nbt, String key, BlockPos pos) {
        CompoundTag posNbt = create();
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
    public static BlockPos getBlockPos(CompoundTag nbt, String key) {
        CompoundTag posNbt = get(nbt, key);
        return PosUtil.flooredBlockPos(getInt(posNbt, "x"), getInt(posNbt, "y"), getInt(posNbt, "z"));
    }

    public static void putVec3i(CompoundTag nbt, String key, Vec3i vec3i) {
        CompoundTag vec3iNbt = create();
        putInt(vec3iNbt, "x", vec3i.getX());
        putInt(vec3iNbt, "y", vec3i.getY());
        putInt(vec3iNbt, "z", vec3i.getZ());
        put(nbt, key, vec3iNbt);
    }

    public static Vec3i getVec3i(CompoundTag nbt, String key) {
        CompoundTag vec3iNbt = get(nbt, key);
        return Vec3iUtil.create(getInt(vec3iNbt, "x"), getInt(vec3iNbt, "y"), getInt(vec3iNbt, "z"));
    }

    public static void putVec3d(CompoundTag nbt, String key, Vec3 vec3d) {
        CompoundTag vec3dNbt = create();
        putDouble(vec3dNbt, "x", vec3d.x());
        putDouble(vec3dNbt, "y", vec3d.y());
        putDouble(vec3dNbt, "z", vec3d.z());
        put(nbt, key, vec3dNbt);
    }

    public static Vec3 getVec3d(CompoundTag nbt, String key) {
        CompoundTag vec3dNbt = get(nbt, key);
        return Vec3dUtil.create(getDouble(vec3dNbt, "x"), getDouble(vec3dNbt, "y"), getDouble(vec3dNbt, "z"));
    }

    public static void putItemStack(CompoundTag nbt, String key, ItemStack stack, CompatRegistryLookup registryLookup) {
        DataResult<Tag> dataResult = ItemStack.CODEC.encodeStart(NbtOps.INSTANCE, stack);
        put(nbt, key, dataResult.getOrThrow());
    }

    public static Optional<ItemStack> getItemStack(CompoundTag nbt, String key, CompatRegistryLookup registryLookup) {
        Tag stackNbt = get(nbt, key);
        DataResult<Pair<ItemStack, Tag>> dataResult = ItemStack.CODEC.decode(NbtOps.INSTANCE, stackNbt);
        if (dataResult.error().isPresent()) return Optional.empty();

        Pair<ItemStack, Tag> pair = dataResult.getOrThrow();
        return Optional.ofNullable(pair.getFirst());
    }

    public static void putSimpleItemStack(CompoundTag nbt, String key, ItemStack stack) {
        CompoundTag stackNbt = create();
        putString(stackNbt, "id", ItemUtil.toID(stack.getItem()).toString());
        putByte(stackNbt, "Count", (byte) ItemStackUtil.getCount(stack));

        CompoundTag tagNbt = create();
        CompoundTag componentsNbt = create();
        put(componentsNbt, "minecraft:custom_data", CustomDataUtil.getOrCreateNbt(stack));
        put(tagNbt, "components", componentsNbt);
        put(stackNbt, "tag", tagNbt);
        put(nbt, key, stackNbt);
    }

    public static Optional<ItemStack> getSimpleItemStack(CompoundTag nbt, String key) {
        if (!has(nbt, key)) return Optional.empty();
        CompoundTag stackNbt = get(nbt, key);

        if (!has(stackNbt, "id") || !has(stackNbt, "Count")) return Optional.empty();
        Item item = ItemUtil.fromId(CompatIdentifier.of(getString(stackNbt, "id")));
        int count = getByte(stackNbt, "Count");

        ItemStack stack = ItemStackUtil.create(item, count);

        if (has(stackNbt, "tag")) {
            CompoundTag tagNbt = get(stackNbt, "tag");
            if (has(tagNbt, "components")) {
                CompoundTag componentsNbt = get(tagNbt, "components");
                if (has(componentsNbt, "minecraft:custom_data")) {
                    CustomDataUtil.setNbt(stack, get(componentsNbt, "minecraft:custom_data"));
                }
            }
        }

        return Optional.of(stack);
    }

    public static ListTag createNbtList() {
        return new ListTag();
    }

    public static int getIntOrDefault(CompoundTag nbt, String key, int defaultValue) {
        if (has(nbt, key))
            return getInt(nbt, key);
        return defaultValue;
    }

    public static String getStringOrDefault(CompoundTag nbt, String key, String defaultValue) {
        if (has(nbt, key))
            return getString(nbt, key);
        return defaultValue;
    }

    public static boolean getBooleanOrDefault(CompoundTag nbt, String key, boolean defaultValue) {
        if (has(nbt, key))
            return getBoolean(nbt, key);
        return defaultValue;
    }

    public static float getFloatOrDefault(CompoundTag nbt, String key, float defaultValue) {
        if (has(nbt, key))
            return getFloat(nbt, key);
        return defaultValue;
    }

    public static double getDoubleOrDefault(CompoundTag nbt, String key, double defaultValue) {
        if (has(nbt, key))
            return getDouble(nbt, key);
        return defaultValue;
    }

    public static long getLongOrDefault(CompoundTag nbt, String key, long defaultValue) {
        if (has(nbt, key))
            return getLong(nbt, key);
        return defaultValue;
    }

    public static byte getByteOrDefault(CompoundTag nbt, String key, byte defaultValue) {
        if (has(nbt, key))
            return getByte(nbt, key);
        return defaultValue;
    }

    public static short getShortOrDefault(CompoundTag nbt, String key, short defaultValue) {
        if (has(nbt, key))
            return getShort(nbt, key);
        return defaultValue;
    }

    public static UUID getUuidOrDefault(CompoundTag nbt, String key, UUID defaultValue) {
        if (has(nbt, key))
            return getUuid(nbt, key);
        return defaultValue;
    }

    public static Tag getElement(CompoundTag nbt, String key) {
        return nbt.get(key);
    }

    public static void putElement(CompoundTag nbt, String key, Tag element) {
        nbt.put(key, element);
    }

    public static void setBlockPosDirect(CompoundTag nbt, BlockPos pos) {
        putInt(nbt, "x", pos.getX());
        putInt(nbt, "y", pos.getY());
        putInt(nbt, "z", pos.getZ());
    }

    public static BlockPos getBlockPosDirect(CompoundTag nbt) {
        return PosUtil.flooredBlockPos(getInt(nbt, "x"), getInt(nbt, "y"), getInt(nbt, "z"));
    }

    public static void setVec3iDirect(CompoundTag nbt, Vec3i vec3i) {
        putInt(nbt, "x", vec3i.getX());
        putInt(nbt, "y", vec3i.getY());
        putInt(nbt, "z", vec3i.getZ());
    }

    public static Vec3i getVec3iDirect(CompoundTag nbt) {
        return Vec3iUtil.create(getInt(nbt, "x"), getInt(nbt, "y"), getInt(nbt, "z"));
    }

    public static void setVec3dDirect(CompoundTag nbt, Vec3 vec3d) {
        putDouble(nbt, "x", vec3d.x());
        putDouble(nbt, "y", vec3d.y());
        putDouble(nbt, "z", vec3d.z());
    }

    public static Vec3 getVec3dDirect(CompoundTag nbt) {
        return Vec3dUtil.create(getDouble(nbt, "x"), getDouble(nbt, "y"), getDouble(nbt, "z"));
    }

    public static void setVec3iDirect(CompoundTag nbt, int x, int y, int z) {
        putInt(nbt, "x", x);
        putInt(nbt, "y", y);
        putInt(nbt, "z", z);
    }

    public static void setVec3dDirect(CompoundTag nbt, double x, double y, double z) {
        putDouble(nbt, "x", x);
        putDouble(nbt, "y", y);
        putDouble(nbt, "z", z);
    }

    public static String asString(Tag nbt) {
        return nbt.asString().orElse("");
    }

    public static StringTag createString(String string) {
        return StringTag.valueOf(string);
    }

    public static IntTag createInt(int value) {
        return IntTag.valueOf(value);
    }

    public static FloatTag createFloat(float value) {
        return FloatTag.valueOf(value);
    }

    public static DoubleTag createDouble(double value) {
        return DoubleTag.valueOf(value);
    }

    public static LongTag createLong(long value) {
        return LongTag.valueOf(value);
    }

    public static ByteTag createByte(byte value) {
        return ByteTag.valueOf(value);
    }

    public static ShortTag createShort(short value) {
        return ShortTag.valueOf(value);
    }

    public static void copyFrom(CompoundTag source, CompoundTag target) {
        target.merge(source);
    }
}

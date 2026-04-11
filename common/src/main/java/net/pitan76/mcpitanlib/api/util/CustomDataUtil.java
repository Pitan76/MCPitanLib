package net.pitan76.mcpitanlib.api.util;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.nbt.CompoundTag;

import java.util.Set;

/**
 * カスタムデータのユーティリティクラス
 */
public class CustomDataUtil {
    /**
     * NBTを取得する。存在しない場合は新しいNBTを作成する。
     * @param stack ItemStack
     * @return NBT
     */
    public static CompoundTag getOrCreateNbt(ItemStack stack) {
        if (!hasNbt(stack)) {
            return NbtUtil.create();
        }

        return getNbt(stack);
    }

    /**
     * NBTを設定する。
     * @param stack ItemStack
     * @param nbt NBT
     */
    public static void setNbt(ItemStack stack, CompoundTag nbt) {
        stack.set(DataComponents.CUSTOM_DATA, CustomData.of(nbt));
    }
    
    /**
     * NBTが存在するかどうかを取得する。
     * @param stack ItemStack
     * @return NBTが存在するかどうか
     */
    public static boolean hasNbt(ItemStack stack) {
        return stack.get(DataComponents.CUSTOM_DATA) != null;
    }
    
    /**
     * NBTを取得する。
     * @param stack ItemStack
     * @return NBT
     */
    public static CompoundTag getNbt(ItemStack stack) {
        if (stack.get(DataComponents.CUSTOM_DATA) == null)
            return NbtUtil.create();

        return stack.get(DataComponents.CUSTOM_DATA).copyTag();
    }
    
    /**
     * 値を設定する。
     * @param stack ItemStack
     * @param key キー
     * @param value 値
     */
    public static void put(ItemStack stack, String key, CompoundTag value) {
        CompoundTag nbt = getOrCreateNbt(stack);
        NbtUtil.put(nbt, key, value);
        setNbt(stack, nbt);
    }
    
    /**
     * 値を取得する。
     * @param stack ItemStack
     * @param key キー
     * @return 値
     */
    public static CompoundTag get(ItemStack stack, String key) {
        CompoundTag nbt = getNbt(stack);
        return nbt.getCompoundOrEmpty(key);
    }
    
    /**
     * 値を削除する。
     * @param stack ItemStack
     * @param key キー
     */
    public static void remove(ItemStack stack, String key) {
        CompoundTag nbt = getNbt(stack);
        nbt.remove(key);
        setNbt(stack, nbt);
    }
    
    /**
     * 値が存在するかどうかを取得する。
     * @param stack ItemStack
     * @param key キー
     * @return 値が存在するかどうか
     */
    public static boolean has(ItemStack stack, String key) {
        if (!hasNbt(stack))
            return false;

        CompoundTag nbt = getNbt(stack);
        return nbt.contains(key);
    }
    
    /**
     * 指定した型の値を取得する
     * @param stack ItemStack
     * @param key キー
     * @param clazz クラス
     * @param <T> 値
     */
    public static <T> T get(ItemStack stack, String key, Class<T> clazz) {
        CompoundTag nbt = getNbt(stack);
        return NbtUtil.get(nbt, key, clazz);
    }
    
    /**
     * 値を設定する。
     * @param stack ItemStack
     * @param key キー
     * @param value 値
     */
    public static <T> void set(ItemStack stack, String key, T value) {
        CompoundTag nbt = getOrCreateNbt(stack);
        NbtUtil.set(nbt, key, value);
        setNbt(stack, nbt);
    }
    
    /**
     * キーの一覧を取得する。
     * @param stack ItemStack
     * @return キーの一覧
     */
    public static Set<String> getKeys(ItemStack stack) {
        CompoundTag nbt = getNbt(stack);
        return NbtUtil.getKeys(nbt);
    }

    /**
     * set(stack, key, value) のエイリアス
     * @param stack ItemStack
     * @param key キー
     * @param value 値
     */
    public static <T> void put(ItemStack stack, String key, T value) {
        set(stack, key, value);
    }

    /**
     * has(stack, key) のエイリアス
     * @param stack ItemStack
     * @param key キー
     * @return 値が存在するかどうか
     */
    public static boolean contains(ItemStack stack, String key) {
        return has(stack, key);
    }

    /**
     * 1.20.3以前下位互換のための修正用
     * @param stack ItemStack
     * @param keys 移植するキー
     */
    public static void fix_oldNbt(ItemStack stack, String[] keys) {

    }

    /**
     * カスタムNBTを削除する
     * @param stack ItemStack
     */
    public static void remove(ItemStack stack) {
        stack.remove(DataComponents.CUSTOM_DATA);
    }
}

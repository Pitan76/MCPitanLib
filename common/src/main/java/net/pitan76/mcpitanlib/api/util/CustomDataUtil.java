package net.pitan76.mcpitanlib.api.util;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;

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
    public static NbtCompound getOrCreateNbt(ItemStack stack) {
        if (!hasNbt(stack)) {
            return NbtUtil.create();
        }

        return getTag(stack);
    }

    /**
     * NBTを設定する。
     * @param stack ItemStack
     * @param nbt NBT
     */
    public static void setNbt(ItemStack stack, NbtCompound nbt) {
        NbtCompound components = NbtUtil.create();
        if (hasNbt(stack)) {
            components = stack.getTag().getCompound("components");
        }
        components.put("minecraft:custom_data", nbt);
        stack.getTag().put("components", components);
    }
    
    /**
     * NBTが存在するかどうかを取得する。
     * @param stack ItemStack
     * @return NBTが存在するかどうか
     */
    public static boolean hasNbt(ItemStack stack) {
        return stack.getTag().contains("components") &&
                stack.getTag().getCompound("components").contains("minecraft:custom_data");
    }
    
    /**
     * NBTを取得する。
     * @param stack ItemStack
     * @return NBT
     */
    public static NbtCompound getTag(ItemStack stack) {
        NbtCompound customData = NbtUtil.create();
        if (stack.getTag().contains("components")) {
            NbtCompound components = stack.getTag().getCompound("components");
            if (components.contains("minecraft:custom_data")) {
                customData = components.getCompound("minecraft:custom_data").copy();
            }
        }
        return customData;
    }
    
    /**
     * 値を設定する。
     * @param stack ItemStack
     * @param key キー
     * @param value 値
     */
    public static void put(ItemStack stack, String key, NbtCompound value) {
        NbtCompound nbt = getOrCreateNbt(stack);
        NbtUtil.put(nbt, key, value);
        setNbt(stack, nbt);
    }
    
    /**
     * 値を取得する。
     * @param stack ItemStack
     * @param key キー
     * @return 値
     */
    public static NbtCompound get(ItemStack stack, String key) {
        NbtCompound nbt = getTag(stack);
        return nbt.getCompound(key);
    }
    
    /**
     * 値を削除する。
     * @param stack ItemStack
     * @param key キー
     */
    public static void remove(ItemStack stack, String key) {
        NbtCompound nbt = getTag(stack);
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
        NbtCompound nbt = getTag(stack);
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
        NbtCompound nbt = getTag(stack);
        return NbtUtil.get(nbt, key, clazz);
    }
    
    /**
     * 値を設定する。
     * @param stack ItemStack
     * @param key キー
     * @param value 値
     */
    public static <T> void set(ItemStack stack, String key, T value) {
        NbtCompound nbt = getOrCreateNbt(stack);
        NbtUtil.set(nbt, key, value);
        setNbt(stack, nbt);
    }
    
    /**
     * キーの一覧を取得する。
     * @param stack ItemStack
     * @return キーの一覧
     */
    public static Set<String> getKeys(ItemStack stack) {
        NbtCompound nbt = getTag(stack);
        return NbtUtil.getKeys(nbt);
    }
}

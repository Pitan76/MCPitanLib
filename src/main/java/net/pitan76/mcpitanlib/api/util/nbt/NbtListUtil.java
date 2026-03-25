package net.pitan76.mcpitanlib.api.util.nbt;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.nbt.ListTag;
import net.pitan76.mcpitanlib.api.util.NbtUtil;

import java.util.Optional;
import java.util.stream.Stream;

public class NbtListUtil {
    public static ListTag create() {
        return NbtUtil.createNbtList();
    }

    public static ListTag copy(ListTag list) {
        return list.copy();
    }

    public static Stream<Tag> stream(ListTag list) {
        return list.stream();
    }

    public static Optional<String> getStringOptional(ListTag list, int index) {
        return list.getString(index);
    }

    public static Tag get(ListTag list, int index) {
        return list.get(index);
    }

    public static Tag getOrDefault(ListTag list, int index, Tag defaultValue) {
        Tag nbt = get(list, index);
        return nbt == null ? defaultValue : nbt;
    }

    public static void set(ListTag list, int index, Tag value) {
        list.set(index, value);
    }

    public static void add(ListTag list, Tag value) {
        list.add(value);
    }

    public static void set(ListTag list, int index, String value) {
        set(list, index, NbtUtil.createString(value));
    }

    public static boolean has(ListTag list, Tag value) {
        return list.contains(value);
    }

    public static int size(ListTag list) {
        return list.size();
    }

    public static void remove(ListTag list, int index) {
        list.remove(index);
    }

    public static void clear(ListTag list) {
        list.clear();
    }

    public static boolean isEmpty(ListTag list) {
        return list.isEmpty();
    }

    public static String getString(ListTag list, int index) {
        return getStringOptional(list, index).orElse("");
    }

    public static void setString(ListTag list, int index, String value) {
        list.set(index, NbtUtil.createString(value));
    }

    public static void addString(ListTag list, String value) {
        list.add(NbtUtil.createString(value));
    }

    public static ListTag getList(ListTag list, int index) {
        return list.getList(index).orElse(create());
    }

    public static CompoundTag getCompound(ListTag list, int index) {
        return list.getCompound(index).orElse(NbtUtil.create());
    }
}

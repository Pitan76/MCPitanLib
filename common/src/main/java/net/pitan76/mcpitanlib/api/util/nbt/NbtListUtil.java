package net.pitan76.mcpitanlib.api.util.nbt;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.pitan76.mcpitanlib.api.util.NbtUtil;

import java.util.Optional;
import java.util.stream.Stream;

public class NbtListUtil {
    public static NbtList create() {
        return NbtUtil.createNbtList();
    }

    public static NbtList copy(NbtList list) {
        return list.copy();
    }

    public static Stream<NbtElement> stream(NbtList list) {
        return list.stream();
    }

    public static Optional<String> getStringOptional(NbtList list, int index) {
        return list.getString(index);
    }

    public static NbtElement get(NbtList list, int index) {
        return list.get(index);
    }

    public static NbtElement getOrDefault(NbtList list, int index, NbtElement defaultValue) {
        NbtElement nbt = get(list, index);
        return nbt == null ? defaultValue : nbt;
    }

    public static void set(NbtList list, int index, NbtElement value) {
        list.set(index, value);
    }

    public static void add(NbtList list, NbtElement value) {
        list.add(value);
    }

    public static void set(NbtList list, int index, String value) {
        set(list, index, NbtUtil.createString(value));
    }

    public static boolean has(NbtList list, NbtElement value) {
        return list.contains(value);
    }

    public static int size(NbtList list) {
        return list.size();
    }

    public static void remove(NbtList list, int index) {
        list.remove(index);
    }

    public static void clear(NbtList list) {
        list.clear();
    }

    public static boolean isEmpty(NbtList list) {
        return list.isEmpty();
    }

    public static String getString(NbtList list, int index) {
        return getStringOptional(list, index).orElse("");
    }

    public static void setString(NbtList list, int index, String value) {
        list.set(index, NbtUtil.createString(value));
    }

    public static void addString(NbtList list, String value) {
        list.add(NbtUtil.createString(value));
    }

    public static NbtList getList(NbtList list, int index) {
        return list.getList(index).orElse(create());
    }

    public static NbtCompound getCompound(NbtList list, int index) {
        return list.getCompound(index).orElse(NbtUtil.create());
    }
}

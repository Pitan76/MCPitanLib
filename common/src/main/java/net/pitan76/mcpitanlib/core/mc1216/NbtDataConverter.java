package net.pitan76.mcpitanlib.core.mc1216;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.storage.NbtReadView;
import net.minecraft.storage.NbtWriteView;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.util.ErrorReporter;
import net.pitan76.mcpitanlib.api.nbt.NbtTypeBytes;
import net.pitan76.mcpitanlib.api.registry.CompatRegistryLookup;
import net.pitan76.mcpitanlib.api.util.NbtUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class NbtDataConverter {
    public static void nbt2writeData(NbtCompound nbt, WriteView view) {
        if (nbt == null || view == null) return;

        List<String> keys = new ArrayList<>(nbt.getKeys());
        for (String key : keys) {
            NbtElement value = nbt.get(key);
            if (value == null) continue;

            byte type = value.getType();
            view.putByte("__nbttype_" + key + "__", type);

            switch (type) {
                case NbtTypeBytes.BYTE:
                    Optional<Byte> optional = value.asByte();
                    if (optional.isEmpty()) continue;
                    view.putByte(key, optional.get());
                    break;
                case NbtTypeBytes.SHORT:
                    Optional<Short> optionalShort = value.asShort();
                    if (optionalShort.isEmpty()) continue;
                    view.putShort(key, optionalShort.get());
                    break;
                case NbtTypeBytes.INT:
                    Optional<Integer> optionalInt = value.asInt();
                    if (optionalInt.isEmpty()) continue;
                    view.putInt(key, optionalInt.get());
                    break;
                case NbtTypeBytes.LONG:
                    Optional<Long> optionalLong = value.asLong();
                    if (optionalLong.isEmpty()) continue;
                    view.putLong(key, optionalLong.get());
                    break;
                case NbtTypeBytes.FLOAT:
                    Optional<Float> optionalFloat = value.asFloat();
                    if (optionalFloat.isEmpty()) continue;
                    view.putFloat(key, optionalFloat.get());
                    break;
                case NbtTypeBytes.DOUBLE:
                    Optional<Double> optionalDouble = value.asDouble();
                    if (optionalDouble.isEmpty()) continue;
                    view.putDouble(key, optionalDouble.get());
                    break;
                case NbtTypeBytes.STRING:
                    Optional<String> optionalString = value.asString();
                    if (optionalString.isEmpty()) continue;
                    view.putString(key, optionalString.get());
                    break;
                case NbtTypeBytes.BYTE_ARRAY:
                    Optional<byte[]> optionalByteArray = value.asByteArray();
                    if (optionalByteArray.isEmpty()) continue;
                    byte[] byteArray = optionalByteArray.get();
                    int[] intArray = new int[byteArray.length];
                    for (int i = 0; i < byteArray.length; i++)
                        intArray[i] = byteArray[i];
                    view.putIntArray(key, intArray);
                    break;
                case NbtTypeBytes.INT_ARRAY:
                    Optional<int[]> optionalIntArray = value.asIntArray();
                    if (optionalIntArray.isEmpty()) continue;
                    view.putIntArray(key, optionalIntArray.get());
                    break;
                case NbtTypeBytes.LONG_ARRAY:
                    Optional<long[]> optionalLongArray = value.asLongArray();
                    if (optionalLongArray.isEmpty()) continue;
                    long[] longArray = optionalLongArray.get();
                    int[] longIntArray = new int[longArray.length];
                    for (int i = 0; i < longArray.length; i++)
                        longIntArray[i] = (int) (longArray[i] & 0xFFFFFFFFL);
                    view.putIntArray(key, longIntArray);
                    break;
                case NbtTypeBytes.COMPOUND:
                    Optional<NbtCompound> optionalCompound = value.asCompound();
                    if (optionalCompound.isEmpty()) continue;
                    NbtCompound nbt2 = optionalCompound.get();
                    view.put(key, NbtCompound.CODEC, nbt2);
                    break;
            }
        }

        view.putString("__all_keys__", String.join(",", keys));
    }

    public static void data2nbt(ReadView view, NbtCompound nbt) {
        if (view == null || nbt == null) return;
        String keysStr = view.getString("__all_keys__", "");
        if (keysStr.isEmpty()) return;
        String[] keys = keysStr.split(",");
        for (String key : keys) {
            if (key == null || key.isEmpty()) continue;

            byte type = view.getByte("__nbttype_" + key + "__", (byte) -1);
            if (type == -1) continue;

            switch (type) {
                case NbtTypeBytes.BYTE:
                    nbt.putByte(key, view.getByte(key, (byte) 0));
                    break;
                case NbtTypeBytes.SHORT:
                    nbt.putShort(key, (short) view.getShort(key, (short) 0));
                    break;
                case NbtTypeBytes.INT:
                    nbt.putInt(key, view.getInt(key, 0));
                    break;
                case NbtTypeBytes.LONG:
                    nbt.putLong(key, view.getLong(key, 0));
                    break;
                case NbtTypeBytes.FLOAT:
                    nbt.putFloat(key, view.getFloat(key, 0));
                    break;
                case NbtTypeBytes.DOUBLE:
                    nbt.putDouble(key, view.getDouble(key, 0));
                    break;
                case NbtTypeBytes.STRING:
                    nbt.putString(key, view.getString(key, ""));
                    break;
                case NbtTypeBytes.BYTE_ARRAY:
                    int[] intArray = view.getOptionalIntArray(key).get();
                    byte[] byteArray = new byte[intArray.length];
                    for (int i = 0; i < intArray.length; i++)
                        byteArray[i] = (byte) intArray[i];
                    nbt.putByteArray(key, byteArray);
                    break;
                case NbtTypeBytes.INT_ARRAY:
                    nbt.putIntArray(key, view.getOptionalIntArray(key).get());
                    break;
                case NbtTypeBytes.LONG_ARRAY:
                    int[] longIntArray = view.getOptionalIntArray(key).get();
                    long[] longArray = new long[longIntArray.length];
                    for (int i = 0; i < longIntArray.length; i++)
                        longArray[i] = longIntArray[i] & 0xFFFFFFFFL;
                    nbt.putLongArray(key, longArray);
                    break;
                case NbtTypeBytes.COMPOUND:
                    nbt.put(key, view.read(key, NbtCompound.CODEC).get());
                    break;
                default:
                    nbt.putString(key, "Unsupported NBT type: " + type);
                    break;
            }
        }
    }

    public static void data2nbt(WriteView view, NbtCompound nbt) {
        if (view == null || nbt == null) return;

        if (view instanceof NbtWriteView)
            NbtUtil.copyFrom(((NbtWriteView) view).getNbt(), nbt);
    }

    public static WriteView nbt2writeData(NbtCompound nbt, CompatRegistryLookup registryLookup) {
        WriteView view = NbtWriteView.create(ErrorReporter.EMPTY);
        nbt2writeData(nbt, view);
        return view;
    }

    public static ReadView nbt2readData(NbtCompound nbt, CompatRegistryLookup registryLookup) {
        if (nbt == null) nbt = NbtUtil.create();
        return NbtReadView.create(ErrorReporter.EMPTY, registryLookup.getRegistryLookup(), nbt);
    }

    public static NbtCompound data2nbt(ReadView view) {
        NbtCompound nbt = new NbtCompound();
        data2nbt(view, nbt);
        return nbt;
    }

    public static NbtCompound data2nbt(WriteView view) {
        NbtCompound nbt = new NbtCompound();
        data2nbt(view, nbt);
        return nbt;
    }
}

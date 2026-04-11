package net.pitan76.mcpitanlib.core.mc1216;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.level.storage.TagValueInput;
import net.minecraft.world.level.storage.TagValueOutput;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.util.ProblemReporter;
import net.pitan76.mcpitanlib.api.nbt.NbtTypeBytes;
import net.pitan76.mcpitanlib.api.registry.CompatRegistryLookup;
import net.pitan76.mcpitanlib.api.util.NbtUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class NbtDataConverter {
    public static void nbt2writeData(CompoundTag nbt, ValueOutput view) {
        if (nbt == null || view == null) return;

        List<String> keys = new ArrayList<>(nbt.keySet());
        for (String key : keys) {
            Tag value = nbt.get(key);
            if (value == null) continue;

            byte type = value.getId();
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
                    Optional<CompoundTag> optionalCompound = value.asCompound();
                    if (optionalCompound.isEmpty()) continue;
                    CompoundTag nbt2 = optionalCompound.get();

                    //System.out.println("NbtDataConverter nbt(" + key + "): " + nbt2);

                    view.store(key, CompoundTag.CODEC, nbt2);
                    break;
            }
        }

        view.putString("__all_keys__", String.join(",", keys));
    }

    public static void data2nbt(ValueInput view, CompoundTag nbt) {
        if (view == null || nbt == null) return;

        // ReadViewだとgetKeys()がないので__all_keys__からキーを取得する
        String keysStr = view.getStringOr("__all_keys__", "");
        if (keysStr.isEmpty()) return;
        String[] keys = keysStr.split(",");
        for (String key : keys) {
            if (key == null || key.isEmpty()) continue;

            byte type = view.getByteOr("__nbttype_" + key + "__", (byte) -1);
            if (type == -1) continue;

            switch (type) {
                case NbtTypeBytes.BYTE:
                    nbt.putByte(key, view.getByteOr(key, (byte) 0));
                    break;
                case NbtTypeBytes.SHORT:
                    nbt.putShort(key, (short) view.getShortOr(key, (short) 0));
                    break;
                case NbtTypeBytes.INT:
                    nbt.putInt(key, view.getIntOr(key, 0));
                    break;
                case NbtTypeBytes.LONG:
                    nbt.putLong(key, view.getLongOr(key, 0));
                    break;
                case NbtTypeBytes.FLOAT:
                    nbt.putFloat(key, view.getFloatOr(key, 0));
                    break;
                case NbtTypeBytes.DOUBLE:
                    nbt.putDouble(key, view.getDoubleOr(key, 0));
                    break;
                case NbtTypeBytes.STRING:
                    nbt.putString(key, view.getStringOr(key, ""));
                    break;
                case NbtTypeBytes.BYTE_ARRAY:
                    int[] intArray = view.getIntArray(key).get();
                    byte[] byteArray = new byte[intArray.length];
                    for (int i = 0; i < intArray.length; i++)
                        byteArray[i] = (byte) intArray[i];
                    nbt.putByteArray(key, byteArray);
                    break;
                case NbtTypeBytes.INT_ARRAY:
                    nbt.putIntArray(key, view.getIntArray(key).get());
                    break;
                case NbtTypeBytes.LONG_ARRAY:
                    int[] longIntArray = view.getIntArray(key).get();
                    long[] longArray = new long[longIntArray.length];
                    for (int i = 0; i < longIntArray.length; i++)
                        longArray[i] = longIntArray[i] & 0xFFFFFFFFL;
                    nbt.putLongArray(key, longArray);
                    break;
                case NbtTypeBytes.COMPOUND:
                    CompoundTag nbt2 = view.read(key, CompoundTag.CODEC).get();
                    nbt.put(key, nbt2);
                    break;
                default:
                    nbt.putString(key, "Unsupported NBT type: " + type);
                    break;
            }
        }
    }

    public static void data2nbt(ValueOutput view, CompoundTag nbt) {
        if (view == null || nbt == null) return;

        if (view instanceof TagValueOutput) {
            //System.out.println("data2nbt(): " + ((NbtWriteView) view).getNbt());
            NbtUtil.copyFrom(((TagValueOutput) view).buildResult(), nbt);
        }
    }

    public static ValueOutput nbt2writeData(CompoundTag nbt, CompatRegistryLookup registryLookup) {
        ValueOutput view = TagValueOutput.createWithoutContext(ProblemReporter.DISCARDING);
        nbt2writeData(nbt, view);
        return view;
    }

    public static ValueInput nbt2readData(CompoundTag nbt, CompatRegistryLookup registryLookup) {
        if (nbt == null) nbt = NbtUtil.create();
        return TagValueInput.create(ProblemReporter.DISCARDING, registryLookup.getRegistryLookup(), nbt);
    }

    public static CompoundTag data2nbt(ValueInput view) {
        CompoundTag nbt = new CompoundTag();
        data2nbt(view, nbt);
        return nbt;
    }

    public static CompoundTag data2nbt(ValueOutput view) {
        CompoundTag nbt = new CompoundTag();
        data2nbt(view, nbt);
        return nbt;
    }
}

package net.pitan76.mcpitanlib.midohra.nbt;

import net.pitan76.mcpitanlib.api.registry.CompatRegistryLookup;
import net.pitan76.mcpitanlib.api.util.NbtUtil;
import net.pitan76.mcpitanlib.api.util.nbt.NbtIoUtil;
import net.pitan76.mcpitanlib.midohra.item.ItemStack;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@SuppressWarnings("deprecation")
public class NbtCompound implements ElementConvertible {
    protected final net.minecraft.nbt.CompoundTag nbt;

    protected NbtCompound(net.minecraft.nbt.CompoundTag nbt) {
        this.nbt = nbt;
    }

    public static NbtCompound of(net.minecraft.nbt.CompoundTag nbt) {
        return new NbtCompound(nbt);
    }

    public static NbtCompound of() {
        return new NbtCompound(NbtUtil.create());
    }

    public NbtCompound copy() {
        return new NbtCompound(NbtUtil.copy(nbt));
    }

    /**
     * SNBT (文字列化されたNBT) からNbtCompoundを作成する。
     * パースに失敗した場合はIllegalArgumentExceptionを投げる。
     */
    public static NbtCompound fromSnbt(String snbt) {
        return of(NbtUtil.fromSnbt(snbt));
    }

    /**
     * SNBT (文字列化されたNBT) からNbtCompoundを作成する。
     * パースに失敗した場合はOptional.empty()を返す。
     */
    public static Optional<NbtCompound> tryFromSnbt(String snbt) {
        return NbtUtil.tryFromSnbt(snbt).map(NbtCompound::of);
    }

    /**
     * SNBT (文字列化されたNBT) に変換する。
     */
    public String toSnbt() {
        return NbtUtil.toSnbt(nbt);
    }

    /**
     * gzip圧縮されたNBTファイルから読み込む。
     */
    public static NbtCompound read(Path path) throws IOException {
        return of(NbtIoUtil.readCompressed(path));
    }

    /**
     * gzip圧縮してNBTファイルへ書き出す。
     */
    public void write(Path path) throws IOException {
        NbtIoUtil.writeCompressed(nbt, path);
    }

    public boolean has(String key) {
        return NbtUtil.has(nbt, key);
    }

    public void putByte(String key, byte value) {
        NbtUtil.putByte(nbt, key, value);
    }

    public byte getByte(String key) {
        return NbtUtil.getByte(nbt, key);
    }

    public void putShort(String key, short value) {
        NbtUtil.putShort(nbt, key, value);
    }

    public short getShort(String key) {
        return NbtUtil.getShort(nbt, key);
    }

    public void putInt(String key, int value) {
        NbtUtil.putInt(nbt, key, value);
    }

    public int getInt(String key) {
        return NbtUtil.getInt(nbt, key);
    }

    public void putLong(String key, long value) {
        NbtUtil.putLong(nbt, key, value);
    }

    public long getLong(String key) {
        return NbtUtil.getLong(nbt, key);
    }

    public void putFloat(String key, float value) {
        NbtUtil.putFloat(nbt, key, value);
    }

    public float getFloat(String key) {
        return NbtUtil.getFloat(nbt, key);
    }

    public void putDouble(String key, double value) {
        NbtUtil.putDouble(nbt, key, value);
    }

    public double getDouble(String key) {
        return NbtUtil.getDouble(nbt, key);
    }

    public void putString(String key, String value) {
        NbtUtil.putString(nbt, key, value);
    }

    public String getString(String key) {
        return NbtUtil.getString(nbt, key);
    }

    public void putBoolean(String key, boolean value) {
        NbtUtil.putBoolean(nbt, key, value);
    }

    public boolean getBoolean(String key) {
        return NbtUtil.getBoolean(nbt, key);
    }

    public void putUuid(String key, UUID value) {
        NbtUtil.putUuid(nbt, key, value);
    }

    public UUID getUuid(String key) {
        return NbtUtil.getUuid(nbt, key);
    }

    public void putItemStack(String key, net.pitan76.mcpitanlib.midohra.item.ItemStack stack, CompatRegistryLookup registryLookup) {
        NbtUtil.putItemStack(nbt, key, stack.toMinecraft(), registryLookup);
    }

    public net.pitan76.mcpitanlib.midohra.item.ItemStack getItemStack(String key, CompatRegistryLookup registryLookup) {
        return ItemStack.of(NbtUtil.getItemStack(nbt, key, registryLookup).get());
    }

    public void put(String key, NbtCompound compound) {
        NbtUtil.put(nbt, key, compound.nbt);
    }

    public void put(String key, net.minecraft.nbt.CompoundTag nbt) {
        NbtUtil.put(this.nbt, key, nbt);
    }

    public void put(String key, ElementConvertible element) {
        NbtUtil.put(nbt, key, element.toMinecraftNbtElement());
    }

    public NbtCompound getCompound(String key) {
        return new NbtCompound(NbtUtil.get(nbt, key));
    }

    public NbtElement get(String key) {
        return NbtElement.of(NbtUtil.getElement(nbt, key));
    }

    public net.minecraft.nbt.CompoundTag toMinecraft() {
        return nbt;
    }

    @Override
    public String toString() {
        return nbt.toString();
    }

    @Override
    public NbtElement toElement() {
        return NbtElement.of(nbt);
    }

    public void putSimpleItemStack(String key, ItemStack stack) {
        NbtUtil.putSimpleItemStack(nbt, key, stack.toMinecraft());
    }

    public Optional<ItemStack> getSimpleItemStack(String key) {
        return NbtUtil.getSimpleItemStack(nbt, key).map(ItemStack::of);
    }

    public NbtList getList(String key) {
        return new NbtList(NbtUtil.getList(nbt, key));
    }

    public NbtList getList(String key, int type) {
        return new NbtList(NbtUtil.getList(nbt, key, type));
    }

    public NbtList getNbtCompoundList(String key) {
        return new NbtList(NbtUtil.getNbtCompoundList(nbt, key));
    }

    public Set<String> getKeys() {
        return NbtUtil.getKeys(nbt);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        NbtCompound that = (NbtCompound) obj;
        return nbt.equals(that.nbt);
    }

    @Override
    public int hashCode() {
        return nbt.hashCode();
    }
}

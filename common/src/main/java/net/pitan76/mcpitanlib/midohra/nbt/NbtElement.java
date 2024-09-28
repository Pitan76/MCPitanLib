package net.pitan76.mcpitanlib.midohra.nbt;

import net.minecraft.nbt.NbtType;

public class NbtElement implements ElementConvertible {
    protected final net.minecraft.nbt.NbtElement nbt;

    protected NbtElement(net.minecraft.nbt.NbtElement nbt) {
        this.nbt = nbt;
    }

    public static NbtElement of(net.minecraft.nbt.NbtElement nbt) {
        return new NbtElement(nbt);
    }

    public NbtElement copy() {
        return new NbtElement(nbt.copy());
    }

    public byte getType() {
        return nbt.getType();
    }

    public NbtType<?> getNbtType() {
        return nbt.getNbtType();
    }

    public int getSizeInBytes() {
        return -1;
    }

    @Override
    public String toString() {
        return nbt.toString();
    }

    @Deprecated
    public net.minecraft.nbt.NbtElement toMinecraft() {
        return nbt;
    }

    @Override
    public NbtElement toElement() {
        return this;
    }
}

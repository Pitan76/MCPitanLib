package net.pitan76.mcpitanlib.midohra.nbt;

import net.minecraft.nbt.NbtType;
import org.jetbrains.annotations.Nullable;

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

    public net.minecraft.nbt.NbtElement toMinecraft() {
        return nbt;
    }

    @Override
    public NbtElement toElement() {
        return this;
    }

    public String asString() {
        return nbt.asString();
    }

    public boolean isNbtCompound() {
        return nbt instanceof net.minecraft.nbt.NbtCompound;
    }

    @Nullable
    public NbtCompound asNbtCompound() {
        if (isNbtCompound()) {
            return NbtCompound.of((net.minecraft.nbt.NbtCompound) nbt);
        }

        return null;
    }

    public NbtCompound asNbtCompoundOrDefault(NbtCompound defaultCompound) {
        NbtCompound compound = asNbtCompound();
        return compound != null ? compound : defaultCompound;
    }

    public boolean isNbtList() {
        return nbt instanceof net.minecraft.nbt.NbtList;
    }

    @Nullable
    public NbtList asNbtList() {
        if (isNbtList()) {
            return NbtList.of((net.minecraft.nbt.NbtList) nbt);
        }

        return null;
    }

    public NbtList asNbtListOrDefault(NbtList defaultList) {
        NbtList list = asNbtList();
        return list != null ? list : defaultList;
    }

    public boolean isNbtString() {
        return nbt instanceof net.minecraft.nbt.NbtString;
    }

    public NbtString asNbtString() {
        if (isNbtString()) {
            return NbtString.of((net.minecraft.nbt.NbtString) nbt);
        }

        return NbtString.of("");
    }

    public NbtString asNbtStringOrDefault(NbtString defaultString) {
        NbtString string = asNbtString();
        return string != null ? string : defaultString;
    }

    public boolean isNbtInteger() {
        return nbt instanceof net.minecraft.nbt.NbtInt;
    }

    public NbtInteger asNbtInteger() {
        if (isNbtInteger()) {
            return NbtInteger.of((net.minecraft.nbt.NbtInt) nbt);
        }

        return NbtInteger.of(0);
    }

    public NbtInteger asNbtIntegerOrDefault(NbtInteger defaultInteger) {
        NbtInteger integer = asNbtInteger();
        return integer != null ? integer : defaultInteger;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        NbtElement that = (NbtElement) obj;
        return nbt.equals(that.nbt);
    }

    @Override
    public int hashCode() {
        return nbt.hashCode();
    }
}

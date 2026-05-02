package net.pitan76.mcpitanlib.midohra.nbt;

import net.minecraft.nbt.TagType;
import org.jetbrains.annotations.Nullable;

public class NbtElement implements ElementConvertible {
    protected final net.minecraft.nbt.Tag nbt;

    protected NbtElement(net.minecraft.nbt.Tag nbt) {
        this.nbt = nbt;
    }

    public static NbtElement of(net.minecraft.nbt.Tag nbt) {
        return new NbtElement(nbt);
    }

    public NbtElement copy() {
        return new NbtElement(nbt.copy());
    }

    public byte getType() {
        return nbt.getId();
    }

    public TagType<?> getNbtType() {
        return nbt.getType();
    }

    public int getSizeInBytes() {
        return nbt.sizeInBytes();
    }

    @Override
    public String toString() {
        return nbt.toString();
    }

    public net.minecraft.nbt.Tag toMinecraft() {
        return nbt;
    }

    @Override
    public NbtElement toElement() {
        return this;
    }

    public String asString() {
        return nbt.asString().orElse("");
    }

    public boolean isNbtCompound() {
        return nbt instanceof net.minecraft.nbt.CompoundTag;
    }

    @Nullable
    public NbtCompound asNbtCompound() {
        if (isNbtCompound()) {
            return NbtCompound.of((net.minecraft.nbt.CompoundTag) nbt);
        }

        return null;
    }

    public NbtCompound asNbtCompoundOrDefault(NbtCompound defaultCompound) {
        NbtCompound compound = asNbtCompound();
        return compound != null ? compound : defaultCompound;
    }

    public boolean isNbtList() {
        return nbt instanceof net.minecraft.nbt.ListTag;
    }

    @Nullable
    public NbtList asNbtList() {
        if (isNbtList()) {
            return NbtList.of((net.minecraft.nbt.ListTag) nbt);
        }

        return null;
    }

    public NbtList asNbtListOrDefault(NbtList defaultList) {
        NbtList list = asNbtList();
        return list != null ? list : defaultList;
    }

    public boolean isNbtString() {
        return nbt instanceof net.minecraft.nbt.StringTag;
    }

    public NbtString asNbtString() {
        if (isNbtString()) {
            return NbtString.of((net.minecraft.nbt.StringTag) nbt);
        }

        return NbtString.of("");
    }

    public NbtString asNbtStringOrDefault(NbtString defaultString) {
        NbtString string = asNbtString();
        return string != null ? string : defaultString;
    }

    public boolean isNbtInteger() {
        return nbt instanceof net.minecraft.nbt.IntTag;
    }

    public NbtInteger asNbtInteger() {
        if (isNbtInteger()) {
            return NbtInteger.of((net.minecraft.nbt.IntTag) nbt);
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

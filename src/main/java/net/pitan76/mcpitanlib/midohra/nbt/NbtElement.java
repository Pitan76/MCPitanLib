package net.pitan76.mcpitanlib.midohra.nbt;

import net.minecraft.nbt.TagType;

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
}

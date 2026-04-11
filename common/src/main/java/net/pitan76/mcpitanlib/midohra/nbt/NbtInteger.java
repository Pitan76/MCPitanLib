package net.pitan76.mcpitanlib.midohra.nbt;

import java.util.Optional;

public class NbtInteger implements ElementConvertible {
    protected final net.minecraft.nbt.NbtInt nbtInt;

    protected NbtInteger(net.minecraft.nbt.NbtInt nbtInt) {
        this.nbtInt = nbtInt;
    }

    public static NbtInteger of(net.minecraft.nbt.NbtInt nbtInt) {
        return new NbtInteger(nbtInt);
    }

    public static NbtInteger of(int value) {
        return new NbtInteger(net.minecraft.nbt.NbtInt.of(value));
    }

    public static Optional<NbtInteger> ofOptional(NbtElement nbtElement) {
        if (nbtElement.toMinecraft() instanceof net.minecraft.nbt.NbtInt)
            return Optional.of(new NbtInteger((net.minecraft.nbt.NbtInt) nbtElement.toMinecraft()));

        return Optional.empty();
    }

    public NbtInteger copy() {
        return new NbtInteger(nbtInt.copy());
    }

    public byte getType() {
        return nbtInt.getType();
    }

    public int getValue() {
        return nbtInt.intValue();
    }

    public String asString() {
        return nbtInt.asString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        return o instanceof NbtInteger && nbtInt.equals(((NbtInteger) o).nbtInt);
    }

    @Override
    public int hashCode() {
        return nbtInt.hashCode();
    }

    @Override
    public String toString() {
        return nbtInt.toString();
    }

    public net.minecraft.nbt.NbtInt toMinecraft() {
        return nbtInt;
    }

    @Override
    public NbtElement toElement() {
        return NbtElement.of(nbtInt);
    }
}

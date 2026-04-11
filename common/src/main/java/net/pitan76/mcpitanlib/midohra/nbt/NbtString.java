package net.pitan76.mcpitanlib.midohra.nbt;

import java.util.Optional;

public class NbtString implements ElementConvertible {
    protected final net.minecraft.nbt.StringTag nbtString;

    protected NbtString(net.minecraft.nbt.StringTag nbtString) {
        this.nbtString = nbtString;
    }

    public static NbtString of(net.minecraft.nbt.StringTag nbtString) {
        return new NbtString(nbtString);
    }

    public static NbtString of(String value) {
        return new NbtString(net.minecraft.nbt.StringTag.valueOf(value));
    }

    public static Optional<NbtString> ofOptional(NbtElement nbtElement) {
        if (nbtElement.toMinecraft() instanceof net.minecraft.nbt.StringTag)
            return Optional.of(new NbtString((net.minecraft.nbt.StringTag) nbtElement.toMinecraft()));

        return Optional.empty();
    }

    public NbtString copy() {
        return new NbtString(nbtString.copy());
    }

    public byte getType() {
        return nbtString.getId();
    }

    public String getValue() {
        return nbtString.value();
    }

    public String asString() {
        return nbtString.asString().orElse("");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        return o instanceof NbtString && nbtString.equals(((NbtString) o).nbtString);
    }

    @Override
    public int hashCode() {
        return nbtString.hashCode();
    }

    @Override
    public String toString() {
        return nbtString.toString();
    }

    public net.minecraft.nbt.StringTag toMinecraft() {
        return nbtString;
    }

    @Override
    public NbtElement toElement() {
        return NbtElement.of(nbtString);
    }
}

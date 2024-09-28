package net.pitan76.mcpitanlib.midohra.nbt;

import java.util.AbstractList;
import java.util.Optional;

@SuppressWarnings("deprecation")
public class NbtList extends AbstractList<NbtElement> implements ElementConvertible {
    protected final net.minecraft.nbt.NbtList nbtList;

    protected NbtList(net.minecraft.nbt.NbtList nbtList) {
        this.nbtList = nbtList;
    }

    public static NbtList of(net.minecraft.nbt.NbtList nbtList) {
        return new NbtList(nbtList);
    }

    public static Optional<NbtList> ofOptional(NbtElement nbtElement) {
        if (nbtElement.toMinecraft() instanceof net.minecraft.nbt.NbtList)
            return Optional.of(new NbtList((net.minecraft.nbt.NbtList) nbtElement.toMinecraft()));

        return Optional.empty();
    }

    public NbtList copy() {
        return new NbtList(nbtList.copy());
    }

    @Override
    public int size() {
        return nbtList.size();
    }

    public byte getType() {
        return nbtList.getType();
    }

    @Override
    public NbtElement get(int index) {
        return NbtElement.of(nbtList.get(index));
    }

    public boolean add(net.minecraft.nbt.NbtElement nbtElement) {
        return nbtList.add(nbtElement);
    }

    @Override
    public boolean add(NbtElement nbtElement) {
        return add(nbtElement.toMinecraft());
    }

    public void add(int index, net.minecraft.nbt.NbtElement nbtElement) {
        nbtList.add(index, nbtElement);
    }

    @Override
    public void add(int index, NbtElement nbtElement) {
        add(index, nbtElement.toMinecraft());
    }

    @Override
    public NbtElement set(int index, NbtElement element) {
        return NbtElement.of(nbtList.set(index, element.toMinecraft()));
    }

    @Override
    public NbtElement remove(int index) {
        return NbtElement.of(nbtList.remove(index));
    }

    @Override
    public boolean isEmpty() {
        return nbtList.isEmpty();
    }

    @Override
    public void clear() {
        nbtList.clear();
    }

    public String asString() {
        return nbtList.asString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        return o instanceof NbtList && nbtList.equals(((NbtList) o).nbtList);
    }

    @Override
    public int hashCode() {
        return nbtList.hashCode();
    }

    @Override
    public String toString() {
        return nbtList.toString();
    }

    @Deprecated
    public net.minecraft.nbt.NbtList toMinecraft() {
        return nbtList;
    }

    @Override
    public NbtElement toElement() {
        return NbtElement.of(nbtList);
    }
}

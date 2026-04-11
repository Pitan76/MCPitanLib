package net.pitan76.mcpitanlib.midohra.network;

import net.minecraft.network.PacketByteBuf;
import net.pitan76.mcpitanlib.api.network.PacketByteUtil;
import net.pitan76.mcpitanlib.midohra.nbt.NbtCompound;
import net.pitan76.mcpitanlib.midohra.util.math.BlockPos;

public class CompatPacketByteBuf extends PacketByteBuf {
    public CompatPacketByteBuf(PacketByteBuf buf) {
        super(buf);
    }

    public CompatPacketByteBuf() {
        super(PacketByteUtil.create());
    }

    public static CompatPacketByteBuf create() {
        return new CompatPacketByteBuf();
    }

    public static CompatPacketByteBuf of(PacketByteBuf buf) {
        return new CompatPacketByteBuf(buf);
    }

    public PacketByteBuf getRaw() {
        return this;
    }

    public CompatPacketByteBuf writeInt(int value) {
        super.writeInt(value);
        return this;
    }

    public int readInt() {
        return super.readInt();
    }

    public CompatPacketByteBuf writeLong(long value) {
        super.writeLong(value);
        return this;
    }

    public long readLong() {
        return super.readLong();
    }

    public CompatPacketByteBuf writeBoolean(boolean value) {
        super.writeBoolean(value);
        return this;
    }

    public boolean readBoolean() {
        return super.readBoolean();
    }

    public CompatPacketByteBuf writeFloat(float value) {
        super.writeFloat(value);
        return this;
    }

    public float readFloat() {
        return super.readFloat();
    }

    public CompatPacketByteBuf writeDouble(double value) {
        super.writeDouble(value);
        return this;
    }

    public double readDouble() {
        return super.readDouble();
    }

    public CompatPacketByteBuf writeShort(short value) {
        super.writeShort(value);
        return this;
    }

    public short readShort() {
        return super.readShort();
    }

    public CompatPacketByteBuf writeByteArray(byte[] array) {
        super.writeByteArray(array);
        return this;
    }

    public byte[] readByteArray() {
        return super.readByteArray();
    }

    public CompatPacketByteBuf writeString(String string) {
        super.writeString(string);
        return this;
    }

    public String readString() {
        return super.readString();
    }

    public CompatPacketByteBuf writeBlockPos(net.minecraft.util.math.BlockPos pos) {
        super.writeBlockPos(pos);
        return this;
    }

    public net.minecraft.util.math.BlockPos readBlockPos() {
        return super.readBlockPos();
    }

    public CompatPacketByteBuf writeBlockPos(BlockPos pos) {
        return writeBlockPos(pos.toMinecraft());
    }

    public BlockPos readBlockPosMidohra() {
        return BlockPos.of(readBlockPos());
    }

    public NbtCompound readNbtM() {
        return PacketByteUtil.readNbtM(this);
    }

    public CompatPacketByteBuf writeNbt(NbtCompound nbt) {
        PacketByteUtil.writeNbt(this, nbt);
        return this;
    }
}

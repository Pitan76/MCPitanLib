package net.pitan76.mcpitanlib.midohra.network;

import net.minecraft.network.FriendlyByteBuf;
import net.pitan76.mcpitanlib.api.network.PacketByteUtil;
import net.pitan76.mcpitanlib.midohra.util.math.BlockPos;

public class CompatPacketByteBuf extends FriendlyByteBuf {
    public CompatPacketByteBuf(FriendlyByteBuf buf) {
        super(buf);
    }

    public CompatPacketByteBuf() {
        super(PacketByteUtil.create());
    }

    public static CompatPacketByteBuf create() {
        return new CompatPacketByteBuf();
    }

    public static CompatPacketByteBuf of(FriendlyByteBuf buf) {
        return new CompatPacketByteBuf(buf);
    }

    public FriendlyByteBuf getRaw() {
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

    public CompatPacketByteBuf writeUtf(String string) {
        super.writeUtf(string);
        return this;
    }

    public String readUtf() {
        return super.readUtf();
    }

    public CompatPacketByteBuf writeBlockPos(net.minecraft.core.BlockPos pos) {
        super.writeBlockPos(pos);
        return this;
    }

    public net.minecraft.core.BlockPos readBlockPos() {
        return super.readBlockPos();
    }

    public CompatPacketByteBuf writeBlockPos(BlockPos pos) {
        return writeBlockPos(pos.toMinecraft());
    }

    public BlockPos readBlockPosMidohra() {
        return BlockPos.of(readBlockPos());
    }
}

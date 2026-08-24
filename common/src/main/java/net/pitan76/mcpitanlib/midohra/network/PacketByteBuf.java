package net.pitan76.mcpitanlib.midohra.network;

import net.pitan76.mcpitanlib.api.network.PacketByteUtil;
import net.pitan76.mcpitanlib.api.text.TextComponent;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;
import net.pitan76.mcpitanlib.midohra.item.ItemStack;
import net.pitan76.mcpitanlib.midohra.nbt.NbtCompound;
import net.pitan76.mcpitanlib.midohra.nbt.NbtElement;
import net.pitan76.mcpitanlib.midohra.util.math.BlockPos;

import java.util.UUID;

public class PacketByteBuf implements IByteBuf {
    private final CompatPacketByteBuf buf;

    public PacketByteBuf() {
        this.buf = new CompatPacketByteBuf();
    }

    public PacketByteBuf(CompatPacketByteBuf buf) {
        this.buf = buf;
    }

    public PacketByteBuf(net.minecraft.network.PacketByteBuf buf) {
        this.buf = new CompatPacketByteBuf(buf);
    }

    public static PacketByteBuf of() {
        return new PacketByteBuf();
    }

    public static PacketByteBuf of(CompatPacketByteBuf buf) {
        return new PacketByteBuf(buf);
    }

    public static PacketByteBuf of(net.minecraft.network.PacketByteBuf buf) {
        return new PacketByteBuf(buf);
    }

    public CompatPacketByteBuf getBuf() {
        return buf;
    }

    @Override
    public CompatPacketByteBuf toCompat() {
        return getBuf();
    }

    @Override
    public net.minecraft.network.PacketByteBuf toRaw() {
        return getBuf().getRaw();
    }

    @Override
    public PacketByteBuf toMidohra() {
        return this;
    }

    @Override
    public int hashCode() {
        return getBuf().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        PacketByteBuf other = (PacketByteBuf) obj;
        return getBuf().equals(other.getBuf());
    }

    public PacketByteBuf writeInt(int value) {
        PacketByteUtil.writeInt(getBuf(), value);
        return this;
    }

    public int readInt() {
        return PacketByteUtil.readInt(getBuf());
    }

    public PacketByteBuf writeLong(long value) {
        PacketByteUtil.writeLong(getBuf(), value);
        return this;
    }

    public long readLong() {
        return PacketByteUtil.readLong(getBuf());
    }

    public PacketByteBuf writeBool(boolean value) {
        PacketByteUtil.writeBoolean(getBuf(), value);
        return this;
    }

    public boolean readBool() {
        return PacketByteUtil.readBoolean(getBuf());
    }

    public PacketByteBuf writeFloat(float value) {
        PacketByteUtil.writeFloat(getBuf(), value);
        return this;
    }

    public float readFloat() {
        return PacketByteUtil.readFloat(getBuf());
    }

    public PacketByteBuf writeDouble(double value) {
        PacketByteUtil.writeDouble(getBuf(), value);
        return this;
    }

    public double readDouble() {
        return PacketByteUtil.readDouble(getBuf());
    }

    public PacketByteBuf writeString(String value) {
        PacketByteUtil.writeString(getBuf(), value);
        return this;
    }

    public String readString() {
        return PacketByteUtil.readString(getBuf());
    }

    public PacketByteBuf writeByte(byte bytes) {
        PacketByteUtil.writeByte(getBuf(), bytes);
        return this;
    }

    public byte readByte() {
        return PacketByteUtil.readByte(getBuf());
    }

    public PacketByteBuf writeBytes(byte[] bytes) {
        PacketByteUtil.writeByteArray(getBuf(), bytes);
        return this;
    }

    public byte[] readBytes() {
        return PacketByteUtil.readByteArray(getBuf());
    }

    public PacketByteBuf writeShort(short value) {
        PacketByteUtil.writeShort(getBuf(), value);
        return this;
    }

    public short readShort() {
        return PacketByteUtil.readShort(getBuf());
    }

    public PacketByteBuf writeVarInt(int value) {
        PacketByteUtil.writeVarInt(getBuf(), value);
        return this;
    }

    public int readVarInt() {
        return PacketByteUtil.readVarInt(getBuf());
    }

    public PacketByteBuf writeVarLong(long value) {
        PacketByteUtil.writeVarLong(getBuf(), value);
        return this;
    }

    public long readVarLong() {
        return PacketByteUtil.readVarLong(getBuf());
    }

    public PacketByteBuf writeVar(Object value) {
        PacketByteUtil.writeVar(getBuf(), value);
        return this;
    }

    public ItemStack readItemStack() {
        return ItemStack.of(PacketByteUtil.readItemStack(getBuf()));
    }

    public PacketByteBuf writeItemStack(ItemStack stack) {
        PacketByteUtil.writeItemStack(getBuf(), stack.toMinecraft());
        return this;
    }

    public NbtCompound readNbt() {
        return PacketByteUtil.readNbtM(getBuf());
    }

    public PacketByteBuf writeNbt(NbtCompound nbt) {
        PacketByteUtil.writeNbt(getBuf(), nbt);
        return this;
    }

    public NbtElement readUnlimitedNbt() {
        return NbtElement.of(PacketByteUtil.readUnlimitedNbt(getBuf()));
    }

    public BlockPos readBlockPos() {
        return PacketByteUtil.readBlockPosM(getBuf());
    }

    public PacketByteBuf writeBlockPos(BlockPos pos) {
        PacketByteUtil.writeBlockPos(getBuf(), pos);
        return this;
    }

    public boolean isReadable() {
        return PacketByteUtil.isReadable(getBuf());
    }

    public boolean isWritable() {
        return PacketByteUtil.isWritable(getBuf());
    }

    public boolean isReadable(int size) {
        return PacketByteUtil.isReadable(getBuf(), size);
    }

    public boolean isWritable(int size) {
        return getBuf().isWritable(size);
    }

    public PacketByteBuf writeIntArray(int[] ints) {
        PacketByteUtil.writeIntArray(getBuf(), ints);
        return this;
    }

    public int[] readIntArray() {
        return PacketByteUtil.readIntArray(getBuf());
    }

    public PacketByteBuf writeLongArray(long[] longs) {
        PacketByteUtil.writeLongArray(getBuf(), longs);
        return this;
    }

    public long[] readLongArray() {
        return PacketByteUtil.readLongArray(getBuf());
    }

    public PacketByteBuf writeByteArray(byte[] bytes) {
        PacketByteUtil.writeByteArray(getBuf(), bytes);
        return this;
    }

    public byte[] readByteArray() {
        return PacketByteUtil.readByteArray(getBuf());
    }

    public PacketByteBuf writeId(CompatIdentifier id) {
        PacketByteUtil.writeIdentifier(getBuf(), id.toMinecraft());
        return this;
    }

    public CompatIdentifier readId() {
        return CompatIdentifier.of(PacketByteUtil.readIdentifier(getBuf()));
    }

    public PacketByteBuf writeText(TextComponent text) {
        PacketByteUtil.writeText(getBuf(), text.getText());
        return this;
    }

    public TextComponent readText() {
        return new TextComponent(PacketByteUtil.readText(getBuf()));
    }

    public int capacity() {
        return getBuf().capacity();
    }

    public int maxCapacity() {
        return getBuf().maxCapacity();
    }

    public PacketByteBuf ensureWritable(int minWritableBytes) {
        getBuf().ensureWritable(minWritableBytes);
        return this;
    }

    public PacketByteBuf clear() {
        getBuf().clear();
        return this;
    }

    public int readableBytes() {
        return getBuf().readableBytes();
    }

    public int writableBytes() {
        return getBuf().writableBytes();
    }

    public PacketByteBuf writeBytes(byte[] src, int srcIndex, int length) {
        getBuf().writeBytes(src, srcIndex, length);
        return this;
    }

    public PacketByteBuf readBytes(byte[] dst, int dstIndex, int length) {
        getBuf().readBytes(dst, dstIndex, length);
        return this;
    }

    public PacketByteBuf writeUuid(UUID uuid) {
        PacketByteUtil.writeUuid(getBuf(), uuid);
        return this;
    }

    public UUID readUuid() {
        return PacketByteUtil.readUuid(getBuf());
    }
}

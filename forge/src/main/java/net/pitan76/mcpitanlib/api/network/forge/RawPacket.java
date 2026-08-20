package net.pitan76.mcpitanlib.api.network.forge;

import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;

public class RawPacket {
    private final Identifier id;
    private final byte[] bytes;

    public RawPacket(Identifier id, byte[] bytes) {
        this.id = id;
        this.bytes = bytes;
    }

    public RawPacket(Identifier id, PacketByteBuf buf) {
        this.id = id;
        byte[] b = new byte[buf.readableBytes()];
        buf.readBytes(b);
        this.bytes = b;
    }

    public Identifier getId() {
        return id;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void encode(PacketByteBuf buf) {
        buf.writeIdentifier(id);
        buf.writeByteArray(bytes);
    }

    public static RawPacket decode(PacketByteBuf buf) {
        Identifier id = buf.readIdentifier();
        byte[] bytes = buf.readByteArray();
        return new RawPacket(id, bytes);
    }
}

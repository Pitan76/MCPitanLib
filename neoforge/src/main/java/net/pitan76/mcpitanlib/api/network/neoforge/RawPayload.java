package net.pitan76.mcpitanlib.api.network.neoforge;

import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public record RawPayload(Identifier identifier, byte[] bytes) implements CustomPayload {

    public RawPayload(Identifier identifier, PacketByteBuf buf) {
        this(identifier, readBytes(buf));
    }

    private static byte[] readBytes(PacketByteBuf buf) {
        byte[] bytes = new byte[buf.readableBytes()];
        buf.readBytes(bytes);
        return bytes;
    }

    @Override
    public void write(PacketByteBuf buf) {
        buf.writeBytes(bytes);
    }

    @Override
    public Identifier id() {
        return identifier;
    }
}

package net.pitan76.mcpitanlib.midohra.network;

public interface IByteBuf {
    default CompatPacketByteBuf toCompat() {
        if (this instanceof CompatPacketByteBuf)
            return (CompatPacketByteBuf) this;
        else if (this instanceof net.minecraft.network.PacketByteBuf)
            return new CompatPacketByteBuf((net.minecraft.network.PacketByteBuf) this);
        else
            throw new UnsupportedOperationException("This IByteBuf cannot be converted to CompatPacketByteBuf");
    }

    default net.minecraft.network.PacketByteBuf toRaw() {
        if (this instanceof net.minecraft.network.PacketByteBuf)
            return (net.minecraft.network.PacketByteBuf) this;
        else
            throw new UnsupportedOperationException("This IByteBuf is not a PacketByteBuf");
    }

    default net.pitan76.mcpitanlib.midohra.network.PacketByteBuf toMidohra() {
        if (this instanceof net.pitan76.mcpitanlib.midohra.network.PacketByteBuf)
            return (net.pitan76.mcpitanlib.midohra.network.PacketByteBuf) this;
        else if (this instanceof CompatPacketByteBuf)
            return new net.pitan76.mcpitanlib.midohra.network.PacketByteBuf((CompatPacketByteBuf) this);
        else if (this instanceof net.minecraft.network.PacketByteBuf)
            return new net.pitan76.mcpitanlib.midohra.network.PacketByteBuf((net.minecraft.network.PacketByteBuf) this);
        else
            throw new UnsupportedOperationException("This IByteBuf cannot be converted to PacketByteBuf");
    }
}

package net.pitan76.mcpitanlib.midohra.network;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufConvertible;
import net.minecraft.network.FriendlyByteBuf;

public interface IByteBuf extends ByteBufConvertible {
    default CompatPacketByteBuf toCompat() {
        if (this instanceof CompatPacketByteBuf)
            return (CompatPacketByteBuf) this;
        else if (this instanceof FriendlyByteBuf)
            return new CompatPacketByteBuf((FriendlyByteBuf) this);
        else
            throw new UnsupportedOperationException("This IByteBuf cannot be converted to CompatPacketByteBuf");
    }

    default FriendlyByteBuf toRaw() {
        if (this instanceof FriendlyByteBuf)
            return (FriendlyByteBuf) this;
        else
            throw new UnsupportedOperationException("This IByteBuf is not a FriendlyByteBuf");
    }

    default net.pitan76.mcpitanlib.midohra.network.PacketByteBuf toMidohra() {
        if (this instanceof net.pitan76.mcpitanlib.midohra.network.PacketByteBuf)
            return (net.pitan76.mcpitanlib.midohra.network.PacketByteBuf) this;
        else if (this instanceof CompatPacketByteBuf)
            return new net.pitan76.mcpitanlib.midohra.network.PacketByteBuf((CompatPacketByteBuf) this);
        else if (this instanceof FriendlyByteBuf)
            return new net.pitan76.mcpitanlib.midohra.network.PacketByteBuf((FriendlyByteBuf) this);
        else
            throw new UnsupportedOperationException("This IByteBuf cannot be converted to PacketByteBuf");
    }

    @Override
    default ByteBuf asByteBuf() {
        if (this instanceof ByteBuf)
            return (ByteBuf) this;
        else
            throw new UnsupportedOperationException("This IByteBuf cannot be converted to ByteBuf");
    }
}

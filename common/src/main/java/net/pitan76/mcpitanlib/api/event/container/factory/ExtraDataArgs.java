package net.pitan76.mcpitanlib.api.event.container.factory;

import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.pitan76.mcpitanlib.api.event.BaseEvent;
import net.pitan76.mcpitanlib.api.network.PacketByteUtil;

public class ExtraDataArgs extends BaseEvent {
    public PacketByteBuf buf;
    public ServerPlayerEntity player;

    public ExtraDataArgs(PacketByteBuf buf, ServerPlayerEntity player) {
        super();
        this.buf = buf;
        this.player = player;
    }

    public ExtraDataArgs() {
        super();
    }

    public ExtraDataArgs(PacketByteBuf buf) {
        super();
        this.buf = buf;
    }

    public boolean hasPlayer() {
        return player != null;
    }

    public boolean hasBuf() {
        return buf != null;
    }

    public PacketByteBuf getBuf() {
        return buf;
    }

    public ServerPlayerEntity getPlayer() {
        return player;
    }

    /**
     * @param obj The object to write
     * @see PacketByteUtil#writeVar(PacketByteBuf, Object)
     */
    public void writeVar(Object obj) {
        PacketByteUtil.writeVar(getBuf(), obj);
    }
}

package net.pitan76.mcpitanlib.api.event.container.factory;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.pitan76.mcpitanlib.api.entity.Player;
import net.pitan76.mcpitanlib.api.event.BaseEvent;
import net.pitan76.mcpitanlib.api.network.PacketByteUtil;
import net.pitan76.mcpitanlib.midohra.network.CompatPacketByteBuf;

public class ExtraDataArgs extends BaseEvent {
    public FriendlyByteBuf buf;
    public ServerPlayer player;

    public ExtraDataArgs(FriendlyByteBuf buf, ServerPlayer player) {
        super();
        this.buf = buf;
        this.player = player;
    }

    public ExtraDataArgs() {
        super();
    }

    public ExtraDataArgs(FriendlyByteBuf buf) {
        super();
        this.buf = buf;
    }

    public boolean hasPlayer() {
        return player != null;
    }

    public boolean hasBuf() {
        return buf != null;
    }

    public FriendlyByteBuf getBuf() {
        return buf;
    }

    public ServerPlayer getPlayer() {
        return player;
    }

    public Player getCompatPlayer() {
        return new Player(player);
    }

    /**
     * @param obj The object to write
     * @see PacketByteUtil#writeVar(FriendlyByteBuf, Object)
     */
    public void writeVar(Object obj) {
        PacketByteUtil.writeVar(getBuf(), obj);
    }

    public CompatPacketByteBuf getCompatBuf() {
        return CompatPacketByteBuf.of(getBuf());
    }
}

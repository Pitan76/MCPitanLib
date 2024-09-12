package net.pitan76.mcpitanlib.core.network;

import io.netty.buffer.ByteBufUtil;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

import java.util.HashMap;
import java.util.Map;

public class BufPayload implements CustomPayload {

    public final Id<BufPayload> ID;

    public BufPayload(byte[] data, Id<BufPayload> id) {
        this.data = data;
        this.ID = id;

        createCodec(id);
    }

    public BufPayload(PacketByteBuf buf, Id<BufPayload> id) {
        this(ByteBufUtil.getBytes(buf), id);
    }

    public BufPayload(PacketByteBuf buf, Identifier id) {
        this(ByteBufUtil.getBytes(buf), id(id));
    }

    public byte[] data;

    public byte[] getData() {
        return data;
    }

    private static final Map<Id<BufPayload>, PacketCodec<PacketByteBuf, BufPayload>> CODEC_CACHE = new HashMap<>();

    public static PacketCodec<PacketByteBuf, BufPayload> getCodec(Id<BufPayload> id) {
        if (CODEC_CACHE.containsKey(id)) return CODEC_CACHE.get(id);

        return createCodec(id);
    }

    private static PacketCodec<PacketByteBuf, BufPayload> createCodec(Id<BufPayload> id) {
        PacketCodec<PacketByteBuf, BufPayload> codec = PacketCodecs.BYTE_ARRAY.xmap((data) -> new BufPayload(data, id), BufPayload::getData).cast();
        CODEC_CACHE.put(id, codec);
        return codec;
    }

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }

    public static Id<BufPayload> id(Identifier id) {
        if (CODEC_CACHE.isEmpty()) return new Id<>(id);

        for (Id<BufPayload> key : CODEC_CACHE.keySet()) {
            if (key.id().equals(id))
                return key;
        }

        return new Id<>(id);
    }
}

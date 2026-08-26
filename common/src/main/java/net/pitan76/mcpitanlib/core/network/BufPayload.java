package net.pitan76.mcpitanlib.core.network;

import io.netty.buffer.ByteBufUtil;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;

import java.util.HashMap;
import java.util.Map;

public class BufPayload implements CustomPacketPayload {

    public final Type<BufPayload> ID;

    public BufPayload(byte[] data, Type<BufPayload> id) {
        this.data = data;
        this.ID = id;

        createCodec(id);
    }

    public BufPayload(FriendlyByteBuf buf, Type<BufPayload> id) {
        this(ByteBufUtil.getBytes(buf), id);
    }

    public BufPayload(FriendlyByteBuf buf, Identifier id) {
        this(ByteBufUtil.getBytes(buf), id(id));
    }

    public byte[] data;

    public byte[] getData() {
        return data;
    }

    private static final Map<Type<BufPayload>, StreamCodec<FriendlyByteBuf, BufPayload>> CODEC_CACHE = new HashMap<>();

    public static StreamCodec<FriendlyByteBuf, BufPayload> getCodec(Type<BufPayload> id) {
        if (CODEC_CACHE.containsKey(id)) return CODEC_CACHE.get(id);

        return createCodec(id);
    }

    public static final int MAX_DATA_SIZE = 1024 * 1024 * 32; // 32MB

    private static StreamCodec<FriendlyByteBuf, BufPayload> createCodec(Type<BufPayload> id) {
        StreamCodec<FriendlyByteBuf, BufPayload> codec = ByteBufCodecs.byteArray(MAX_DATA_SIZE).map((data) -> new BufPayload(data, id), BufPayload::getData).cast();
        CODEC_CACHE.put(id, codec);
        return codec;
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return ID;
    }

    public static Type<BufPayload> id(Identifier id) {
        if (CODEC_CACHE.isEmpty()) return new Type<>(id);

        for (Type<BufPayload> key : CODEC_CACHE.keySet()) {
            if (key.id().equals(id))
                return key;
        }

        return new Type<>(id);
    }
}

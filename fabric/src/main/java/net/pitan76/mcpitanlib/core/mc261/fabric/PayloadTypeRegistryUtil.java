package net.pitan76.mcpitanlib.core.mc261.fabric;

import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.minecraft.resources.Identifier;
import net.pitan76.mcpitanlib.core.network.BufPayload;

import java.util.HashSet;
import java.util.Set;

/**
 * BufPayloadのペイロードタイプ登録を一元管理する。
 */
public class PayloadTypeRegistryUtil {
    private static final Set<Identifier> clientbound = new HashSet<>();
    private static final Set<Identifier> serverbound = new HashSet<>();

    /**
     * 双方向のペイロードタイプを登録する。重複登録は無視される。
     */
    public static void registerBoth(Identifier identifier) {
        registerClientbound(identifier);
        registerServerbound(identifier);
    }

    public static void registerClientbound(Identifier identifier) {
        if (!clientbound.add(identifier)) return;

        BufPayload.Type<BufPayload> id = BufPayload.id(identifier);
        PayloadTypeRegistry.clientboundPlay().register(id, BufPayload.getCodec(id));
    }

    public static void registerServerbound(Identifier identifier) {
        if (!serverbound.add(identifier)) return;

        BufPayload.Type<BufPayload> id = BufPayload.id(identifier);
        PayloadTypeRegistry.serverboundPlay().register(id, BufPayload.getCodec(id));
    }
}

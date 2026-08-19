package net.pitan76.mcpitanlib.core.network.fabric;

import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.minecraft.util.Identifier;
import net.pitan76.mcpitanlib.core.network.BufPayload;

import java.util.HashSet;
import java.util.Set;

/**
 * ペイロードタイプの登録状況をクライアント/サーバー間で共有する。
 * <p>
 * 送信側と受信側で別々に登録すると同じIDを二重登録してしまうため、ここで一元管理する。
 */
public class FabricPayloadTypes {

    private static final Set<Identifier> S2C_REGISTERED = new HashSet<>();
    private static final Set<Identifier> C2S_REGISTERED = new HashSet<>();

    public static synchronized void registerS2C(Identifier identifier) {
        if (!S2C_REGISTERED.add(identifier)) return;

        BufPayload.Id<BufPayload> id = BufPayload.id(identifier);
        PayloadTypeRegistry.playS2C().register(id, BufPayload.getCodec(id));
    }

    public static synchronized void registerC2S(Identifier identifier) {
        if (!C2S_REGISTERED.add(identifier)) return;

        BufPayload.Id<BufPayload> id = BufPayload.id(identifier);
        PayloadTypeRegistry.playC2S().register(id, BufPayload.getCodec(id));
    }
}

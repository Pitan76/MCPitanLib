package net.pitan76.mcpitanlib.fabric.event;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.network.ClientPlayerEntity;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Fabric APIの1.19.2版にはClientPreAttackCallbackが無いため、
 * MinecraftClientMixinから発火する自前のディスパッチャで代替する。
 */
@Environment(EnvType.CLIENT)
public class ClientPreAttackCallbacks {
    private static final List<PreAttack> HANDLERS = new CopyOnWriteArrayList<>();

    public static void register(PreAttack handler) {
        HANDLERS.add(handler);
    }

    public static void preAttack(ClientPlayerEntity player) {
        for (PreAttack handler : HANDLERS) {
            handler.preAttack(player);
        }
    }

    @FunctionalInterface
    public interface PreAttack {
        void preAttack(ClientPlayerEntity player);
    }
}

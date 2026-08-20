package net.pitan76.mcpitanlib.api.util;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.SharedConstants;
import net.pitan76.mcpitanlib.api.util.client.ClientUtil;

public class MCVersionUtil {

    public static int getProtocolVersion() {
        return SharedConstants.getProtocolVersion();
    }

    public static String getGameVersion() {
        return ClientVersionHolder.get();
    }

    // クライアント専用クラス(MinecraftClient)への参照を切り離すためのホルダー
    @Environment(EnvType.CLIENT)
    private static class ClientVersionHolder {
        private static String get() {
            return ClientUtil.getClient().getLaunchedVersion();
        }
    }

    public static boolean isSupportedComponent() {
        return SharedConstants.getProtocolVersion() >= 766;
    }
}

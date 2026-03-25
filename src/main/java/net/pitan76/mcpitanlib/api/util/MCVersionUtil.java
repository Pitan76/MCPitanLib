package net.pitan76.mcpitanlib.api.util;

import net.minecraft.SharedConstants;
import net.pitan76.mcpitanlib.api.util.client.ClientUtil;

public class MCVersionUtil {

    public static int getProtocolVersion() {
        return SharedConstants.getProtocolVersion();
    }

    public static String getGameVersion() {
        return ClientUtil.getClient().getGameVersion();
    }

    public static boolean isSupportedComponent() {
        return SharedConstants.getProtocolVersion() >= 766;
    }
}

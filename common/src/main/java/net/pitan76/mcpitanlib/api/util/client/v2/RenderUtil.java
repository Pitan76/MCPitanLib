package net.pitan76.mcpitanlib.api.util.client.v2;

import net.pitan76.mcpitanlib.api.util.CompatIdentifier;

public class RenderUtil extends net.pitan76.mcpitanlib.api.util.client.RenderUtil {
    public static void setShaderTexture(int texture, CompatIdentifier id) {
        setShaderTexture(texture, id.toMinecraft());
    }
}

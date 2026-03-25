package net.pitan76.mcpitanlib.guilib;

import net.pitan76.mcpitanlib.api.util.CompatIdentifier;
import net.pitan76.mcpitanlib.api.util.PlatformUtil;

public class MPLGuiLib {
    public static final String MOD_ID = "mplguilib";
    public static final String MOD_NAME = "MPL GuiLib";

    public static void init() {
        if (PlatformUtil.isClient())
            MGLClientNetworks.init();
    }

    // ----
    /**
     * @param path The path of the id
     * @return The id
     */
    public static CompatIdentifier _id(String path) {
        return CompatIdentifier.of(MOD_ID, path);
    }

    public String getId() {
        return MOD_ID;
    }

    public String getName() {
        return MOD_NAME;
    }
}
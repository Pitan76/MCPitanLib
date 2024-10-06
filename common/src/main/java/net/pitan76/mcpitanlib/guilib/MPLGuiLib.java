package net.pitan76.mcpitanlib.guilib;

import net.pitan76.mcpitanlib.api.CommonModInitializer;
import net.pitan76.mcpitanlib.api.registry.v2.CompatRegistryV2;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;

public class MPLGuiLib extends CommonModInitializer {
    public static final String MOD_ID = "mplguilib";
    public static final String MOD_NAME = "MPL GuiLib";

    public static MPLGuiLib INSTANCE;
    public static CompatRegistryV2 registry;

    @Override
    public void init() {
        INSTANCE = this;
        registry = super.registry;

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

    @Override
    public String getId() {
        return MOD_ID;
    }

    @Override
    public String getName() {
        return MOD_NAME;
    }
}
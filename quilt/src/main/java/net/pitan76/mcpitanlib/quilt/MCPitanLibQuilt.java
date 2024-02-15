package net.pitan76.mcpitanlib.quilt;

import net.pitan76.mcpitanlib.MCPitanLib;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;

public class MCPitanLibQuilt implements ModInitializer {

    @Override
    public void onInitialize(ModContainer mod) {
        MCPitanLib.init();
    }
}
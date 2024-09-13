package net.pitan76.mcpitanlib.api.client.event.listener;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
@FunctionalInterface
public interface WorldRenderContextListener {
    void renderer(WorldRenderContext context);
}

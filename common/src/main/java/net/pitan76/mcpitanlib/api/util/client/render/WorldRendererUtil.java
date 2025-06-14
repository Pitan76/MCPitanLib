package net.pitan76.mcpitanlib.api.util.client.render;

import net.minecraft.client.render.WorldRenderer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class WorldRendererUtil {
    public static int getLightmapCoordinates(World world, BlockPos pos) {
        return WorldRenderer.getLightmapCoordinates(world, pos);
    }

    public static int getLightmapCoordinates(net.pitan76.mcpitanlib.midohra.world.World world, net.pitan76.mcpitanlib.midohra.util.math.BlockPos pos) {
        return getLightmapCoordinates(world.getRaw(), pos.toMinecraft());
    }
}

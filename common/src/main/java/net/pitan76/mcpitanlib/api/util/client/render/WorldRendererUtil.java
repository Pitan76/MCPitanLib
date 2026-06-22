package net.pitan76.mcpitanlib.api.util.client.render;

import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

public class WorldRendererUtil {
    public static int getLightmapCoordinates(Level world, BlockPos pos) {
        return world.getLightEmission(pos); // 'LevelRenderer' のメソッド 'getLightCoords' を解決できません
    }

    public static int getLightmapCoordinates(net.pitan76.mcpitanlib.midohra.world.World world, net.pitan76.mcpitanlib.midohra.util.math.BlockPos pos) {
        return getLightmapCoordinates(world.getRaw(), pos.toMinecraft());
    }
}

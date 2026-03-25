package net.pitan76.mcpitanlib.api.world;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.pitan76.mcpitanlib.api.util.WorldUtil;

public class ExtendWorld {
    public Level world;
    public ExtendWorld(Level world) {
        this.world = world;
    }

    public boolean isDay() {
        return WorldUtil.isDay(world);
    }

    public boolean isThundering() {
        return WorldUtil.isThundering(world);
    }

    public boolean isSkyVisible(BlockPos pos) {
        return WorldUtil.isSkyVisible(world, pos);
    }

    public boolean isRaining() {
        return WorldUtil.isRaining(world);
    }

    public boolean hasSkyLight() {
        return WorldUtil.hasSkyLight(world);
    }
    
    public boolean isNight() {
        return WorldUtil.isNight(world);
    }

    public boolean isClient() {
        return WorldUtil.isClient(world);
    }

    public boolean isServer() {
        return WorldUtil.isServer(world);
    }

    public Level getMinecraftWorld() {
        return world;
    }
}

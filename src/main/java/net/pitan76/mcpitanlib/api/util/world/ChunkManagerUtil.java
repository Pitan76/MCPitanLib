package net.pitan76.mcpitanlib.api.util.world;

import net.minecraft.server.level.ServerChunkCache;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.pitan76.mcpitanlib.midohra.util.math.BlockPos;

public class ChunkManagerUtil {

    public static void markForUpdate(ServerChunkCache manager, BlockPos pos) {
        markForUpdate(manager, pos.toRaw());
    }

    public static void markForUpdate(ServerChunkCache manager, net.minecraft.core.BlockPos pos) {
        manager.blockChanged(pos);
    }

    public static void markForUpdate(ServerLevel world, net.minecraft.core.BlockPos pos) {
       markForUpdate(ServerWorldUtil.getChunkManager(world), pos);
    }

    public static void markForUpdate(Level world, net.minecraft.core.BlockPos pos) {
        if (!(world instanceof ServerLevel)) return;
        markForUpdate((ServerLevel) world, pos);
    }
}

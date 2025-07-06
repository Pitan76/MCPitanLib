package net.pitan76.mcpitanlib.api.util.world;

import net.minecraft.server.world.ServerChunkManager;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import net.pitan76.mcpitanlib.midohra.util.math.BlockPos;

public class ChunkManagerUtil {

    public static void markForUpdate(ServerChunkManager manager, BlockPos pos) {
        markForUpdate(manager, pos.toRaw());
    }

    public static void markForUpdate(ServerChunkManager manager, net.minecraft.util.math.BlockPos pos) {
        manager.markForUpdate(pos);
    }

    public static void markForUpdate(ServerWorld world, net.minecraft.util.math.BlockPos pos) {
       markForUpdate(ServerWorldUtil.getChunkManager(world), pos);
    }

    public static void markForUpdate(World world, net.minecraft.util.math.BlockPos pos) {
        if (!(world instanceof ServerWorld)) return;
        markForUpdate((ServerWorld) world, pos);
    }
}

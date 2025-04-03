package net.pitan76.mcpitanlib.api.util.world;

import net.minecraft.particle.ParticleEffect;
import net.minecraft.server.world.ServerWorld;
import net.pitan76.mcpitanlib.api.entity.Player;

public class ServerWorldUtil {
    public static void spawnParticles(ServerWorld world, ParticleEffect particle, double x, double y, double z, int count, double velocityX, double velocityY, double velocityZ, double speed) {
        world.spawnParticles(particle, x, y, z, count, velocityX, velocityY, velocityZ, speed);
    }

    public static void spawnParticles(ServerWorld world, Player player, ParticleEffect particle, boolean force, double x, double y, double z, int count, double velocityX, double velocityY, double velocityZ, double speed) {
        if (player.isServer()) {
            world.spawnParticles(player.getServerPlayer().get(), particle, force, x, y, z, count, velocityX, velocityY, velocityZ, speed);
        }
    }
}

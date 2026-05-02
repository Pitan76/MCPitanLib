package net.pitan76.mcpitanlib.midohra.entity;

import net.minecraft.world.entity.Entity;
import net.pitan76.mcpitanlib.midohra.util.math.BlockPos;
import net.pitan76.mcpitanlib.midohra.util.math.Vector3d;
import net.pitan76.mcpitanlib.midohra.world.ServerWorld;
import net.pitan76.mcpitanlib.midohra.world.World;

/**
 * An interface for entities that can be used in the Midohra API.
 * This interface provides default methods to get the entity's wrapper, position, world, and type.
 * It is recommended to implement this interface in your custom entity classes to easily integrate with the Midohra API.
 */
public interface IEntityM {
    default EntityWrapper getEntityWrapper() {
        return EntityWrapper.of((Entity) this);
    }

    default BlockPos getMidohraBlockPos() {
        return getEntityWrapper().getBlockPos();
    }

    default Vector3d getMidohraPos() {
        return getEntityWrapper().getPos();
    }

    default World getMidohraWorld() {
        return getEntityWrapper().getWorld();
    }

    default EntityTypeWrapper getTypeM() {
        return getEntityWrapper().getType();
    }

    default boolean hasServerWorld() {
        return getMidohraWorld().isServer();
    }

    default ServerWorld getMidohraServerWorld() {
        return getMidohraWorld().toServerWorld().get();
    }
}

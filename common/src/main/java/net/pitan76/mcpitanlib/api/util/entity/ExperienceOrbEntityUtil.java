package net.pitan76.mcpitanlib.api.util.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ExperienceOrbEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.pitan76.mcpitanlib.api.util.WorldUtil;
import net.pitan76.mcpitanlib.midohra.entity.ExperienceOrbEntityWrapper;

import java.util.List;
import java.util.stream.Collectors;

public class ExperienceOrbEntityUtil {
    public static int getExperienceAmount(ExperienceOrbEntity entity) {
        return entity.getExperienceAmount();
    }

    public static List<ExperienceOrbEntity> getEntities(World world, Box box) {
        return WorldUtil.getEntitiesByType(world, EntityType.EXPERIENCE_ORB, box);
    }

    public static List<ExperienceOrbEntity> getEntities(net.pitan76.mcpitanlib.midohra.world.World world, net.pitan76.mcpitanlib.midohra.util.math.Box box) {
        return getEntities(world.getRaw(), box.toMinecraft());
    }

    public static List<ExperienceOrbEntityWrapper> getEntityWrappers(net.pitan76.mcpitanlib.midohra.world.World world, net.pitan76.mcpitanlib.midohra.util.math.Box box) {
        return getEntities(world, box).stream().map(ExperienceOrbEntityWrapper::of).collect(Collectors.toList());
    }

    public static void spawn(World world, Vec3d pos, int amount) {
        WorldUtil.spawnExperienceOrb(world, pos, amount);
    }

    public static void spawn(World world, BlockPos pos, int amount) {
        WorldUtil.spawnExperienceOrb(world, pos, amount);
    }

    public static void spawn(net.pitan76.mcpitanlib.midohra.world.World world, net.pitan76.mcpitanlib.midohra.util.math.Vector3d pos, int amount) {
        spawn(world.getRaw(), new Vec3d(pos.getX(), pos.getY(), pos.getZ()), amount);
    }

    public static void spawn(net.pitan76.mcpitanlib.midohra.world.World world, net.pitan76.mcpitanlib.midohra.util.math.BlockPos pos, int amount) {
        spawn(world.getRaw(), pos.toMinecraft(), amount);
    }
}

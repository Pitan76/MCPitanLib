package net.pitan76.mcpitanlib.api.util.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.pitan76.mcpitanlib.api.util.WorldUtil;
import net.pitan76.mcpitanlib.midohra.entity.ExperienceOrbEntityWrapper;

import java.util.List;
import java.util.stream.Collectors;

public class ExperienceOrbEntityUtil {
    public static int getExperienceAmount(ExperienceOrb entity) {
        return entity.getValue();
    }

    public static List<ExperienceOrb> getEntities(Level world, AABB box) {
        return WorldUtil.getEntitiesByType(world, EntityType.EXPERIENCE_ORB, box);
    }

    public static List<ExperienceOrb> getEntities(net.pitan76.mcpitanlib.midohra.world.World world, net.pitan76.mcpitanlib.midohra.util.math.Box box) {
        return getEntities(world.getRaw(), box.toMinecraft());
    }

    public static List<ExperienceOrbEntityWrapper> getEntityWrappers(net.pitan76.mcpitanlib.midohra.world.World world, net.pitan76.mcpitanlib.midohra.util.math.Box box) {
        return getEntities(world, box).stream().map(ExperienceOrbEntityWrapper::of).collect(Collectors.toList());
    }

    public static void spawn(Level world, Vec3 pos, int amount) {
        WorldUtil.spawnExperienceOrb(world, pos, amount);
    }

    public static void spawn(Level world, BlockPos pos, int amount) {
        WorldUtil.spawnExperienceOrb(world, pos, amount);
    }

    public static void spawn(net.pitan76.mcpitanlib.midohra.world.World world, net.pitan76.mcpitanlib.midohra.util.math.Vector3d pos, int amount) {
        spawn(world.getRaw(), new Vec3(pos.getX(), pos.getY(), pos.getZ()), amount);
    }

    public static void spawn(net.pitan76.mcpitanlib.midohra.world.World world, net.pitan76.mcpitanlib.midohra.util.math.BlockPos pos, int amount) {
        spawn(world.getRaw(), pos.toMinecraft(), amount);
    }
}

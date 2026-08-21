package net.pitan76.mcpitanlib.api.util;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.pitan76.mcpitanlib.api.entity.CompatEntity;
import net.pitan76.mcpitanlib.api.event.item.ItemAppendTooltipEvent;
import net.pitan76.mcpitanlib.api.event.nbt.NbtRWArgs;
import net.pitan76.mcpitanlib.api.registry.CompatRegistryLookup;
import net.pitan76.mcpitanlib.api.tile.CompatBlockEntity;

public class RegistryLookupUtil {
    public static CompatRegistryLookup getRegistryLookup(ItemAppendTooltipEvent e) {
        if (e == null || e.context == null) return null;
        return new CompatRegistryLookup(e.context.registries());
    }

    public static CompatRegistryLookup getRegistryLookup(CompatEntity entity) {
        if (entity == null) return null;
        return new CompatRegistryLookup(entity.registryAccess());
    }

    public static CompatRegistryLookup getRegistryLookup(Entity entity) {
        if (entity == null) return null;
        return new CompatRegistryLookup(entity.registryAccess());
    }

    public static CompatRegistryLookup getRegistryLookup(CompatBlockEntity entity) {
        if (entity == null || entity.getLevel() == null) return null;
        return new CompatRegistryLookup(entity.getLevel().registryAccess());
    }

    public static CompatRegistryLookup getRegistryLookup(BlockEntity entity) {
        if (entity == null || entity.getLevel() == null) return null;
        return new CompatRegistryLookup(entity.getLevel().registryAccess());
    }

    public static CompatRegistryLookup getRegistryLookup(NbtRWArgs args) {
        if (args == null) return null;
        return args.getRegistryLookup();
    }

    public static CompatRegistryLookup getRegistryLookup(Level world) {
        if (world == null) return null;
        return new CompatRegistryLookup(world.registryAccess());
    }
}

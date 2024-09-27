package net.pitan76.mcpitanlib.api.util;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import net.pitan76.mcpitanlib.api.entity.CompatEntity;
import net.pitan76.mcpitanlib.api.event.item.ItemAppendTooltipEvent;
import net.pitan76.mcpitanlib.api.event.nbt.NbtRWArgs;
import net.pitan76.mcpitanlib.api.registry.CompatRegistryLookup;
import net.pitan76.mcpitanlib.api.tile.CompatBlockEntity;

public class RegistryLookupUtil {
    public static CompatRegistryLookup getRegistryLookup(ItemAppendTooltipEvent e) {
        return new CompatRegistryLookup();
    }

    public static CompatRegistryLookup getRegistryLookup(CompatEntity entity) {
        return new CompatRegistryLookup(entity.getWorld().getRegistryManager());
    }

    public static CompatRegistryLookup getRegistryLookup(Entity entity) {
        return new CompatRegistryLookup(entity.getWorld().getRegistryManager());
    }

    public static CompatRegistryLookup getRegistryLookup(CompatBlockEntity entity) {
        return new CompatRegistryLookup(entity.getWorld().getRegistryManager());
    }

    public static CompatRegistryLookup getRegistryLookup(BlockEntity entity) {
        return new CompatRegistryLookup(entity.getWorld().getRegistryManager());
    }

    public static CompatRegistryLookup getRegistryLookup(NbtRWArgs args) {
        return args.getRegistryLookup();
    }

    public static CompatRegistryLookup getRegistryLookup(World world) {
        return new CompatRegistryLookup(world.getRegistryManager());
    }
}
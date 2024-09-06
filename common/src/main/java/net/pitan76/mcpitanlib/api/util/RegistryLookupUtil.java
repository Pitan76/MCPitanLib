package net.pitan76.mcpitanlib.api.util;

import net.minecraft.block.entity.BlockEntity;
import net.pitan76.mcpitanlib.api.event.nbt.NbtRWArgs;
import net.pitan76.mcpitanlib.api.registry.CompatRegistryLookup;
import net.pitan76.mcpitanlib.api.tile.CompatBlockEntity;

public class RegistryLookupUtil {
    public static CompatRegistryLookup getRegistryLookup(CompatBlockEntity entity) {
        return new CompatRegistryLookup();
    }

    public static CompatRegistryLookup getRegistryLookup(BlockEntity entity) {
        return new CompatRegistryLookup();
    }

    public static CompatRegistryLookup getRegistryLookup(NbtRWArgs args) {
        return args.getRegistryLookup();
    }
}

package net.pitan76.mcpitanlib.api.util.forge;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.fml.loading.FMLPaths;

import java.nio.file.Path;

public class PlatformUtilImpl {
    public static boolean isClient() {
        return FMLEnvironment.dist == Dist.CLIENT;
    }

    public static boolean isServer() {
        return FMLEnvironment.dist == Dist.DEDICATED_SERVER;
    }

    public static Path getConfigFolder() {
        return FMLPaths.CONFIGDIR.get();
    }

    public static boolean isModLoaded(String id) {
        return ModList.get().isLoaded(id);
    }
}

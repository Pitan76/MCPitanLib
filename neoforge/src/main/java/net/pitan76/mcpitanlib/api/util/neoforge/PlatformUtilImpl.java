package net.pitan76.mcpitanlib.api.util.neoforge;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.ModList;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.fml.loading.FMLLoader;
import net.neoforged.fml.loading.FMLPaths;
import net.neoforged.fml.loading.moddiscovery.ModInfo;

import java.nio.file.Path;
import java.util.Collection;

public class PlatformUtilImpl {
    public static boolean isClient() {
        return FMLEnvironment.getDist() == Dist.CLIENT;
    }

    public static boolean isServer() {
        return FMLEnvironment.getDist() == Dist.DEDICATED_SERVER;
    }

    public static Path getConfigFolder() {
        return FMLPaths.CONFIGDIR.get();
    }

    public static boolean isModLoaded(String id) {
        return ModList.get().isLoaded(id);
    }

    public static boolean isDevelopmentEnvironment() {
        return !FMLEnvironment.isProduction();
    }

    public static Path getGameFolder() {
        return FMLLoader.getCurrent().getGameDir();
    }

    public static Collection<String> getModIds() {
        return FMLLoader.getCurrent().getLoadingModList().getMods().stream().map(ModInfo::getModId).toList();
    }

    public static String getGameVersion() {
        return FMLLoader.getCurrent().getVersionInfo().mcVersion();
    }

    public static boolean isFabric() {
        return false;
    }

    public static boolean isForge() {
        return false;
    }

    public static boolean isNeoForge() {
        return true;
    }
}

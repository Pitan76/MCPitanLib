package net.pitan76.mcpitanlib.api.util.neoforge;

import net.minecraft.SharedConstants;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.ModList;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.fml.loading.FMLPaths;
import net.neoforged.neoforgespi.language.IModInfo;

import java.nio.file.Path;
import java.util.Collection;
import java.util.stream.Collectors;

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

    public static boolean isDevelopmentEnvironment() {
        return !FMLEnvironment.isProduction();
    }

    public static Path getGameFolder() {
        return FMLPaths.GAMEDIR.get();
    }

    public static Collection<String> getModIds() {
        return ModList.get().getMods().stream().map(IModInfo::getModId).collect(Collectors.toList());
    }

    public static String getGameVersion() {
        return SharedConstants.getGameVersion().name();
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

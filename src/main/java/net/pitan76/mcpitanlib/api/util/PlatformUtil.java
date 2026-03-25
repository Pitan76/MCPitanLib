package net.pitan76.mcpitanlib.api.util;

import net.fabricmc.api.EnvType;
import net.fabricmc.loader.api.FabricLoader;

import java.io.File;
import java.nio.file.Path;
import java.util.Collection;

public class PlatformUtil {
    public static boolean isDevelopmentEnvironment() {
        return FabricLoader.getInstance().isDevelopmentEnvironment();
    }

    public static boolean isFabric() {
        return true;
    }

    public static boolean isForge() {
        return false;
    }

    public static boolean isNeoForge() {
        return false;
    }

    public static boolean isClient() {
        return FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT;
    }

    public static boolean isServer() {
        return FabricLoader.getInstance().getEnvironmentType() == EnvType.SERVER;
    }

    public static Path getConfigFolder() {
        return FabricLoader.getInstance().getConfigDir();
    }

    public static boolean isModLoaded(String id) {
        return FabricLoader.getInstance().isModLoaded(id);
    }

    public static Path getGameFolder() {
        return FabricLoader.getInstance().getGameDir();
    }

    public static Path getModsFolder() {
        return FabricLoader.getInstance().getGameDir().resolve("mods");
    }

    public static File getConfigFolderAsFile() {
        return getConfigFolder().toFile();
    }

    public static File getGameFolderAsFile() {
        return getGameFolder().toFile();
    }

    public static File getModsFolderAsFile() {
        return getModsFolder().toFile();
    }

    public static Collection<String> getModIds() {
        return FabricLoader.getInstance().getAllMods().stream().map(modContainer -> modContainer.getMetadata().getId()).toList();
    }

    public static EnvType getEnv() {
        return FabricLoader.getInstance().getEnvironmentType();
    }

    public static String getGameVersion() {
        return FabricLoader.getInstance().getRawGameVersion();
    }
}

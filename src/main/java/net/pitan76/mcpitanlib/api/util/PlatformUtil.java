package net.pitan76.mcpitanlib.api.util;

import dev.architectury.injectables.annotations.ExpectPlatform;
import dev.architectury.platform.Platform;
import dev.architectury.utils.Env;
import net.fabricmc.api.EnvType;

import java.io.File;
import java.nio.file.Path;
import java.util.Collection;

public class PlatformUtil {
    public static boolean isDevelopmentEnvironment() {
        return Platform.isDevelopmentEnvironment();
    }

    public static boolean isFabric() {
        return Platform.isFabric();
    }

    public static boolean isForge() {
        return Platform.isMinecraftForge();
    }

    public static boolean isNeoForge() {
        return Platform.isNeoForge();
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
        return Platform.getGameFolder();
    }

    public static Path getModsFolder() {
        return Platform.getModsFolder();
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
        return Platform.getModIds();
    }

    public static EnvType getEnv() {
        return Platform.getEnv();
    }

    public static String getGameVersion() {
        return Platform.getMinecraftVersion();
    }
}

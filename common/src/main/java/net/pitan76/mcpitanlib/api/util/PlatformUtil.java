package net.pitan76.mcpitanlib.api.util;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.fabricmc.api.EnvType;
import net.fabricmc.loader.api.FabricLoader;

import java.io.File;
import java.nio.file.Path;
import java.util.Collection;

public class PlatformUtil {
    @ExpectPlatform
    public static boolean isDevelopmentEnvironment() {
        return FabricLoader.getInstance().isDevelopmentEnvironment();
    }

    @ExpectPlatform
    public static boolean isFabric() {
        return false;
    }

    @ExpectPlatform
    public static boolean isForge() {
        return false;
    }

    @ExpectPlatform
    public static boolean isNeoForge() {
        return false;
    }

    @ExpectPlatform
    public static boolean isClient() {
        return FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT;
    }

    @ExpectPlatform
    public static boolean isServer() {
        return FabricLoader.getInstance().getEnvironmentType() == EnvType.SERVER;
    }

    @ExpectPlatform
    public static Path getConfigFolder() {
        return FabricLoader.getInstance().getConfigDir();
    }

    @ExpectPlatform
    public static boolean isModLoaded(String id) {
        return FabricLoader.getInstance().isModLoaded(id);
    }

    @ExpectPlatform
    public static Path getGameFolder() {
        return FabricLoader.getInstance().getGameDir();
    }

    public static Path getModsFolder() {
        return getGameFolder().resolve("mods");
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

    @ExpectPlatform
    public static Collection<String> getModIds() {
        return FabricLoader.getInstance().getAllMods().stream().map(modContainer -> modContainer.getMetadata().getId()).toList();
    }

    @ExpectPlatform
    public static EnvType getEnv() {
        return FabricLoader.getInstance().getEnvironmentType();
    }

    @ExpectPlatform
    public static String getGameVersion() {
        return FabricLoader.getInstance().getRawGameVersion();
    }
}

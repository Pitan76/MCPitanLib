package net.pitan76.mcpitanlib.api.util;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.fabricmc.api.EnvType;

import java.io.File;
import java.nio.file.Path;
import java.util.Collection;

public class PlatformUtil {
    @ExpectPlatform
    public static boolean isDevelopmentEnvironment() {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static boolean isFabric() {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static boolean isForge() {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static boolean isNeoForge() {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static boolean isModLoaded(String id) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static boolean isClient() {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static boolean isServer() {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static Path getConfigFolder() {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static Path getGameFolder() {
        throw new AssertionError();
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
        throw new AssertionError();
    }

    public static EnvType getEnv() {
        return isClient() ? EnvType.CLIENT : EnvType.SERVER;
    }

    @ExpectPlatform
    public static String getGameVersion() {
        throw new AssertionError();
    }
}

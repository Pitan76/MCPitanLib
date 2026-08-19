package net.pitan76.mcpitanlib.api.util.fabric;

import net.fabricmc.api.EnvType;
import net.fabricmc.loader.api.FabricLoader;

import java.nio.file.Path;
import java.util.Collection;
import java.util.stream.Collectors;

public class PlatformUtilImpl {
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

    public static boolean isDevelopmentEnvironment() {
        return FabricLoader.getInstance().isDevelopmentEnvironment();
    }

    public static Path getGameFolder() {
        return FabricLoader.getInstance().getGameDir();
    }

    public static Collection<String> getModIds() {
        return FabricLoader.getInstance().getAllMods().stream()
                .map(modContainer -> modContainer.getMetadata().getId())
                .collect(Collectors.toList());
    }

    public static String getGameVersion() {
        return FabricLoader.getInstance().getModContainer("minecraft")
                .map(modContainer -> modContainer.getMetadata().getVersion().getFriendlyString())
                .orElse("");
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
}

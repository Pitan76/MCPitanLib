package net.pitan76.mcpitanlib.api.util.fabric;

import net.fabricmc.api.EnvType;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;

public class PlatformUtilImpl {
    public static boolean isDevelopmentEnvironment() {
        return FabricLoader.getInstance().isDevelopmentEnvironment();
    }

    public static boolean isFabric() {
        return true;
    }

    public static boolean isForge() {
        return false;
    }

    public static boolean isModLoaded(String id) {
        return FabricLoader.getInstance().isModLoaded(id);
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

    public static Path getGameFolder() {
        return FabricLoader.getInstance().getGameDir();
    }

    public static Collection<String> getModIds() {
        Collection<String> ids = new ArrayList<String>();
        for (ModContainer container : FabricLoader.getInstance().getAllMods()) {
            ids.add(container.getMetadata().getId());
        }

        return ids;
    }
}

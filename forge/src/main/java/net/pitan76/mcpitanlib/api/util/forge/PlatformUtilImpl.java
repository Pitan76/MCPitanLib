package net.pitan76.mcpitanlib.api.util.forge;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.forgespi.language.IModInfo;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;

public class PlatformUtilImpl {
    public static boolean isDevelopmentEnvironment() {
        return !FMLEnvironment.production;
    }

    public static boolean isFabric() {
        return false;
    }

    public static boolean isForge() {
        return true;
    }

    public static boolean isModLoaded(String id) {
        return ModList.get().isLoaded(id);
    }

    public static boolean isClient() {
        return FMLEnvironment.dist == Dist.CLIENT;
    }

    public static boolean isServer() {
        return FMLEnvironment.dist == Dist.DEDICATED_SERVER;
    }

    public static Path getConfigFolder() {
        return FMLPaths.CONFIGDIR.get();
    }

    public static Path getGameFolder() {
        return FMLPaths.GAMEDIR.get();
    }

    public static Collection<String> getModIds() {
        Collection<String> ids = new ArrayList<String>();
        for (IModInfo info : ModList.get().getMods()) {
            ids.add(info.getModId());
        }

        return ids;
    }
}

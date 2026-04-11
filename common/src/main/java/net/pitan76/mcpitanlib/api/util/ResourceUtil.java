package net.pitan76.mcpitanlib.api.util;

import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.resources.Identifier;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class ResourceUtil {
    public static Resource getResource(ResourceManager resourceManager, Identifier identifier) {
        return resourceManager.getResource(identifier).get();
    }

    public static InputStream getInputStream(Resource resource) throws IOException {
        return resource.open();
    }

    public static Map<Identifier, Resource> findResources(ResourceManager resourceManager, String startingPath, String endingPath) throws IOException {
        return resourceManager.listResources(startingPath, s -> s.toString().endsWith(endingPath));
    }

    public static void close(Resource resource) throws IOException {
        getInputStream(resource).close();
    }
}

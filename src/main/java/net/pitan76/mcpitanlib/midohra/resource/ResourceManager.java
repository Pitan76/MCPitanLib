package net.pitan76.mcpitanlib.midohra.resource;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.LevelAccessor;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;
import net.pitan76.mcpitanlib.api.util.LoggerUtil;
import net.pitan76.mcpitanlib.api.util.ResourceUtil;
import net.pitan76.mcpitanlib.midohra.server.MCServer;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ResourceManager {
    private final net.minecraft.server.packs.resources.ResourceManager resourceManager;

    protected ResourceManager(net.minecraft.server.packs.resources.ResourceManager resourceManager) {
        this.resourceManager = resourceManager;
    }

    public static ResourceManager of(net.minecraft.server.packs.resources.ResourceManager resourceManager) {
        return new ResourceManager(resourceManager);
    }

    public static ResourceManager of(net.minecraft.server.MinecraftServer server) {
        return of(MCServer.of(server));
    }

    public static ResourceManager of(MCServer server) {
        return of(server.getRaw());
    }

    @Environment(EnvType.CLIENT)
    public static ResourceManager of(Minecraft client) {
        return of(client.getResourceManager());
    }

    public static ResourceManager of(LevelAccessor worldAccess) {
        return of(worldAccess.getServer());
    }

    public static ResourceManager of(net.pitan76.mcpitanlib.midohra.world.WorldAccess worldAccess) {
        return of(worldAccess.getServer());
    }

    public net.minecraft.server.packs.resources.ResourceManager getRaw() {
        return resourceManager;
    }

    public net.minecraft.server.packs.resources.ResourceManager toMinecraft() {
        return getRaw();
    }

    public Map<CompatIdentifier, Resource> findResources(String startPath, String endPath) {
        Map<CompatIdentifier, Resource> map = new HashMap<>();
        try {
            Map<Identifier, net.minecraft.server.packs.resources.Resource> rawMap = ResourceUtil.findResources(resourceManager, startPath, endPath);
            for (Map.Entry<Identifier, net.minecraft.server.packs.resources.Resource> entry : rawMap.entrySet()) {
                map.put(CompatIdentifier.fromMinecraft(entry.getKey()), Resource.of(entry.getValue()));
            }
        } catch (IOException e) {
            LoggerUtil.error(LoggerUtil.getLogger(), "Failed to read " + startPath + ": " + e.getMessage());
            return null;
        }

        return map;
    }

    public Resource getResource(CompatIdentifier id) {
        Optional<net.minecraft.server.packs.resources.Resource> resource = resourceManager.getResource(id.toMinecraft());
        return resource.map(Resource::of).orElse(null);
    }

    public List<Resource> getAllResources(CompatIdentifier id) {
        return resourceManager.getResourceStack(id.toMinecraft())
                .stream().map(Resource::of).toList();
    }

}

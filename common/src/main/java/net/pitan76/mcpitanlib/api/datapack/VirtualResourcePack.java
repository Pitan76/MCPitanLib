package net.pitan76.mcpitanlib.api.datapack;

import net.minecraft.resource.InputSupplier;
import net.minecraft.resource.ResourcePack;
import net.minecraft.resource.ResourcePackInfo;
import net.minecraft.resource.ResourcePackSource;
import net.minecraft.resource.ResourceType;
import net.minecraft.resource.metadata.ResourceMetadataReader;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.io.InputStream;
import java.util.Optional;
import java.util.Set;

/**
 * {@link VirtualDatapack} のリソースが属するパック。
 * 実体を持たないので、ほぼ全てのメソッドは空を返す。
 */
public class VirtualResourcePack implements ResourcePack {

    public static final VirtualResourcePack INSTANCE = new VirtualResourcePack();

    private static final ResourcePackInfo INFO =
            new ResourcePackInfo("mcpitanlib_virtual", Text.literal("MCPitanLib Virtual"), ResourcePackSource.BUILTIN, Optional.empty());

    private VirtualResourcePack() {
    }

    @Override
    public InputSupplier<InputStream> openRoot(String... segments) {
        return null;
    }

    @Override
    public InputSupplier<InputStream> open(ResourceType type, Identifier id) {
        return null;
    }

    @Override
    public void findResources(ResourceType type, String namespace, String prefix, ResultConsumer consumer) {
    }

    @Override
    public Set<String> getNamespaces(ResourceType type) {
        return Set.of();
    }

    @Override
    public <T> T parseMetadata(ResourceMetadataReader<T> reader) {
        return null;
    }

    @Override
    public ResourcePackInfo getInfo() {
        return INFO;
    }

    @Override
    public void close() {
    }
}

package net.pitan76.mcpitanlib.api.datapack;

import net.minecraft.resource.InputSupplier;
import net.minecraft.resource.ResourcePack;
import net.minecraft.resource.ResourceType;
import net.minecraft.resource.metadata.ResourceMetadataReader;
import net.minecraft.util.Identifier;

import java.io.InputStream;
import java.util.Collections;
import java.util.Set;

/**
 * {@link VirtualDatapack} のリソースが属するパック。
 * 実体を持たないので、ほぼ全てのメソッドは空を返す。
 */
public class VirtualResourcePack implements ResourcePack {

    public static final VirtualResourcePack INSTANCE = new VirtualResourcePack();

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
        return Collections.emptySet();
    }

    @Override
    public <T> T parseMetadata(ResourceMetadataReader<T> reader) {
        return null;
    }

    @Override
    public String getName() {
        return "MCPitanLib Virtual";
    }

    @Override
    public void close() {
    }
}

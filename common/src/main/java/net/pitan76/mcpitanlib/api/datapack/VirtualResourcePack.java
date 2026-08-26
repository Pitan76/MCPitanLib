package net.pitan76.mcpitanlib.api.datapack;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.server.packs.PackLocationInfo;
import net.minecraft.server.packs.PackResources;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.metadata.MetadataSectionType;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraft.server.packs.resources.IoSupplier;

import java.io.InputStream;
import java.util.Optional;
import java.util.Set;

/**
 * {@link VirtualDatapack} のリソースが属するパック。
 * 実体を持たないので、ほぼ全てのメソッドは空を返す。
 */
public class VirtualResourcePack implements PackResources {

    public static final VirtualResourcePack INSTANCE = new VirtualResourcePack();

    private static final PackLocationInfo INFO =
            new PackLocationInfo("mcpitanlib_virtual", Component.literal("MCPitanLib Virtual"), PackSource.BUILT_IN, Optional.empty());

    private VirtualResourcePack() {
    }

    @Override
    public IoSupplier<InputStream> getRootResource(String... segments) {
        return null;
    }

    @Override
    public IoSupplier<InputStream> getResource(PackType type, Identifier id) {
        return null;
    }

    @Override
    public void listResources(PackType type, String namespace, String prefix, ResourceOutput output) {
    }

    @Override
    public Set<String> getNamespaces(PackType type) {
        return Set.of();
    }

    @Override
    public <T> T getMetadataSection(MetadataSectionType<T> type) {
        return null;
    }

    @Override
    public PackLocationInfo location() {
        return INFO;
    }

    @Override
    public void close() {
    }
}

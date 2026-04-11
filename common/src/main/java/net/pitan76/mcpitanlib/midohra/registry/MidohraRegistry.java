package net.pitan76.mcpitanlib.midohra.registry;

import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.pitan76.mcpitanlib.api.block.v2.CompatBlock;
import net.pitan76.mcpitanlib.api.entity.CompatEntity;
import net.pitan76.mcpitanlib.api.entity.EntityTypeBuilder;
import net.pitan76.mcpitanlib.api.item.v2.CompatItem;
import net.pitan76.mcpitanlib.api.registry.result.RegistryResult;
import net.pitan76.mcpitanlib.api.registry.result.SupplierResult;
import net.pitan76.mcpitanlib.api.registry.v2.CompatRegistryV2;
import net.pitan76.mcpitanlib.api.tile.BlockEntityTypeBuilder;
import net.pitan76.mcpitanlib.api.tile.CompatBlockEntity;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;
import net.pitan76.mcpitanlib.midohra.block.BlockWrapper;
import net.pitan76.mcpitanlib.midohra.block.SupplierBlockWrapper;
import net.pitan76.mcpitanlib.midohra.block.SupplierTypedBlockWrapper;
import net.pitan76.mcpitanlib.midohra.block.TypedBlockWrapper;
import net.pitan76.mcpitanlib.midohra.block.entity.BlockEntityTypeWrapper;
import net.pitan76.mcpitanlib.midohra.block.entity.SupplierBlockEntityTypeWrapper;
import net.pitan76.mcpitanlib.midohra.block.entity.SupplierTypedBlockEntityTypeWrapper;
import net.pitan76.mcpitanlib.midohra.block.entity.TypedBlockEntityTypeWrapper;
import net.pitan76.mcpitanlib.midohra.entity.EntityTypeWrapper;
import net.pitan76.mcpitanlib.midohra.entity.SupplierEntityTypeWrapper;
import net.pitan76.mcpitanlib.midohra.entity.SupplierTypedEntityTypeWrapper;
import net.pitan76.mcpitanlib.midohra.entity.TypedEntityTypeWrapper;
import net.pitan76.mcpitanlib.midohra.item.ItemWrapper;
import net.pitan76.mcpitanlib.midohra.item.SupplierItemWrapper;
import net.pitan76.mcpitanlib.midohra.item.SupplierTypedItemWrapper;
import net.pitan76.mcpitanlib.midohra.item.TypedItemWrapper;

import java.util.function.Supplier;

public class MidohraRegistry {
    protected final CompatRegistryV2 registry;

    public MidohraRegistry(CompatRegistryV2 registry) {
        this.registry = registry;
    }

    public static MidohraRegistry of(CompatRegistryV2 registry) {
        return new MidohraRegistry(registry);
    }

    public CompatRegistryV2 getCompatRegistry() {
        return registry;
    }

    public ItemWrapper registerRawItem(CompatIdentifier id, Supplier<Item> supplier) {
        RegistryResult<Item> result = registry.registerItem(id, supplier);
        return SupplierItemWrapper.of(result);
    }

    public ItemWrapper registerRawItem(String id, Supplier<Item> supplier) {
        return registerRawItem(fixId(id), supplier);
    }

    public <T extends CompatItem> TypedItemWrapper<T> registerItem(CompatIdentifier id, Supplier<T> supplier) {
        ItemWrapper wrapper = registerRawItem(id, supplier::get);
        return SupplierTypedItemWrapper.of(wrapper);
    }

    public <T extends CompatItem> TypedItemWrapper<T> registerItem(String id, Supplier<T> supplier) {
        return registerItem(fixId(id), supplier);
    }

    public BlockWrapper registerRawBlock(CompatIdentifier id, Supplier<Block> supplier) {
        RegistryResult<Block> result = registry.registerBlock(id, supplier);
        return SupplierBlockWrapper.of(result);
    }

    public BlockWrapper registerRawBlock(String id, Supplier<Block> supplier) {
        return registerRawBlock(fixId(id), supplier);
    }

    public <T extends CompatBlock> TypedBlockWrapper<T> registerBlock(CompatIdentifier id, Supplier<T> supplier) {
        BlockWrapper wrapper = registerRawBlock(id, supplier::get);
        return SupplierTypedBlockWrapper.of(wrapper);
    }

    public <T extends CompatBlock> TypedBlockWrapper<T> registerBlock(String id, Supplier<T> supplier) {
        return registerBlock(fixId(id), supplier);
    }

    public BlockEntityTypeWrapper registerRawBlockEntityType(CompatIdentifier id, Supplier<BlockEntityType<?>> supplier) {
        RegistryResult<BlockEntityType<?>> result = registry.registerBlockEntityType(id, supplier);
        return SupplierBlockEntityTypeWrapper.of(result);
    }

    public BlockEntityTypeWrapper registerRawBlockEntityType(String id, Supplier<BlockEntityType<?>> supplier) {
        return registerRawBlockEntityType(fixId(id), supplier);
    }

    public <T extends CompatBlockEntity> BlockEntityTypeWrapper registerRawBlockEntityType(CompatIdentifier id, BlockEntityTypeBuilder<T> builder) {
        SupplierResult<BlockEntityType<T>> result = registry.registerBlockEntityType(id, builder);
        return SupplierBlockEntityTypeWrapper.of(result::get);
    }

    public <T extends CompatBlockEntity> BlockEntityTypeWrapper registerRawBlockEntityType(String id, BlockEntityTypeBuilder<T> builder) {
        return registerRawBlockEntityType(fixId(id), builder);
    }

    public <T extends CompatBlockEntity> TypedBlockEntityTypeWrapper<T> registerBlockEntityType(CompatIdentifier id, BlockEntityTypeBuilder<T> builder) {
        BlockEntityTypeWrapper wrapper = registerRawBlockEntityType(id, builder);
        return SupplierTypedBlockEntityTypeWrapper.of(() -> (BlockEntityType<T>) wrapper.get());
    }

    public EntityTypeWrapper registerRawEntityType(CompatIdentifier id, Supplier<EntityType<?>> supplier) {
        RegistryResult<EntityType<?>> result = registry.registerEntity(id, supplier);
        return SupplierEntityTypeWrapper.of(result);
    }

    public EntityTypeWrapper registerRawEntityType(String id, Supplier<EntityType<?>> supplier) {
        return registerRawEntityType(fixId(id), supplier);
    }

    public <T extends CompatEntity> EntityTypeWrapper registerRawEntityType(CompatIdentifier id, EntityTypeBuilder<T> builder) {
        RegistryResult<EntityType<?>> result = registry.registerEntity(id, () -> builder.build());
        return SupplierEntityTypeWrapper.of(result::get);
    }

    public <T extends CompatEntity> EntityTypeWrapper registerRawEntityType(String id, EntityTypeBuilder<T> builder) {
        return registerRawEntityType(fixId(id), builder);
    }

    public <T extends CompatEntity> TypedEntityTypeWrapper<T> registerEntityType(CompatIdentifier id, EntityTypeBuilder<T> builder) {
        EntityTypeWrapper wrapper = registerRawEntityType(id, builder);
        return SupplierTypedEntityTypeWrapper.of(() -> (EntityType<T>) wrapper.get());
    }

    public <T extends CompatEntity> TypedEntityTypeWrapper<T> registerEntityType(String id, EntityTypeBuilder<T> builder) {
        return registerEntityType(fixId(id), builder);
    }

    /**
     * If the id doesn't contain a namespace, add the default namespace to it.
     * @param id The id to fix.
     * @return The fixed id.
     */
    public CompatIdentifier fixId(String id) {
        if (!id.contains(":")) {
            id = registry.cr1.getNamespace() + ":" + id;
        }
        return CompatIdentifier.of(id);
    }
}

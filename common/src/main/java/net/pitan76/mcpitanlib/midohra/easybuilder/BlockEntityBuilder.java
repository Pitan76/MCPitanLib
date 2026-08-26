package net.pitan76.mcpitanlib.midohra.easybuilder;

import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.pitan76.mcpitanlib.api.event.nbt.ReadNbtArgs;
import net.pitan76.mcpitanlib.api.event.nbt.WriteNbtArgs;
import net.pitan76.mcpitanlib.api.registry.result.SupplierResult;
import net.pitan76.mcpitanlib.api.registry.v2.CompatRegistryV2;
import net.pitan76.mcpitanlib.midohra.registry.MidohraRegistryV2;
import net.pitan76.mcpitanlib.api.tile.BlockEntityTypeBuilder;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;
import net.pitan76.mcpitanlib.midohra.block.BlockWrapper;
import net.pitan76.mcpitanlib.midohra.block.entity.BlockEntityTypeWrapper;
import net.pitan76.mcpitanlib.midohra.block.entity.BlockEntityWrapper;
import net.pitan76.mcpitanlib.midohra.block.entity.RewritableBlockEntityTypeWrapper;
import net.pitan76.mcpitanlib.midohra.easybuilder.built.BuiltBlockEntity;

import java.util.Arrays;
import java.util.function.BiConsumer;

public class BlockEntityBuilder {

    public BiConsumer<BlockEntityWrapper, WriteNbtArgs> onWriteNbt;
    public BiConsumer<BlockEntityWrapper, ReadNbtArgs> onReadNbt;
    public BiConsumer<BlockEntityWrapper, BlockEntityBuilder> onInit;

    public CompatIdentifier id;

    public BlockEntityBuilder(CompatIdentifier id) {
        this.id = id;
    }

    public static BlockEntityBuilder of(CompatIdentifier id) {
        return new BlockEntityBuilder(id);
    }

    public BlockEntityTypeWrapper build(CompatRegistryV2 registry, CompatIdentifier id, BlockWrapper... blocks) {
        RewritableBlockEntityTypeWrapper type = RewritableBlockEntityTypeWrapper.of();
        BlockEntityBuilder builder = this;

        SupplierResult<BlockEntityType<BlockEntity>> result = registry.registerBlockEntityType(id, BlockEntityTypeBuilder.create(
                e -> new BuiltBlockEntity(type, builder, e),
                Arrays.stream(blocks).map(BlockWrapper::get).toArray(Block[]::new)
        ));

        type.set(result.get());

        return type;
    }

    public BlockEntityTypeWrapper build(CompatRegistryV2 registry, BlockWrapper... blocks) {
        if (id == null)
            throw new IllegalStateException("BlockEntity id is not set. hint: use build(CompatRegistryV2, CompatIdentifier, BlockWrapper...)");

        return build(registry, id, blocks);
    }

    public BlockEntityTypeWrapper build(MidohraRegistryV2 registry, CompatIdentifier id, BlockWrapper... blocks) {
        return build(registry.getCompatRegistry(), id, blocks);
    }

    public BlockEntityTypeWrapper build(MidohraRegistryV2 registry, BlockWrapper... blocks) {
        return build(registry.getCompatRegistry(), blocks);
    }

    public BlockEntityBuilder onInit(BiConsumer<BlockEntityWrapper, BlockEntityBuilder> onInit) {
        this.onInit = onInit;
        return this;
    }

    public BlockEntityBuilder onWriteNbt(BiConsumer<BlockEntityWrapper, WriteNbtArgs> onWriteNbt) {
        this.onWriteNbt = onWriteNbt;
        return this;
    }

    public BlockEntityBuilder onReadNbt(BiConsumer<BlockEntityWrapper, ReadNbtArgs> onReadNbt) {
        this.onReadNbt = onReadNbt;
        return this;
    }

    public BlockEntityBuilder write(BlockEntityBuilder copy) {
        copy.onInit = this.onInit;
        copy.onWriteNbt = this.onWriteNbt;
        copy.onReadNbt = this.onReadNbt;

        return copy;
    }

    public BlockEntityBuilder copy(CompatIdentifier id) {
        return write(new BlockEntityBuilder(id));
    }

    public BlockEntityBuilder copy() {
        return copy(this.id);
    }
}

package net.pitan76.mcpitanlib.midohra.easybuilder;

import net.minecraft.block.entity.BlockEntityType;
import net.pitan76.mcpitanlib.api.block.ExtendBlock;
import net.pitan76.mcpitanlib.api.block.v2.BlockSettingsBuilder;
import net.pitan76.mcpitanlib.api.registry.v2.CompatRegistryV2;
import net.pitan76.mcpitanlib.api.util.BlockEntityTypeUtil;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;
import net.pitan76.mcpitanlib.midohra.block.SupplierBlockWrapper;

import java.util.function.Supplier;

public class BlockWithBlockEntityBuilder extends BlockBuilder {

    public BlockEntityType<?> blockEntityType;

    public BlockWithBlockEntityBuilder(BlockSettingsBuilder settingsBuilder) {
        super(settingsBuilder);
    }

    public BlockWithBlockEntityBuilder(CompatIdentifier id) {
        super(id);
    }

    public BlockWithBlockEntityBuilder(BlockSettingsBuilder settingsBuilder, BlockEntityType<?> blockEntityType) {
        super(settingsBuilder);
        this.blockEntityType = blockEntityType;
    }

    public BlockWithBlockEntityBuilder(CompatIdentifier id, BlockEntityType<?> blockEntityType) {
        super(id);
        this.blockEntityType = blockEntityType;
    }

    @Override
    public SupplierBlockWrapper build(CompatRegistryV2 registry) {
        if (settingsBuilder.id == null)
            throw new IllegalStateException("Block id is not set. hint: use build(CompatRegistryV2, CompatIdentifier)");

        Supplier<ExtendBlock> result = registry.registerExtendBlock(settingsBuilder.id, () -> new BuiltBlockWithEntity(this));

        return SupplierBlockWrapper.of(result::get);
    }

    @Override
    public SupplierBlockWrapper build(CompatRegistryV2 registry, CompatIdentifier id) {
        Supplier<ExtendBlock> result = registry.registerExtendBlock(id, () -> new BuiltBlockWithEntity(this, id));

        return SupplierBlockWrapper.of(result::get);
    }

    public static BlockWithBlockEntityBuilder of(CompatIdentifier id) {
        return new BlockWithBlockEntityBuilder(id);
    }

    public static BlockWithBlockEntityBuilder of(BlockSettingsBuilder settingsBuilder) {
        return new BlockWithBlockEntityBuilder(settingsBuilder);
    }

    public static BlockWithBlockEntityBuilder of(CompatIdentifier id, BlockEntityType<?> blockEntityType) {
        return new BlockWithBlockEntityBuilder(id, blockEntityType);
    }

    public static BlockWithBlockEntityBuilder of(BlockSettingsBuilder settingsBuilder, BlockEntityType<?> blockEntityType) {
        return new BlockWithBlockEntityBuilder(settingsBuilder, blockEntityType);
    }

    public BlockBuilder applyBlockEntity(BlockEntityType<?> blockEntityType) {
        this.blockEntityType = blockEntityType;
        return this;
    }

    public BlockBuilder applyBlockEntity(CompatIdentifier id) {
        return applyBlockEntity(BlockEntityTypeUtil.fromId(id));
    }
}

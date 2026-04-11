package net.pitan76.mcpitanlib.midohra.easybuilder.built;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.pitan76.mcpitanlib.api.block.ExtendBlockEntityProvider;
import net.pitan76.mcpitanlib.api.block.v2.CompatibleBlockSettings;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;
import net.pitan76.mcpitanlib.midohra.easybuilder.BlockWithBlockEntityBuilder;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class BuiltBlockWithEntity extends BuiltBlock implements ExtendBlockEntityProvider {

    public Supplier<BlockEntityType<? extends BlockEntity>> blockEntityType;

    public BuiltBlockWithEntity(CompatibleBlockSettings settings) {
        super(settings);
    }

    public BuiltBlockWithEntity(BlockWithBlockEntityBuilder builder) {
        super(builder);

        this.blockEntityType = builder.blockEntityType;
    }

    public BuiltBlockWithEntity(BlockWithBlockEntityBuilder builder, CompatIdentifier id) {
        super(builder, id);

        this.blockEntityType = builder.blockEntityType;
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityType<T> getBlockEntityType() {
        if (blockEntityType == null)
            return null;

        return (BlockEntityType<T>) blockEntityType.get();
    }
}

package net.pitan76.mcpitanlib.api.block;

import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.core.BlockPos;
import net.pitan76.mcpitanlib.api.block.v2.CompatBlockProvider;
import net.pitan76.mcpitanlib.api.block.v2.CompatibleBlockSettings;
import net.pitan76.mcpitanlib.api.event.block.TileCreateEvent;
import net.pitan76.mcpitanlib.api.sound.CompatSoundEvents;

import java.util.function.Supplier;

public class CompatChestBlock extends ChestBlock implements CompatBlockProvider {
    public CompatChestBlock(Properties settings, Supplier<BlockEntityType<? extends ChestBlockEntity>> supplier) {
        super(supplier, CompatSoundEvents.BLOCK_CHEST_OPEN.get(), CompatSoundEvents.BLOCK_CHEST_CLOSE.get(), settings);
    }

    public CompatibleBlockSettings compatSettings;

    public CompatChestBlock(CompatibleBlockSettings settings, Supplier<BlockEntityType<? extends ChestBlockEntity>> supplier) {
        this(settings.build(), supplier);
    }

    /**
     * @deprecated Use {@link #createBlockEntity(TileCreateEvent)} instead.
     */
    @Deprecated
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return createBlockEntity(new TileCreateEvent(pos, state));
    }

    /**
     * @see ExtendBlockEntityProvider#createBlockEntity(TileCreateEvent)
     */
    public BlockEntity createBlockEntity(TileCreateEvent event) {
        return super.newBlockEntity(event.getBlockPos(), event.getBlockState());
    }

    @Override
    public CompatibleBlockSettings getCompatSettings() {
        return compatSettings;
    }
}

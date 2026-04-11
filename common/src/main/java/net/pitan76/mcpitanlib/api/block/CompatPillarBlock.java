package net.pitan76.mcpitanlib.api.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.core.Direction;
import net.pitan76.mcpitanlib.api.block.v2.CompatBlockProvider;
import net.pitan76.mcpitanlib.api.block.v2.CompatibleBlockSettings;
import net.pitan76.mcpitanlib.api.event.block.AppendPropertiesArgs;
import net.pitan76.mcpitanlib.api.event.block.PlacementStateArgs;
import net.pitan76.mcpitanlib.api.state.property.CompatProperties;
import net.pitan76.mcpitanlib.api.state.property.EnumProperty;
import net.pitan76.mcpitanlib.core.serialization.CompatMapCodec;

public class CompatPillarBlock extends RotatedPillarBlock implements CompatBlockProvider {

    public static final EnumProperty<Direction.Axis> AXIS = CompatProperties.of(RotatedPillarBlock.AXIS);

    public CompatibleBlockSettings settings;

    @Override
    public CompatibleBlockSettings getCompatSettings() {
        return settings;
    }

    public CompatPillarBlock(Properties settings) {
        super(settings);
    }

    public CompatPillarBlock(CompatibleBlockSettings settings) {
        this(settings.build());
        this.settings = settings;
    }

    public void appendProperties(AppendPropertiesArgs args) {
        super.createBlockStateDefinition(args.builder);
    }

    public BlockState getPlacementState(PlacementStateArgs args) {
        return super.getStateForPlacement(args.ctx);
    }

    @Deprecated
    @Override
    public void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        appendProperties(new AppendPropertiesArgs(builder));
    }

    @Deprecated
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        return getPlacementState(new PlacementStateArgs(ctx));
    }

    // ExtendBlockProvider
    @Deprecated
    @Override
    public void appendProperties(AppendPropertiesArgs args, Options options) {
        CompatBlockProvider.super.appendProperties(args, options);
    }

    @Deprecated
    @Override
    public BlockState getPlacementState(PlacementStateArgs args, Options options) {
        return CompatBlockProvider.super.getPlacementState(args, options);
    }

    @Deprecated
    @Override
    public MapCodec<? extends RotatedPillarBlock> codec() {
        return getCompatCodec().getCodec();
    }

    public CompatMapCodec<? extends RotatedPillarBlock> getCompatCodec() {
        return CompatMapCodec.of(super.codec());
    }
}

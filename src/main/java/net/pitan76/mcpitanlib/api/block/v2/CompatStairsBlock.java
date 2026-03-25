package net.pitan76.mcpitanlib.api.block.v2;

import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.properties.Half;
import net.minecraft.world.level.block.state.properties.StairsShape;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.level.BlockGetter;
import net.pitan76.mcpitanlib.api.block.args.v2.CollisionShapeEvent;
import net.pitan76.mcpitanlib.api.block.args.v2.OutlineShapeEvent;
import net.pitan76.mcpitanlib.api.state.property.BooleanProperty;
import net.pitan76.mcpitanlib.api.state.property.CompatProperties;
import net.pitan76.mcpitanlib.api.state.property.DirectionProperty;
import net.pitan76.mcpitanlib.api.state.property.EnumProperty;
import net.pitan76.mcpitanlib.midohra.block.BlockState;

public class CompatStairsBlock extends net.pitan76.mcpitanlib.api.block.CompatStairsBlock {

    public static final DirectionProperty FACING = CompatProperties.ofDir(StairBlock.FACING);
    public static final EnumProperty<Half> HALF = CompatProperties.of(StairBlock.HALF);
    public static final EnumProperty<StairsShape> SHAPE = CompatProperties.of(StairBlock.SHAPE);
    public static final BooleanProperty WATERLOGGED = CompatProperties.of(StairBlock.WATERLOGGED);

    public CompatStairsBlock(net.minecraft.world.level.block.state.BlockState baseBlockState, CompatibleBlockSettings settings) {
        super(baseBlockState, settings);
    }

    public CompatStairsBlock(BlockState baseBlockState, CompatibleBlockSettings settings) {
        this(baseBlockState.toMinecraft(), settings);
    }

    public VoxelShape getOutlineShape(OutlineShapeEvent e) {
        return super.getShape(e.state.toMinecraft(), e.world.getRaw(), e.pos.toMinecraft(), e.context);
    }

    public VoxelShape getCollisionShape(CollisionShapeEvent e) {
        return super.getCollisionShape(e.state.toMinecraft(), e.world.getRaw(), e.pos.toMinecraft(), e.context);
    }

    @Deprecated
    @Override
    public VoxelShape getOutlineShape(net.pitan76.mcpitanlib.api.event.block.OutlineShapeEvent e) {
        return getOutlineShape(new OutlineShapeEvent(e.state, e.world, e.pos, e.context));
    }

    @Deprecated
    @Override
    public VoxelShape getShape(net.minecraft.world.level.block.state.BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return getOutlineShape(new OutlineShapeEvent(state, world, pos, context));
    }

    @Deprecated
    @Override
    public VoxelShape getOutlineShape(OutlineShapeEvent e, Options options) {
        return super.getOutlineShape(e, options);
    }

    @Override
    public VoxelShape getCollisionShape(net.minecraft.world.level.block.state.BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return getCollisionShape(new CollisionShapeEvent(state, world, pos, context));
    }

    @Deprecated
    @Override
    public VoxelShape getCollisionShape(CollisionShapeEvent event, Options options) {
        return super.getCollisionShape(event, options);
    }
}

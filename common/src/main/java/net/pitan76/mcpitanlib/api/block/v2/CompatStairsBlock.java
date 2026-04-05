package net.pitan76.mcpitanlib.api.block.v2;

import net.minecraft.block.ShapeContext;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.enums.BlockHalf;
import net.minecraft.block.enums.StairShape;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.pitan76.mcpitanlib.api.block.args.v2.CollisionShapeEvent;
import net.pitan76.mcpitanlib.api.block.args.v2.OutlineShapeEvent;
import net.pitan76.mcpitanlib.api.state.property.*;
import net.pitan76.mcpitanlib.midohra.block.BlockState;

public class CompatStairsBlock extends net.pitan76.mcpitanlib.api.block.CompatStairsBlock {

    public static final DirectionProperty FACING = CompatProperties.ofDir(StairsBlock.FACING);
    public static final EnumProperty<BlockHalf> HALF = CompatProperties.of(StairsBlock.HALF);
    public static final EnumProperty<StairShape> SHAPE = CompatProperties.of(StairsBlock.SHAPE);
    public static final BooleanProperty WATERLOGGED = CompatProperties.of(StairsBlock.WATERLOGGED);

    public static final BlockHalfProperty COMPAT_HALF = BlockHalfProperty.ofRaw(StairsBlock.HALF);
    public static final StairShapeProperty COMPAT_SHAPE = StairShapeProperty.ofRaw(StairsBlock.SHAPE);

    public CompatStairsBlock(net.minecraft.block.BlockState baseBlockState, CompatibleBlockSettings settings) {
        super(baseBlockState, settings);
    }

    public CompatStairsBlock(BlockState baseBlockState, CompatibleBlockSettings settings) {
        this(baseBlockState.toMinecraft(), settings);
    }

    public VoxelShape getOutlineShape(OutlineShapeEvent e) {
        return super.getOutlineShape(e.state.toMinecraft(), e.world.getRaw(), e.pos.toMinecraft(), e.context);
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
    public VoxelShape getOutlineShape(net.minecraft.block.BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return getOutlineShape(new OutlineShapeEvent(state, world, pos, context));
    }

    @Deprecated
    @Override
    public VoxelShape getOutlineShape(OutlineShapeEvent e, Options options) {
        return super.getOutlineShape(e, options);
    }

    @Override
    public VoxelShape getCollisionShape(net.minecraft.block.BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return getCollisionShape(new CollisionShapeEvent(state, world, pos, context));
    }

    @Deprecated
    @Override
    public VoxelShape getCollisionShape(CollisionShapeEvent event, Options options) {
        return super.getCollisionShape(event, options);
    }
}

package net.pitan76.mcpitanlib.api.state.property;

import net.minecraft.block.enums.BlockHalf;
import net.minecraft.block.enums.StairShape;
import net.minecraft.state.property.Properties;

public class CompatProperties {
    public static final DirectionProperty FACING = new DirectionProperty(Properties.FACING);
    public static final DirectionProperty HORIZONTAL_FACING = new DirectionProperty(Properties.HORIZONTAL_FACING);
    public static final DirectionProperty HOPPER_FACING = new DirectionProperty(Properties.HOPPER_FACING);

    public static final BooleanProperty POWERED = new BooleanProperty(Properties.POWERED);
    public static final BooleanProperty ENABLED = new BooleanProperty(Properties.ENABLED);
    public static final BooleanProperty WATERLOGGED = new BooleanProperty(Properties.WATERLOGGED);
    public static final BooleanProperty LIT = new BooleanProperty(Properties.LIT);
    public static final BooleanProperty OCCUPIED = new BooleanProperty(Properties.OCCUPIED);
    public static final BooleanProperty ATTACHED = new BooleanProperty(Properties.ATTACHED);
    public static final BooleanProperty HANGING = new BooleanProperty(Properties.HANGING);
    public static final BooleanProperty BOTTOM = new BooleanProperty(Properties.BOTTOM);
    public static final BooleanProperty OPEN = new BooleanProperty(Properties.OPEN);
    public static final BooleanProperty UNSTABLE = new BooleanProperty(Properties.UNSTABLE);

    public static final IntProperty POWER = new IntProperty(Properties.POWER);
    public static final IntProperty LAYERS = new IntProperty(Properties.LAYERS);
    public static final IntProperty NOTE = new IntProperty(Properties.NOTE);

    public static final EnumProperty<BlockHalf> BLOCK_HALF = new EnumProperty<>(Properties.BLOCK_HALF);
    public static final EnumProperty<StairShape> STAIR_SHAPE = new EnumProperty<>(Properties.STAIR_SHAPE);
}

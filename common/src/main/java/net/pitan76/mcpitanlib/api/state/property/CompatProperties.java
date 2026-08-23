package net.pitan76.mcpitanlib.api.state.property;

import net.minecraft.world.level.block.state.properties.BedPart;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.ChestType;
import net.minecraft.world.level.block.state.properties.ComparatorMode;
import net.minecraft.world.level.block.state.properties.DoorHingeSide;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.Half;
import net.minecraft.world.level.block.state.properties.PistonType;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.util.StringRepresentable;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.level.block.state.properties.StairsShape;

public class CompatProperties {
    public static final DirectionProperty FACING = new DirectionProperty(BlockStateProperties.FACING);
    public static final DirectionProperty HORIZONTAL_FACING = new DirectionProperty(BlockStateProperties.HORIZONTAL_FACING);
    public static final DirectionProperty HOPPER_FACING = new DirectionProperty(BlockStateProperties.FACING_HOPPER);
    public static final DirectionProperty VERTICAL_DIRECTION = new DirectionProperty(BlockStateProperties.VERTICAL_DIRECTION);

    public static final BooleanProperty POWERED = new BooleanProperty(BlockStateProperties.POWERED);
    public static final BooleanProperty ENABLED = new BooleanProperty(BlockStateProperties.ENABLED);
    public static final BooleanProperty WATERLOGGED = new BooleanProperty(BlockStateProperties.WATERLOGGED);
    public static final BooleanProperty LIT = new BooleanProperty(BlockStateProperties.LIT);
    public static final BooleanProperty OCCUPIED = new BooleanProperty(BlockStateProperties.OCCUPIED);
    public static final BooleanProperty ATTACHED = new BooleanProperty(BlockStateProperties.ATTACHED);
    public static final BooleanProperty HANGING = new BooleanProperty(BlockStateProperties.HANGING);
    public static final BooleanProperty BOTTOM = new BooleanProperty(BlockStateProperties.BOTTOM);
    public static final BooleanProperty OPEN = new BooleanProperty(BlockStateProperties.OPEN);
    public static final BooleanProperty UNSTABLE = new BooleanProperty(BlockStateProperties.UNSTABLE);
    public static final BooleanProperty UP = new BooleanProperty(BlockStateProperties.UP);
    public static final BooleanProperty DOWN = new BooleanProperty(BlockStateProperties.DOWN);
    public static final BooleanProperty NORTH = new BooleanProperty(BlockStateProperties.NORTH);
    public static final BooleanProperty EAST = new BooleanProperty(BlockStateProperties.EAST);
    public static final BooleanProperty SOUTH = new BooleanProperty(BlockStateProperties.SOUTH);
    public static final BooleanProperty WEST = new BooleanProperty(BlockStateProperties.WEST);

    public static final IntProperty POWER = new IntProperty(BlockStateProperties.POWER);
    public static final IntProperty LAYERS = new IntProperty(BlockStateProperties.LAYERS);
    public static final IntProperty NOTE = new IntProperty(BlockStateProperties.NOTE);
    public static final IntProperty ROTATION = new IntProperty(BlockStateProperties.ROTATION_16);
    public static final IntProperty EGGS = new IntProperty(BlockStateProperties.EGGS);
    public static final IntProperty DELAY = new IntProperty(BlockStateProperties.DELAY);

    public static final BlockHalfProperty BLOCK_HALF = new BlockHalfProperty(BlockStateProperties.HALF);
    public static final StairShapeProperty STAIR_SHAPE = new StairShapeProperty(BlockStateProperties.STAIRS_SHAPE);
    public static final SlabTypeProperty SLAB_TYPE = new SlabTypeProperty(BlockStateProperties.SLAB_TYPE);
    public static final ChestTypeProperty CHEST_TYPE = new ChestTypeProperty(BlockStateProperties.CHEST_TYPE);
    public static final PistonTypeProperty PISTON_TYPE = new PistonTypeProperty(BlockStateProperties.PISTON_TYPE);
    public static final BedPartProperty BED_PART = new BedPartProperty(BlockStateProperties.BED_PART);
    public static final DoorHingeProperty DOOR_HINGE = new DoorHingeProperty(BlockStateProperties.DOOR_HINGE);
    public static final DoubleBlockHalfProperty DOUBLE_BLOCK_HALF = new DoubleBlockHalfProperty(BlockStateProperties.DOUBLE_BLOCK_HALF);
    public static final AxisProperty AXIS = new AxisProperty(BlockStateProperties.AXIS);
    public static final AxisProperty HORIZONTAL_AXIS = new AxisProperty(BlockStateProperties.HORIZONTAL_AXIS);
    public static final ComparatorModeProperty COMPARATOR_MODE = new ComparatorModeProperty(BlockStateProperties.MODE_COMPARATOR);

    public static IProperty<?> of(Property<?> property) {
        if (property instanceof net.minecraft.world.level.block.state.properties.IntegerProperty) {
            return of((net.minecraft.world.level.block.state.properties.IntegerProperty) property);
        }
        if (property instanceof net.minecraft.world.level.block.state.properties.BooleanProperty) {
            return of((net.minecraft.world.level.block.state.properties.BooleanProperty) property);
        }
        if (property instanceof net.minecraft.world.level.block.state.properties.EnumProperty) {
            if (property.getValueClass() == Direction.class) {
                return ofDir((net.minecraft.world.level.block.state.properties.EnumProperty<Direction>) property);
            }
            if (property.getValueClass() == Direction.Axis.class) {
                return ofAxis((net.minecraft.world.level.block.state.properties.EnumProperty<Direction.Axis>) property);
            }
            return of((net.minecraft.world.level.block.state.properties.EnumProperty<?>) property);
        }
        return UnknownProperty.of(property);
    }

    public static IntProperty of(net.minecraft.world.level.block.state.properties.IntegerProperty property) {
        if (property == BlockStateProperties.POWER) return POWER;
        if (property == BlockStateProperties.LAYERS) return LAYERS;
        if (property == BlockStateProperties.NOTE) return NOTE;
        if (property == BlockStateProperties.ROTATION_16) return ROTATION;
        if (property == BlockStateProperties.EGGS) return EGGS;
        if (property == BlockStateProperties.DELAY) return DELAY;

        return new IntProperty(property);
    }

    public static BooleanProperty of(net.minecraft.world.level.block.state.properties.BooleanProperty property) {
        if (property == BlockStateProperties.POWERED) return POWERED;
        if (property == BlockStateProperties.ENABLED) return ENABLED;
        if (property == BlockStateProperties.WATERLOGGED) return WATERLOGGED;
        if (property == BlockStateProperties.LIT) return LIT;
        if (property == BlockStateProperties.OCCUPIED) return OCCUPIED;
        if (property == BlockStateProperties.ATTACHED) return ATTACHED;
        if (property == BlockStateProperties.HANGING) return HANGING;
        if (property == BlockStateProperties.BOTTOM) return BOTTOM;
        if (property == BlockStateProperties.OPEN) return OPEN;
        if (property == BlockStateProperties.UNSTABLE) return UNSTABLE;
        if (property == BlockStateProperties.UP) return UP;
        if (property == BlockStateProperties.DOWN) return DOWN;
        if (property == BlockStateProperties.NORTH) return NORTH;
        if (property == BlockStateProperties.EAST) return EAST;
        if (property == BlockStateProperties.SOUTH) return SOUTH;
        if (property == BlockStateProperties.WEST) return WEST;

        return new BooleanProperty(property);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public static <T extends Enum<T> & StringRepresentable> EnumProperty<T> of(net.minecraft.world.level.block.state.properties.EnumProperty<T> property) {
        if (property.equals(BlockStateProperties.HALF)) return (EnumProperty) BLOCK_HALF;
        if (property.equals(BlockStateProperties.STAIRS_SHAPE)) return (EnumProperty) STAIR_SHAPE;
        if (property.equals(BlockStateProperties.SLAB_TYPE)) return (EnumProperty) SLAB_TYPE;
        if (property.equals(BlockStateProperties.CHEST_TYPE)) return (EnumProperty) CHEST_TYPE;
        if (property.equals(BlockStateProperties.PISTON_TYPE)) return (EnumProperty) PISTON_TYPE;
        if (property.equals(BlockStateProperties.BED_PART)) return (EnumProperty) BED_PART;
        if (property.equals(BlockStateProperties.DOOR_HINGE)) return (EnumProperty) DOOR_HINGE;
        if (property.equals(BlockStateProperties.DOUBLE_BLOCK_HALF)) return (EnumProperty) DOUBLE_BLOCK_HALF;
        if (property.equals(BlockStateProperties.AXIS)) return (EnumProperty) EnumProperty.of(AXIS.getProperty());
        if (property.equals(BlockStateProperties.HORIZONTAL_AXIS)) return (EnumProperty) EnumProperty.of(HORIZONTAL_AXIS.getProperty());
        if (property.equals(BlockStateProperties.MODE_COMPARATOR)) return (EnumProperty) COMPARATOR_MODE;

        return new EnumProperty<>(property);
    }

    public static DirectionProperty ofDir(net.minecraft.world.level.block.state.properties.EnumProperty<Direction> property) {
        if (property == BlockStateProperties.FACING) return FACING;
        if (property == BlockStateProperties.HORIZONTAL_FACING) return HORIZONTAL_FACING;
        if (property == BlockStateProperties.FACING_HOPPER) return HOPPER_FACING;
        if (property == BlockStateProperties.VERTICAL_DIRECTION) return VERTICAL_DIRECTION;

        return new DirectionProperty(property);
    }

    public static AxisProperty ofAxis(net.minecraft.world.level.block.state.properties.EnumProperty<Direction.Axis> property) {
        if (property == BlockStateProperties.AXIS) return AXIS;
        if (property == BlockStateProperties.HORIZONTAL_AXIS) return HORIZONTAL_AXIS;

        return new AxisProperty(property);
    }

    public static BlockHalfProperty ofBlockHalf(net.minecraft.world.level.block.state.properties.EnumProperty<Half> property) {
        if (property == BlockStateProperties.HALF) return BLOCK_HALF;
        return new BlockHalfProperty(property);
    }

    public static StairShapeProperty ofStairShape(net.minecraft.world.level.block.state.properties.EnumProperty<StairsShape> property) {
        if (property == BlockStateProperties.STAIRS_SHAPE) return STAIR_SHAPE;
        return new StairShapeProperty(property);
    }

    public static SlabTypeProperty ofSlabType(net.minecraft.world.level.block.state.properties.EnumProperty<SlabType> property) {
        if (property == BlockStateProperties.SLAB_TYPE) return SLAB_TYPE;
        return new SlabTypeProperty(property);
    }

    public static ChestTypeProperty ofChestType(net.minecraft.world.level.block.state.properties.EnumProperty<ChestType> property) {
        if (property == BlockStateProperties.CHEST_TYPE) return CHEST_TYPE;
        return new ChestTypeProperty(property);
    }

    public static PistonTypeProperty ofPistonType(net.minecraft.world.level.block.state.properties.EnumProperty<PistonType> property) {
        if (property == BlockStateProperties.PISTON_TYPE) return PISTON_TYPE;
        return new PistonTypeProperty(property);
    }

    public static BedPartProperty ofBedPart(net.minecraft.world.level.block.state.properties.EnumProperty<BedPart> property) {
        if (property == BlockStateProperties.BED_PART) return BED_PART;
        return new BedPartProperty(property);
    }

    public static DoorHingeProperty ofDoorHinge(net.minecraft.world.level.block.state.properties.EnumProperty<DoorHingeSide> property) {
        if (property == BlockStateProperties.DOOR_HINGE) return DOOR_HINGE;
        return new DoorHingeProperty(property);
    }

    public static ComparatorModeProperty ofComparatorMode(net.minecraft.world.level.block.state.properties.EnumProperty<ComparatorMode> property) {
        if (property == BlockStateProperties.MODE_COMPARATOR) return COMPARATOR_MODE;
        return new ComparatorModeProperty(property);
    }

    public static DoubleBlockHalfProperty ofDoubleBlockHalf(net.minecraft.world.level.block.state.properties.EnumProperty<DoubleBlockHalf> property) {
        if (property == BlockStateProperties.DOUBLE_BLOCK_HALF) return DOUBLE_BLOCK_HALF;
        return new DoubleBlockHalfProperty(property);
    }
}

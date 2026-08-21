package net.pitan76.mcpitanlib.api.state.property;

import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.ChestType;
import net.minecraft.world.level.block.state.properties.ComparatorMode;
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

    public static final EnumProperty<Half> BLOCK_HALF = new EnumProperty<>(BlockStateProperties.HALF);
    public static final EnumProperty<StairsShape> STAIR_SHAPE = new EnumProperty<>(BlockStateProperties.STAIRS_SHAPE);
    public static final EnumProperty<SlabType> SLAB_TYPE = new EnumProperty<>(BlockStateProperties.SLAB_TYPE);
    public static final EnumProperty<ChestType> CHEST_TYPE = new EnumProperty<>(BlockStateProperties.CHEST_TYPE);
    public static final EnumProperty<PistonType> PISTON_TYPE = new EnumProperty<>(BlockStateProperties.PISTON_TYPE);
    public static final AxisProperty AXIS = new AxisProperty(BlockStateProperties.AXIS);
    public static final AxisProperty HORIZONTAL_AXIS = new AxisProperty(BlockStateProperties.HORIZONTAL_AXIS);
    public static final EnumProperty<ComparatorMode> COMPARATOR_MODE = new EnumProperty<>(BlockStateProperties.MODE_COMPARATOR);

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

    public static <T extends Enum<T> & StringRepresentable> EnumProperty<T> of(net.minecraft.world.level.block.state.properties.EnumProperty<T> property) {
        if (property.equals(BlockStateProperties.HALF)) return (EnumProperty) BLOCK_HALF;
        if (property.equals(BlockStateProperties.STAIRS_SHAPE)) return (EnumProperty) STAIR_SHAPE;
        if (property.equals(BlockStateProperties.SLAB_TYPE)) return (EnumProperty) SLAB_TYPE;
        if (property.equals(BlockStateProperties.CHEST_TYPE)) return (EnumProperty) CHEST_TYPE;
        if (property.equals(BlockStateProperties.PISTON_TYPE)) return (EnumProperty) PISTON_TYPE;
        if (property.equals(BlockStateProperties.AXIS)) return (EnumProperty<T>) EnumProperty.of(AXIS.getProperty());
        if (property.equals(BlockStateProperties.HORIZONTAL_AXIS)) return (EnumProperty<T>) EnumProperty.of(HORIZONTAL_AXIS.getProperty());
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
}

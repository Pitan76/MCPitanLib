package net.pitan76.mcpitanlib.api.state.property;

import net.minecraft.block.enums.*;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.math.Direction;

public class CompatProperties {
    public static final DirectionProperty FACING = new DirectionProperty(Properties.FACING);
    public static final DirectionProperty HORIZONTAL_FACING = new DirectionProperty(Properties.HORIZONTAL_FACING);
    public static final DirectionProperty HOPPER_FACING = new DirectionProperty(Properties.HOPPER_FACING);
    public static final DirectionProperty VERTICAL_DIRECTION = new DirectionProperty(Properties.VERTICAL_DIRECTION);

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
    public static final BooleanProperty UP = new BooleanProperty(Properties.UP);
    public static final BooleanProperty DOWN = new BooleanProperty(Properties.DOWN);
    public static final BooleanProperty NORTH = new BooleanProperty(Properties.NORTH);
    public static final BooleanProperty EAST = new BooleanProperty(Properties.EAST);
    public static final BooleanProperty SOUTH = new BooleanProperty(Properties.SOUTH);
    public static final BooleanProperty WEST = new BooleanProperty(Properties.WEST);

    public static final IntProperty POWER = new IntProperty(Properties.POWER);
    public static final IntProperty LAYERS = new IntProperty(Properties.LAYERS);
    public static final IntProperty NOTE = new IntProperty(Properties.NOTE);
    public static final IntProperty ROTATION = new IntProperty(Properties.ROTATION);
    public static final IntProperty EGGS = new IntProperty(Properties.EGGS);
    public static final IntProperty DELAY = new IntProperty(Properties.DELAY);

    public static final EnumProperty<BlockHalf> BLOCK_HALF = new EnumProperty<>(Properties.BLOCK_HALF);
    public static final EnumProperty<StairShape> STAIR_SHAPE = new EnumProperty<>(Properties.STAIR_SHAPE);
    public static final EnumProperty<SlabType> SLAB_TYPE = new EnumProperty<>(Properties.SLAB_TYPE);
    public static final EnumProperty<ChestType> CHEST_TYPE = new EnumProperty<>(Properties.CHEST_TYPE);
    public static final EnumProperty<PistonType> PISTON_TYPE = new EnumProperty<>(Properties.PISTON_TYPE);
    public static final EnumProperty<Direction.Axis> AXIS = new EnumProperty<>(Properties.AXIS);
    public static final EnumProperty<Direction.Axis> HORIZONTAL_AXIS = new EnumProperty<>(Properties.HORIZONTAL_AXIS);
    public static final ComparatorModeProperty COMPARATOR_MODE = new ComparatorModeProperty(Properties.COMPARATOR_MODE);

    public static IProperty<?> of(Property<?> property) {
        if (property instanceof net.minecraft.state.property.IntProperty) {
            return of((net.minecraft.state.property.IntProperty) property);
        }
        if (property instanceof net.minecraft.state.property.BooleanProperty) {
            return of((net.minecraft.state.property.BooleanProperty) property);
        }
        if (property instanceof net.minecraft.state.property.EnumProperty) {
            return of((net.minecraft.state.property.EnumProperty<?>) property);
        }
        if (property instanceof net.minecraft.state.property.DirectionProperty) {
            return ofDir((net.minecraft.state.property.DirectionProperty) property);
        }
        return UnknownProperty.of(property);
    }

    public static IntProperty of(net.minecraft.state.property.IntProperty property) {
        if (property == Properties.POWER) return POWER;
        if (property == Properties.LAYERS) return LAYERS;
        if (property == Properties.NOTE) return NOTE;
        if (property == Properties.ROTATION) return ROTATION;
        if (property == Properties.EGGS) return EGGS;
        if (property == Properties.DELAY) return DELAY;

        return new IntProperty(property);
    }

    public static BooleanProperty of(net.minecraft.state.property.BooleanProperty property) {
        if (property == Properties.POWERED) return POWERED;
        if (property == Properties.ENABLED) return ENABLED;
        if (property == Properties.WATERLOGGED) return WATERLOGGED;
        if (property == Properties.LIT) return LIT;
        if (property == Properties.OCCUPIED) return OCCUPIED;
        if (property == Properties.ATTACHED) return ATTACHED;
        if (property == Properties.HANGING) return HANGING;
        if (property == Properties.BOTTOM) return BOTTOM;
        if (property == Properties.OPEN) return OPEN;
        if (property == Properties.UNSTABLE) return UNSTABLE;
        if (property == Properties.UP) return UP;
        if (property == Properties.DOWN) return DOWN;
        if (property == Properties.NORTH) return NORTH;
        if (property == Properties.EAST) return EAST;
        if (property == Properties.SOUTH) return SOUTH;
        if (property == Properties.WEST) return WEST;

        return new BooleanProperty(property);
    }

    public static <T extends Enum<T> & StringIdentifiable> EnumProperty<T> of(net.minecraft.state.property.EnumProperty<T> property) {
        if (property.equals(Properties.BLOCK_HALF)) return (EnumProperty) BLOCK_HALF;
        if (property.equals(Properties.STAIR_SHAPE)) return (EnumProperty) STAIR_SHAPE;
        if (property.equals(Properties.SLAB_TYPE)) return (EnumProperty) SLAB_TYPE;
        if (property.equals(Properties.CHEST_TYPE)) return (EnumProperty) CHEST_TYPE;
        if (property.equals(Properties.PISTON_TYPE)) return (EnumProperty) PISTON_TYPE;
        if (property.equals(Properties.AXIS)) return (EnumProperty) AXIS;
        if (property.equals(Properties.HORIZONTAL_AXIS)) return (EnumProperty) HORIZONTAL_AXIS;
        if (property.equals(Properties.COMPARATOR_MODE)) return (EnumProperty) COMPARATOR_MODE;

        return new EnumProperty<>(property);
    }

    public static DirectionProperty ofDir(net.minecraft.state.property.DirectionProperty property) {
        if (property == Properties.FACING) return FACING;
        if (property == Properties.HORIZONTAL_FACING) return HORIZONTAL_FACING;
        if (property == Properties.HOPPER_FACING) return HOPPER_FACING;
        if (property == Properties.VERTICAL_DIRECTION) return VERTICAL_DIRECTION;

        return new DirectionProperty(property);
    }

    public static ComparatorModeProperty ofComparatorMode(net.minecraft.state.property.EnumProperty<ComparatorMode> property) {
        if (property == Properties.COMPARATOR_MODE) return COMPARATOR_MODE;
        return new ComparatorModeProperty(property);
    }
}

package net.pitan76.mcpitanlib.api.util;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.Half;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.properties.StairsShape;
import net.minecraft.state.property.*;
import net.minecraft.util.StringRepresentable;
import net.minecraft.core.Direction;
import net.pitan76.mcpitanlib.api.event.block.AppendPropertiesArgs;
import net.pitan76.mcpitanlib.api.state.property.CompatProperties;
import net.pitan76.mcpitanlib.api.state.property.DirectionProperty;

public class PropertyUtil {
    public static BooleanProperty createBooleanProperty(String name) {
        return BooleanProperty.create(name);
    }

    public static DirectionProperty createDirectionProperty(String name) {
        return DirectionProperty.of(name);
    }

    public static IntegerProperty createIntProperty(String name, int min, int max) {
        return IntegerProperty.create(name, min, max);
    }

    public static <T extends Enum<T> & StringRepresentable> EnumProperty<T> createEnumProperty(String name, Class<T> type) {
        return EnumProperty.create(name, type);
    }

    public static BlockState with(BlockState state, BooleanProperty property, boolean value) {
        return state.setValue(property, value);
    }

    public static BlockState with(BlockState state, IntegerProperty property, int value) {
        return state.setValue(property, value);
    }

    public static BlockState with(BlockState state, DirectionProperty property, Direction value) {
        return property.with(state, value);
    }

    public static boolean get(BlockState state, BooleanProperty property) {
        return state.getValue(property);
    }

    public static int get(BlockState state, IntegerProperty property) {
        return state.getValue(property);
    }

    public static Direction get(BlockState state, DirectionProperty property) {
        return property.get(state);
    }

    public static net.pitan76.mcpitanlib.midohra.util.math.Direction getAsMidohra(BlockState state, DirectionProperty property) {
        return net.pitan76.mcpitanlib.midohra.util.math.Direction.of(property.get(state));
    }

    public static Direction getFacing(BlockState state) {
        return facing().get(state);
    }

    public static Direction getHorizontalFacing(BlockState state) {
        return horizontalFacing().get(state);
    }

    public static int getPower(BlockState state) {
        return state.getValue(power());
    }

    public static boolean isPowered(BlockState state) {
        return state.getValue(powered());
    }

    public static void append(AppendPropertiesArgs args, Property<?>... properties) {
        args.addProperty(properties);
    }

    public static void appendFacing(AppendPropertiesArgs args) {
        facing().apply(args);
    }

    public static void appendHorizontalFacing(AppendPropertiesArgs args) {
        horizontalFacing().apply(args);
    }

    public static void appendPower(AppendPropertiesArgs args) {
        args.addProperty(power());
    }

    public static void appendPowered(AppendPropertiesArgs args) {
        args.addProperty(powered());
    }

    public static IntegerProperty power() {
        return BlockStateProperties.POWER;
    }

    public static BooleanProperty powered() {
        return BlockStateProperties.POWERED;
    }

    public static DirectionProperty facing() {
        return CompatProperties.FACING;
    }

    public static DirectionProperty horizontalFacing() {
        return CompatProperties.HORIZONTAL_FACING;
    }

    public static EnumProperty<Direction.Axis> axis() {
        return BlockStateProperties.AXIS;
    }

    public static EnumProperty<Half> blockHalf() {
        return BlockStateProperties.HALF;
    }

    public static EnumProperty<StairsShape> stairShape() {
        return BlockStateProperties.STAIRS_SHAPE;
    }

    public static BooleanProperty lit() {
        return BlockStateProperties.LIT;
    }

    public static BooleanProperty waterlogged() {
        return BlockStateProperties.WATERLOGGED;
    }

    public static BooleanProperty attached() {
        return BlockStateProperties.ATTACHED;
    }

    public static BooleanProperty hanging() {
        return BlockStateProperties.HANGING;
    }

    public static BooleanProperty bottom() {
        return BlockStateProperties.BOTTOM;
    }

    public static BooleanProperty conditional() {
        return BlockStateProperties.CONDITIONAL;
    }

    public static BooleanProperty inWall() {
        return BlockStateProperties.IN_WALL;
    }

    public static BooleanProperty open() {
        return BlockStateProperties.OPEN;
    }

    public static BooleanProperty occupied() {
        return BlockStateProperties.OCCUPIED;
    }

    public static boolean contains(BlockState state, Property<?> property) {
        return state.hasProperty(property);
    }
}

package net.pitan76.mcpitanlib.api.util;

import net.minecraft.block.BlockState;
import net.minecraft.block.enums.BlockHalf;
import net.minecraft.block.enums.StairShape;
import net.minecraft.state.property.*;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.math.Direction;
import net.pitan76.mcpitanlib.api.event.block.AppendPropertiesArgs;

public class PropertyUtil {
    public static BooleanProperty createBooleanProperty(String name) {
        return BooleanProperty.of(name);
    }

    public static DirectionProperty createDirectionProperty(String name) {
        return DirectionProperty.of(name);
    }

    public static IntProperty createIntProperty(String name, int min, int max) {
        return IntProperty.of(name, min, max);
    }

    public static <T extends Enum<T> & StringIdentifiable> EnumProperty<T> createEnumProperty(String name, Class<T> type) {
        return EnumProperty.of(name, type);
    }

    public static BlockState with(BlockState state, BooleanProperty property, boolean value) {
        return state.with(property, value);
    }

    public static BlockState with(BlockState state, IntProperty property, int value) {
        return state.with(property, value);
    }

    public static BlockState with(BlockState state, DirectionProperty property, Direction value) {
        return state.with(property, value);
    }

    public static boolean get(BlockState state, BooleanProperty property) {
        return state.get(property);
    }

    public static int get(BlockState state, IntProperty property) {
        return state.get(property);
    }

    public static Direction get(BlockState state, DirectionProperty property) {
        return state.get(property);
    }

    public static Direction getFacing(BlockState state) {
        return state.get(facing());
    }

    public static Direction getHorizontalFacing(BlockState state) {
        return state.get(horizontalFacing());
    }

    public static int getPower(BlockState state) {
        return state.get(power());
    }

    public static boolean isPowered(BlockState state) {
        return state.get(powered());
    }

    public static void append(AppendPropertiesArgs args, Property<?>... properties) {
        args.addProperty(properties);
    }

    public static void appendFacing(AppendPropertiesArgs args) {
        args.addProperty(facing());
    }

    public static void appendHorizontalFacing(AppendPropertiesArgs args) {
        args.addProperty(horizontalFacing());
    }

    public static void appendPower(AppendPropertiesArgs args) {
        args.addProperty(power());
    }

    public static void appendPowered(AppendPropertiesArgs args) {
        args.addProperty(powered());
    }

    public static IntProperty power() {
        return Properties.POWER;
    }

    public static BooleanProperty powered() {
        return Properties.POWERED;
    }

    public static DirectionProperty facing() {
        return Properties.FACING;
    }

    public static DirectionProperty horizontalFacing() {
        return Properties.HORIZONTAL_FACING;
    }

    public static EnumProperty<Direction.Axis> axis() {
        return Properties.AXIS;
    }

    public static EnumProperty<BlockHalf> blockHalf() {
        return Properties.BLOCK_HALF;
    }

    public static EnumProperty<StairShape> stairShape() {
        return Properties.STAIR_SHAPE;
    }

    public static BooleanProperty lit() {
        return Properties.LIT;
    }

    public static BooleanProperty waterlogged() {
        return Properties.WATERLOGGED;
    }

    public static BooleanProperty attached() {
        return Properties.ATTACHED;
    }

    public static BooleanProperty hanging() {
        return Properties.HANGING;
    }

    public static BooleanProperty bottom() {
        return Properties.BOTTOM;
    }

    public static BooleanProperty conditional() {
        return Properties.CONDITIONAL;
    }

    public static BooleanProperty inWall() {
        return Properties.IN_WALL;
    }

    public static BooleanProperty open() {
        return Properties.OPEN;
    }

    public static BooleanProperty occupied() {
        return Properties.OCCUPIED;
    }
}

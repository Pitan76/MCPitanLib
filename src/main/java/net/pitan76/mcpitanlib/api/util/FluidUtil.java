package net.pitan76.mcpitanlib.api.util;

import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.item.Item;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.LevelReader;

public class FluidUtil {
    public static Identifier toID(Fluid fluid) {
        return BuiltInRegistries.FLUID.getKey(fluid);
    }

    public static Fluid fromId(Identifier identifier) {
        return BuiltInRegistries.FLUID.getValue(identifier);
    }

    public static Fluid fromId(CompatIdentifier identifier) {
        return fromId(identifier.toMinecraft());
    }

    public static int getRawId(Fluid fluid) {
        return BuiltInRegistries.FLUID.getId(fluid);
    }

    public static Fluid fromIndex(int index) {
        return BuiltInRegistries.FLUID.byId(index);
    }

    public static boolean isExist(CompatIdentifier id) {
        return BuiltInRegistries.FLUID.containsKey(id.toMinecraft());
    }

    public static CompatIdentifier toCompatId(Fluid fluid) {
        return CompatIdentifier.fromMinecraft(toID(fluid));
    }

    public static Fluid fromCompatId(CompatIdentifier id) {
        return fromId(id.toMinecraft());
    }

    public static FlowingFluid water() {
        return Fluids.WATER;
    }

    public static FlowingFluid lava() {
        return Fluids.LAVA;
    }

    public static Fluid empty() {
        return Fluids.EMPTY;
    }

    public static FlowingFluid flowingWater() {
        return Fluids.FLOWING_WATER;
    }

    public static FlowingFluid flowingLava() {
        return Fluids.FLOWING_LAVA;
    }

    public static boolean isStill(Fluid fluid) {
        return fluid == water() || fluid == lava();
    }

    public static boolean isStill(FluidState state) {
        return state.isSource();
    }

    public static FluidState getStill(FlowingFluid fluid, boolean falling) {
        return fluid.getSource(falling);
    }

    public static FluidState getFlowing(FlowingFluid fluid, int level, boolean falling) {
        return fluid.getFlowing(level, falling);
    }

    public static FluidState getStill(FlowingFluid fluid) {
        return getStill(fluid, false);
    }

    public static FluidState getStillWater() {
        return getStill(water());
    }

    public static FluidState getStillLava() {
        return getStill(lava());
    }

    public static boolean isFlowing(Fluid fluid) {
        return fluid == flowingWater() || fluid == flowingLava();
    }

    public static int getTickRate(Fluid fluid, LevelReader world) {
        return fluid.getTickDelay(world);
    }

    public static FluidState getDefaultState(Fluid fluid) {
        return fluid.defaultFluidState();
    }


    public static Item getBucketItem(Fluid fluid) {
        return fluid.getBucket();
    }
}

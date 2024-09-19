package net.pitan76.mcpitanlib.api.util;

import net.minecraft.fluid.FlowableFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.world.WorldView;

public class FluidUtil {
    public static Identifier toID(Fluid fluid) {
        return Registries.FLUID.getId(fluid);
    }

    public static Fluid fromId(Identifier identifier) {
        return Registries.FLUID.get(identifier);
    }

    public static int getRawId(Fluid fluid) {
        return Registries.FLUID.getRawId(fluid);
    }

    public static Fluid fromIndex(int index) {
        return Registries.FLUID.get(index);
    }

    public static boolean isExist(CompatIdentifier id) {
        return Registries.FLUID.containsId(id.toMinecraft());
    }

    public static CompatIdentifier toCompatId(Fluid fluid) {
        return CompatIdentifier.fromMinecraft(toID(fluid));
    }

    public static Fluid fromCompatId(CompatIdentifier id) {
        return fromId(id.toMinecraft());
    }

    public static FlowableFluid water() {
        return Fluids.WATER;
    }

    public static FlowableFluid lava() {
        return Fluids.LAVA;
    }

    public static Fluid empty() {
        return Fluids.EMPTY;
    }

    public static FlowableFluid flowingWater() {
        return Fluids.FLOWING_WATER;
    }

    public static FlowableFluid flowingLava() {
        return Fluids.FLOWING_LAVA;
    }

    public static boolean isStill(Fluid fluid) {
        return fluid == water() || fluid == lava();
    }

    public static boolean isStill(FluidState state) {
        return state.isStill();
    }

    public static FluidState getStill(FlowableFluid fluid, boolean falling) {
        return fluid.getStill(falling);
    }

    public static FluidState getFlowing(FlowableFluid fluid, int level, boolean falling) {
        return fluid.getFlowing(level, falling);
    }

    public static FluidState getStill(FlowableFluid fluid) {
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

    public static int getTickRate(Fluid fluid, WorldView world) {
        return fluid.getTickRate(world);
    }

    public static FluidState getDefaultState(Fluid fluid) {
        return fluid.getDefaultState();
    }


}

package net.pitan76.mcpitanlib.midohra.world.tick;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;
import net.pitan76.mcpitanlib.midohra.block.BlockWrapper;
import net.pitan76.mcpitanlib.midohra.fluid.FluidWrapper;
import net.pitan76.mcpitanlib.midohra.util.math.BlockPos;

public class ScheduledTickView {
    private final net.minecraft.world.level.ScheduledTickAccess scheduledTickView;

    public ScheduledTickView(net.minecraft.world.level.ScheduledTickAccess scheduledTickView) {
        this.scheduledTickView = scheduledTickView;
    }

    public static net.pitan76.mcpitanlib.midohra.world.tick.ScheduledTickView of(net.minecraft.world.level.ScheduledTickAccess scheduledTickView) {
        return new net.pitan76.mcpitanlib.midohra.world.tick.ScheduledTickView(scheduledTickView);
    }

    public static net.pitan76.mcpitanlib.midohra.world.tick.ScheduledTickView of(net.minecraft.world.level.Level world) {
        return new net.pitan76.mcpitanlib.midohra.world.tick.ScheduledTickView(world);
    }

    public net.minecraft.world.level.ScheduledTickAccess toMinecraft() {
        return get();
    }

    protected net.minecraft.world.level.ScheduledTickAccess get() {
        return scheduledTickView;
    }

    public void scheduleBlockTick(BlockPos blockPos, BlockWrapper blockWrapper, int delay) {
        scheduleBlockTick(blockPos.toMinecraft(), blockWrapper.get(), delay);
    }

    public void scheduleBlockTick(net.minecraft.core.BlockPos blockPos, Block block, int delay) {
        get().scheduleTick(blockPos, block, delay);
    }

    public void scheduleFluidTick(BlockPos blockPos, FluidWrapper fluidWrapper, int delay) {
        scheduleFluidTick(blockPos.toMinecraft(), fluidWrapper.get(), delay);
    }

    public void scheduleFluidTick(net.minecraft.core.BlockPos blockPos, Fluid block, int delay) {
        get().scheduleTick(blockPos, block, delay);
    }
}

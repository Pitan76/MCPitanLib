package net.pitan76.mcpitanlib.midohra.world.tick;

import net.minecraft.block.Block;
import net.minecraft.fluid.Fluid;
import net.minecraft.world.WorldAccess;
import net.pitan76.mcpitanlib.midohra.block.BlockWrapper;
import net.pitan76.mcpitanlib.midohra.fluid.FluidWrapper;
import net.pitan76.mcpitanlib.midohra.util.math.BlockPos;

public class ScheduledTickView {
    private final WorldAccess scheduledTickView;

    public ScheduledTickView(WorldAccess scheduledTickView) {
        this.scheduledTickView = scheduledTickView;
    }

    public static net.pitan76.mcpitanlib.midohra.world.tick.ScheduledTickView of(net.minecraft.world.World world) {
        return of((WorldAccess) world);
    }

    public static net.pitan76.mcpitanlib.midohra.world.tick.ScheduledTickView of(WorldAccess world) {
        return new net.pitan76.mcpitanlib.midohra.world.tick.ScheduledTickView(world);
    }

    public WorldAccess toMinecraft() {
        return get();
    }

    protected WorldAccess get() {
        return scheduledTickView;
    }

    public void scheduleBlockTick(BlockPos blockPos, BlockWrapper blockWrapper, int delay) {
        scheduleBlockTick(blockPos.toMinecraft(), blockWrapper.get(), delay);
    }

    public void scheduleBlockTick(net.minecraft.util.math.BlockPos blockPos, Block block, int delay) {
        get().scheduleBlockTick(blockPos, block, delay);
    }

    public void scheduleFluidTick(BlockPos blockPos, FluidWrapper fluidWrapper, int delay) {
        scheduleFluidTick(blockPos.toMinecraft(), fluidWrapper.get(), delay);
    }

    public void scheduleFluidTick(net.minecraft.util.math.BlockPos blockPos, Fluid block, int delay) {
        get().scheduleFluidTick(blockPos, block, delay);
    }
}

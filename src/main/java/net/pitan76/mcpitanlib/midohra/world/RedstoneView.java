package net.pitan76.mcpitanlib.midohra.world;

import net.pitan76.mcpitanlib.midohra.util.math.BlockPos;
import net.pitan76.mcpitanlib.midohra.util.math.Direction;

public interface RedstoneView {

    net.minecraft.world.level.SignalGetter getRedstoneView();

    default boolean isReceivingRedstonePower(BlockPos pos) {
        return getRedstoneView().hasNeighborSignal(pos.toMinecraft());
    }

    default int getEmittedRedstonePower(BlockPos pos, Direction direction) {
        return getRedstoneView().getSignal(pos.toMinecraft(), direction.toMinecraft());
    }

    default int getEmittedRedstonePower(BlockPos pos, Direction direction, boolean onlyFromGate) {
        return getRedstoneView().getControlInputSignal(pos.toMinecraft(), direction.toMinecraft(), onlyFromGate);
    }

    default boolean isEmittingRedstonePower(BlockPos pos, Direction direction) {
        return getRedstoneView().hasSignal(pos.toMinecraft(), direction.toMinecraft());
    }

    default int getStrongRedstonePower(BlockPos pos, Direction direction) {
        return getRedstoneView().getDirectSignal(pos.toMinecraft(), direction.toMinecraft());
    }

    default int getReceivedStrongRedstonePower(BlockPos pos) {
        return getRedstoneView().getDirectSignalTo(pos.toMinecraft());
    }

    static RedstoneView of(net.minecraft.world.level.SignalGetter redstoneView) {
        return () -> redstoneView;
    }

    static RedstoneView of(net.minecraft.world.level.LevelReader world) {
        return WorldView.of(world);
    }
}

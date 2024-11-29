package net.pitan76.mcpitanlib.midohra.world;

import net.pitan76.mcpitanlib.midohra.util.math.BlockPos;
import net.pitan76.mcpitanlib.midohra.util.math.Direction;

public interface RedstoneView {

    net.minecraft.world.RedstoneView getRedstoneView();

    default boolean isReceivingRedstonePower(BlockPos pos) {
        return getRedstoneView().isReceivingRedstonePower(pos.toMinecraft());
    }

    default int getEmittedRedstonePower(BlockPos pos, Direction direction) {
        return getRedstoneView().getEmittedRedstonePower(pos.toMinecraft(), direction.toMinecraft());
    }

    default int getEmittedRedstonePower(BlockPos pos, Direction direction, boolean onlyFromGate) {
        return getRedstoneView().getEmittedRedstonePower(pos.toMinecraft(), direction.toMinecraft(), onlyFromGate);
    }

    default boolean isEmittingRedstonePower(BlockPos pos, Direction direction) {
        return getRedstoneView().isEmittingRedstonePower(pos.toMinecraft(), direction.toMinecraft());
    }

    default int getStrongRedstonePower(BlockPos pos, Direction direction) {
        return getRedstoneView().getStrongRedstonePower(pos.toMinecraft(), direction.toMinecraft());
    }

    default int getReceivedStrongRedstonePower(BlockPos pos) {
        return getRedstoneView().getReceivedStrongRedstonePower(pos.toMinecraft());
    }

    static RedstoneView of(net.minecraft.world.RedstoneView redstoneView) {
        return () -> redstoneView;
    }

    static RedstoneView of(net.minecraft.world.WorldView world) {
        return WorldView.of(world);
    }
}

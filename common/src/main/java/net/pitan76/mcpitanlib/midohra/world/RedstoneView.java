package net.pitan76.mcpitanlib.midohra.world;

import net.minecraft.world.World;
import net.pitan76.mcpitanlib.midohra.util.math.BlockPos;
import net.pitan76.mcpitanlib.midohra.util.math.Direction;

public interface RedstoneView {

    net.minecraft.world.WorldView getRedstoneView();

    default boolean isReceivingRedstonePower(BlockPos pos) {
        if (getRedstoneView() instanceof World) {
            return ((World) getRedstoneView()).isReceivingRedstonePower(pos.toMinecraft());
        }
        return false;
    }

    default int getEmittedRedstonePower(BlockPos pos, Direction direction) {
        if (getRedstoneView() instanceof World) {
            return ((World) getRedstoneView()).getEmittedRedstonePower(pos.toMinecraft(), direction.toMinecraft());
        }
        return 0;
    }

    default int getEmittedRedstonePower(BlockPos pos, Direction direction, boolean onlyFromGate) {
        if (getRedstoneView() instanceof World) {
            return ((World) getRedstoneView()).getEmittedRedstonePower(pos.toMinecraft(), direction.toMinecraft());
        }
        return 0;
    }

    default boolean isEmittingRedstonePower(BlockPos pos, Direction direction) {
        if (getRedstoneView() instanceof World) {
            return ((World) getRedstoneView()).isEmittingRedstonePower(pos.toMinecraft(), direction.toMinecraft());
        }
        return false;
    }

    default int getStrongRedstonePower(BlockPos pos, Direction direction) {
        return getRedstoneView().getStrongRedstonePower(pos.toMinecraft(), direction.toMinecraft());
    }

    default int getReceivedStrongRedstonePower(BlockPos pos) {
        if (getRedstoneView() instanceof World) {
            return ((World) getRedstoneView()).getReceivedStrongRedstonePower(pos.toMinecraft());
        }
        return 0;
    }

    static RedstoneView of(net.minecraft.world.WorldView world) {
        return WorldView.of(world);
    }
}

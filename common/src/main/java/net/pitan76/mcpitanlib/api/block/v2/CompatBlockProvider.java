package net.pitan76.mcpitanlib.api.block.v2;

import net.minecraft.block.Block;
import net.minecraft.util.shape.VoxelShape;
import net.pitan76.mcpitanlib.api.block.CompatBlockRenderType;
import net.pitan76.mcpitanlib.api.block.ExtendBlockProvider;
import net.pitan76.mcpitanlib.api.block.args.RenderTypeArgs;
import net.pitan76.mcpitanlib.api.block.args.RotateArgs;
import net.pitan76.mcpitanlib.api.block.args.SideInvisibleArgs;
import net.pitan76.mcpitanlib.api.block.args.v2.CollisionShapeEvent;
import net.pitan76.mcpitanlib.api.block.args.v2.OutlineShapeEvent;
import net.pitan76.mcpitanlib.api.block.args.v2.PlacementStateArgs;
import net.pitan76.mcpitanlib.api.block.args.v2.StateForNeighborUpdateArgs;
import net.pitan76.mcpitanlib.midohra.block.BlockState;
import net.pitan76.mcpitanlib.midohra.block.BlockWrapper;
import net.pitan76.mcpitanlib.mixin.BlockInvoker;

public interface CompatBlockProvider extends ExtendBlockProvider {
    CompatibleBlockSettings getCompatSettings();

    default BlockWrapper getWrapper() {
        return this instanceof Block ? BlockWrapper.of((Block) this) : BlockWrapper.of();
    }

    default CompatBlockRenderType getRenderType(RenderTypeArgs args, Options options) {
        options.cancel = false;
        return null;
    }

    default BlockState rotate(RotateArgs args, Options options) {
        options.cancel = false;
        return null;
    }

    default Boolean isSideInvisible(SideInvisibleArgs args, Options options) {
        options.cancel = false;
        return null;
    }

    default BlockState getDefaultMidohraState() {
        if (this instanceof Block) {
            return BlockState.of(((Block) this).getDefaultState());
        }

        return null;
    }

    default void setDefaultState(BlockState state) {
        if (this instanceof Block) {
            ((BlockInvoker) this).setDefaultState(state.toMinecraft());
        }
    }

    @Override
    @Deprecated
    default net.minecraft.block.BlockState getPlacementState(net.pitan76.mcpitanlib.api.event.block.PlacementStateArgs args, Options options) {
        return ExtendBlockProvider.super.getPlacementState(args, options);
    }

    default BlockState getPlacementState(PlacementStateArgs args, Options options) {
        options.cancel = false;
        return null;
    }

    default BlockState getStateForNeighborUpdate(StateForNeighborUpdateArgs args, Options options) {
        options.cancel = false;
        return null;
    }

    @Override
    @Deprecated
    default VoxelShape getOutlineShape(net.pitan76.mcpitanlib.api.event.block.OutlineShapeEvent event, Options options) {
        return ExtendBlockProvider.super.getOutlineShape(event, options);
    }

    default VoxelShape getOutlineShape(OutlineShapeEvent event, Options options) {
        options.cancel = false;
        return null;
    }

    @Deprecated
    @Override
    default VoxelShape getCollisionShape(net.pitan76.mcpitanlib.api.event.block.CollisionShapeEvent event, Options options) {
        return ExtendBlockProvider.super.getCollisionShape(event, options);
    }

    default VoxelShape getCollisionShape(CollisionShapeEvent event, Options options) {
        options.cancel = false;
        return null;
    }
}

package net.pitan76.mcpitanlib.mixin;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;
import net.pitan76.mcpitanlib.api.block.CompatBlockRenderType;
import net.pitan76.mcpitanlib.api.block.ExtendBlockProvider.Options;
import net.pitan76.mcpitanlib.api.block.args.RenderTypeArgs;
import net.pitan76.mcpitanlib.api.block.args.RotateArgs;
import net.pitan76.mcpitanlib.api.block.args.SideInvisibleArgs;
import net.pitan76.mcpitanlib.api.block.args.v2.CollisionShapeEvent;
import net.pitan76.mcpitanlib.api.block.args.v2.OutlineShapeEvent;
import net.pitan76.mcpitanlib.api.block.args.v2.StateForNeighborUpdateArgs;
import net.pitan76.mcpitanlib.api.block.v2.CompatBlockProvider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractBlock.class)
public class AbstractBlock4CompatProviderMixin {
    @Inject(method = "getCollisionShape", at = @At("HEAD"), cancellable = true)
    private void mcpitanlib$inject_getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context, CallbackInfoReturnable<VoxelShape> cir) {
        if (this instanceof CompatBlockProvider) {
            CompatBlockProvider provider = (CompatBlockProvider) this;
            Options options = new Options();
            VoxelShape returnValue = provider.getCollisionShape(new CollisionShapeEvent(state, world, pos, context), options);
            if (options.cancel && returnValue != null)
                cir.setReturnValue(returnValue);
        }
    }

    @Inject(method = "getOutlineShape", at = @At("HEAD"), cancellable = true)
    private void mcpitanlib$inject_getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context, CallbackInfoReturnable<VoxelShape> cir) {
        if (this instanceof CompatBlockProvider) {
            CompatBlockProvider provider = (CompatBlockProvider) this;
            Options options = new Options();
            VoxelShape returnValue = provider.getOutlineShape(new OutlineShapeEvent(state, world, pos, context), options);
            if (options.cancel && returnValue != null)
                cir.setReturnValue(returnValue);
        }
    }

    @Inject(method = "getStateForNeighborUpdate", at = @At("HEAD"), cancellable = true)
    private void mcpitanlib$inject_getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos, CallbackInfoReturnable<BlockState> cir) {
        if (this instanceof CompatBlockProvider) {
            CompatBlockProvider provider = (CompatBlockProvider) this;
            Options options = new Options();
            net.pitan76.mcpitanlib.midohra.block.BlockState returnValue = provider.getStateForNeighborUpdate(new StateForNeighborUpdateArgs(state, direction, neighborState, world, pos, neighborPos), options);
            if (options.cancel && returnValue != null)
                cir.setReturnValue(returnValue.toMinecraft());
        }
    }

    @Inject(method = "getRenderType", at = @At("HEAD"), cancellable = true)
    private void mcpitanlib$inject_getRenderType(BlockState state, CallbackInfoReturnable<BlockRenderType> cir) {
        if (this instanceof CompatBlockProvider) {
            CompatBlockProvider provider = (CompatBlockProvider) this;
            Options options = new Options();
            CompatBlockRenderType returnValue = provider.getRenderType(new RenderTypeArgs(state), options);
            if (options.cancel && returnValue != null)
                cir.setReturnValue(returnValue.toMinecraft());
        }
    }

    @Inject(method = "rotate", at = @At("HEAD"), cancellable = true)
    private void mcpitanlib$inject_rotate(BlockState state, BlockRotation rotation, CallbackInfoReturnable<BlockState> cir) {
        if (this instanceof CompatBlockProvider) {
            CompatBlockProvider provider = (CompatBlockProvider) this;
            Options options = new Options();
            net.pitan76.mcpitanlib.midohra.block.BlockState returnValue = provider.rotate(new RotateArgs(state, rotation), options);
            if (options.cancel && returnValue != null)
                cir.setReturnValue(returnValue.toMinecraft());
        }
    }

    @Inject(method = "isSideInvisible", at = @At("HEAD"), cancellable = true)
    private void mcpitanlib$inject_isSideInvisible(BlockState state, BlockState stateFrom, Direction direction, CallbackInfoReturnable<Boolean> cir) {
        if (this instanceof CompatBlockProvider) {
            CompatBlockProvider provider = (CompatBlockProvider) this;
            Options options = new Options();
            Boolean returnValue = provider.isSideInvisible(new SideInvisibleArgs(state, stateFrom, direction), options);
            if (options.cancel && returnValue != null)
                cir.setReturnValue(returnValue);
        }
    }
}

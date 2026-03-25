package net.pitan76.mcpitanlib.mixin;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.pitan76.mcpitanlib.api.block.CompatBlockRenderType;
import net.pitan76.mcpitanlib.api.block.ExtendBlockProvider.Options;
import net.pitan76.mcpitanlib.api.block.args.RenderTypeArgs;
import net.pitan76.mcpitanlib.api.block.args.RotateArgs;
import net.pitan76.mcpitanlib.api.block.args.SideInvisibleArgs;
import net.pitan76.mcpitanlib.api.block.args.v2.CollisionShapeEvent;
import net.pitan76.mcpitanlib.api.block.args.v2.OutlineShapeEvent;
import net.pitan76.mcpitanlib.api.block.args.v2.StateForNeighborUpdateArgs;
import net.pitan76.mcpitanlib.api.block.v2.CompatBlockProvider;
import net.pitan76.mcpitanlib.api.util.math.random.CompatRandom;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockBehaviour.class)
public class BlockBehaviour4CompatProviderMixin {
    @Inject(method = "getCollisionShape", at = @At("HEAD"), cancellable = true)
    private void mcpitanlib$inject_getCollisionShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context, CallbackInfoReturnable<VoxelShape> cir) {
        if (this instanceof CompatBlockProvider) {
            CompatBlockProvider provider = (CompatBlockProvider) this;
            Options options = new Options();
            VoxelShape returnValue = provider.getCollisionShape(new CollisionShapeEvent(state, world, pos, context), options);
            if (options.cancel && returnValue != null)
                cir.setReturnValue(returnValue);
        }
    }

    @Inject(method = "getShape", at = @At("HEAD"), cancellable = true)
    private void mcpitanlib$inject_getOutlineShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context, CallbackInfoReturnable<VoxelShape> cir) {
        if (this instanceof CompatBlockProvider) {
            CompatBlockProvider provider = (CompatBlockProvider) this;
            Options options = new Options();
            VoxelShape returnValue = provider.getOutlineShape(new OutlineShapeEvent(state, world, pos, context), options);
            if (options.cancel && returnValue != null)
                cir.setReturnValue(returnValue);
        }
    }

    @Inject(method = "updateShape", at = @At("HEAD"), cancellable = true)
    private void mcpitanlib$inject_getStateForNeighborUpdate(BlockState state, LevelReader world, ScheduledTickAccess tickView, BlockPos pos, Direction direction, BlockPos neighborPos, BlockState neighborState, RandomSource random, CallbackInfoReturnable<BlockState> cir) {
        if (this instanceof CompatBlockProvider) {
            CompatBlockProvider provider = (CompatBlockProvider) this;
            Options options = new Options();
            net.pitan76.mcpitanlib.midohra.block.BlockState returnValue = provider.getStateForNeighborUpdate(new StateForNeighborUpdateArgs(state, direction, neighborState, world, pos, neighborPos, tickView, new CompatRandom(random)), options);
            if (options.cancel && returnValue != null)
                cir.setReturnValue(returnValue.toMinecraft());
        }
    }

    @Inject(method = "getRenderShape", at = @At("HEAD"), cancellable = true)
    private void mcpitanlib$inject_getRenderType(BlockState state, CallbackInfoReturnable<RenderShape> cir) {
        if (this instanceof CompatBlockProvider) {
            CompatBlockProvider provider = (CompatBlockProvider) this;
            Options options = new Options();
            CompatBlockRenderType returnValue = provider.getRenderType(new RenderTypeArgs(state), options);
            if (options.cancel && returnValue != null)
                cir.setReturnValue(returnValue.toMinecraft());
        }
    }

    @Inject(method = "rotate", at = @At("HEAD"), cancellable = true)
    private void mcpitanlib$inject_rotate(BlockState state, Rotation rotation, CallbackInfoReturnable<BlockState> cir) {
        if (this instanceof CompatBlockProvider) {
            CompatBlockProvider provider = (CompatBlockProvider) this;
            Options options = new Options();
            net.pitan76.mcpitanlib.midohra.block.BlockState returnValue = provider.rotate(new RotateArgs(state, rotation), options);
            if (options.cancel && returnValue != null)
                cir.setReturnValue(returnValue.toMinecraft());
        }
    }

    @Inject(method = "skipRendering", at = @At("HEAD"), cancellable = true)
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

package net.pitan76.mcpitanlib.mixin;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.context.LootContextParameterSet;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.pitan76.mcpitanlib.api.block.ExtendBlockProvider;
import net.pitan76.mcpitanlib.api.block.ExtendBlockProvider.Options;
import net.pitan76.mcpitanlib.api.event.block.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(AbstractBlock.class)
public class AbstractBlockMixin {
    @Inject(method = "getCollisionShape", at = @At("HEAD"), cancellable = true)
    private void mcpitanlib$inject_getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context, CallbackInfoReturnable<VoxelShape> cir) {
        if (this instanceof ExtendBlockProvider) {
            ExtendBlockProvider provider = (ExtendBlockProvider) this;
            Options options = new Options();
            VoxelShape returnValue = provider.getCollisionShape(new CollisionShapeEvent(state, world, pos, context), options);
            if (options.cancel && returnValue != null)
                cir.setReturnValue(returnValue);
        }
    }

    @Inject(method = "getOutlineShape", at = @At("HEAD"), cancellable = true)
    private void mcpitanlib$inject_getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context, CallbackInfoReturnable<VoxelShape> cir) {
        if (this instanceof ExtendBlockProvider) {
            ExtendBlockProvider provider = (ExtendBlockProvider) this;
            Options options = new Options();
            VoxelShape returnValue = provider.getOutlineShape(new OutlineShapeEvent(state, world, pos, context), options);
            if (options.cancel && returnValue != null)
                cir.setReturnValue(returnValue);
        }
    }

    @Inject(method = "scheduledTick", at = @At("HEAD"), cancellable = true)
    private void mcpitanlib$inject_scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random, CallbackInfo ci) {
        if (this instanceof ExtendBlockProvider) {
            ExtendBlockProvider provider = (ExtendBlockProvider) this;
            Options options = new Options();
            provider.scheduledTick(new BlockScheduledTickEvent(state, world, pos, random), options);
            if (options.cancel)
                ci.cancel();
        }
    }

    @Inject(method = "onUse", at = @At("HEAD"), cancellable = true)
    private void mcpitanlib$inject_onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit, CallbackInfoReturnable<ActionResult> cir) {
        if (this instanceof ExtendBlockProvider) {
            ExtendBlockProvider provider = (ExtendBlockProvider) this;
            Options options = new Options();
            ActionResult returnValue = provider.onRightClick(new BlockUseEvent(state, world, pos, player, hand, hit), options);
            if (options.cancel && returnValue != null)
                cir.setReturnValue(returnValue);
        }
    }

    @Inject(method = "createScreenHandlerFactory", at = @At("HEAD"), cancellable = true)
    private void mcpitanlib$inject_createScreenHandlerFactory(BlockState state, World world, BlockPos pos, CallbackInfoReturnable<NamedScreenHandlerFactory> cir) {
        if (this instanceof ExtendBlockProvider) {
            ExtendBlockProvider provider = (ExtendBlockProvider) this;
            Options options = new Options();
            NamedScreenHandlerFactory returnValue = new SimpleNamedScreenHandlerFactory((syncId, inventory, player) ->
                provider.createScreenHandler(new ScreenHandlerCreateEvent(state, world, pos, syncId, inventory, player), options), provider.getScreenTitle());
            if (options.cancel && returnValue != null)
                cir.setReturnValue(returnValue);
        }
    }

    @Inject(method = "onStateReplaced", at = @At("HEAD"), cancellable = true)
    private void mcpitanlib$inject_onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved, CallbackInfo ci) {
        if (this instanceof ExtendBlockProvider) {
            ExtendBlockProvider provider = (ExtendBlockProvider) this;
            Options options = new Options();
            provider.onStateReplaced(new StateReplacedEvent(state, world, pos, newState, moved), options);
            if (options.cancel)
                ci.cancel();
        }
    }

    @Inject(method = "getDroppedStacks", at = @At("HEAD"), cancellable = true)
    private void mcpitanlib$inject_getDroppedStacks(BlockState state, LootContextParameterSet.Builder builder, CallbackInfoReturnable<List<ItemStack>> cir) {
        if (this instanceof ExtendBlockProvider) {
            ExtendBlockProvider provider = (ExtendBlockProvider) this;
            Options options = new Options();
            List<ItemStack> returnValue = provider.getDroppedStacks(new DroppedStacksArgs(state, builder), options);
            if (options.cancel && returnValue != null)
                cir.setReturnValue(returnValue);
        }
    }

    @Inject(method = "canPathfindThrough", at = @At("HEAD"), cancellable = true)
    private void mcpitanlib$inject_canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type, CallbackInfoReturnable<Boolean> cir) {
        if (this instanceof ExtendBlockProvider) {
            ExtendBlockProvider provider = (ExtendBlockProvider) this;
            Options options = new Options();
            Boolean returnValue = provider.canPathfindThrough(new CanPathfindThroughArgs(state, world, pos, type), options);
            if (options.cancel && returnValue != null)
                cir.setReturnValue(returnValue);
        }
    }

}

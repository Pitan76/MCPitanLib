package net.pitan76.mcpitanlib.mixin;

import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.pitan76.mcpitanlib.api.block.ExtendBlockProvider;
import net.pitan76.mcpitanlib.api.block.ExtendBlockProvider.Options;
import net.pitan76.mcpitanlib.api.event.block.*;
import net.pitan76.mcpitanlib.api.util.CompatActionResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(BlockBehaviour.class)
public class BlockBehaviourMixin {
    @Inject(method = "getCollisionShape", at = @At("HEAD"), cancellable = true)
    private void mcpitanlib$inject_getCollisionShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context, CallbackInfoReturnable<VoxelShape> cir) {
        if (this instanceof ExtendBlockProvider) {
            ExtendBlockProvider provider = (ExtendBlockProvider) this;
            Options options = new Options();
            VoxelShape returnValue = provider.getCollisionShape(new CollisionShapeEvent(state, world, pos, context), options);
            if (options.cancel && returnValue != null)
                cir.setReturnValue(returnValue);
        }
    }

    @Inject(method = "getShape", at = @At("HEAD"), cancellable = true)
    private void mcpitanlib$inject_getOutlineShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context, CallbackInfoReturnable<VoxelShape> cir) {
        if (this instanceof ExtendBlockProvider) {
            ExtendBlockProvider provider = (ExtendBlockProvider) this;
            Options options = new Options();
            VoxelShape returnValue = provider.getOutlineShape(new OutlineShapeEvent(state, world, pos, context), options);
            if (options.cancel && returnValue != null)
                cir.setReturnValue(returnValue);
        }
    }

    @Inject(method = "tick", at = @At("HEAD"), cancellable = true)
    private void mcpitanlib$inject_scheduledTick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random, CallbackInfo ci) {
        if (this instanceof ExtendBlockProvider) {
            ExtendBlockProvider provider = (ExtendBlockProvider) this;
            Options options = new Options();
            provider.scheduledTick(new BlockScheduledTickEvent(state, world, pos, random), options);
            if (options.cancel)
                ci.cancel();
        }
    }

    @Inject(method = "useWithoutItem", at = @At("HEAD"), cancellable = true)
    private void mcpitanlib$inject_onUse(BlockState state, Level world, BlockPos pos, Player player, BlockHitResult hit, CallbackInfoReturnable<InteractionResult> cir) {
        if (this instanceof ExtendBlockProvider) {
            ExtendBlockProvider provider = (ExtendBlockProvider) this;
            Options options = new Options();
            CompatActionResult returnValue = provider.onRightClick(new BlockUseEvent(state, world, pos, player, player.getUsedItemHand(), hit), options);
            if (options.cancel && returnValue != null)
                cir.setReturnValue(returnValue.toActionResult());
        }
    }

    @Inject(method = "getMenuProvider", at = @At("HEAD"), cancellable = true)
    private void mcpitanlib$inject_createScreenHandlerFactory(BlockState state, Level world, BlockPos pos, CallbackInfoReturnable<MenuProvider> cir) {
        if (this instanceof ExtendBlockProvider) {
            ExtendBlockProvider provider = (ExtendBlockProvider) this;
            Options options = new Options();
            MenuProvider returnValue = new SimpleMenuProvider((syncId, inventory, player) ->
                provider.createScreenHandler(new ScreenHandlerCreateEvent(state, world, pos, syncId, inventory, player), options), provider.getScreenTitle());
            if (options.cancel && returnValue != null)
                cir.setReturnValue(returnValue);
        }
    }

    @Inject(method = "affectNeighborsAfterRemoval", at = @At("HEAD"), cancellable = true)
    private void mcpitanlib$inject_onStateReplaced(BlockState state, ServerLevel world, BlockPos pos, boolean moved, CallbackInfo ci) {
        if (this instanceof ExtendBlockProvider) {
            ExtendBlockProvider provider = (ExtendBlockProvider) this;
            Options options = new Options();
            provider.onStateReplaced(new StateReplacedEvent(state, world, pos, world.getBlockState(pos), moved), options);
            if (options.cancel)
                ci.cancel();
        }
    }

    @Inject(method = "getDrops", at = @At("HEAD"), cancellable = true)
    private void mcpitanlib$inject_getDroppedStacks(BlockState state, LootParams.Builder builder, CallbackInfoReturnable<List<ItemStack>> cir) {
        if (this instanceof ExtendBlockProvider) {
            ExtendBlockProvider provider = (ExtendBlockProvider) this;
            Options options = new Options();
            List<ItemStack> returnValue = provider.getDroppedStacks(new DroppedStacksArgs(state, builder), options);
            if (options.cancel && returnValue != null)
                cir.setReturnValue(returnValue);
        }
    }

    @Inject(method = "isPathfindable", at = @At("HEAD"), cancellable = true)
    private void mcpitanlib$inject_canPathfindThrough(BlockState state, PathComputationType type, CallbackInfoReturnable<Boolean> cir) {
        if (this instanceof ExtendBlockProvider) {
            ExtendBlockProvider provider = (ExtendBlockProvider) this;
            Options options = new Options();
            Boolean returnValue = provider.canPathfindThrough(new CanPathfindThroughArgs(state, type), options);
            if (options.cancel && returnValue != null)
                cir.setReturnValue(returnValue);
        }
    }

    @Inject(method = "getCloneItemStack", at = @At("HEAD"), cancellable = true)
    private void mcpitanlib$getPickStack(LevelReader world, BlockPos pos, BlockState state, boolean includeData, CallbackInfoReturnable<ItemStack> cir) {
        // ExtendBlockProviderを実装している場合 (1.21.5からAbstractBlockに移転)
        if (this instanceof ExtendBlockProvider) {
            ExtendBlockProvider provider = (ExtendBlockProvider) this;
            Options options = new Options();

            PickStackEvent event = new PickStackEvent(world, pos, state);
            event.setIncludeData(includeData);

            ItemStack returnValue = provider.getPickStack(event, options);
            if (options.cancel && returnValue != null)
                cir.setReturnValue(returnValue);
        }
    }
}

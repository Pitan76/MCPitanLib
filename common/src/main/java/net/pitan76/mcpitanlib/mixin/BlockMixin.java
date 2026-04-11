package net.pitan76.mcpitanlib.mixin;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.pitan76.mcpitanlib.api.block.ExtendBlockProvider;
import net.pitan76.mcpitanlib.api.block.ExtendBlockProvider.Options;
import net.pitan76.mcpitanlib.api.event.block.*;
import net.pitan76.mcpitanlib.api.event.block.result.BlockBreakResult;
import net.pitan76.mcpitanlib.api.event.v1.listener.BlockBreakTask;
import net.pitan76.mcpitanlib.api.event.v1.listener.BlockPlacedTask;
import net.pitan76.mcpitanlib.api.event.v2.BlockEventRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Block.class)
public class BlockMixin {

    @Inject(method = "setPlacedBy", at = @At("HEAD"), cancellable = true)
    private void mcpitanlib$onPlaced(Level world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack, CallbackInfo ci) {
        // イベントを呼び出す
        if (!BlockEventRegistry.ON_PLACED.isEmpty()) {
            int maxPriority = BlockEventRegistry.ON_PLACED.getMaxPriority();
            for (int p = maxPriority; p >= 0; p--) {
                for (BlockPlacedTask listener : BlockEventRegistry.ON_PLACED.getListenersAsList(p)) {
                    listener.onPlaced(new BlockPlacedEvent(world, pos, state, placer, stack));
                }
            }
        }

        // ExtendBlockProviderを実装している場合
        if (this instanceof ExtendBlockProvider) {
            ExtendBlockProvider provider = (ExtendBlockProvider) this;
            Options options = new Options();
            provider.onPlaced(new BlockPlacedEvent(world, pos, state, placer, stack), options);
            if (options.cancel)
                ci.cancel();
        }
    }

    @Inject(method = "playerWillDestroy", at = @At("HEAD"), cancellable = true)
    private void mcpitanlib$onBreak(Level world, BlockPos pos, BlockState state, Player player, CallbackInfoReturnable<BlockState> cir) {
        // イベントを呼び出す
        if (!BlockEventRegistry.ON_BREAK.isEmpty()) {
            BlockState newState = state;
            int maxPriority = BlockEventRegistry.ON_BREAK.getMaxPriority();
            for (int p = maxPriority; p >= 0; p--) {
                for (BlockBreakTask listener : BlockEventRegistry.ON_BREAK.getListenersAsList(p)) {
                    newState = listener.onBreak(new BlockBreakEvent(world, pos, newState, player)).state;
                }
            }
            if (newState != state) {
                cir.setReturnValue(newState);
                state = newState;
            }
        }

        // ExtendBlockProviderを実装している場合
        if (this instanceof ExtendBlockProvider) {
            ExtendBlockProvider provider = (ExtendBlockProvider) this;
            Options options = new Options();
            BlockBreakResult returnValue = provider.onBreak(new BlockBreakEvent(world, pos, state, player), options);
            if (options.cancel && returnValue != null)
                cir.setReturnValue(returnValue.getState());
        }
    }

    @Inject(method = "createBlockStateDefinition", at = @At("HEAD"), cancellable = true)
    private void mcpitanlib$appendProperties(StateDefinition.Builder<Block, BlockState> builder, CallbackInfo ci) {
        // ExtendBlockProviderを実装している場合
        if (this instanceof ExtendBlockProvider) {
            ExtendBlockProvider provider = (ExtendBlockProvider) this;
            Options options = new Options();
            provider.appendProperties(new AppendPropertiesArgs(builder), options);
            if (options.cancel)
                ci.cancel();
        }
    }

    @Inject(method = "getStateForPlacement", at = @At("HEAD"), cancellable = true)
    private void mcpitanlib$getPlacementState(BlockPlaceContext ctx, CallbackInfoReturnable<BlockState> cir) {
        // ExtendBlockProviderを実装している場合
        if (this instanceof ExtendBlockProvider) {
            ExtendBlockProvider provider = (ExtendBlockProvider) this;
            Options options = new Options();
            BlockState returnValue = provider.getPlacementState(new PlacementStateArgs(ctx), options);
            if (options.cancel && returnValue != null)
                cir.setReturnValue(returnValue);
        }
    }
}

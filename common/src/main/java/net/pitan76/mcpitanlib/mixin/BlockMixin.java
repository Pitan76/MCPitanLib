package net.pitan76.mcpitanlib.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.pitan76.mcpitanlib.api.block.ExtendBlockProvider;
import net.pitan76.mcpitanlib.api.block.ExtendBlockProvider.Options;
import net.pitan76.mcpitanlib.api.event.block.BlockBreakEvent;
import net.pitan76.mcpitanlib.api.event.block.BlockPlacedEvent;
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

    @Inject(method = "onPlaced", at = @At("HEAD"), cancellable = true)
    private void inject_onPlaced(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack, CallbackInfo ci) {
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

    @Inject(method = "onBreak", at = @At("HEAD"), cancellable = true)
    private void inject_onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player, CallbackInfoReturnable<BlockState> cir) {
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
}

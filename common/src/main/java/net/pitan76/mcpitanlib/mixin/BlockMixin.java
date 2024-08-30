package net.pitan76.mcpitanlib.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.state.StateManager;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.pitan76.mcpitanlib.api.block.ExtendBlockProvider;
import net.pitan76.mcpitanlib.api.block.ExtendBlockProvider.Options;
import net.pitan76.mcpitanlib.api.event.block.*;
import net.pitan76.mcpitanlib.api.event.block.result.BlockBreakResult;
import net.pitan76.mcpitanlib.api.event.item.ItemAppendTooltipEvent;
import net.pitan76.mcpitanlib.api.event.v1.listener.BlockBreakTask;
import net.pitan76.mcpitanlib.api.event.v1.listener.BlockPlacedTask;
import net.pitan76.mcpitanlib.api.event.v2.BlockEventRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(Block.class)
public class BlockMixin {

    @Inject(method = "onPlaced", at = @At("HEAD"), cancellable = true)
    private void mcpitanlib$onPlaced(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack, CallbackInfo ci) {
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
    private void mcpitanlib$onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player, CallbackInfo ci) {
        // イベントを呼び出す
        if (!BlockEventRegistry.ON_BREAK.isEmpty()) {
            int maxPriority = BlockEventRegistry.ON_BREAK.getMaxPriority();
            for (int p = maxPriority; p >= 0; p--) {
                for (BlockBreakTask listener : BlockEventRegistry.ON_BREAK.getListenersAsList(p)) {
                    listener.onBreak(new BlockBreakEvent(world, pos, state, player));
                }
            }
        }

        // ExtendBlockProviderを実装している場合
        if (this instanceof ExtendBlockProvider) {
            ExtendBlockProvider provider = (ExtendBlockProvider) this;
            Options options = new Options();
            BlockBreakResult returnValue = provider.onBreak(new BlockBreakEvent(world, pos, state, player), options);
            if (options.cancel)
                ci.cancel();
        }
    }

    @Inject(method = "getPickStack", at = @At("HEAD"), cancellable = true)
    private void mcpitanlib$getPickStack(WorldView world, BlockPos pos, BlockState state, CallbackInfoReturnable<ItemStack> cir) {
        // ExtendBlockProviderを実装している場合
        if (this instanceof ExtendBlockProvider) {
            ExtendBlockProvider provider = (ExtendBlockProvider) this;
            Options options = new Options();
            ItemStack returnValue = provider.getPickStack(new PickStackEvent(world, pos, state), options);
            if (options.cancel && returnValue != null)
                cir.setReturnValue(returnValue);
        }
    }

    @Inject(method = "appendProperties", at = @At("HEAD"), cancellable = true)
    private void mcpitanlib$appendProperties(StateManager.Builder<Block, BlockState> builder, CallbackInfo ci) {
        // ExtendBlockProviderを実装している場合
        if (this instanceof ExtendBlockProvider) {
            ExtendBlockProvider provider = (ExtendBlockProvider) this;
            Options options = new Options();
            provider.appendProperties(new AppendPropertiesArgs(builder), options);
            if (options.cancel)
                ci.cancel();
        }
    }

    @Inject(method = "getPlacementState", at = @At("HEAD"), cancellable = true)
    private void mcpitanlib$getPlacementState(ItemPlacementContext ctx, CallbackInfoReturnable<BlockState> cir) {
        // ExtendBlockProviderを実装している場合
        if (this instanceof ExtendBlockProvider) {
            ExtendBlockProvider provider = (ExtendBlockProvider) this;
            Options options = new Options();
            BlockState returnValue = provider.getPlacementState(new PlacementStateArgs(ctx), options);
            if (options.cancel && returnValue != null)
                cir.setReturnValue(returnValue);
        }
    }

    @Inject(method = "appendTooltip", at = @At("HEAD"), cancellable = true)
    private void mcpitanlib$appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType type, CallbackInfo ci) {
        // ExtendBlockProviderを実装している場合
        if (this instanceof ExtendBlockProvider) {
            ExtendBlockProvider provider = (ExtendBlockProvider) this;
            Options options = new Options();
            provider.appendTooltip(new ItemAppendTooltipEvent(stack, null, tooltip, type, context), options);
            if (options.cancel)
                ci.cancel();
        }
    }
}

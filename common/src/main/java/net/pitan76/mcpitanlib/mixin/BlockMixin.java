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
import net.pitan76.mcpitanlib.api.event.v1.BlockEventRegistry;
import net.pitan76.mcpitanlib.api.event.v1.listener.BlockBreakTask;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Block.class)
public class BlockMixin {

    @Inject(method = "onPlaced", at = @At("HEAD"), cancellable = true)
    private void inject_onPlaced(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack, CallbackInfo ci) {
        if (!BlockEventRegistry.onPlacedListeners.isEmpty()) {
            BlockEventRegistry.onPlacedListeners.forEach(l -> l.onPlaced(new BlockPlacedEvent(world, pos, state, placer, stack)));
        }

        if (this instanceof ExtendBlockProvider) {
            ExtendBlockProvider provider = (ExtendBlockProvider) this;
            Options options = new Options();
            provider.onPlaced(new BlockPlacedEvent(world, pos, state, placer, stack), options);
            if (options.cancel)
                ci.cancel();
        }
    }

    @Inject(method = "onBreak", at = @At("HEAD"), cancellable = true)
    private void inject_onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player, CallbackInfo ci) {
        if (!BlockEventRegistry.onBreakListeners.isEmpty()) {
            for (BlockBreakTask l : BlockEventRegistry.onBreakListeners) {
                l.onBreak(new BlockBreakEvent(world, pos, state, player));
            }
        }

        if (this instanceof ExtendBlockProvider) {
            ExtendBlockProvider provider = (ExtendBlockProvider) this;
            Options options = new Options();
            BlockBreakResult returnValue = provider.onBreak(new BlockBreakEvent(world, pos, state, player), options);
            if (options.cancel)
                ci.cancel();
        }
    }
}

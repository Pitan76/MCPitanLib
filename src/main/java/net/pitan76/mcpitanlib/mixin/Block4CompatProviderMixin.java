package net.pitan76.mcpitanlib.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemPlacementContext;
import net.pitan76.mcpitanlib.api.block.ExtendBlockProvider.Options;
import net.pitan76.mcpitanlib.api.block.args.v2.PlacementStateArgs;
import net.pitan76.mcpitanlib.api.block.v2.CompatBlockProvider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

// TODO(Ravel): can not resolve target class Block
@Mixin(Block.class)
public class Block4CompatProviderMixin {

    // TODO(Ravel): no target class
    @Inject(method = "getPlacementState", at = @At("HEAD"), cancellable = true)
    private void mcpitanlib$getPlacementState(ItemPlacementContext ctx, CallbackInfoReturnable<BlockState> cir) {
        // ExtendBlockProviderを実装している場合
        if (this instanceof CompatBlockProvider) {
            CompatBlockProvider provider = (CompatBlockProvider) this;
            Options options = new Options();
            net.pitan76.mcpitanlib.midohra.block.BlockState returnValue = provider.getPlacementState(new PlacementStateArgs(ctx), options);
            if (options.cancel && returnValue != null)
                cir.setReturnValue(returnValue.toMinecraft());
        }
    }
}

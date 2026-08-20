package net.pitan76.mcpitanlib.mixin;

import net.minecraft.block.Block;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.world.BlockView;
import net.pitan76.mcpitanlib.api.block.ExtendBlockProvider;
import net.pitan76.mcpitanlib.api.block.ExtendBlockProvider.Options;
import net.pitan76.mcpitanlib.api.event.item.ItemAppendTooltipEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

/**
 * TooltipContextがクライアント専用クラスのため、appendTooltip関連はこちらのクライアント専用Mixinへ分離している。
 * (共通側のBlockMixinに置くと専用サーバーでMixinの適用に失敗する)
 */
@Mixin(Block.class)
public class BlockClientMixin {
    @Inject(method = "appendTooltip", at = @At("HEAD"), cancellable = true)
    private void mcpitanlib$appendTooltip(ItemStack stack, BlockView world, List<Text> tooltip, TooltipContext context, CallbackInfo ci) {
        // ExtendBlockProviderを実装している場合
        if (this instanceof ExtendBlockProvider) {
            ExtendBlockProvider provider = (ExtendBlockProvider) this;
            Options options = new Options();
            provider.appendTooltip(new ItemAppendTooltipEvent(stack, world, tooltip, context), options);
            if (options.cancel)
                ci.cancel();
        }
    }
}

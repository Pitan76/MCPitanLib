package net.pitan76.mcpitanlib.mixin;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import net.pitan76.mcpitanlib.api.event.item.ItemAppendTooltipEvent;
import net.pitan76.mcpitanlib.api.item.ExtendItemProvider;
import net.pitan76.mcpitanlib.api.item.ExtendItemProvider.Options;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

/**
 * TooltipContextがクライアント専用クラスのため、appendTooltip関連はこちらのクライアント専用Mixinへ分離している。
 * (共通側のItemMixinに置くと専用サーバーでMixinの適用に失敗する)
 */
@Mixin(Item.class)
public class ItemClientMixin {
    @Inject(method = "appendTooltip", at = @At("HEAD"), cancellable = true)
    private void mcpitanlib$appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context, CallbackInfo ci) {
        if (this instanceof ExtendItemProvider) {
            ExtendItemProvider provider = (ExtendItemProvider) this;
            Options options = new Options();
            provider.appendTooltip(new ItemAppendTooltipEvent(stack, world, tooltip, context), options);
            if (options.cancel)
                ci.cancel();
        }
    }
}

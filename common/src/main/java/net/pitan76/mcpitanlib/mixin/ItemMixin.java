package net.pitan76.mcpitanlib.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.pitan76.mcpitanlib.api.event.item.*;
import net.pitan76.mcpitanlib.api.item.ExtendItemProvider;
import net.pitan76.mcpitanlib.api.item.ExtendItemProvider.Options;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(Item.class)
public class ItemMixin {
    @Inject(method = "use", at = @At("HEAD"), cancellable = true)
    private void mcpitanlib$use(World world, PlayerEntity user, Hand hand, CallbackInfoReturnable<TypedActionResult<ItemStack>> cir) {
        if (this instanceof ExtendItemProvider) {
            ExtendItemProvider provider = (ExtendItemProvider) this;
            Options options = new Options();
            TypedActionResult<ItemStack> returnValue = provider.onRightClick(new ItemUseEvent(world, user, hand), options);
            if (options.cancel && returnValue != null)
                cir.setReturnValue(returnValue);
        }
    }

    @Inject(method = "useOnBlock", at = @At("HEAD"), cancellable = true)
    private void mcpitanlib$useOnBlock(ItemUsageContext context, CallbackInfoReturnable<ActionResult> cir) {
        if (this instanceof ExtendItemProvider) {
            ExtendItemProvider provider = (ExtendItemProvider) this;
            ItemUsageContextMixin contextAccessor = (ItemUsageContextMixin) context;
            Options options = new Options();
            ActionResult returnValue = provider.onRightClickOnBlock(new ItemUseOnBlockEvent(context.getPlayer(), context.getHand(), contextAccessor.getHit()), options);
            if (options.cancel && returnValue != null)
                cir.setReturnValue(returnValue);
        }
    }

    @Inject(method = "useOnEntity", at = @At("HEAD"), cancellable = true)
    private void mcpitanlib$useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
        if (this instanceof ExtendItemProvider) {
            ExtendItemProvider provider = (ExtendItemProvider) this;
            Options options = new Options();
            ActionResult returnValue = provider.onRightClickOnEntity(new ItemUseOnEntityEvent(stack, user, entity, hand), options);
            if (options.cancel && returnValue != null)
                cir.setReturnValue(returnValue);
        }
    }

    @Inject(method = "finishUsing", at = @At("HEAD"), cancellable = true)
    private void mcpitanlib$finishUsing(ItemStack stack, World world, LivingEntity user, CallbackInfoReturnable<ItemStack> cir) {
        if (this instanceof ExtendItemProvider) {
            ExtendItemProvider provider = (ExtendItemProvider) this;
            Options options = new Options();
            ItemStack returnValue = provider.onFinishUsing(new ItemFinishUsingEvent(stack, world, user), options);
            if (options.cancel && returnValue != null)
                cir.setReturnValue(returnValue);
        }
    }

    @Inject(method = "hasRecipeRemainder", at = @At("HEAD"), cancellable = true)
    private void mcpitanlib$hasRecipeRemainder(CallbackInfoReturnable<Boolean> cir) {
        if (this instanceof ExtendItemProvider) {
            ExtendItemProvider provider = (ExtendItemProvider) this;
            Options options = new Options();
            boolean returnValue = provider.hasRecipeRemainder(options);
            if (options.cancel)
                cir.setReturnValue(returnValue);
        }
    }

    @Inject(method = "appendTooltip", at = @At("HEAD"), cancellable = true)
    private void mcpitanlib$appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType type, CallbackInfo ci) {
        if (this instanceof ExtendItemProvider) {
            ExtendItemProvider provider = (ExtendItemProvider) this;
            Options options = new Options();
            provider.appendTooltip(new ItemAppendTooltipEvent(stack, null, tooltip, type, context), options);
            if (options.cancel)
                ci.cancel();
        }
    }

    @Inject(method = "postHit", at = @At("HEAD"), cancellable = true)
    private void mcpitanlib$postHit(ItemStack stack, LivingEntity target, LivingEntity attacker, CallbackInfoReturnable<Boolean> cir) {
        if (this instanceof ExtendItemProvider) {
            ExtendItemProvider provider = (ExtendItemProvider) this;
            Options options = new Options();
            boolean returnValue = provider.postHit(new PostHitEvent(stack, target, attacker), options);
            if (options.cancel)
                cir.setReturnValue(returnValue);
        }
    }

    @Inject(method = "postMine", at = @At("HEAD"), cancellable = true)
    private void mcpitanlib$postMine(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner, CallbackInfoReturnable<Boolean> cir) {
        if (this instanceof ExtendItemProvider) {
            ExtendItemProvider provider = (ExtendItemProvider) this;
            Options options = new Options();
            boolean returnValue = provider.postMine(new PostMineEvent(stack, world, state, pos, miner), options);
            if (options.cancel)
                cir.setReturnValue(returnValue);
        }
    }
}

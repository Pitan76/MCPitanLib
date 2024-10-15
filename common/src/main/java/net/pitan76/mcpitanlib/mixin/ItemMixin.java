package net.pitan76.mcpitanlib.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
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
    private void mcpitanlib$use(World world, PlayerEntity user, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
        if (this instanceof ExtendItemProvider) {
            ExtendItemProvider provider = (ExtendItemProvider) this;
            Options options = new Options();
            ActionResult returnValue = provider.onRightClick(new ItemUseEvent(world, user, hand), options).toActionResult();
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
            ActionResult returnValue = provider.onRightClickOnBlock(new ItemUseOnBlockEvent(context.getPlayer(), context.getHand(), contextAccessor.getHit()), options).toActionResult();
            if (options.cancel && returnValue != null)
                cir.setReturnValue(returnValue);
        }
    }

    @Inject(method = "useOnEntity", at = @At("HEAD"), cancellable = true)
    private void mcpitanlib$useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
        if (this instanceof ExtendItemProvider) {
            ExtendItemProvider provider = (ExtendItemProvider) this;
            Options options = new Options();
            ActionResult returnValue = provider.onRightClickOnEntity(new ItemUseOnEntityEvent(stack, user, entity, hand), options).toActionResult();
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

    /*
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
    */

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

    @Inject(method = "onCraft", at = @At("HEAD"), cancellable = true)
    private void mcpitanlib$onCraft(ItemStack stack, World world, CallbackInfo ci) {
        if (this instanceof ExtendItemProvider) {
            ExtendItemProvider provider = (ExtendItemProvider) this;
            Options options = new Options();
            provider.onCraft(new CraftEvent(stack, world), options);
            if (options.cancel)
                ci.cancel();
        }
    }

    /*
    @Inject(method = "isEnchantable", at = @At("HEAD"), cancellable = true)
    private void mcpitanlib$isEnchantable(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        if (this instanceof ExtendItemProvider) {
            ExtendItemProvider provider = (ExtendItemProvider) this;
            Options options = new Options();
            boolean returnValue = provider.isEnchantable(new EnchantableArgs(stack), options);
            if (options.cancel)
                cir.setReturnValue(returnValue);
        }
    }
    */

    /*
    @Inject(method = "getEnchantability", at = @At("HEAD"), cancellable = true)
    private void mcpitanlib$getEnchantability(CallbackInfoReturnable<Integer> cir) {
        if (this instanceof ExtendItemProvider) {
            ExtendItemProvider provider = (ExtendItemProvider) this;
            Options options = new Options();
            int returnValue = provider.getEnchantability(new EnchantabilityArgs(), options);
            if (options.cancel)
                cir.setReturnValue(returnValue);
        }
    }
    */

    @Inject(method = "getItemBarColor", at = @At("HEAD"), cancellable = true)
    private void mcpitanlib$getItemBarColor(ItemStack stack, CallbackInfoReturnable<Integer> cir) {
        if (this instanceof ExtendItemProvider) {
            ExtendItemProvider provider = (ExtendItemProvider) this;
            Options options = new Options();
            int returnValue = provider.getItemBarColor(new ItemBarColorArgs(stack), options);
            if (options.cancel)
                cir.setReturnValue(returnValue);
        }
    }

    @Inject(method = "isItemBarVisible", at = @At("HEAD"), cancellable = true)
    private void mcpitanlib$isItemBarVisible(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        if (this instanceof ExtendItemProvider) {
            ExtendItemProvider provider = (ExtendItemProvider) this;
            Options options = new Options();
            boolean returnValue = provider.isItemBarVisible(new ItemBarVisibleArgs(stack), options);
            if (options.cancel)
                cir.setReturnValue(returnValue);
        }
    }

    @Inject(method = "getItemBarStep", at = @At("HEAD"), cancellable = true)
    private void mcpitanlib$getItemBarStep(ItemStack stack, CallbackInfoReturnable<Integer> cir) {
        if (this instanceof ExtendItemProvider) {
            ExtendItemProvider provider = (ExtendItemProvider) this;
            Options options = new Options();
            int returnValue = provider.getItemBarStep(new ItemBarStepArgs(stack), options);
            if (options.cancel)
                cir.setReturnValue(returnValue);
        }
    }

    @Inject(method = "getBonusAttackDamage", at = @At("HEAD"), cancellable = true)
    private void mcpitanlib$getBonusAttackDamage(Entity target, float baseAttackDamage, DamageSource damageSource, CallbackInfoReturnable<Float> cir) {
        if (this instanceof ExtendItemProvider) {
            ExtendItemProvider provider = (ExtendItemProvider) this;
            Options options = new Options();
            float returnValue = provider.getBonusAttackDamage(new BonusAttackDamageArgs(target, baseAttackDamage, damageSource), options);
            if (options.cancel)
                cir.setReturnValue(returnValue);
        }
    }
}

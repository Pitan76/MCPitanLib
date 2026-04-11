package net.pitan76.mcpitanlib.mixin;

import net.minecraft.world.item.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.pitan76.mcpitanlib.api.event.item.*;
import net.pitan76.mcpitanlib.api.event.v2.ItemEventRegistry;
import net.pitan76.mcpitanlib.api.event.v2.listener.InventoryTickTask;
import net.pitan76.mcpitanlib.api.item.ExtendItemProvider;
import net.pitan76.mcpitanlib.api.item.ExtendItemProvider.Options;
import net.pitan76.mcpitanlib.api.item.FixedRecipeRemainderItem;
import net.pitan76.mcpitanlib.api.item.args.UseActionArgs;
import net.pitan76.mcpitanlib.api.item.consume.CompatUseAction;
import net.pitan76.mcpitanlib.api.item.v2.CompatItemProvider;
import net.pitan76.mcpitanlib.api.util.CompatActionResult;
import net.pitan76.mcpitanlib.api.util.ItemStackUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.function.Consumer;

@Mixin(Item.class)
public class ItemMixin {
    @Inject(method = "use", at = @At("HEAD"), cancellable = true)
    private void mcpitanlib$use(Level world, Player user, InteractionHand hand, CallbackInfoReturnable<InteractionResult> cir) {
        if (this instanceof ExtendItemProvider) {
            ExtendItemProvider provider = (ExtendItemProvider) this;
            Options options = new Options();
            CompatActionResult returnValue = provider.onRightClick(new ItemUseEvent(world, user, hand), options);
            if (options.cancel && returnValue != null)
                cir.setReturnValue(returnValue.toActionResult());
        }
    }

    @Inject(method = "useOn", at = @At("HEAD"), cancellable = true)
    private void mcpitanlib$useOnBlock(UseOnContext context, CallbackInfoReturnable<InteractionResult> cir) {
        if (this instanceof ExtendItemProvider) {
            ExtendItemProvider provider = (ExtendItemProvider) this;
            UseOnContextMixin contextAccessor = (UseOnContextMixin) context;
            Options options = new Options();
            CompatActionResult returnValue = provider.onRightClickOnBlock(new ItemUseOnBlockEvent(context.getPlayer(), context.getHand(), contextAccessor.getHitResult()), options);
            if (options.cancel && returnValue != null)
                cir.setReturnValue(returnValue.toActionResult());
        }
    }

    @Inject(method = "interactLivingEntity", at = @At("HEAD"), cancellable = true)
    private void mcpitanlib$useOnEntity(ItemStack stack, Player user, LivingEntity entity, InteractionHand hand, CallbackInfoReturnable<InteractionResult> cir) {
        if (this instanceof ExtendItemProvider) {
            ExtendItemProvider provider = (ExtendItemProvider) this;
            Options options = new Options();
            CompatActionResult returnValue = provider.onRightClickOnEntity(new ItemUseOnEntityEvent(stack, user, entity, hand), options);
            if (options.cancel && returnValue != null)
                cir.setReturnValue(returnValue.toActionResult());
        }
    }

    @Inject(method = "finishUsingItem", at = @At("HEAD"), cancellable = true)
    private void mcpitanlib$finishUsing(ItemStack stack, Level world, LivingEntity user, CallbackInfoReturnable<ItemStack> cir) {
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

    @Inject(method = "appendHoverText", at = @At("HEAD"), cancellable = true)
    private void mcpitanlib$appendTooltip(ItemStack stack, Item.TooltipContext context, TooltipDisplay displayComponent, Consumer<Component> textConsumer, TooltipFlag type, CallbackInfo ci) {
        if (this instanceof ExtendItemProvider) {
            ExtendItemProvider provider = (ExtendItemProvider) this;
            Options options = new Options();
            provider.appendTooltip(new ItemAppendTooltipEvent(stack, context, displayComponent, textConsumer, type), options);
            if (options.cancel)
                ci.cancel();
        }
    }

    @Inject(method = "hurtEnemy", at = @At("HEAD"), cancellable = true)
    private void mcpitanlib$postHit(ItemStack stack, LivingEntity target, LivingEntity attacker, CallbackInfo ci) {
        if (this instanceof ExtendItemProvider) {
            ExtendItemProvider provider = (ExtendItemProvider) this;
            Options options = new Options();
            provider.postHit(new PostHitEvent(stack, target, attacker), options);
            if (options.cancel)
                ci.cancel();
        }
    }

    @Inject(method = "mineBlock", at = @At("HEAD"), cancellable = true)
    private void mcpitanlib$postMine(ItemStack stack, Level world, BlockState state, BlockPos pos, LivingEntity miner, CallbackInfoReturnable<Boolean> cir) {
        if (this instanceof ExtendItemProvider) {
            ExtendItemProvider provider = (ExtendItemProvider) this;
            Options options = new Options();
            boolean returnValue = provider.postMine(new PostMineEvent(stack, world, state, pos, miner), options);
            if (options.cancel)
                cir.setReturnValue(returnValue);
        }
    }

    @Inject(method = "onCraftedPostProcess", at = @At("HEAD"), cancellable = true)
    private void mcpitanlib$onCraft(ItemStack stack, Level world, CallbackInfo ci) {
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

    @Inject(method = "getBarColor", at = @At("HEAD"), cancellable = true)
    private void mcpitanlib$getItemBarColor(ItemStack stack, CallbackInfoReturnable<Integer> cir) {
        if (this instanceof ExtendItemProvider) {
            ExtendItemProvider provider = (ExtendItemProvider) this;
            Options options = new Options();
            int returnValue = provider.getItemBarColor(new ItemBarColorArgs(stack), options);
            if (options.cancel)
                cir.setReturnValue(returnValue);
        }
    }

    @Inject(method = "isBarVisible", at = @At("HEAD"), cancellable = true)
    private void mcpitanlib$isItemBarVisible(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        if (this instanceof ExtendItemProvider) {
            ExtendItemProvider provider = (ExtendItemProvider) this;
            Options options = new Options();
            boolean returnValue = provider.isItemBarVisible(new ItemBarVisibleArgs(stack), options);
            if (options.cancel)
                cir.setReturnValue(returnValue);
        }
    }

    @Inject(method = "getBarWidth", at = @At("HEAD"), cancellable = true)
    private void mcpitanlib$getItemBarStep(ItemStack stack, CallbackInfoReturnable<Integer> cir) {
        if (this instanceof ExtendItemProvider) {
            ExtendItemProvider provider = (ExtendItemProvider) this;
            Options options = new Options();
            int returnValue = provider.getItemBarStep(new ItemBarStepArgs(stack), options);
            if (options.cancel)
                cir.setReturnValue(returnValue);
        }
    }

    @Inject(method = "getAttackDamageBonus", at = @At("HEAD"), cancellable = true)
    private void mcpitanlib$getBonusAttackDamage(Entity target, float baseAttackDamage, DamageSource damageSource, CallbackInfoReturnable<Float> cir) {
        if (this instanceof ExtendItemProvider) {
            ExtendItemProvider provider = (ExtendItemProvider) this;
            Options options = new Options();
            float returnValue = provider.getBonusAttackDamage(new BonusAttackDamageArgs(target, baseAttackDamage, damageSource), options);
            if (options.cancel)
                cir.setReturnValue(returnValue);
        }
    }

    @Inject(method = "getUseAnimation", at = @At("HEAD"), cancellable = true)
    private void mcpitanlib$getUseAction(ItemStack stack, CallbackInfoReturnable<ItemUseAnimation> cir) {
        if (this instanceof CompatItemProvider) {
            CompatItemProvider provider = (CompatItemProvider) this;
            Options options = new Options();
            CompatUseAction returnValue = provider.getUseAction(new UseActionArgs(stack), options);
            if (options.cancel)
                cir.setReturnValue(returnValue.get());
        }
    }

    @Inject(method = "inventoryTick", at = @At("HEAD"), cancellable = true)
    private void mcpitanlib$inventoryTick(ItemStack stack, ServerLevel world, Entity entity, EquipmentSlot slot, CallbackInfo ci) {
        // イベントを呼び出す
        if (!ItemEventRegistry.INVENTORY_TICK.isEmpty()) {
            int maxPriority = ItemEventRegistry.INVENTORY_TICK.getMaxPriority();
            for (int p = maxPriority; p >= 0; p--) {
                for (InventoryTickTask listener : ItemEventRegistry.INVENTORY_TICK.getListenersAsList(p)) {
                    listener.inventoryTick(new InventoryTickEvent(stack, world, entity, slot));
                }
            }
        }

        // CompatItemProviderを実装している場合
        if (this instanceof CompatItemProvider) {
            CompatItemProvider provider = (CompatItemProvider) this;
            Options options = new Options();
            provider.inventoryTick(new InventoryTickEvent(stack, world, entity, slot), options);
            if (options.cancel)
                ci.cancel();
        }
    }

    @Inject(method = "getCraftingRemainder", at = @At("HEAD"), cancellable = true)
    private void mcpitanlib$getRecipeRemainder(CallbackInfoReturnable<ItemStackTemplate> cir) {
        if (this instanceof FixedRecipeRemainderItem) {
            ItemStack returnValue = ((FixedRecipeRemainderItem) this)
                    .getFixedRecipeRemainder(ItemStackUtil.create((Item) (Object) this));
            cir.setReturnValue(ItemStackTemplate.fromNonEmptyStack(returnValue));
        }
    }
}

package net.pitan76.mcpitanlib.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.text.Text;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.pitan76.mcpitanlib.api.event.item.*;
import net.pitan76.mcpitanlib.api.event.v2.ItemEventRegistry;
import net.pitan76.mcpitanlib.api.event.v2.listener.InventoryTickTask;
import net.pitan76.mcpitanlib.api.item.ExtendItemProvider;
import net.pitan76.mcpitanlib.api.item.ExtendItemProvider.Options;
import net.pitan76.mcpitanlib.api.item.args.UseActionArgs;
import net.pitan76.mcpitanlib.api.item.consume.CompatUseAction;
import net.pitan76.mcpitanlib.api.item.v2.CompatItemProvider;
import net.pitan76.mcpitanlib.api.util.CompatActionResult;
import net.pitan76.mcpitanlib.api.util.StackActionResult;
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
            StackActionResult returnValue = provider.onRightClick(new ItemUseEvent(world, user, hand), options);
            if (options.cancel && returnValue != null)
                cir.setReturnValue(returnValue.toTypedActionResult());
        }
    }

    @Inject(method = "useOnBlock", at = @At("HEAD"), cancellable = true)
    private void mcpitanlib$useOnBlock(ItemUsageContext context, CallbackInfoReturnable<ActionResult> cir) {
        if (this instanceof ExtendItemProvider) {
            ExtendItemProvider provider = (ExtendItemProvider) this;
            ItemUsageContextMixin contextAccessor = (ItemUsageContextMixin) context;
            Options options = new Options();
            CompatActionResult returnValue = provider.onRightClickOnBlock(new ItemUseOnBlockEvent(context.getPlayer(), context.getHand(), contextAccessor.getHit()), options);
            if (options.cancel && returnValue != null)
                cir.setReturnValue(returnValue.toActionResult());
        }
    }

    @Inject(method = "useOnEntity", at = @At("HEAD"), cancellable = true)
    private void mcpitanlib$useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
        if (this instanceof ExtendItemProvider) {
            ExtendItemProvider provider = (ExtendItemProvider) this;
            Options options = new Options();
            CompatActionResult returnValue = provider.onRightClickOnEntity(new ItemUseOnEntityEvent(stack, user, entity, hand), options);
            if (options.cancel && returnValue != null)
                cir.setReturnValue(returnValue.toActionResult());
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
    private void mcpitanlib$onCraft(ItemStack stack, World world, PlayerEntity player, CallbackInfo ci) {
        if (this instanceof ExtendItemProvider) {
            ExtendItemProvider provider = (ExtendItemProvider) this;
            Options options = new Options();
            provider.onCraft(new CraftEvent(stack, world, player), options);
            if (options.cancel)
                ci.cancel();
        }
    }

    @Inject(method = "getRarity", at = @At("HEAD"), cancellable = true)
    private void mcpitanlib$getRarity(ItemStack stack, CallbackInfoReturnable<Rarity> cir) {
        if (this instanceof ExtendItemProvider) {
            ExtendItemProvider provider = (ExtendItemProvider) this;
            Options options = new Options();
            Rarity returnValue = provider.getRarity(stack, options);
            if (options.cancel)
                cir.setReturnValue(returnValue);
        }
    }

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

    @Inject(method = "getUseAction", at = @At("HEAD"), cancellable = true)
    private void mcpitanlib$getUseAction(ItemStack stack, CallbackInfoReturnable<UseAction> cir) {
        if (this instanceof CompatItemProvider) {
            CompatItemProvider provider = (CompatItemProvider) this;
            Options options = new Options();
            CompatUseAction returnValue = provider.getUseAction(new UseActionArgs(stack), options);
            if (options.cancel)
                cir.setReturnValue(returnValue.getUseAction());
        }
    }

    @Inject(method = "inventoryTick", at = @At("HEAD"), cancellable = true)
    private void mcpitanlib$inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected, CallbackInfo ci) {
        // イベントを呼び出す
        if (!ItemEventRegistry.INVENTORY_TICK.isEmpty()) {
            int maxPriority = ItemEventRegistry.INVENTORY_TICK.getMaxPriority();
            for (int p = maxPriority; p >= 0; p--) {
                for (InventoryTickTask listener : ItemEventRegistry.INVENTORY_TICK.getListenersAsList(p)) {
                    listener.inventoryTick(new InventoryTickEvent(stack, world, entity, slot, selected));
                }
            }
        }

        // CompatItemProviderを実装している場合
        if (this instanceof CompatItemProvider) {
            CompatItemProvider provider = (CompatItemProvider) this;
            Options options = new Options();
            provider.inventoryTick(new InventoryTickEvent(stack, world, entity, slot, selected), options);
            if (options.cancel)
                ci.cancel();
        }
    }
}

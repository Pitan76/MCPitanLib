package net.pitan76.mcpitanlib.api.item;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.Rarity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.pitan76.mcpitanlib.api.event.item.*;
import net.pitan76.mcpitanlib.api.util.CompatActionResult;
import net.pitan76.mcpitanlib.api.util.StackActionResult;
import net.pitan76.mcpitanlib.core.Dummy;
import net.pitan76.mcpitanlib.mixin.UseOnContextMixin;

import java.util.function.Consumer;

public class ExtendItem extends Item implements ICompatItem {

    public ExtendItem(Properties settings) {
        super(settings);
    }

    public ExtendItem(CompatibleItemSettings settings) {
        super(settings.build());
    }

    @Deprecated
    @Override
    public InteractionResult use(Level world, Player user, InteractionHand hand) {
        return onRightClick(new ItemUseEvent(world, user, hand)).toActionResult();
    }

    @Deprecated
    @Override
    public InteractionResult useOn(UseOnContext context) {
        UseOnContextMixin contextAccessor = (UseOnContextMixin) context;
        return onRightClickOnBlock(new ItemUseOnBlockEvent(context.getPlayer(), context.getHand(), contextAccessor.getHitResult())).toActionResult();
    }

    @Deprecated
    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity user) {
        return onFinishUsing(new ItemFinishUsingEvent(stack, world, user));
    }

    @Deprecated
    @Override
    public InteractionResult interactLivingEntity(ItemStack stack, Player user, LivingEntity entity, InteractionHand hand) {
        return onRightClickOnEntity(new ItemUseOnEntityEvent(stack, user, entity, hand)).toActionResult();
    }

    @Override
    public ItemUseAnimation getUseAnimation(ItemStack stack) {
        return super.getUseAnimation(stack);
    }

    @Deprecated
    //@Override
    public boolean hasRecipeRemainder() {
        return hasRecipeRemainder(new Dummy());
    }

    @Deprecated
    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay displayComponent, Consumer<Component> textConsumer, TooltipFlag type) {
        appendTooltip(new ItemAppendTooltipEvent(stack, context, displayComponent, textConsumer, type));
    }

    @Deprecated
    @Override
    public void onCraftedPostProcess(ItemStack stack, Level world) {
        onCraft(new CraftEvent(stack, world));
    }

    @Deprecated
    @Override
    public void hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        postHit(new PostHitEvent(stack, target, attacker));
    }

    @Deprecated
    @Override
    public boolean mineBlock(ItemStack stack, Level world, BlockState state, BlockPos pos, LivingEntity miner) {
        return postMine(new PostMineEvent(stack, world, state, pos, miner));
    }

    /**
     * item right click event
     *
     * @param event ItemUseEvent
     * @return ActionResultType
     */
    public StackActionResult onRightClick(ItemUseEvent event) {
        return StackActionResult.create(CompatActionResult.create(super.use(event.world, event.user.getPlayerEntity(), event.hand)), event.stack);
    }

    /**
     * item right click event on block
     * @param event ItemUseOnBlockEvent
     * @return ActionResultType
     */
    public CompatActionResult onRightClickOnBlock(ItemUseOnBlockEvent event) {
        return CompatActionResult.create(super.useOn(event.toIUC()));
    }

    /**
     * item finish using event
     * @param event ItemFinishUsingEvent
     * @return ItemStack
     */
    public ItemStack onFinishUsing(ItemFinishUsingEvent event) {
        return super.finishUsingItem(event.stack, event.world, event.user);
    }

    /**
     * item right click event on entity
     * @param event ItemUseOnEntityEvent
     * @return ActionResultType
     */
    public CompatActionResult onRightClickOnEntity(ItemUseOnEntityEvent event) {
        return CompatActionResult.create(super.interactLivingEntity(event.stack, event.user.getEntity(), event.entity, event.hand));
    }

    /**
     * check if item has recipe remainder
     * @param dummy Dummy
     * @return boolean
     */
    public boolean hasRecipeRemainder(Dummy dummy) {
        return false;
    }

    /**
     * append tooltip to item
     * @param event ItemAppendTooltipEvent
     */
    public void appendTooltip(ItemAppendTooltipEvent event) {
        super.appendHoverText(event.stack, event.context, event.displayComponent, event.textConsumer, event.type);
    }

    /**
     * on craft event
     * @param event CraftEvent
     */
    public void onCraft(CraftEvent event) {
        super.onCraftedPostProcess(event.stack, event.world);
    }

    /**
     * post hit event
     * @param event PostHitEvent
     * @return boolean
     */
    public boolean postHit(PostHitEvent event) {
        super.hurtEnemy(event.stack, event.target, event.attacker);
        return true;
    }

    /**
     * post mine event
     * @param event PostMineEvent
     * @return boolean
     */
    public boolean postMine(PostMineEvent event) {
        return super.mineBlock(event.stack, event.world, event.state, event.pos, event.miner);
    }

    // -1.20.6
    public Rarity getRarity(ItemStack stack) {
        return Rarity.COMMON;
    }

    @Deprecated
    public boolean isEnchantable(ItemStack stack) {
        return isEnchantable(new EnchantableArgs(stack));
    }

    public boolean isEnchantable(EnchantableArgs args) {
        return false;
    }

    @Deprecated
    public int getEnchantability() {
        return getEnchantability(new EnchantabilityArgs());
    }

    public int getEnchantability(EnchantabilityArgs args) {
        return 0;
    }

    @Deprecated
    @Override
    public int getBarColor(ItemStack stack) {
        return getItemBarColor(new ItemBarColorArgs(stack));
    }

    public int getItemBarColor(ItemBarColorArgs args) {
        return super.getBarColor(args.stack);
    }

    @Deprecated
    @Override
    public boolean isBarVisible(ItemStack stack) {
        return isItemBarVisible(new ItemBarVisibleArgs(stack));
    }

    public boolean isItemBarVisible(ItemBarVisibleArgs args) {
        return super.isBarVisible(args.stack);
    }

    @Deprecated
    @Override
    public int getBarWidth(ItemStack stack) {
        return getItemBarStep(new ItemBarStepArgs(stack));
    }

    public int getItemBarStep(ItemBarStepArgs args) {
        return super.getBarWidth(args.stack);
    }

    @Deprecated
    @Override
    public float getAttackDamageBonus(Entity target, float baseAttackDamage, DamageSource damageSource) {
        return getBonusAttackDamage(new BonusAttackDamageArgs(target, baseAttackDamage, damageSource));
    }

    public float getBonusAttackDamage(BonusAttackDamageArgs args) {
        return super.getAttackDamageBonus(args.target, args.baseAttackDamage, args.damageSource);
    }

    @Deprecated
    //@Override
    public boolean canRepair(ItemStack stack, ItemStack ingredient) {
        return canRepair(new CanRepairArgs(stack, ingredient));
    }

    public boolean canRepair(CanRepairArgs args) {
        return false;
    }

    @Deprecated
    @Override
    public boolean canDestroyBlock(ItemStack stack, BlockState state, Level world, BlockPos pos, LivingEntity user) {
        return canMine(new CanMineArgs(stack, state, world, pos, user));
    }

    public boolean canMine(CanMineArgs args) {
        return super.canDestroyBlock(args.stack, args.state, args.world, args.pos, args.entity);
    }
}

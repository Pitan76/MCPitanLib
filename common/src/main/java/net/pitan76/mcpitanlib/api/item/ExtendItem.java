package net.pitan76.mcpitanlib.api.item;

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
import net.minecraft.util.Rarity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.pitan76.mcpitanlib.api.event.item.*;
import net.pitan76.mcpitanlib.api.util.CompatActionResult;
import net.pitan76.mcpitanlib.core.Dummy;
import net.pitan76.mcpitanlib.mixin.ItemUsageContextMixin;

import java.util.List;

public class ExtendItem extends Item {
    public ExtendItem(Settings settings) {
        super(settings);
    }

    public ExtendItem(CompatibleItemSettings settings) {
        super(settings.build());
    }

    @Deprecated
    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        return onRightClick(new ItemUseEvent(world, user, hand)).toActionResult();
    }

    @Deprecated
    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        ItemUsageContextMixin contextAccessor = (ItemUsageContextMixin) context;
        return onRightClickOnBlock(new ItemUseOnBlockEvent(context.getPlayer(), context.getHand(), contextAccessor.getHit())).toActionResult();
    }

    @Deprecated
    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        return onFinishUsing(new ItemFinishUsingEvent(stack, world, user));
    }

    @Deprecated
    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        return onRightClickOnEntity(new ItemUseOnEntityEvent(stack, user, entity, hand)).toActionResult();
    }

    @Deprecated
    //@Override
    public boolean hasRecipeRemainder() {
        return hasRecipeRemainder(new Dummy());
    }

    @Deprecated
    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        appendTooltip(new ItemAppendTooltipEvent(stack, null, tooltip, type, context));
    }

    @Deprecated
    @Override
    public void onCraft(ItemStack stack, World world) {
        onCraft(new CraftEvent(stack, world));
    }

    @Deprecated
    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        return postHit(new PostHitEvent(stack, target, attacker));
    }

    @Deprecated
    @Override
    public boolean postMine(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner) {
        return postMine(new PostMineEvent(stack, world, state, pos, miner));
    }

    /**
     * item right click event
     *
     * @param event ItemUseEvent
     * @return ActionResultType
     */
    public CompatActionResult onRightClick(ItemUseEvent event) {
        return CompatActionResult.create(super.use(event.world, event.user.getPlayerEntity(), event.hand));
    }

    /**
     * item right click event on block
     * @param event ItemUseOnBlockEvent
     * @return ActionResultType
     */
    public CompatActionResult onRightClickOnBlock(ItemUseOnBlockEvent event) {
        return CompatActionResult.create(super.useOnBlock(event.toIUC()));
    }

    /**
     * item finish using event
     * @param event ItemFinishUsingEvent
     * @return ItemStack
     */
    public ItemStack onFinishUsing(ItemFinishUsingEvent event) {
        return super.finishUsing(event.stack, event.world, event.user);
    }

    /**
     * item right click event on entity
     * @param event ItemUseOnEntityEvent
     * @return ActionResultType
     */
    public CompatActionResult onRightClickOnEntity(ItemUseOnEntityEvent event) {
        return CompatActionResult.create(super.useOnEntity(event.stack, event.user.getEntity(), event.entity, event.hand));
    }

    /**
     * check if item has recipe remainder
     * @param dummy Dummy
     * @return boolean
     */
    // TODO: Remove this method in 1.21.2
    public boolean hasRecipeRemainder(Dummy dummy) {
        return false;
    }

    /**
     * append tooltip to item
     * @param event ItemAppendTooltipEvent
     */
    public void appendTooltip(ItemAppendTooltipEvent event) {
        super.appendTooltip(event.stack, event.context, event.tooltip, event.type);
    }

    /**
     * on craft event
     * @param event CraftEvent
     */
    public void onCraft(CraftEvent event) {
        super.onCraft(event.stack, event.world);
    }

    /**
     * post hit event
     * @param event PostHitEvent
     * @return boolean
     */
    public boolean postHit(PostHitEvent event) {
        return super.postHit(event.stack, event.target, event.attacker);
    }

    /**
     * post mine event
     * @param event PostMineEvent
     * @return boolean
     */
    public boolean postMine(PostMineEvent event) {
        return super.postMine(event.stack, event.world, event.state, event.pos, event.miner);
    }

    // -1.20.6
    public Rarity getRarity(ItemStack stack) {
        return Rarity.COMMON;
    }

    // TODO: 1.21.2
    @Deprecated
    //@Override
    public boolean isEnchantable(ItemStack stack) {
        return isEnchantable(new EnchantableArgs(stack));
    }

    public boolean isEnchantable(EnchantableArgs args) {
        return false;
    }

    // TODO: 1.21.2
    @Deprecated
    //@Override
    public int getEnchantability() {
        return getEnchantability(new EnchantabilityArgs());
    }

    public int getEnchantability(EnchantabilityArgs args) {
        return 0;
    }

    @Deprecated
    @Override
    public int getItemBarColor(ItemStack stack) {
        return getItemBarColor(new ItemBarColorArgs(stack));
    }

    public int getItemBarColor(ItemBarColorArgs args) {
        return super.getItemBarColor(args.stack);
    }

    @Deprecated
    @Override
    public boolean isItemBarVisible(ItemStack stack) {
        return isItemBarVisible(new ItemBarVisibleArgs(stack));
    }

    public boolean isItemBarVisible(ItemBarVisibleArgs args) {
        return super.isItemBarVisible(args.stack);
    }

    @Deprecated
    @Override
    public int getItemBarStep(ItemStack stack) {
        return getItemBarStep(new ItemBarStepArgs(stack));
    }

    public int getItemBarStep(ItemBarStepArgs args) {
        return super.getItemBarStep(args.stack);
    }

    @Deprecated
    @Override
    public float getBonusAttackDamage(Entity target, float baseAttackDamage, DamageSource damageSource) {
        return getBonusAttackDamage(new BonusAttackDamageArgs(target, baseAttackDamage, damageSource));
    }

    public float getBonusAttackDamage(BonusAttackDamageArgs args) {
        return super.getBonusAttackDamage(args.target, args.baseAttackDamage, args.damageSource);
    }

    // TODO: 1.21.2
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
    public boolean canMine(BlockState state, World world, BlockPos pos, PlayerEntity miner) {
        return canMine(new CanMineArgs(state, world, pos, miner));
    }

    public boolean canMine(CanMineArgs args) {
        return super.canMine(args.state, args.world, args.pos, args.miner.getEntity());
    }
}

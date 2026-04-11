package net.pitan76.mcpitanlib.api.item;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.level.Level;
import net.pitan76.mcpitanlib.api.event.item.*;
import net.pitan76.mcpitanlib.api.util.CompatActionResult;
import net.pitan76.mcpitanlib.api.util.StackActionResult;
import net.pitan76.mcpitanlib.core.Dummy;
import net.pitan76.mcpitanlib.mixin.UseOnContextMixin;

import java.util.function.Consumer;

public class ExtendBlockItem extends BlockItem implements ExtendItemProvider {

    public ExtendBlockItem(Block block, Properties settings) {
        super(block, settings);
    }

    public ExtendBlockItem(Block block, CompatibleItemSettings settings) {
        super(block, settings.build());
    }

    // ExtendItem
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

    @Deprecated
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
}

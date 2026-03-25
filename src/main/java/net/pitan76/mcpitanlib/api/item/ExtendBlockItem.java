package net.pitan76.mcpitanlib.api.item;

import net.minecraft.block.Block;
import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.pitan76.mcpitanlib.api.event.item.*;
import net.pitan76.mcpitanlib.api.util.CompatActionResult;
import net.pitan76.mcpitanlib.api.util.StackActionResult;
import net.pitan76.mcpitanlib.core.Dummy;
import net.pitan76.mcpitanlib.mixin.ItemUsageContextMixin;

import java.util.List;
import java.util.function.Consumer;

public class ExtendBlockItem extends BlockItem implements ExtendItemProvider {

    public ExtendBlockItem(Block block, Settings settings) {
        super(block, settings);
    }

    public ExtendBlockItem(Block block, CompatibleItemSettings settings) {
        super(block, settings.build());
    }

    // ExtendItem
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
    public boolean hasRecipeRemainder() {
        return hasRecipeRemainder(new Dummy());
    }

    @Deprecated
    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {
        appendTooltip(new ItemAppendTooltipEvent(stack, context, displayComponent, textConsumer, type));
    }

    @Deprecated
    @Override
    public void onCraft(ItemStack stack, World world) {
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
    public boolean hasRecipeRemainder(Dummy dummy) {
        return false;
    }

    /**
     * append tooltip to item
     * @param event ItemAppendTooltipEvent
     */
    public void appendTooltip(ItemAppendTooltipEvent event) {
        super.appendTooltip(event.stack, event.context, event.displayComponent, event.textConsumer, event.type);
    }

    /**
     * on craft event
     * @param event CraftEvent
     */
    public void onCraft(CraftEvent event) {
        super.onCraft(event.stack, event.world);
    }
}

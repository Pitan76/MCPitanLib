package net.pitan76.mcpitanlib.api.item;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.TypedActionResult;
import net.pitan76.mcpitanlib.api.event.item.*;

public interface ExtendItemProvider {

    /**
     * item right click event
     *
     * @param event ItemUseEvent
     * @return ActionResultType
     */
    default TypedActionResult<ItemStack> onRightClick(ItemUseEvent event, Options options) {
        options.cancel = false;
        return null;
    }

    /**
     * item right click event on block
     * @param event ItemUseOnBlockEvent
     * @return ActionResultType
     */
    default ActionResult onRightClickOnBlock(ItemUseOnBlockEvent event, Options options) {
        options.cancel = false;
        return null;
    }

    /**
     * using finish with item
     * @param event ItemFinishUsingEvent
     * @return ItemStack
     */
    default ItemStack onFinishUsing(ItemFinishUsingEvent event, Options options) {
        options.cancel = false;
        return null;
    }

    /**
     * on click with item on entity
     * @param event ItemUseOnEntityEvent
     * @return ActionResult
     */
    default ActionResult onRightClickOnEntity(ItemUseOnEntityEvent event, Options options) {
        options.cancel = false;
        return null;
    }

    /**
     * item has recipe remainder
     * @param options Options
     * @return boolean
     */
    default boolean hasRecipeRemainder(Options options) {
        options.cancel = false;
        return false;
    }

    /**
     * add tooltip
     * @param event ItemAppendTooltipEvent
     */
    default void appendTooltip(ItemAppendTooltipEvent event, Options options) {
        options.cancel = false;
    }

    /**
     * post hit event
     * @param event PostHitEvent
     * @return boolean
     */
    default boolean postHit(PostHitEvent event, Options options) {
        options.cancel = false;
        return false;
    }

    /**
     * post mine event
     * @param event PostMineEvent
     * @return boolean
     */
    default boolean postMine(PostMineEvent event, Options options) {
        options.cancel = false;
        return false;
    }

    public static class Options {
        public boolean cancel = true;
    }
}

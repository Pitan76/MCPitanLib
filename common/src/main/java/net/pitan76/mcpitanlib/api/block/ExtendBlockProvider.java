package net.pitan76.mcpitanlib.api.block;

import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.shape.VoxelShape;
import net.pitan76.mcpitanlib.api.event.block.*;
import net.pitan76.mcpitanlib.api.event.block.result.BlockBreakResult;
import net.pitan76.mcpitanlib.api.event.item.ItemAppendTooltipEvent;
import net.pitan76.mcpitanlib.api.util.TextUtil;

import java.util.List;

public interface ExtendBlockProvider {

    /**
     * get collision voxel shape
     * @param event CollisionShapeEvent
     * @param options Options
     * @return VoxelShape
     */
    default VoxelShape getCollisionShape(CollisionShapeEvent event, Options options) {
        options.cancel = false;
        return null;
    }

    /**
     * get outline voxel shape
     * @param event OutlineShapeEvent
     * @param options Options
     * @return VoxelShape
     */
    default VoxelShape getOutlineShape(OutlineShapeEvent event, Options options) {
        options.cancel = false;
        return null;
    }

    /**
     * block scheduled tick event
     * @param event BlockScheduledTickEvent
     * @param options Options
     */
    default void scheduledTick(BlockScheduledTickEvent event, Options options) {
        options.cancel = false;
    }

    /**
     * block right click event
     * @param event BlockUseEvent
     * @param options Options
     * @return ActionResult
     */
    default ActionResult onRightClick(BlockUseEvent event, Options options) {
        options.cancel = false;
        return null;
    }

    /**
     * screen handler create event
     * @param event ScreenHandlerCreateEvent
     * @param options Options
     * @return ScreenHandler
     */
    default ScreenHandler createScreenHandler(ScreenHandlerCreateEvent event, Options options) {
        options.cancel = false;
        return null;
    }

    /**
     * get screen title
     * @return Text
     */
    default Text getScreenTitle() {
        return TextUtil.literal("");
    }

    /**
     * block placed event
     * @param event BlockPlacedEvent
     * @param options Options
     */
    default void onPlaced(BlockPlacedEvent event, Options options) {
        options.cancel = false;
    }

    /**
     * block break event
     * @param event BlockBreakEvent
     * @param options Options
     * @return BlockBreakResult
     */
    default BlockBreakResult onBreak(BlockBreakEvent event, Options options) {
        options.cancel = false;
        return null;
    }

    /**
     * block state replaced event
     * @param event StateReplacedEvent
     * @param options Options
     */
    default void onStateReplaced(StateReplacedEvent event, Options options) {
        options.cancel = false;
    }

    /**
     * get pick stack
     * @param event PickStackEvent
     * @param options Options
     * @return ItemStack
     */
    default ItemStack getPickStack(PickStackEvent event, Options options) {
        options.cancel = false;
        return null;
    }

    /**
     * get placement state
     * @param args PlacementStateArgs
     * @param options Options
     */
    default BlockState getPlacementState(PlacementStateArgs args, Options options) {
        options.cancel = false;
        return null;
    }

    /**
     * append properties
     * @param args AppendPropertiesArgs
     * @param options Options
     */
    default void appendProperties(AppendPropertiesArgs args, Options options) {
        options.cancel = false;
    }

    /**
     * get dropped stacks
     * @param args DroppedStacksArgs
     * @param options Options
     * @return List<ItemStack>
     */
    default List<ItemStack> getDroppedStacks(DroppedStacksArgs args, Options options) {
        options.cancel = false;
        return null;
    }

    default void appendTooltip(ItemAppendTooltipEvent event, Options options) {
        options.cancel = false;
    }

    public static class Options {
        public boolean cancel = true;
    }
}

package net.pitan76.mcpitanlib.api.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.loot.context.LootContextParameterSet;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.pitan76.mcpitanlib.api.event.block.*;
import net.pitan76.mcpitanlib.api.event.block.result.BlockBreakResult;
import net.pitan76.mcpitanlib.api.event.item.ItemAppendTooltipEvent;
import net.pitan76.mcpitanlib.api.util.TextUtil;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ExtendBlock extends Block {
    public ExtendBlock(Settings settings) {
        super(settings);
    }

    public ExtendBlock(CompatibleBlockSettings settings) {
        super(settings.build());
    }

    /**
     * get collision voxel shape
     * @param event CollisionShapeEvent
     * @return VoxelShape
     */
    public VoxelShape getCollisionShape(CollisionShapeEvent event) {
        return super.getCollisionShape(event.state, event.world, event.pos, event.context);
    }

    @Deprecated
    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return getCollisionShape(new CollisionShapeEvent(state, world, pos, context));
    }

    /**
     * get outline voxel shape
     * @param event OutlineShapeEvent
     * @return VoxelShape
     */
    public VoxelShape getOutlineShape(OutlineShapeEvent event) {
        return super.getOutlineShape(event.state, event.world, event.pos, event.context);
    }

    @Deprecated
    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return getOutlineShape(new OutlineShapeEvent(state, world, pos, context));
    }

    /**
     * block scheduled tick event
     * @param event BlockScheduledTickEvent
     */
    public void scheduledTick(BlockScheduledTickEvent event) {
        super.scheduledTick(event.state, event.world, event.pos, event.random);
    }

    @Override
    @Deprecated
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        scheduledTick(new BlockScheduledTickEvent(state, world, pos, random));
    }

    @Override
    @Deprecated
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        return onRightClick(new BlockUseEvent(state, world, pos, player, player.getActiveHand(), hit));
    }

    /**
     * block right click event
     * @param event ActionResultType
     * @return BlockUseEvent
     */
    public ActionResult onRightClick(BlockUseEvent event) {
        return super.onUse(event.state, event.world, event.pos, event.player.getPlayerEntity(), event.hit);
    }

    @Deprecated
    @Nullable
    @Override
    public NamedScreenHandlerFactory createScreenHandlerFactory(BlockState state, World world, BlockPos pos) {
        return new SimpleNamedScreenHandlerFactory((syncId, inventory, player) ->
                createScreenHandler(new ScreenHandlerCreateEvent(state, world, pos, syncId, inventory, player)), getScreenTitle()
        );
    }

    /**
     * screen handler create event
     * @param event ScreenHandlerCreateEvent
     * @return ScreenHandler
     */
    @Nullable
    public ScreenHandler createScreenHandler(ScreenHandlerCreateEvent event) {
        return null;
    }

    /**
     * get screen title
     * @return Text
     */
    @Nullable
    public Text getScreenTitle() {
        return TextUtil.literal("");
    }

    @Override
    @Deprecated
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        onPlaced(new BlockPlacedEvent(world, pos, state, placer, itemStack));
    }

    /**
     * block placed event
     * @param event BlockPlacedEvent
     */
    public void onPlaced(BlockPlacedEvent event) {
        super.onPlaced(event.world, event.pos, event.state, event.placer, event.stack);
    }

    @Override
    @Deprecated
    public BlockState onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        return onBreak(new BlockBreakEvent(world, pos, state, player)).state;
    }

    /**
     * block break event
     * @param event BlockBreakEvent
     * @return BlockBreakResult
     */
    public BlockBreakResult onBreak(BlockBreakEvent event) {
        BlockState state = super.onBreak(event.world, event.pos, event.state, event.getPlayerEntity());
        return new BlockBreakResult(state);
    }

    @Override
    @Deprecated
    public ItemStack getPickStack(WorldView world, BlockPos pos, BlockState state) {
        return getPickStack(new PickStackEvent(world, pos, state));
    }

    /**
     * block pick stack event
     * @param event PickStackEvent
     * @return ItemStack
     */
    public ItemStack getPickStack(PickStackEvent event) {
        return super.getPickStack(event.worldView, event.pos, event.state);
    }

    @Override
    @Deprecated
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        onStateReplaced(new StateReplacedEvent(state, world, pos, newState, moved));
    }

    /**
     * block state replaced event
     * @param event StateReplacedEvent
     */
    public void onStateReplaced(StateReplacedEvent event) {
        super.onStateReplaced(event.state, event.world, event.pos, event.newState, event.moved);
    }

    @Deprecated
    @Override
    public List<ItemStack> getDroppedStacks(BlockState state, LootContextParameterSet.Builder builder) {
        return getDroppedStacks(new DroppedStacksArgs(state, builder));
    }

    /**
     * block dropped stacks event
     * @param args DroppedStacksArgs
     * @return List<ItemStack>
     */
    public List<ItemStack> getDroppedStacks(DroppedStacksArgs args) {
        return super.getDroppedStacks(args.state, args.builder);
    }

    @Deprecated
    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify) {
        neighborUpdate(new NeighborUpdateEvent(state, world, pos, sourceBlock, sourcePos, notify));
    }

    /**
     * block neighbor update event
     * @param event NeighborUpdateEvent
     */
    public void neighborUpdate(NeighborUpdateEvent event) {
        super.neighborUpdate(event.state, event.world, event.pos, event.sourceBlock, event.sourcePos, event.notify);
    }

    @Deprecated
    @Override
    public void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        appendProperties(new AppendPropertiesArgs(builder));
    }

    /**
     * append properties event
     * @param args AppendPropertiesArgs
     */
    public void appendProperties(AppendPropertiesArgs args) {
        super.appendProperties(args.builder);
    }

    /**
     * Compatible for getDefaultState()
     * @return default block state
     */
    public BlockState getNewDefaultState() {
        return super.getDefaultState();
    }

    /**
     * Compatible for setDefaultState()
     * @param state BlockState
     */
    public void setNewDefaultState(BlockState state) {
        super.setDefaultState(state);
    }

    @Deprecated
    @Override
    public @Nullable BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getPlacementState(new PlacementStateArgs(ctx, this));
    }

    /**
     * get placement state
     * @param args PlacementStateArgs
     * @return BlockState
     */
    public @Nullable BlockState getPlacementState(PlacementStateArgs args) {
        return super.getPlacementState(args.ctx);
    }

    @Deprecated
    @Override
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType type) {
        appendTooltip(new ItemAppendTooltipEvent(stack, null, tooltip, type, context));
    }

    /**
     * append tooltip to item
     * @param event ItemAppendTooltipEvent
     */
    public void appendTooltip(ItemAppendTooltipEvent event) {
        super.appendTooltip(event.stack, event.context, event.tooltip, event.type);
    }
}

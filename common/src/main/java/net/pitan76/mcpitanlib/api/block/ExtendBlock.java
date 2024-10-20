package net.pitan76.mcpitanlib.api.block;

import com.google.common.collect.ImmutableMap;
import com.mojang.serialization.MapCodec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
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
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import net.pitan76.mcpitanlib.api.entity.Player;
import net.pitan76.mcpitanlib.api.event.block.*;
import net.pitan76.mcpitanlib.api.event.block.result.BlockBreakResult;
import net.pitan76.mcpitanlib.api.event.block.StateForNeighborUpdateArgs;
import net.pitan76.mcpitanlib.api.event.item.ItemAppendTooltipEvent;
import net.pitan76.mcpitanlib.api.util.TextUtil;
import net.pitan76.mcpitanlib.core.serialization.CompatMapCodec;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Function;

public class ExtendBlock extends Block {
    public CompatibleBlockSettings compatSettings;

    public ExtendBlock(Settings settings) {
        super(settings);
    }

    public ExtendBlock(CompatibleBlockSettings settings) {
        super(settings.build());
        this.compatSettings = settings;
    }

    /**
     * get compatible block settings
     * @return CompatibleBlockSettings
     */
    public CompatibleBlockSettings getCompatSettings() {
        return compatSettings;
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
        super.scheduledTick(event.state, event.world, event.pos, event.random.getMcRandom());
    }

    @Override
    @Deprecated
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        scheduledTick(new BlockScheduledTickEvent(state, world, pos, random));
    }

    @Override
    @Deprecated
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        return onRightClick(new BlockUseEvent(state, world, pos, player, hand, hit));
    }

    /**
     * block right click event
     * @param event ActionResultType
     * @return BlockUseEvent
     */
    public ActionResult onRightClick(BlockUseEvent event) {
        return super.onUse(event.state, event.world, event.pos, event.player.getPlayerEntity(), event.hand, event.hit);
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
    public void appendTooltip(ItemStack stack, @Nullable BlockView world, List<Text> tooltip, TooltipContext context) {
        appendTooltip(new ItemAppendTooltipEvent(stack, world, tooltip, context));
    }

    /**
     * append tooltip to item
     * @param event ItemAppendTooltipEvent
     */
    public void appendTooltip(ItemAppendTooltipEvent event) {
        super.appendTooltip(event.stack, event.blockView, event.tooltip, event.context);
    }

    @Deprecated
    @Override
    public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
        return canPathfindThrough(new CanPathfindThroughArgs(state, world, pos, type));
    }

    @SuppressWarnings("removal")
    public boolean canPathfindThrough(CanPathfindThroughArgs args) {
        return super.canPathfindThrough(args.state, args.getBlockView(), args.getPos(), args.type);
    }

    @Deprecated
    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        onEntityCollision(new EntityCollisionEvent(state, world, pos, entity));
    }

    public void onEntityCollision(EntityCollisionEvent e) {
        super.onEntityCollision(e.state, e.world, e.pos, e.entity);
    }

    @Deprecated
    @Override
    public void onBlockBreakStart(BlockState state, World world, BlockPos pos, PlayerEntity player) {
        onBlockBreakStart(new BlockBreakStartEvent(state, world, pos, new Player(player)));
    }

    public void onBlockBreakStart(BlockBreakStartEvent e) {
        super.onBlockBreakStart(e.state, e.world, e.pos, e.player.getPlayerEntity());
    }

    @Deprecated
    @Override
    protected MapCodec<? extends Block> getCodec() {
        return getCompatCodec().getCodec();
    }

    public CompatMapCodec<? extends Block> getCompatCodec() {
        return CompatMapCodec.of(super.getCodec());
    }

    @Deprecated
    @Override
    public FluidState getFluidState(BlockState state) {
        return getFluidState(new FluidStateArgs(state));
    }

    public FluidState getFluidState(FluidStateArgs args) {
        return super.getFluidState(args.getState());
    }

    @Deprecated
    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        return getStateForNeighborUpdate(new StateForNeighborUpdateArgs(state, direction, neighborState, world, pos, neighborPos));
    }

    public BlockState getStateForNeighborUpdate(StateForNeighborUpdateArgs args) {
        return super.getStateForNeighborUpdate(args.state, args.direction, args.neighborState, args.world, args.pos, args.neighborPos);
    }

    @Deprecated
    @Override
    protected ImmutableMap<BlockState, VoxelShape> getShapesForStates(Function<BlockState, VoxelShape> stateToShape) {
        return getShapesForStates(new ShapesForStatesArgs(stateToShape));
    }

    public ImmutableMap<BlockState, VoxelShape> getShapesForStates(ShapesForStatesArgs args) {
        return super.getShapesForStates(args.stateToShape);
    }

    public StateManager<Block, BlockState> callGetStateManager() {
        return super.getStateManager();
    }
}

package net.pitan76.mcpitanlib.api.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.InsideBlockEffectApplier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.redstone.Orientation;
import net.minecraft.world.level.ScheduledTickAccess;
import net.pitan76.mcpitanlib.api.entity.Player;
import net.pitan76.mcpitanlib.api.event.block.*;
import net.pitan76.mcpitanlib.api.event.block.result.BlockBreakResult;
import net.pitan76.mcpitanlib.api.event.block.StateForNeighborUpdateArgs;
import net.pitan76.mcpitanlib.api.event.item.ItemAppendTooltipEvent;
import net.pitan76.mcpitanlib.api.util.CompatActionResult;
import net.pitan76.mcpitanlib.api.util.TextUtil;
import net.pitan76.mcpitanlib.api.util.math.random.CompatRandom;
import net.pitan76.mcpitanlib.core.serialization.CompatMapCodec;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ExtendBlock extends Block implements ICompatBlock {
    public CompatibleBlockSettings compatSettings;

    public ExtendBlock(Properties settings) {
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
    public VoxelShape getCollisionShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return getCollisionShape(new CollisionShapeEvent(state, world, pos, context));
    }

    /**
     * get outline voxel shape
     * @param event OutlineShapeEvent
     * @return VoxelShape
     */
    public VoxelShape getOutlineShape(OutlineShapeEvent event) {
        return super.getShape(event.state, event.world, event.pos, event.context);
    }

    @Deprecated
    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return getOutlineShape(new OutlineShapeEvent(state, world, pos, context));
    }

    /**
     * block scheduled tick event
     * @param event BlockScheduledTickEvent
     */
    public void scheduledTick(BlockScheduledTickEvent event) {
        super.tick(event.state, event.world, event.pos, event.random.getMcRandom());
    }

    @Override
    @Deprecated
    public void tick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
        scheduledTick(new BlockScheduledTickEvent(state, world, pos, random));
    }

    @Override
    @Deprecated
    protected InteractionResult useWithoutItem(BlockState state, Level world, BlockPos pos, net.minecraft.world.entity.player.Player player, BlockHitResult hit) {
        return onRightClick(new BlockUseEvent(state, world, pos, player, player.getUsedItemHand(), hit)).toActionResult();
    }

    /**
     * block right click event
     * @param event ActionResultType
     * @return BlockUseEvent
     */
    public CompatActionResult onRightClick(BlockUseEvent event) {
        return CompatActionResult.create(super.useWithoutItem(event.state, event.world, event.pos, event.player.getPlayerEntity(), event.hit));
    }

    @Deprecated
    @Nullable
    @Override
    public MenuProvider getMenuProvider(BlockState state, Level world, BlockPos pos) {
        return new SimpleMenuProvider((syncId, inventory, player) ->
                createScreenHandler(new ScreenHandlerCreateEvent(state, world, pos, syncId, inventory, player)), getScreenTitle()
        );
    }

    /**
     * screen handler create event
     * @param event ScreenHandlerCreateEvent
     * @return ScreenHandler
     */
    @Nullable
    public AbstractContainerMenu createScreenHandler(ScreenHandlerCreateEvent event) {
        return null;
    }

    /**
     * get screen title
     * @return Text
     */
    @Nullable
    public Component getScreenTitle() {
        return TextUtil.literal("");
    }

    @Override
    @Deprecated
    public void setPlacedBy(Level world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        onPlaced(new BlockPlacedEvent(world, pos, state, placer, itemStack));
    }

    /**
     * block placed event
     * @param event BlockPlacedEvent
     */
    public void onPlaced(BlockPlacedEvent event) {
        super.setPlacedBy(event.world, event.pos, event.state, event.placer, event.stack);
    }

    @Override
    @Deprecated
    public BlockState playerWillDestroy(Level world, BlockPos pos, BlockState state, net.minecraft.world.entity.player.Player player) {
        return onBreak(new BlockBreakEvent(world, pos, state, player)).state;
    }

    /**
     * block break event
     * @param event BlockBreakEvent
     * @return BlockBreakResult
     */
    public BlockBreakResult onBreak(BlockBreakEvent event) {
        BlockState state = super.playerWillDestroy(event.world, event.pos, event.state, event.getPlayerEntity());
        return new BlockBreakResult(state);
    }

    @Override
    @Deprecated
    public ItemStack getCloneItemStack(LevelReader world, BlockPos pos, BlockState state, boolean includeData) {
        PickStackEvent event = new PickStackEvent(world, pos, state);
        event.setIncludeData(includeData);
        return getPickStack(event);
    }

    /**
     * block pick stack event
     * @param event PickStackEvent
     * @return ItemStack
     */
    public ItemStack getPickStack(PickStackEvent event) {
        return super.getCloneItemStack(event.worldView, event.pos, event.state, event.includeData);
    }

    @Override
    @Deprecated
    public void affectNeighborsAfterRemoval(BlockState state, ServerLevel world, BlockPos pos, boolean moved) {
        onStateReplaced(new StateReplacedEvent(state, world, pos, world.getBlockState(pos), moved));
    }

    /**
     * block state replaced event
     * @param event StateReplacedEvent
     */
    public void onStateReplaced(StateReplacedEvent event) {
        super.affectNeighborsAfterRemoval(event.state, (ServerLevel) event.world, event.pos, event.moved);
    }

    @Deprecated
    @Override
    public List<ItemStack> getDrops(BlockState state, LootParams.Builder builder) {
        return getDroppedStacks(new DroppedStacksArgs(state, builder));
    }

    /**
     * block dropped stacks event
     * @param args DroppedStacksArgs
     * @return List<ItemStack>
     */
    public List<ItemStack> getDroppedStacks(DroppedStacksArgs args) {
        return super.getDrops(args.state, args.builder);
    }

    @Deprecated
    @Override
    public void neighborChanged(BlockState state, Level world, BlockPos pos, Block sourceBlock, Orientation wireOrientation, boolean notify) {
        neighborUpdate(new NeighborUpdateEvent(state, world, pos, sourceBlock, wireOrientation, notify));
    }

    /**
     * block neighbor update event
     * @param event NeighborUpdateEvent
     */
    public void neighborUpdate(NeighborUpdateEvent event) {
        super.neighborChanged(event.state, event.world, event.pos, event.sourceBlock, event.wireOrientation, event.notify);
    }

    @Deprecated
    @Override
    public void createBlockStateDefinition(net.minecraft.world.level.block.state.StateDefinition.Builder builder) {
        appendProperties(new AppendPropertiesArgs(builder));
    }

    /**
     * append properties event
     * @param args AppendPropertiesArgs
     */
    public void appendProperties(AppendPropertiesArgs args) {
        super.createBlockStateDefinition(args.builder);
    }

    /**
     * Compatible for getDefaultState()
     * @return default block state
     */
    public BlockState getNewDefaultState() {
        return super.defaultBlockState();
    }

    /**
     * Compatible for setDefaultState()
     * @param state BlockState
     */
    public void setNewDefaultState(BlockState state) {
        super.registerDefaultState(state);
    }

    @Deprecated
    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext ctx) {
        return this.getPlacementState(new PlacementStateArgs(ctx, this));
    }

    /**
     * get placement state
     * @param args PlacementStateArgs
     * @return BlockState
     */
    public @Nullable BlockState getPlacementState(PlacementStateArgs args) {
        return super.getStateForPlacement(args.ctx);
    }

    /**
     * append tooltip to item
     * @param event ItemAppendTooltipEvent
     */
    public void appendTooltip(ItemAppendTooltipEvent event) {

    }

    @Deprecated
    @Override
    public boolean isPathfindable(BlockState state, PathComputationType type) {
        return canPathfindThrough(new CanPathfindThroughArgs(state, type));
    }

    public boolean canPathfindThrough(CanPathfindThroughArgs args) {
        return super.isPathfindable(args.state, args.type);
    }

    @Deprecated
    @Override
    protected void entityInside(BlockState state, Level world, BlockPos pos, Entity entity, InsideBlockEffectApplier handler, boolean bl) {
        onEntityCollision(new EntityCollisionEvent(state, world, pos, entity, handler, bl));
    }

    public void onEntityCollision(EntityCollisionEvent e) {
        super.entityInside(e.state, e.world, e.pos, e.entity, e.handler, e.bl);
    }

    @Deprecated
    @Override
    public void attack(BlockState state, Level world, BlockPos pos, net.minecraft.world.entity.player.Player player) {
        onBlockBreakStart(new BlockBreakStartEvent(state, world, pos, new Player(player)));
    }

    public void onBlockBreakStart(BlockBreakStartEvent e) {
        super.attack(e.state, e.world, e.pos, e.player.getPlayerEntity());
    }

    @Deprecated
    @Override
    protected MapCodec<? extends Block> codec() {
        return getCompatCodec().getCodec();
    }

    public CompatMapCodec<? extends Block> getCompatCodec() {
        return CompatMapCodec.of(super.codec());
    }

    @Deprecated
    @Override
    protected FluidState getFluidState(BlockState state) {
        return getFluidState(new FluidStateArgs(state));
    }

    public FluidState getFluidState(FluidStateArgs args) {
        return super.getFluidState(args.getState());
    }

    @Deprecated
    @Override
    protected BlockState updateShape(BlockState state, LevelReader world, ScheduledTickAccess tickView, BlockPos pos, Direction direction, BlockPos neighborPos, BlockState neighborState, RandomSource random) {
        return getStateForNeighborUpdate(new StateForNeighborUpdateArgs(state, direction, neighborState, world, pos, neighborPos, tickView, new CompatRandom(random)));
    }

    public BlockState getStateForNeighborUpdate(StateForNeighborUpdateArgs args) {
        return super.updateShape(args.state, args.world, args.tickView, args.pos, args.direction, args.neighborPos, args.neighborState, args.random.getMcRandom());
    }

//    @Deprecated
//    @Override
//    protected ImmutableMap<BlockState, VoxelShape> getShapesForStates(Function<BlockState, VoxelShape> stateToShape) {
//        return getShapesForStates(new ShapesForStatesArgs(stateToShape));
//    }
//
//    public ImmutableMap<BlockState, VoxelShape> getShapesForStates(ShapesForStatesArgs args) {
//        return super.(args.stateToShape);
//    }

    public StateDefinition<Block, BlockState> callGetStateManager() {
        return super.getStateDefinition();
    }
}
